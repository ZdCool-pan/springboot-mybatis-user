package com.sinosoft;

import com.sinosoft.database.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Import({ DynamicDataSourceRegister.class })
@MapperScan("com.sinosoft.mapper")
public class ComServerUserApplication{
    private static final Logger LOGGER = LoggerFactory.getLogger(ComServerUserApplication.class);

    public static void main(String[] args) {
        LOGGER.info("启动开始");
        SpringApplication.run(ComServerUserApplication.class, args);
        LOGGER.info("启动完成");
    }
}
