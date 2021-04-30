package com.carcassonne.gameserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.carcassonne.bean"})
@MapperScan("com.carcassonne.mapper")
@SpringBootApplication

public class GameserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameserverApplication.class, args);
    }

}
