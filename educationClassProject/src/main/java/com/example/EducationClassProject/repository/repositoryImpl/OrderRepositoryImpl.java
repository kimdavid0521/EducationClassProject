//package com.example.EducationClassProject.repository.repositoryImpl;
//
//import com.example.EducationClassProject.domain.Order;
//import com.example.EducationClassProject.repository.customRepository.OrderRepositoryCustom;
//
////import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public class OrderRepositoryImpl implements OrderRepositoryCustom {
//
////    private JPAQueryFactory jpaQueryFactory;
//
//    // orderUid 로 order 객체를 가져옵니다. ( jpa 보다 쿼리문이 성능 측면에서 더 좋아서 쿼리문으로 작성 하였습니다. )
//    // ( 한번의 쿼리로 order 와 관련된 payment, user을 즉시 로딩하여(eager) 가져오기 때문입니다. )
//    @Override
//    public Optional<Order> findOrderAndPaymentAndMember(String orderUid) {
////        QOrder order = QOrder.order;
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Order> findOrderAndPayment(String orderUid) {
//        return Optional.empty();
//    }
//}
