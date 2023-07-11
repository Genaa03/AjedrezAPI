package com.example.tpajedrezapi.repositories;

import com.example.tpajedrezapi.entities.JugadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<JugadorEntity, Long> {
}
