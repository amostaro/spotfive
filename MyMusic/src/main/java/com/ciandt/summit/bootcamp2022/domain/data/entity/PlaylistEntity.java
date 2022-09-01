package com.ciandt.summit.bootcamp2022.domain.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity(name = "Playlists")
@Table(name = "Playlists")
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private String id;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "playlistEntity", cascade = CascadeType.ALL)
    private Set<UserEntity> userEntityList;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonProperty(value = "playlistMusic")
    @JoinTable(name = "Playlistmusicas",
    joinColumns = @JoinColumn(name = "Playlistid", referencedColumnName = "Id"),
    inverseJoinColumns = @JoinColumn(name = "Musicaid", referencedColumnName = "Id"))
    private Set<MusicEntity> musicEntityList;
}
