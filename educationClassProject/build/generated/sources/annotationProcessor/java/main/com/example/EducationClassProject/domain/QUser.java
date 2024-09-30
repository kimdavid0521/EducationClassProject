package com.example.EducationClassProject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1795956001L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final StringPath email = createString("email");

    public final EnumPath<com.example.EducationClassProject.domain.enums.Gender> gender = createEnum("gender", com.example.EducationClassProject.domain.enums.Gender.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final EnumPath<com.example.EducationClassProject.domain.enums.MemberStatus> memberStatus = createEnum("memberStatus", com.example.EducationClassProject.domain.enums.MemberStatus.class);

    public final ListPath<Lecture, QLecture> ownedLectures = this.<Lecture, QLecture>createList("ownedLectures", Lecture.class, QLecture.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final EnumPath<com.example.EducationClassProject.domain.enums.Role> role = createEnum("role", com.example.EducationClassProject.domain.enums.Role.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public final StringPath username = createString("username");

    public final EnumPath<com.example.EducationClassProject.domain.enums.Verify> verify = createEnum("verify", com.example.EducationClassProject.domain.enums.Verify.class);

    public final QVerifyCard verifyCard;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.verifyCard = inits.isInitialized("verifyCard") ? new QVerifyCard(forProperty("verifyCard"), inits.get("verifyCard")) : null;
    }

}

