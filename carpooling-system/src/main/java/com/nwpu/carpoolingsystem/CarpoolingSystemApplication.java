package com.nwpu.carpoolingsystem;

import com.nwpu.carpoolingsystem.config.AuthServerRsaKeyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AuthServerRsaKeyProperties.class)
@MapperScan(value = "com.nwpu.carpoolingsystem.mapper")
public class CarpoolingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarpoolingSystemApplication.class, args);
    }

}
