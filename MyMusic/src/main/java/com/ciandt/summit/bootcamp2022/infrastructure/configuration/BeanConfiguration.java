package com.ciandt.summit.bootcamp2022.infrastructure.configuration;

import com.ciandt.summit.bootcamp2022.domain.port.interfaces.ArtistServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.ArtistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.ArtistServiceImpl;
import com.ciandt.summit.bootcamp2022.domain.service.MusicServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    MusicServicePort musicServicePort(MusicRepositoryPort musicRepositoryPort) {
        return new MusicServiceImpl(musicRepositoryPort);
    }

    @Bean
    ArtistServicePort artistServicePort(ArtistRepositoryPort artistRepositoryPort) {
        return new ArtistServiceImpl(artistRepositoryPort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
