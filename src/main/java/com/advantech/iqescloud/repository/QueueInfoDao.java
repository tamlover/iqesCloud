package com.advantech.iqescloud.repository;

import com.advantech.iqescloud.entity.QueueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query(value = "select DISTINCT q.table_type_describe from queue_info as q where q.restaurant_id=?1",nativeQuery = true)
    List<String> getTableTypeDescribe(Long restaurantId);

    @Query(value = "select count(q) from QueueInfo q where q.tableTypeDescribe=?1 and q.restaurant.id=?2")
    Integer getCountsByTableTypeDescribe(String tableTypeDescribe, Long restaurantId);

    @Query(value = "select count(q) from QueueInfo q where q.tableTypeDescribe=?1")
    Integer getCountsByTableType(String tableTypeDescribe);


    @Query(value = "select count(q) from QueueInfo q where q.queueStartTime like ?1 and q.restaurant.id = ?2")
    Integer getByQueueStartTime(String queueStartTime,Long restaurantId);

    @Query(value = "select count(q) from QueueInfo q where q.queueStartTime like ?1 and q.restaurant.id = ?2 and q.queueState='3'")
    Integer getByQueueStartTimeAndQueueState(String queueStartTime,Long restaurantId);

    @Query(value = "update queue_info set queue_state = '2' where queue_state = '1'",nativeQuery = true)
    void update2();

    @Query(value = "update queue_info set queue_time=?1 where table_type_describe='小桌' AND id%2==0",nativeQuery = true)
    void update3(Integer queueTime);

    @Query(value = "update QueueInfo set queueTime=?1 where tableTypeDescribe='大桌'")
    void update4(Integer queueTime);

    List<QueueInfo> getByTableTypeDescribe(String tableType);

    @Query(value = "select count (q) from QueueInfo as q where q.restaurant.id=:restaurantId and q.tableTypeDescribe=:tableType and q.queueTime between :time1 and :time2")
    int  getByQueueTimeBetween(@Param("restaurantId")long restaurantId,@Param("tableType")String tableType,@Param("time1")int time1,@Param("time2")int time2);

    @Query(value = "select count (q) from QueueInfo as q where q.restaurant.id=:restaurantId and q.tableTypeDescribe=:tableType and q.queueTime between :time1 and :time2 and q.queueState='3'")
    int  getByQueueTimeBetweenAndQueueState(@Param("restaurantId")long restaurantId,@Param("tableType")String tableType,@Param("time1")int time1,@Param("time2")int time2);
}
