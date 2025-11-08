package com.example.myapp.domain.shop.repository;

import com.example.myapp.domain.region.entity.Region;
import com.example.myapp.domain.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    // Method name query to find a shop by name
    Optional<Shop> findByName(String name);

    // Method name query to find shops by region
    List<Shop> findByRegion(Region region);

    // Method name query to find a shop by name and address
    Optional<Shop> findByNameAndAddress(String name, String address);
}
