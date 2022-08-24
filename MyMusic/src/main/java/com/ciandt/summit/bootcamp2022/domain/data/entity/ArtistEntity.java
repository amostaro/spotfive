package com.ciandt.summit.bootcamp2022.domain.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Artistas")
@Table(name = "Artistas")
public class ArtistEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private String id;

    @Column(name = "Nome")
    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "artistEntity", cascade = CascadeType.ALL)
    private Set<MusicEntity> musicEntityList;

}
