package com.example.EducationClassProject.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserChat is a Querydsl query type for UserChat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserChat extends EntityPathBase<UserChat> {

    private static final long serialVersionUID = -536932361L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserChat userChat = new QUserChat("userChat");

    public final com.example.EducationClassProject.domain.QChatroom chatroom;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.EducationClassProject.domain.QUser user;

    public QUserChat(String variable) {
        this(UserChat.class, forVariable(variable), INITS);
    }

    public QUserChat(Path<? extends UserChat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserChat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserChat(PathMetadata metadata, PathInits inits) {
        this(UserChat.class, metadata, inits);
    }

    public QUserChat(Class<? extends UserChat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatroom = inits.isInitialized("chatroom") ? new com.example.EducationClassProject.domain.QChatroom(forProperty("chatroom"), inits.get("chatroom")) : null;
        this.user = inits.isInitialized("user") ? new com.example.EducationClassProject.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

