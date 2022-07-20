package com.maohulu.custom.deepblue;

import cn.hutool.core.date.DateUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * @author huliu
 * @description TODO
 * @since 2022/4/14 10:43
 */
public class Demo {
    public static void main(String[] args) {
        /**
         *  netStatus 2 在线 1离线
         *  deviceOnlineStatus  4 正在执行任务  3 待命   5678
         *
         *  排序要求： 在线-正在执行任务 > 在线-待命（等其他状态） >离线-正在执行任务 > 离线-待命（等其他状态）
         */

        Device device1 = new Device("设备1", "2", "4", DateUtil.parseDate("2022-1-1"));
        Device device3 = new Device("设备3", "2", "3", DateUtil.parseDate("2022-1-3"));
        Device device4 = new Device("设备4", "2", "4", DateUtil.parseDate("2022-1-10"));
        Device device8 = new Device("设备8", "2", "3", DateUtil.parseDate("2022-1-15"));
        Device device9 = new Device("设备9", "2", "6", DateUtil.parseDate("2022-1-15"));
        Device device10 = new Device("设备10", "2", "7", DateUtil.parseDate("2022-1-11"));
        Device device2 = new Device("设备2", "1", "4", DateUtil.parseDate("2022-1-2"));
        Device device5 = new Device("设备5", "1", "4", DateUtil.parseDate("2022-1-5"));
        Device device6 = new Device("设备6", "1", "3", DateUtil.parseDate("2022-1-1"));
        Device device7 = new Device("设备7", "1", "3", DateUtil.parseDate("2022-1-6"));
        Device device11 = new Device("设备11", "1", "6", DateUtil.parseDate("2022-1-15"));
        Device device12 = new Device("设备12", "1", "7", DateUtil.parseDate("2022-1-11"));

        List<Device> deviceList = Arrays.asList(device1, device2, device3, device4, device5, device6, device7,device8,device9,device10,device11,device12);

        method1(deviceList);
    }

    private static void method1(List<Device> deviceList) {

        List<String> onlineStatus = Arrays.asList("3", "4");

        deviceList.stream()
                // 在线设备-正在执行任务 创建时间倒序
                .sorted(Comparator.comparing(Device::getNetStatus,Comparator.reverseOrder())
                        .thenComparing(Device::getDeviceOnlineStatus, (x , y) -> {
                            if (onlineStatus.contains(x) && onlineStatus.contains(y)){
                                if (onlineStatus.contains(x)&& "4".equals(x) && !"4".equals(y)) {
                                    return -1;
                                } else if (!"4".equals(x) && "4".equals(y)) {
                                    return 1;
                                } else {
                                    return 0;
                                }
                            }
                            return 1;
                        })
                        .thenComparing(Device::getCreateTime,Comparator.reverseOrder()))
                .forEach(System.out::println);
    }
}
