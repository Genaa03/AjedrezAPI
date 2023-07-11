package com.example.tpajedrezapi.repositories;

import com.example.tpajedrezapi.entities.PartidaAjedrezEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaAjedrezRepository extends JpaRepository<PartidaAjedrezEntity, Long> {
}
