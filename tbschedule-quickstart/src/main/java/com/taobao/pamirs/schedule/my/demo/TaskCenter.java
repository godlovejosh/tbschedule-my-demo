package com.taobao.pamirs.schedule.my.demo;

import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Properties;

/**
 * Created by wuxing
 */
public class TaskCenter {

    public static void main(String[] args) throws Exception {

        // 初始化Spring
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "classpath:schedule-service-bean.xml");

        // 初始化调度工厂
        TBScheduleManagerFactory scheduleManagerFactory = new TBScheduleManagerFactory();

        Properties p = new Properties();
        p.put("zkConnectString", "localhost:2181");
        p.put("rootPath", "/schedule/cubc/trade");
        p.put("zkSessionTimeout", "60000");
//        p.put("userName", "zookeeper");
//        p.put("password", "zookeeper");
        p.put("isCheckParentPath", "true");

        scheduleManagerFactory.setApplicationContext(ctx);

        scheduleManagerFactory.init(p);
    }
}
