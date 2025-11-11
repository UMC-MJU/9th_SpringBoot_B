package com.example.myapp.domain.agreement.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAgreement is a Querydsl query type for Agreement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAgreement extends EntityPathBase<Agreement> {

    private static final long serialVersionUID = -700253501L;

    public static final QAgreement agreement = new QAgreement("agreement");

    public final com.example.myapp.global.entity.QBaseTimeEntity _super = new com.example.myapp.global.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<com.example.myapp.domain.user.entity.UserAgreement, com.example.myapp.domain.user.entity.QUserAgreement> userAgreementList = this.<com.example.myapp.domain.user.entity.UserAgreement, com.example.myapp.domain.user.entity.QUserAgreement>createList("userAgreementList", com.example.myapp.domain.user.entity.UserAgreement.class, com.example.myapp.domain.user.entity.QUserAgreement.class, PathInits.DIRECT2);

    public QAgreement(String variable) {
        super(Agreement.class, forVariable(variable));
    }

    public QAgreement(Path<? extends Agreement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAgreement(PathMetadata metadata) {
        super(Agreement.class, metadata);
    }

}

