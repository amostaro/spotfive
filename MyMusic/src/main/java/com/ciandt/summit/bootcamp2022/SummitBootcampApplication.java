package com.ciandt.summit.bootcamp2022;

import com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository.SpringMusicRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableFeignClients
@EnableCaching
@SpringBootApplication(scanBasePackages={
		"com.ciandt.summit.bootcamp2022.application",
		"com.ciandt.summit.bootcamp2022.infrastructure",
		"com.ciandt.summit.bootcamp2022.domain"})
@EnableJpaRepositories(basePackageClasses = SpringMusicRepository.class)
public class SummitBootcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(SummitBootcampApplication.class, args);
	}

}
