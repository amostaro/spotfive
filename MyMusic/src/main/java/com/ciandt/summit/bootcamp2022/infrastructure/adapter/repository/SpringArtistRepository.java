package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringArtistRepository extends JpaRepository<ArtistEntity, String> {

    @Query("SELECT a FROM Artistas a WHERE upper(a.name) LIKE %:name%")
    List<ArtistEntity> findAllByNameLikeIgnoreCase(String name);

}

