package com.ciandt.summit.bootcamp2022.domain.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Musicas")
@Table(name = "Musicas")
public class MusicEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private String id;

    @Column(name = "Nome")
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artistaid", referencedColumnName = "Id")
    private ArtistEntity artistEntity;

    @Column(name = "Id", insertable = false, updatable = false)
    private String artistId;

    @ManyToMany
    @JsonProperty(value = "playlistMusic")
    @JoinTable(name = "PlaylistMusicas",
            joinColumns = @JoinColumn(name = "MusicId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "PlaylistId", referencedColumnName = "id"))
    @JsonIgnore
    private Set<PlaylistEntity> playlistEntityList;

}
