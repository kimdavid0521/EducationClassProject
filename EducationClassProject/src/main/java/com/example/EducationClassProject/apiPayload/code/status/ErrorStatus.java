package com.example.EducationClassProject.apiPayload.code.status;

import com.example.EducationClassProject.apiPayload.code.BaseErrorCode;
import com.example.EducationClassProject.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버에러, 관리자 문의."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증 필요."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // user 에러
    _NOT_FOUND_USER(HttpStatus.NOT_FOUND, "USER400", "유저를 찾을 수 없습니다."),
    _ALREADY_VERIFIED_USER(HttpStatus.BAD_REQUEST, "USER4002", "이미 선생으로 검증된 유저입니다."),
    _ALREADY_REQUEST_VERIFY(HttpStatus.FORBIDDEN, "USER4003", "이미 검증 요청을 보낸 사용자입니다."),
    _PASSWORD_NOT_OK(HttpStatus.BAD_REQUEST, "USER4004", "비밀번호가 일치하지 않습니다"),
    _NOT_FOUND_EMAIL(HttpStatus.NOT_FOUND, "USER4005", "가입되지 않은 이메일입니다."),

    // class 에러
    _NOT_FOUND_CLASS(HttpStatus.NOT_FOUND, "CLASS400", "클래스를 찾을 수 없습니다."),
    _NOT_VERIFY_CLASS(HttpStatus.UNAUTHORIZED,"CLASS401", "권한이 없어 클래스를 생성할 수 없습니다."),
    _NOT_CLASS_OWNER(HttpStatus.FORBIDDEN, "CLASS402", "클래스의 owner가 아닙니다. (수정 권한 없음)"),
    _NOT_CLASS_OWNER_DELETE(HttpStatus.FORBIDDEN, "CLASS403", "클래스의 owner가 아닙니다. (삭제 권한 없음)"),
    _ALREADY_JOINED_CLASS(HttpStatus.CONFLICT, "CLASS404", "이미 강의에 참여한 학생입니다."),
    _NOT_TEACHER(HttpStatus.FORBIDDEN,"CLASS405", "해당 계정은 선생 계정이 아닙니다."),
    _MAKE_POINT_NOT_ENOUGH(HttpStatus.CONFLICT, "CLASS406", "포인트가 부족하여 class 생성이 불가능합니다."),
    _JOIN_POINT_NOT_ENOUGH(HttpStatus.CONFLICT, "CLASS407", "포인트가 부족하여 강의 참여가 불가능합니다."),

    //verifyCard 에러
    _NOT_FOUND_VERIFYCARD(HttpStatus.NOT_FOUND, "VERIFY400", "검증서를 확인할 수 없습니다"),

    // auth 에러
    _AUTH_EXPIRE_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN401", "토큰이 만료되었습니다."),
    _AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "AUTH001", "인증 실패하였습니다"),

    // order 에러
    _NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "ORDER401", "없는 상품입니다."),
    _NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "ORDER402", "해당 주문은 존재하지 않습니다."),
    _NOT_FOUND_ORDER_UID(HttpStatus.NOT_FOUND, "ORDER403", "해당 orderUid 는 유효하지 않습니다."),

    // payment 에러
    _ERROR_IAMPORT_VALIDATION(HttpStatus.INTERNAL_SERVER_ERROR, "PAYMENT400", "iamport 검증 중 에러가 발생했습니다."),
    _ERROR_PAYMENT(HttpStatus.PAYMENT_REQUIRED, "PAYMENT401", "iamport 결제 중 에러가 발생했습니다."),
    _PAYMENT_PRICE_ERROR(HttpStatus.CONFLICT, "PAYMENT402", "결제 금액 위변조 의심"),
    _NOT_FOUND_PRICE(HttpStatus.FORBIDDEN, "PAYMENT403", "현재 등록되지 않은 상품 금액입니다."),

    // chat 에러
    _NOT_FOUND_CHATROOM(HttpStatus.NOT_FOUND, "CHAT400", "채팅방을 찾을 수 없습니다."),
    _ALREADY_JOIN_USER(HttpStatus.CONFLICT, "CHAT401", "이미 채팅방에 들어와있는 유저 입니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder().
                httpStatus(httpStatus).
                message(message).
                code(code).
                isSuccess(false).
                build();
    }
}
