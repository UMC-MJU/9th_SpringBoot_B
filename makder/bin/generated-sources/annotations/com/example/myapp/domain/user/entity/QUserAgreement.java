package com.example.myapp.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAgreement is a Querydsl query type for UserAgreement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAgreement extends EntityPathBase<UserAgreement> {

    private static final long serialVersionUID = 1378143255L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAgreement userAgreement = new QUserAgreement("userAgreement");

    public final com.example.myapp.global.entity.QBaseTimeEntity _super = new com.example.myapp.global.entity.QBaseTimeEntity(this);

    public final com.example.myapp.domain.agreement.entity.QAgreement agreement;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QUserAgreement(String variable) {
        this(UserAgreement.class, forVariable(variable), INITS);
    }

    public QUserAgreement(Path<? extends UserAgreement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAgreement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAgreement(PathMetadata metadata, PathInits inits) {
        this(UserAgreement.class, metadata, inits);
    }

    public QUserAgreement(Class<? extends UserAgreement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.agreement = inits.isInitialized("agreement") ? new com.example.myapp.domain.agreement.entity.QAgreement(forProperty("agreement")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

