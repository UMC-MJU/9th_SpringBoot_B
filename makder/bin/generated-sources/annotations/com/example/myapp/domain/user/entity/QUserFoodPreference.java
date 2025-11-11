package com.example.myapp.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFoodPreference is a Querydsl query type for UserFoodPreference
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFoodPreference extends EntityPathBase<UserFoodPreference> {

    private static final long serialVersionUID = -89584148L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFoodPreference userFoodPreference = new QUserFoodPreference("userFoodPreference");

    public final com.example.myapp.global.entity.QBaseTimeEntity _super = new com.example.myapp.global.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.example.myapp.domain.food.entity.QFoodCategory foodCategory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QUserFoodPreference(String variable) {
        this(UserFoodPreference.class, forVariable(variable), INITS);
    }

    public QUserFoodPreference(Path<? extends UserFoodPreference> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFoodPreference(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFoodPreference(PathMetadata metadata, PathInits inits) {
        this(UserFoodPreference.class, metadata, inits);
    }

    public QUserFoodPreference(Class<? extends UserFoodPreference> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodCategory = inits.isInitialized("foodCategory") ? new com.example.myapp.domain.food.entity.QFoodCategory(forProperty("foodCategory")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

