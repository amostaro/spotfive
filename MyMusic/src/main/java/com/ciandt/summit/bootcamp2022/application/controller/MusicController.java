package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository.SpringMusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/music")
public class MusicController {

    private final SpringMusicRepository springMusicRepository;

    @GetMapping
    public List<MusicEntity> get() {
        return springMusicRepository.findMusicEntityOrderByNameLikeIgnoreCase("The Beatles");
    }
}
