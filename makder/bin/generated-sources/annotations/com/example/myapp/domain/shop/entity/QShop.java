package com.example.myapp.domain.shop.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShop is a Querydsl query type for Shop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShop extends EntityPathBase<Shop> {

    private static final long serialVersionUID = -236288759L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShop shop = new QShop("shop");

    public final com.example.myapp.global.entity.QBaseTimeEntity _super = new com.example.myapp.global.entity.QBaseTimeEntity(this);

    public final StringPath address = createString("address");

    public final ListPath<com.example.myapp.domain.challenge.entity.Challenge, com.example.myapp.domain.challenge.entity.QChallenge> challengeList = this.<com.example.myapp.domain.challenge.entity.Challenge, com.example.myapp.domain.challenge.entity.QChallenge>createList("challengeList", com.example.myapp.domain.challenge.entity.Challenge.class, com.example.myapp.domain.challenge.entity.QChallenge.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.example.myapp.domain.region.entity.QRegion region;

    public final ListPath<com.example.myapp.domain.review.entity.Review, com.example.myapp.domain.review.entity.QReview> reviewList = this.<com.example.myapp.domain.review.entity.Review, com.example.myapp.domain.review.entity.QReview>createList("reviewList", com.example.myapp.domain.review.entity.Review.class, com.example.myapp.domain.review.entity.QReview.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QShop(String variable) {
        this(Shop.class, forVariable(variable), INITS);
    }

    public QShop(Path<? extends Shop> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShop(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShop(PathMetadata metadata, PathInits inits) {
        this(Shop.class, metadata, inits);
    }

    public QShop(Class<? extends Shop> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.region = inits.isInitialized("region") ? new com.example.myapp.domain.region.entity.QRegion(forProperty("region")) : null;
    }

}

