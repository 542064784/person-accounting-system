package com.damon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
/**
 *  person account system application class
 *
 * @author Damon Chen
 * @date 2019/06/10
 */
@SpringBootApplication
@EnableJpaAuditing
public class PersonAccountSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonAccountSystemApplication.class, args);
    }
}
