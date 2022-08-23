package com.ciandt.summit.bootcamp2022;

import com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository.SpringArtistRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={
		"com.ciandt.summit.bootcamp2022.application",
		"com.ciandt.summit.bootcamp2022.infrastructure",
		"com.ciandt.summit.bootcamp2022.domain"})
@EnableJpaRepositories(basePackageClasses = SpringArtistRepository.class)
public class SummitBootcampApplication {

	public static void main(String[] args) {
		SpringApplication.run(SummitBootcampApplication.class, args);
	}

}
