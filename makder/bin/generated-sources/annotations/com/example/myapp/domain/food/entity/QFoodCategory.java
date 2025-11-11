package com.example.myapp.domain.food.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodCategory is a Querydsl query type for FoodCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodCategory extends EntityPathBase<FoodCategory> {

    private static final long serialVersionUID = -1845738505L;

    public static final QFoodCategory foodCategory = new QFoodCategory("foodCategory");

    public final com.example.myapp.global.entity.QBaseTimeEntity _super = new com.example.myapp.global.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<com.example.myapp.domain.user.entity.UserFoodPreference, com.example.myapp.domain.user.entity.QUserFoodPreference> userFoodPreferenceList = this.<com.example.myapp.domain.user.entity.UserFoodPreference, com.example.myapp.domain.user.entity.QUserFoodPreference>createList("userFoodPreferenceList", com.example.myapp.domain.user.entity.UserFoodPreference.class, com.example.myapp.domain.user.entity.QUserFoodPreference.class, PathInits.DIRECT2);

    public QFoodCategory(String variable) {
        super(FoodCategory.class, forVariable(variable));
    }

    public QFoodCategory(Path<? extends FoodCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodCategory(PathMetadata metadata) {
        super(FoodCategory.class, metadata);
    }

}

