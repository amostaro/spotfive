package com.ciandt.summit.bootcamp2022.domain.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "TipoUsuario")
@Table(name = "TipoUsuario")
public class TipoUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, length = 200)
    private String id;

    @Column(name = "Descricao")
    private String descricao;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoUsuarioEntity")
    private Set<UserEntity> userEntities;

}
