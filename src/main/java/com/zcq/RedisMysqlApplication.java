package com.zcq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
/* mybatis-enhance-actable固定配置 */
@MapperScan({"com.gitee.sunchenbin.mybatis.actable.dao.*","com.zcq.dao"})
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*","com.zcq"})
public class RedisMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisMysqlApplication.class, args);
    }

}
