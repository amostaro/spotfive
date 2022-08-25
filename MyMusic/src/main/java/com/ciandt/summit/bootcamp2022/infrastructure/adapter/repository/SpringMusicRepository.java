package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface SpringMusicRepository extends JpaRepository<MusicEntity, String> {

    @Query("SELECT m FROM Musicas m JOIN m.artistEntity ma WHERE ma.name LIKE %:name% OR m.name LIKE %:name%")
    List<MusicEntity> findAllByNameLikeIgnoreCase(String name);


}

