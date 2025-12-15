package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "code")
    private FoodCategory foodCategory;

    @OneToMany(mappedBy = "food")
    private List<MemberFood> memberFoodList;

    public static Food of(FoodCategory foodCategory) {
        Food food = new Food();
        food.foodCategory = foodCategory;
        return food;
    }

}
