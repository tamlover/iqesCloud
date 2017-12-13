package com.advantech.iqescloud.entity.DTO.queueInfo;

import com.advantech.iqescloud.entity.QueueInfo;

/**
 * about queue
 */
public class QueueInfoDTO {

    private Long RestaurantId;

    private QueueInfo queueInfo;

    public Long getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        RestaurantId = restaurantId;
    }

    public QueueInfo getQueueInfo() {
        return queueInfo;
    }

    public void setQueueInfo(QueueInfo queueInfo) {
        this.queueInfo = queueInfo;
    }
}
