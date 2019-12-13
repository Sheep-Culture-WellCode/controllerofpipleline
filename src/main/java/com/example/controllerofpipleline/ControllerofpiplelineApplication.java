package com.example.controllerofpipleline;

import com.example.controllerofpipleline.security.OptionFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
@EnableTransactionManagement
@EnableScheduling
@ImportResource({"classpath:mapping/*.xml"})
@MapperScan("com.example.controllerofpipleline.mapper")
@EntityScan("com.example.controllerofpipleline.domin")
@SpringBootApplication
public class ControllerofpiplelineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControllerofpiplelineApplication.class, args);
	}

	@Bean
	public OptionFilter getFilter(){
		return new OptionFilter();
	}
}
