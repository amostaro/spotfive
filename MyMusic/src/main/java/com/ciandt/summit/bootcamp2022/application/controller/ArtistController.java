package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.data.dto.ArtistDTO;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.ArtistServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {

    private final ArtistServicePort artistServicePort;

    @GetMapping
    public List<ArtistDTO> get(@RequestParam String name) {
        return artistServicePort.findAllByNameLikeIgnoreCase(name);
    }

}
