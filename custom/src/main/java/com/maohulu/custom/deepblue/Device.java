package com.maohulu.custom.deepblue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author huliu
 * @description TODO
 * @since 2022/4/14 10:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    private String name;

    /**
     * 网络状态(1-离线，2-在线)
     */
    private String netStatus;


    /**
     * 工作状态(3-待命4执行任务5新建地图6录制路径7重定位8自动充电9手动充电)
     */
    private String deviceOnlineStatus;

    private Date createTime;
}
