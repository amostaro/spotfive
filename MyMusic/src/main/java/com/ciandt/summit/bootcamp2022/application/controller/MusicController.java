package com.ciandt.summit.bootcamp2022.application.controller;

import com.ciandt.summit.bootcamp2022.domain.data.dto.DataDTO;
import com.ciandt.summit.bootcamp2022.domain.data.dto.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.port.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.domain.service.exception.LengthValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/musicas")
public class MusicController {

    private final MusicServicePort musicServicePort;

    @GetMapping
    public DataDTO get(@RequestParam String filtro) throws LengthValidationException {
        List<MusicDTO> allByNameLikeIgnoreCase = musicServicePort.findAllByNameLikeIgnoreCase(filtro);
        DataDTO dataDTO = new DataDTO();
        dataDTO.setData(new HashSet<>(allByNameLikeIgnoreCase));
        return dataDTO;

    }


}
