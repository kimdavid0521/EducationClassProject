package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.OrderHandler;
import com.example.EducationClassProject.apiPayload.exception.handler.PaymentHandler;
import com.example.EducationClassProject.domain.Order;
import com.example.EducationClassProject.domain.enums.PaymentState;
import com.example.EducationClassProject.dto.PaymentDTO.PaymentRequestDTO;
import com.example.EducationClassProject.dto.PaymentDTO.PaymentResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.OrderRepository;
import com.example.EducationClassProject.repository.PaymentRepository;
import com.example.EducationClassProject.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final JWTUtil jwtUtil;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final IamportClient iamportClient;

    // orderId로 결제 정보 조회
    @Override
    public PaymentResponseDTO.PaymentPreviewDTO previewPayment(Long orderId, String token) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new OrderHandler(ErrorStatus._NOT_FOUND_ORDER);
        });

        String orderUid = order.getOrderUid(); // 랜덤으로 지정해줬던 orderUid 를 뽑아냅니다.
        Order orderResult = orderRepository.findOrderAndPaymentAndMember(orderUid).orElseThrow(() -> {
            throw new OrderHandler(ErrorStatus._NOT_FOUND_ORDER_UID);
        });

        return PaymentResponseDTO.PaymentPreviewDTO.builder()
                .orderUid(orderResult.getOrderUid())
                .product(orderResult.getProduct())
                .paymentPrice(orderResult.getPrice())
                .buyerName(orderResult.getUser().getUsername())
                .buyerEmail(orderResult.getUser().getEmail())
                .build();

    }

    // 결제 검증
    @Override
    public IamportResponse<Payment> validationPayment(PaymentRequestDTO.CallBackDTO callBackDTO, String token) {
        // 외부 api 호출 실패를 대비하여 예외 처리를 통한 안정적인 검증 프로세스를 보장하기 위해 try - catch 문으로 구현하였습니다.
        try {
            // 결제 조회
            IamportResponse<Payment> iamportResponse = iamportClient.paymentByImpUid(callBackDTO.getPaymentUid());

            // 주문 내역 조회
            Order order = orderRepository.findOrderAndPayment(callBackDTO.getOrderUid()).orElseThrow(() -> {
                throw new OrderHandler(ErrorStatus._NOT_FOUND_ORDER_UID);
            });

            // 결제 상태가 paid 가 아니라면 데이터의 무결성을 위해 오더 정보와 주문 정보를 지워버리게 처리했습니다.
            // (pending, failed, cancelled, refund, expired)
            if (!iamportResponse.getResponse().getStatus().equals("paid")) {
                orderRepository.delete(order);
                paymentRepository.delete(order.getPayment());
                throw new PaymentHandler(ErrorStatus._ERROR_PAYMENT);
            }

            BigDecimal price = order.getPayment().getPrice(); // 데이터 베이스에 저장되어있는 결제 금액
            BigDecimal iamportPrice = iamportResponse.getResponse().getAmount(); // 실제 결제 금액

            // 결제 금액 검증
            if (!price.equals(iamportPrice)) {
                orderRepository.delete(order);
                paymentRepository.delete(order.getPayment());

                // 결제금액 위변조로 의심되는 금액을 취소합니다.
                iamportClient.cancelPaymentByImpUid(new CancelData(iamportResponse.getResponse().getImpUid(), true, new BigDecimal(String.valueOf(iamportPrice))));

                throw new PaymentHandler(ErrorStatus._ERROR_PAYMENT);
            }

            // 결제 상태 변경 및 impUid 업데이트
            order.getPayment().updatePaymentState(PaymentState.PAID, iamportResponse.getResponse().getImpUid());

            // 유저 포인트 갱신
            int currentPoint = order.getUser().getPoint();

            if (price.equals(BigDecimal.valueOf(1000))) {
                order.getUser().updateUserPoint(currentPoint + 1000);
            } else if (price.equals(BigDecimal.valueOf(2000))) {
                order.getUser().updateUserPoint(currentPoint + 2000);
            } else if (price.equals(BigDecimal.valueOf(3000))) {
                order.getUser().updateUserPoint(currentPoint + 3000);
            } else if (price.equals(BigDecimal.valueOf(4000))) {
                order.getUser().updateUserPoint(currentPoint + 4000);
            } else {
                throw new PaymentHandler(ErrorStatus._NOT_FOUND_PRICE);
            }

            return iamportResponse;

        } catch (IamportResponseException | IOException e) {
            throw new PaymentHandler(ErrorStatus._ERROR_IAMPORT_VALIDATION);
        }
    }
}
