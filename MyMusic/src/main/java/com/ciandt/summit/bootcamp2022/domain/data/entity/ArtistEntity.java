package com.ciandt.summit.bootcamp2022.domain.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Artistas")
public class ArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private String id;

    @Column(name = "Nome")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "artistEntity",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MusicEntity musicEntity;
}
