package com.advantech.iqescloud.repository;

import com.advantech.iqescloud.entity.QueueInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueInfoDao extends JpaRepository<QueueInfo,Long> {
}
