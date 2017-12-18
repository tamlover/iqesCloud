package com.advantech.iqescloud.repository;

import com.advantech.iqescloud.entity.ShopUser;
import org.springframework.data.repository.CrudRepository;

public interface ShopUserDao extends CrudRepository<ShopUser, Long> {

    ShopUser findByAccount(String account);
}
