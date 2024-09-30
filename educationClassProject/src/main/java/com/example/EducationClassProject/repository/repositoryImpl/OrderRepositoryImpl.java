package com.example.EducationClassProject.repository.repositoryImpl;

import com.example.EducationClassProject.domain.Order;
import com.example.EducationClassProject.domain.QOrder;
import com.example.EducationClassProject.domain.QPayment;
import com.example.EducationClassProject.domain.QUser;
import com.example.EducationClassProject.repository.customRepository.OrderRepositoryCustom;

//import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    // orderUid 로 order 객체를 가져옵니다. ( jpa 보다 쿼리문이 성능 측면에서 더 좋아서 쿼리문으로 작성 하였습니다. )
    // ( 한번의 쿼리로 order 와 관련된 payment, user을 즉시 로딩하여(eager) 가져오기 때문입니다. )
    // query dsl로 리팩토링
    @Override
    public Optional<Order> findOrderAndPaymentAndMember(String orderUid) {
        QOrder order = QOrder.order;
        QPayment payment = QPayment.payment;
        QUser user = QUser.user;

        Order result = jpaQueryFactory
                .select(order)
                .from(order)
                .leftJoin(order.payment, payment).fetchJoin()
                .leftJoin(order.user, user).fetchJoin()
                .where(order.orderUid.eq(orderUid))
                .fetchOne();

        return Optional.ofNullable(result);
    }

    // order 엔티티를 가져올때 payment 로 즉시로딩으로 가져와 성능 최적을 위해 쿼리문으로 작성하였습니다.
    // query dsl로 리팩토링
    @Override
    public Optional<Order> findOrderAndPayment(String orderUid) {
        QOrder order = QOrder.order;
        QPayment payment = QPayment.payment;

        Order result = jpaQueryFactory
                .select(order)
                .from(order)
                .leftJoin(order.payment, payment).fetchJoin()
                .where(order.orderUid.eq(orderUid))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
