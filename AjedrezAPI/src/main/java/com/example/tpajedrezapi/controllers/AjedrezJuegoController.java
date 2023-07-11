package com.example.tpajedrezapi.controllers;

import com.example.tpajedrezapi.dtos.common.BooleanResponse;
import com.example.tpajedrezapi.dtos.jugador.JugadorReadDTO;
import com.example.tpajedrezapi.dtos.partidaAjedrez.MovimientoDTO;
import com.example.tpajedrezapi.dtos.partidaAjedrez.PartidaAjedrezCreateDTO;
import com.example.tpajedrezapi.dtos.partidaAjedrez.PartidaAjedrezReadDTO;
import com.example.tpajedrezapi.entities.PartidaAjedrezEntity;
import com.example.tpajedrezapi.services.IPartidaAjedrezService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/partidas")
public class AjedrezJuegoController {

    @Qualifier("partidaAjedrezServiceImpl")
    @Autowired
    IPartidaAjedrezService partidaAjedrezService;

    @PostMapping("/")
    public ResponseEntity<PartidaAjedrezReadDTO> createAjedrezJuego(@RequestBody PartidaAjedrezCreateDTO partidaAjedrezCreateDTO) throws Exception {
        try{
            return ResponseEntity.ok(partidaAjedrezService.createPartida(partidaAjedrezCreateDTO));
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/getPartida/{id}")
    public ResponseEntity<PartidaAjedrezReadDTO> getPartidaById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(partidaAjedrezService.getPartida(id));
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
    @GetMapping("/getPartidas")
    public ResponseEntity<List<PartidaAjedrezReadDTO>> getPartidas(){
        try{
            return ResponseEntity.ok(partidaAjedrezService.getPartidas());
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/mostrarTablero/{id}/{idJugador}")
    public ResponseEntity<StringBuilder> mostrarTablero(@PathVariable Long id, @PathVariable Long idJugador){
        try{
            return ResponseEntity.ok(partidaAjedrezService.mostrarTablero(id, idJugador));
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
    @PostMapping("/realizarMoviento/{id}/{idJugador}")
    public ResponseEntity<PartidaAjedrezReadDTO> realizarMovimiento(@PathVariable Long id, @PathVariable Long idJugador , @RequestBody MovimientoDTO movimientoDTO){
        try{
            return ResponseEntity.ok(partidaAjedrezService.realizarMovimiento(id, idJugador, movimientoDTO.columnaInicio, movimientoDTO.filaInicio, movimientoDTO.columnaFinal, movimientoDTO.filaFinal));
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BooleanResponse> delete(@PathVariable Long id) throws ResponseStatusException{
        try{
            return ResponseEntity.ok(partidaAjedrezService.delete(id));

        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
