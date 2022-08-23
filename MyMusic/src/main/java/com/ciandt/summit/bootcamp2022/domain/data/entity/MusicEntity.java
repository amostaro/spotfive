package com.ciandt.summit.bootcamp2022.domain.data.entity;

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
@Table(name = "Musicas")
public class MusicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private String id;

    @Column(name = "Nome")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ArtistaId", referencedColumnName = "Id")
    private ArtistEntity artistEntity;

    @Column(name = "Id", insertable = false, updatable = false)
    private String artistId;

}
