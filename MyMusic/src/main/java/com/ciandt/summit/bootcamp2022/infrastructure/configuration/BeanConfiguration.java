package com.ciandt.summit.bootcamp2022.infrastructure.configuration;

import com.ciandt.summit.bootcamp2022.domain.port.interfaces.ArtistServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.ArtistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.ArtistServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    ArtistServicePort artistServicePort(ArtistRepositoryPort artistRepositoryPort) {
        return new ArtistServiceImpl(artistRepositoryPort);
    }
}
