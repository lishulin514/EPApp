package com.bl.ep;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.bl.ep.dao")
//@ComponentScan(basePackages = {"com.bl.ep","org.n3r"})
//开启定时任务
@EnableScheduling
//开启异步调用方法
//发送短信 发送邮件 App消息推送 节省运维凌晨发布任务时间提供效率
@EnableAsync
public class EpAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpAppApplication.class, args);
	}
}
