package com.example.tpajedrezapi.services;

import com.example.tpajedrezapi.dtos.common.BooleanResponse;
import com.example.tpajedrezapi.dtos.jugador.JugadorCreateDTO;
import com.example.tpajedrezapi.dtos.jugador.JugadorReadDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IJugadorService {
    JugadorReadDTO get(Long id) throws Exception;
    List<JugadorReadDTO> getAll() throws Exception;
    JugadorReadDTO save(JugadorCreateDTO jugadorDto) throws Exception;
    String update(Long id, JugadorCreateDTO jugadorDto) throws Exception;
    BooleanResponse delete(Long id) throws Exception;

}
