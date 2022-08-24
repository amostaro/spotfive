package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.MusicServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/music")
public class MusicController {

    private final MusicServicePort musicServicePort;

    @GetMapping
    public List<MusicDTO> get(@RequestParam String name) {
        return musicServicePort.findAllByNameLikeIgnoreCase(name);
    }
}
