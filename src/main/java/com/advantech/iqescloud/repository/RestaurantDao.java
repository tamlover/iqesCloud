package com.advantech.iqescloud.repository;

import com.advantech.iqescloud.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantDao extends JpaRepository<Restaurant,Long> {
}
