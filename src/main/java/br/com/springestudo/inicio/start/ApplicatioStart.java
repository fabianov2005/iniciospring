package br.com.springestudo.inicio.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.springestudo.inicio.endpoint")
@EntityScan("br.com.springestudo.inicio.model")
@EnableJpaRepositories("br.com.springestudo.inicio.repository")
public class ApplicatioStart {
    public static void main(String[] args){
        SpringApplication.run(ApplicatioStart.class, args);
    }

}
