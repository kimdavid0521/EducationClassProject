package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // orderUid 로 order 객체를 가져옵니다. ( jpa 보다 쿼리문이 성능 측면에서 더 좋아서 쿼리문으로 작성 하였습니다. )
    // ( 한번의 쿼리로 order 와 관련된 payment, user을 즉시 로딩하여(eager) 가져오기 때문입니다. )
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.payment p LEFT JOIN FETCH o.user m WHERE o.orderUid = :orderUid")
    Optional<Order> findOrderAndPaymentAndMember(@Param("orderUid") String orderUid);


}
