package com.example.EducationClassProject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = -1557073294L;

    public static final QPayment payment = new QPayment("payment");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.example.EducationClassProject.domain.enums.PaymentState> paymentState = createEnum("paymentState", com.example.EducationClassProject.domain.enums.PaymentState.class);

    public final StringPath paymentUid = createString("paymentUid");

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public QPayment(String variable) {
        super(Payment.class, forVariable(variable));
    }

    public QPayment(Path<? extends Payment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPayment(PathMetadata metadata) {
        super(Payment.class, metadata);
    }

}

