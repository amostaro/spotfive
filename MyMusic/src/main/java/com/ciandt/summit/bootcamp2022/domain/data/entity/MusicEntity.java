package com.ciandt.summit.bootcamp2022.domain.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

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
    @JoinColumn(name = "artistaid", referencedColumnName = "id")
    private ArtistEntity artistEntity;

    @Column(name = "artistaid", insertable = false, updatable = false)
    private String artistId;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonProperty(value = "playlistMusic")
    @JoinTable(name = "Playlistmusicas",
            joinColumns = @JoinColumn(name = "Musicid", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "Playlistid", referencedColumnName = "Id"))
    private Set<PlaylistEntity> playlistEntityList;

}
