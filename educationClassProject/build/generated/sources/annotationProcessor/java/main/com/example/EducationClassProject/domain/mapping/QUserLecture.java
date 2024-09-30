package com.example.EducationClassProject.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserLecture is a Querydsl query type for UserLecture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLecture extends EntityPathBase<UserLecture> {

    private static final long serialVersionUID = -1980082049L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserLecture userLecture = new QUserLecture("userLecture");

    public final com.example.EducationClassProject.domain.QBaseEntity _super = new com.example.EducationClassProject.domain.QBaseEntity(this);

    public final com.example.EducationClassProject.domain.QLecture aLecture;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final com.example.EducationClassProject.domain.QUser user;

    public QUserLecture(String variable) {
        this(UserLecture.class, forVariable(variable), INITS);
    }

    public QUserLecture(Path<? extends UserLecture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserLecture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserLecture(PathMetadata metadata, PathInits inits) {
        this(UserLecture.class, metadata, inits);
    }

    public QUserLecture(Class<? extends UserLecture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.aLecture = inits.isInitialized("aLecture") ? new com.example.EducationClassProject.domain.QLecture(forProperty("aLecture"), inits.get("aLecture")) : null;
        this.user = inits.isInitialized("user") ? new com.example.EducationClassProject.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

