package com.khjeon.gifticon;

import com.khjeon.gifticon.service.BootUpService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySource("classpath:myconfig.properties")
public class SpringbootJpaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ac = SpringApplication.run(SpringbootJpaApplication.class, args);
		ac.getBean(BootUpService.class).setup();
	}
}
