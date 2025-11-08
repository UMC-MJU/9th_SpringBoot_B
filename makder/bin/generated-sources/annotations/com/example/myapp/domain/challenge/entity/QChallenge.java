package com.example.myapp.domain.challenge.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChallenge is a Querydsl query type for Challenge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChallenge extends EntityPathBase<Challenge> {

    private static final long serialVersionUID = 204724195L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChallenge challenge = new QChallenge("challenge");

    public final com.example.myapp.global.entity.QBaseTimeEntity _super = new com.example.myapp.global.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> reward = createNumber("reward", Integer.class);

    public final com.example.myapp.domain.shop.entity.QShop shop;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<com.example.myapp.domain.user.entity.UserChallenge, com.example.myapp.domain.user.entity.QUserChallenge> userChallengeList = this.<com.example.myapp.domain.user.entity.UserChallenge, com.example.myapp.domain.user.entity.QUserChallenge>createList("userChallengeList", com.example.myapp.domain.user.entity.UserChallenge.class, com.example.myapp.domain.user.entity.QUserChallenge.class, PathInits.DIRECT2);

    public QChallenge(String variable) {
        this(Challenge.class, forVariable(variable), INITS);
    }

    public QChallenge(Path<? extends Challenge> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChallenge(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChallenge(PathMetadata metadata, PathInits inits) {
        this(Challenge.class, metadata, inits);
    }

    public QChallenge(Class<? extends Challenge> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shop = inits.isInitialized("shop") ? new com.example.myapp.domain.shop.entity.QShop(forProperty("shop"), inits.get("shop")) : null;
    }

}

