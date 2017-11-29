package com.advantech.iqescloud.repository;

import com.advantech.iqescloud.entity.IqesUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author huqili.tp
 */
public interface UserDao extends CrudRepository<IqesUser,Long> {

    IqesUser findByTel(String tel);
}
