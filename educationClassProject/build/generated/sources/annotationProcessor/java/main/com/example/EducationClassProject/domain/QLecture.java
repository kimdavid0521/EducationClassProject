package com.example.EducationClassProject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLecture is a Querydsl query type for Lecture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLecture extends EntityPathBase<Lecture> {

    private static final long serialVersionUID = -717697558L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLecture lecture = new QLecture("lecture");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final EnumPath<com.example.EducationClassProject.domain.enums.ClassDay> classDay = createEnum("classDay", com.example.EducationClassProject.domain.enums.ClassDay.class);

    public final StringPath classExplain = createString("classExplain");

    public final StringPath classIntro = createString("classIntro");

    public final EnumPath<com.example.EducationClassProject.domain.enums.ClassLevel> classLevel = createEnum("classLevel", com.example.EducationClassProject.domain.enums.ClassLevel.class);

    public final StringPath className = createString("className");

    public final DateTimePath<java.time.LocalDateTime> classStartDay = createDateTime("classStartDay", java.time.LocalDateTime.class);

    public final EnumPath<com.example.EducationClassProject.domain.enums.ClassStatus> classStatus = createEnum("classStatus", com.example.EducationClassProject.domain.enums.ClassStatus.class);

    public final EnumPath<com.example.EducationClassProject.domain.enums.Test> classTest = createEnum("classTest", com.example.EducationClassProject.domain.enums.Test.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser owner;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateAt = _super.updateAt;

    public QLecture(String variable) {
        this(Lecture.class, forVariable(variable), INITS);
    }

    public QLecture(Path<? extends Lecture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLecture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLecture(PathMetadata metadata, PathInits inits) {
        this(Lecture.class, metadata, inits);
    }

    public QLecture(Class<? extends Lecture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new QUser(forProperty("owner"), inits.get("owner")) : null;
    }

}

