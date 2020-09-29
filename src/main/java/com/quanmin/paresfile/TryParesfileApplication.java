package com.quanmin.paresfile;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableAutoConfiguration
@ComponentScans(value = {
        @ComponentScan(value = "com.quanmin.paresfile.config"),
        @ComponentScan(basePackages = {"com.quanmin.paresfile.modules"})
})
@EnableScheduling
@EncryptablePropertySource("classpath:application.yml")
public class TryParesfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(TryParesfileApplication.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("try-paresfile-task");
        return taskScheduler;
    }
}
