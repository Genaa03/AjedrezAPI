package com.example.tpajedrezapi.services.implementations;

import com.example.tpajedrezapi.domain.EstadosJuego;
import com.example.tpajedrezapi.dtos.common.BooleanResponse;
import com.example.tpajedrezapi.dtos.jugador.JugadorCreateDTO;
import com.example.tpajedrezapi.dtos.jugador.JugadorReadDTO;
import com.example.tpajedrezapi.entities.JugadorEntity;
import com.example.tpajedrezapi.entities.PartidaAjedrezEntity;
import com.example.tpajedrezapi.repositories.JugadorRepository;
import com.example.tpajedrezapi.repositories.PartidaAjedrezRepository;
import com.example.tpajedrezapi.services.IJugadorService;
import com.example.tpajedrezapi.services.IPartidaAjedrezService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JugadorServiceImpl implements IJugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PartidaAjedrezRepository partidaAjedrezRepository;

    @Override
    public JugadorReadDTO get(Long id) throws Exception {
        JugadorEntity jugadorEntity;
        try {
            jugadorEntity = jugadorRepository.findById(id).orElse(null);
        } catch (Exception ex) {
            throw new Exception("Se produjo un error al procesar su petición", ex);
        }
        if (jugadorEntity == null) {
            throw new IllegalArgumentException("No se pudo encontrar el jugador con id " + id);
        }
        return modelMapper.map(jugadorEntity, JugadorReadDTO.class);
    }

    @Override
    public List<JugadorReadDTO> getAll() throws Exception {
        List<JugadorEntity> jugadorEntities;
        try {
            jugadorEntities = jugadorRepository.findAll();
        } catch (Exception ex) {
            throw new Exception("Se produjo un error al procesar su petición", ex);
        }

        List<JugadorReadDTO> jugadoresDtosList = new ArrayList<>();
        for (JugadorEntity jugadorEntity : jugadorEntities) {
            jugadoresDtosList.add(modelMapper.map(jugadorEntity, JugadorReadDTO.class));
        }
        return jugadoresDtosList;
    }

    @Override
    public JugadorReadDTO save(JugadorCreateDTO jugadorDto) throws Exception {
        try {
            JugadorEntity jugadorEntity = modelMapper.map(jugadorDto, JugadorEntity.class);
            jugadorRepository.save(jugadorEntity);
            return modelMapper.map(jugadorEntity, JugadorReadDTO.class);

        } catch (Exception ex) {
            throw new Exception("Se produjo un error al procesar su petición", ex);
        }
    }

    @Override
    public String update(Long id, JugadorCreateDTO jugadorDto) throws Exception {
        JugadorEntity jugadorEntity;
        try {
            jugadorEntity = jugadorRepository.findById(id).orElse(null);
        } catch (Exception ex) {
            throw new Exception("Se produjo un error al procesar su petición", ex);
        }
        if (jugadorEntity == null) {
            throw new IllegalArgumentException("Jugador no encontrado");
        }
        jugadorEntity = modelMapper.map(jugadorDto, JugadorEntity.class);
        jugadorEntity.setId(id);
        jugadorRepository.save(jugadorEntity);

        return null;
    }

    @Override
    public BooleanResponse delete(Long id) throws Exception {
        JugadorEntity jugadorEntity;
        List<PartidaAjedrezEntity> partidasEntities;
        try {
            jugadorEntity = jugadorRepository.findById(id).orElse(null);
            partidasEntities = partidaAjedrezRepository.findAll();

            if (jugadorEntity != null) {
                for (PartidaAjedrezEntity partida : partidasEntities) {
                    if ((Objects.equals(partida.getJNegras().getId(), id) || Objects.equals(partida.getJBlancas().getId(), id)
                            && partida.getEstadosJuego() != EstadosJuego.JAQUE_MATE)) {
                        throw new Exception("El jugador se encuentra en una partida activa");
                    }
                }
                jugadorRepository.deleteById(id);
                return BooleanResponse.Ok();
            } else {
                throw new Exception("El jugador con id " + id + " no existe");
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }

}
