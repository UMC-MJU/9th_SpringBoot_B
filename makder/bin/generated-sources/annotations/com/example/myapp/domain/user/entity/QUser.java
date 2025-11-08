package com.example.myapp.domain.user.entity;

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

    private static final long serialVersionUID = -1680273613L;

    public static final QUser user = new QUser("user");

    public final com.example.myapp.global.entity.QBaseTimeEntity _super = new com.example.myapp.global.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<com.example.myapp.global.enums.Gender> gender = createEnum("gender", com.example.myapp.global.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final ListPath<com.example.myapp.domain.review.entity.Reply, com.example.myapp.domain.review.entity.QReply> replyList = this.<com.example.myapp.domain.review.entity.Reply, com.example.myapp.domain.review.entity.QReply>createList("replyList", com.example.myapp.domain.review.entity.Reply.class, com.example.myapp.domain.review.entity.QReply.class, PathInits.DIRECT2);

    public final ListPath<com.example.myapp.domain.review.entity.Review, com.example.myapp.domain.review.entity.QReview> reviewList = this.<com.example.myapp.domain.review.entity.Review, com.example.myapp.domain.review.entity.QReview>createList("reviewList", com.example.myapp.domain.review.entity.Review.class, com.example.myapp.domain.review.entity.QReview.class, PathInits.DIRECT2);

    public final EnumPath<com.example.myapp.global.enums.UserStatus> status = createEnum("status", com.example.myapp.global.enums.UserStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<UserAgreement, QUserAgreement> userAgreementList = this.<UserAgreement, QUserAgreement>createList("userAgreementList", UserAgreement.class, QUserAgreement.class, PathInits.DIRECT2);

    public final ListPath<UserChallenge, QUserChallenge> userChallengeList = this.<UserChallenge, QUserChallenge>createList("userChallengeList", UserChallenge.class, QUserChallenge.class, PathInits.DIRECT2);

    public final ListPath<UserFoodPreference, QUserFoodPreference> userFoodPreferenceList = this.<UserFoodPreference, QUserFoodPreference>createList("userFoodPreferenceList", UserFoodPreference.class, QUserFoodPreference.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

