package com.example.EducationClassProject.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClassImage is a Querydsl query type for ClassImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClassImage extends EntityPathBase<ClassImage> {

    private static final long serialVersionUID = 1247646423L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClassImage classImage = new QClassImage("classImage");

    public final QLecture aLecture;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgUrl = createString("imgUrl");

    public QClassImage(String variable) {
        this(ClassImage.class, forVariable(variable), INITS);
    }

    public QClassImage(Path<? extends ClassImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClassImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClassImage(PathMetadata metadata, PathInits inits) {
        this(ClassImage.class, metadata, inits);
    }

    public QClassImage(Class<? extends ClassImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.aLecture = inits.isInitialized("aLecture") ? new QLecture(forProperty("aLecture"), inits.get("aLecture")) : null;
    }

}

