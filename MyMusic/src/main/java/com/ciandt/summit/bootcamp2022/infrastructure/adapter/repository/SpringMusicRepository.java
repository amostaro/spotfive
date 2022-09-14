package com.ciandt.summit.bootcamp2022.infrastructure.adapter.repository;

import com.ciandt.summit.bootcamp2022.domain.data.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringMusicRepository extends JpaRepository<MusicEntity, String> {

    @Query("SELECT m FROM Musicas m JOIN m.artistEntity ma WHERE ma.name LIKE %:name% OR m.name LIKE %:name% ORDER BY ma.name, m.name")
    List<MusicEntity> findAllByNameLikeIgnoreCase(@Param("name") String name);

    Optional<MusicEntity> findById(String idMusic);


}

