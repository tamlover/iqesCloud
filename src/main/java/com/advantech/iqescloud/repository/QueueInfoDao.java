package com.advantech.iqescloud.repository;

import com.advantech.iqescloud.entity.QueueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface QueueInfoDao extends JpaRepository<QueueInfo, Long> {

    List<QueueInfo> findByRestaurantIdAndQueueStartTimeLike(Long restaurantId, String date);

    /**
     * param include year month day
     *
     * @param queueStartTime
     * @return
     */
    @Query(value = "select avg(q.queueTime) from QueueInfo q where q.queueStartTime like ?1 and q.tableTypeDescribe=?2 and q.restaurant.id=?3")
    Long getAverageOfQueueTimeByQueueStartTime(String queueStartTime, String tableTypeDescribe, Long restaurantId);

    /**
     * @return
     */
    @Query(value = "select DISTINCT q.tableTypeDescribe from QueueInfo q where q.restaurant.id=?1")
    List<String> getTableTypeDescribe(Long restaurantId);

    @Query(value = "select count(q) from QueueInfo q where q.tableTypeDescribe=?1 and q.restaurant.id=?2")
    Integer getCountsByTableTypeDescribe(String tableTypeDescribe, Long restaurantId);

    /**
     *
     */
    @Query(value = "select count(q) from QueueInfo q where q.queueStartTime like ?1 and q.restaurant.id = ?2")
    Integer getByQueueStartTime(String queueStartTime,Long restaurantId);

    @Query(value = "select count(q) from QueueInfo q where q.queueStartTime like ?1 and q.restaurant.id = ?2 and q.queueState='3'")
    Integer getByQueueStartTimeAndQueueState(String queueStartTime,Long restaurantId);

    @Query(value = "update queue_info set queue_state = '2' where queue_state = '1'",nativeQuery = true)
    void update2();
}
