package com.ciandt.summit.bootcamp2022.infrastructure.configuration;

import com.ciandt.summit.bootcamp2022.domain.port.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.UserServicePort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.port.repository.UserRepositoryPort;
import com.ciandt.summit.bootcamp2022.domain.service.MusicServiceImpl;
import com.ciandt.summit.bootcamp2022.domain.service.PlaylistServiceImpl;
import com.ciandt.summit.bootcamp2022.domain.service.UserServiceImpl;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableCaching
public class BeanConfiguration {

    @Bean
    Config config() {
        Config config = new Config();
        MapConfig mapConfig = new MapConfig();
        mapConfig.setTimeToLiveSeconds(600);
        config.getMapConfigs().put("DataDTO", mapConfig);
        return config;
    }

    @Bean
    MusicServicePort musicServicePort(MusicRepositoryPort musicRepositoryPort) {
        return new MusicServiceImpl(musicRepositoryPort);
    }

    @Bean
    UserServicePort userServicePort(UserRepositoryPort userRepositoryPort) {
        return new UserServiceImpl(userRepositoryPort);
    }

    @Bean
    PlaylistServicePort playlistServicePort(PlaylistRepositoryPort playlistRepositoryPort,
                                            MusicRepositoryPort musicRepositoryPort) {
        return new PlaylistServiceImpl(playlistRepositoryPort, musicRepositoryPort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
