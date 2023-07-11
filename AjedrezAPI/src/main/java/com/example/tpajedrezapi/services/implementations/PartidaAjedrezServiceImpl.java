package com.example.tpajedrezapi.services.implementations;

import com.example.tpajedrezapi.dtos.common.BooleanResponse;
import com.example.tpajedrezapi.dtos.partidaAjedrez.PartidaAjedrezCreateDTO;
import com.example.tpajedrezapi.dtos.jugador.JugadorReadDTO;
import com.example.tpajedrezapi.dtos.partidaAjedrez.PartidaAjedrezReadDTO;
import com.example.tpajedrezapi.entities.PartidaAjedrezEntity;
import com.example.tpajedrezapi.domain.PartidaAjedrez;
import com.example.tpajedrezapi.domain.*;
import com.example.tpajedrezapi.repositories.PartidaAjedrezRepository;
import com.example.tpajedrezapi.services.IPartidaAjedrezService;
import com.example.tpajedrezapi.services.IJugadorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidaAjedrezServiceImpl implements IPartidaAjedrezService {

    @Qualifier("jugadorServiceImpl")
    @Autowired
    private IJugadorService jugadorService;
    @Autowired
    private PartidaAjedrezRepository partidaAjedrezRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PartidaAjedrezReadDTO getPartida(Long id) {
        PartidaAjedrezEntity partidaAjedrez = partidaAjedrezRepository.findById(id).orElse(null);
        if(partidaAjedrez != null) {
            return modelMapper.map(partidaAjedrez, PartidaAjedrezReadDTO.class);
        }else {
            throw new IllegalArgumentException("No se encontró la partida");
        }
    }

    @Override
    public List<PartidaAjedrezReadDTO> getPartidas() {
        List<PartidaAjedrezEntity> partidas = partidaAjedrezRepository.findAll();
        return partidas.stream().map(p-> modelMapper.map(p, PartidaAjedrezReadDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PartidaAjedrezReadDTO createPartida(PartidaAjedrezCreateDTO partidaAjedrez) throws Exception {
        JugadorReadDTO jugador1;
        JugadorReadDTO jugador2;
        try{
            jugador1= jugadorService.get(partidaAjedrez.getJBlancas());
            jugador2 = jugadorService.get(partidaAjedrez.getJNegras());

            if(jugador1 == null || jugador2 == null){
                throw new IllegalArgumentException("Jugador no encontrado");
            }

            if(jugador1.getId().equals(jugador2.getId())){
                throw new IllegalArgumentException("Inserte 2 jugadores distintos");
            }

        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        Jugador jugador1Model = modelMapper.map(jugador1, Jugador.class);
        Jugador jugador2Model = modelMapper.map(jugador2, Jugador.class);
        Tablero tablero = new Tablero();
        tablero.inicializarTablero();

        PartidaAjedrez nuevaPartidaModel = new PartidaAjedrez(jugador1Model, jugador2Model);
        PartidaAjedrezEntity nuevaPartidaEntity = modelMapper.map(nuevaPartidaModel, PartidaAjedrezEntity.class);
        nuevaPartidaEntity.setPiezasNegras(tablero.getPiezasNegras());
        nuevaPartidaEntity.setPiezasBlancas(tablero.getPiezasBlancas());
        nuevaPartidaEntity.setEstadosJuego(EstadosJuego.EN_JUEGO);
        partidaAjedrezRepository.save(nuevaPartidaEntity);

        return modelMapper.map(nuevaPartidaEntity, PartidaAjedrezReadDTO.class);
    }

    @Override
    public StringBuilder mostrarTablero(long id, long idJugador) throws Exception {
        PartidaAjedrezEntity partida = partidaAjedrezRepository.findById(id).orElse(null);
        if(partida == null){
            throw new IllegalArgumentException("No existe el tablero con id "+id);
        }

        if(partida.getJNegras().getId() != idJugador && partida.getJBlancas().getId() != idJugador){
            throw new IllegalArgumentException("No existe un jugador con el id "+idJugador+" en la partida actual");
        }
        List<Pieza> piezasBlancas = partida.getPiezasBlancas();
        List<Pieza> piezasNegras = partida.getPiezasNegras();

        Tablero tablero = new Tablero();
        tablero.actualizarTablero(piezasBlancas, piezasNegras);

        if(partida.getJBlancas().getId().equals(idJugador)){
            return tablero.mostrarTableroBlanco();
        }else return tablero.mostrarTableroNegro();

    }
    public PartidaAjedrezReadDTO realizarMovimiento(Long idPartida,Long idJugador,String columnaInicial, int filaInicial, String columnaFinal, int filaFinal) throws Exception{

        try{
            PartidaAjedrezEntity partida = partidaAjedrezRepository.findById(idPartida).orElse(null);
            if(partida == null){
                throw new IllegalArgumentException("No existe la partida con id "+idPartida);
            }

            PartidaAjedrezReadDTO partidaAjedrezReadDTO = modelMapper.map(partida,PartidaAjedrezReadDTO.class);

            if(partidaAjedrezReadDTO.getEstadosJuego() == EstadosJuego.JAQUE_MATE){
                throw new IllegalArgumentException("La partida esta finalizada, hay JAQUE MATE");
            }
            boolean color;
            if(partidaAjedrezReadDTO.getIdJugadorUltimoMovimiento() == null){
                color = true;
                partidaAjedrezReadDTO.setIdJugadorUltimoMovimiento(partidaAjedrezReadDTO.getJNegras().getId());
            }else{
                color = partidaAjedrezReadDTO.getIdJugadorUltimoMovimiento().equals(partidaAjedrezReadDTO.getJNegras().getId());
            }

            if(!partidaAjedrezReadDTO.getJNegras().getId().equals(idJugador) && !partidaAjedrezReadDTO.getJBlancas().getId().equals(idJugador)){
                throw new IllegalArgumentException("No existe un jugador con el id "+idJugador+" en la partida actual");
            }
            if(partidaAjedrezReadDTO.getIdJugadorUltimoMovimiento().equals(idJugador)){
                throw new IllegalArgumentException("No es el turno del jugador con id " + idJugador);
            }

            Posicion posicionInicial = new Posicion(columnaInicial.toUpperCase(),filaInicial-1);
            Posicion pFinal = new Posicion(columnaFinal.toUpperCase(),filaFinal-1);
            GestorMovimientos gestor = new GestorMovimientos(partidaAjedrezReadDTO.getPiezasBlancas(),partidaAjedrezReadDTO.getPiezasNegras());


            Pieza piezaMovida = gestor.piezaEnPosicion(posicionInicial,color);

            if(gestor.piezaEnPosicion(posicionInicial, color) == null){
                throw new IllegalArgumentException("No existe una pieza en esa posicion");
            }
            if(color){
                if(!gestor.esValidoMovimiento(piezaMovida,posicionInicial,pFinal, partidaAjedrezReadDTO.getPiezasBlancas(), partidaAjedrezReadDTO.getPiezasNegras())){
                }
            }else{
                if(!gestor.esValidoMovimiento(piezaMovida,posicionInicial,pFinal, partidaAjedrezReadDTO.getPiezasNegras(), partidaAjedrezReadDTO.getPiezasBlancas())){
                }
            }

            if(gestor.piezaEnPosicion(pFinal,!color) == null){
                piezaMovida.setPosicion(pFinal);
                if(partidaAjedrezReadDTO.getIdJugadorUltimoMovimiento() == partidaAjedrezReadDTO.getJBlancas().getId()){
                    partidaAjedrezReadDTO.setIdJugadorUltimoMovimiento(partidaAjedrezReadDTO.getJNegras().getId());
                }else{
                    partidaAjedrezReadDTO.setIdJugadorUltimoMovimiento(partidaAjedrezReadDTO.getJBlancas().getId());
                }
            }else{
                // LO QUE HACE SI TIENE QUE COMER UNA PIEZA

                Pieza piezaEnemiga = gestor.piezaEnPosicion(pFinal,!color);
                piezaEnemiga.setViva(false); // LA MATO
                piezaMovida.setPosicion(pFinal);
                if(partidaAjedrezReadDTO.getIdJugadorUltimoMovimiento() == partidaAjedrezReadDTO.getJBlancas().getId()){
                    partidaAjedrezReadDTO.setIdJugadorUltimoMovimiento(partidaAjedrezReadDTO.getJNegras().getId());
                }else{
                    partidaAjedrezReadDTO.setIdJugadorUltimoMovimiento(partidaAjedrezReadDTO.getJBlancas().getId());
                }
            }

            // SUMA MOVIMIENTOS
            if(idJugador.equals(partidaAjedrezReadDTO.getJBlancas().getId())){

                partidaAjedrezReadDTO.getJBlancas().setCantidadMovimientos(partidaAjedrezReadDTO.getJBlancas().getCantidadMovimientos()+1);
            }else{
                partidaAjedrezReadDTO.getJNegras().setCantidadMovimientos(partidaAjedrezReadDTO.getJNegras().getCantidadMovimientos()+1);
            }

            // CONTROL DE JAQUE Y JAQUE MATE
            if(gestor.hayJaque(color,partidaAjedrezReadDTO.getPiezasNegras(),partidaAjedrezReadDTO.getPiezasBlancas())){
                partidaAjedrezReadDTO.setEstadosJuego(EstadosJuego.JAQUE);
            }
            if(gestor.hayJaqueMate(color,partidaAjedrezReadDTO.getPiezasNegras(),partidaAjedrezReadDTO.getPiezasBlancas())){
                partidaAjedrezReadDTO.setEstadosJuego(EstadosJuego.JAQUE_MATE);
                if(color){
                    partidaAjedrezReadDTO.getJBlancas().setPartidasGanadas(partidaAjedrezReadDTO.getJBlancas().getPartidasGanadas()+1);
                }else{
                    partidaAjedrezReadDTO.getJNegras().setPartidasGanadas(partidaAjedrezReadDTO.getJBlancas().getPartidasGanadas()+1);
                }
            }

            partidaAjedrezRepository.save(modelMapper.map(partidaAjedrezReadDTO,PartidaAjedrezEntity.class));
            return modelMapper.map(partidaAjedrezReadDTO,PartidaAjedrezReadDTO.class);
        }catch(Exception ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    @Override
    public BooleanResponse delete(Long id) throws  Exception{
            PartidaAjedrezEntity partida;
        try {
            partida= partidaAjedrezRepository.findById(id).orElse(null);
        } catch (Exception ex){
            throw new Exception("Se produjo un error al procesar la solicitud");
        }
            if(partida != null){
                partidaAjedrezRepository.deleteById(id);
                return BooleanResponse.Ok();
            } else {
                throw new IllegalArgumentException("La partida con id "+id+" no existe");
            }
    }
}
