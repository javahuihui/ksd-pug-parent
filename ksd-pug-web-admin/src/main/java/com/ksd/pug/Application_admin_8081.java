package com.ksd.pug;

import com.pug.anno.EnableMpMybatis;
import com.pug.config.EnablePugLogs;
import com.pug.redis.config.EnablePugLimiter;
import com.pug.resultex.anno.EnableResultEx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableResultEx
@EnableMpMybatis
@EnablePugLimiter
@EnablePugLogs
public class Application_admin_8081 {

    public static void main(String[] args) {
        SpringApplication.run(Application_admin_8081.class,args);
    }
}
