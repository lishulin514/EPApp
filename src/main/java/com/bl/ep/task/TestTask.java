package com.bl.ep.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestTask {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * fixedRate = 3000  单位毫秒
     * 最多6位不支持年
     */
//    @Scheduled(cron = "4-40 * * * * ?")
    public void reportCurrentTime(){
        System.out.println("现在时间："+dateFormat.format(new Date()));
    }
}
