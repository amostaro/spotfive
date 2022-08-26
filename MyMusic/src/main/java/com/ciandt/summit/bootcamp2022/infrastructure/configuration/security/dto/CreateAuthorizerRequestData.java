package com.ciandt.summit.bootcamp2022.infrastructure.configuration.security.dto;

import lombok.Data;

@Data
public class CreateAuthorizerRequestData {

    private String name;

    private String token;

}
