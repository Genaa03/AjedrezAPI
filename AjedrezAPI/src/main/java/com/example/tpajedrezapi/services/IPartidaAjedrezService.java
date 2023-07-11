package com.example.tpajedrezapi.services;

import com.example.tpajedrezapi.dtos.common.BooleanResponse;
import com.example.tpajedrezapi.dtos.partidaAjedrez.PartidaAjedrezCreateDTO;
import com.example.tpajedrezapi.dtos.partidaAjedrez.PartidaAjedrezReadDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPartidaAjedrezService {

    PartidaAjedrezReadDTO getPartida(Long id);
    List<PartidaAjedrezReadDTO> getPartidas();
    PartidaAjedrezReadDTO createPartida(PartidaAjedrezCreateDTO partidaAjedrez) throws Exception;
    StringBuilder mostrarTablero(long id, long idJugador) throws Exception;
    PartidaAjedrezReadDTO realizarMovimiento(Long idPartida, Long idJugador,String columnaInicial, int filaInicial, String columnaFinal, int filaFinal) throws Exception;

    BooleanResponse delete(Long id) throws Exception;

}
