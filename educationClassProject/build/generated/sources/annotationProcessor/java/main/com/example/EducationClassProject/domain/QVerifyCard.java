package com.example.EducationClassProject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVerifyCard is a Querydsl query type for VerifyCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVerifyCard extends EntityPathBase<VerifyCard> {

    private static final long serialVersionUID = 524938045L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVerifyCard verifyCard = new QVerifyCard("verifyCard");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath career = createString("career");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final StringPath grade = createString("grade");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath info = createString("info");

    public final StringPath link = createString("link");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final QUser user;

    public QVerifyCard(String variable) {
        this(VerifyCard.class, forVariable(variable), INITS);
    }

    public QVerifyCard(Path<? extends VerifyCard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVerifyCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVerifyCard(PathMetadata metadata, PathInits inits) {
        this(VerifyCard.class, metadata, inits);
    }

    public QVerifyCard(Class<? extends VerifyCard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

