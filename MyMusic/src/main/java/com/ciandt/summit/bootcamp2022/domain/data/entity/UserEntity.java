package com.ciandt.summit.bootcamp2022.domain.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "Usuarios")
@Table(name = "Usuarios")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private String id;

    @Column(name = "Nome")
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "playlistid", referencedColumnName = "Id")
    private PlaylistEntity playlistEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "Id")
    private TipoUsuarioEntity tipoUsuarioEntity;

    @Column(name = "playlistid", insertable = false, updatable = false)
    private String playlistId;
}
