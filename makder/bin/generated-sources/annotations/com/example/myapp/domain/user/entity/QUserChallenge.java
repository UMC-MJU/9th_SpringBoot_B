package com.example.myapp.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserChallenge is a Querydsl query type for UserChallenge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserChallenge extends EntityPathBase<UserChallenge> {

    private static final long serialVersionUID = 1804990064L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserChallenge userChallenge = new QUserChallenge("userChallenge");

    public final com.example.myapp.global.entity.QBaseTimeEntity _super = new com.example.myapp.global.entity.QBaseTimeEntity(this);

    public final com.example.myapp.domain.challenge.entity.QChallenge challenge;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isComplete = createBoolean("isComplete");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QUserChallenge(String variable) {
        this(UserChallenge.class, forVariable(variable), INITS);
    }

    public QUserChallenge(Path<? extends UserChallenge> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserChallenge(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserChallenge(PathMetadata metadata, PathInits inits) {
        this(UserChallenge.class, metadata, inits);
    }

    public QUserChallenge(Class<? extends UserChallenge> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.challenge = inits.isInitialized("challenge") ? new com.example.myapp.domain.challenge.entity.QChallenge(forProperty("challenge"), inits.get("challenge")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

