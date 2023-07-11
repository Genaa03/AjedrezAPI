package com.example.tpajedrezapi.controllers;

import com.example.tpajedrezapi.domain.Jugador;
import com.example.tpajedrezapi.dtos.common.BooleanResponse;
import com.example.tpajedrezapi.dtos.jugador.JugadorCreateDTO;
import com.example.tpajedrezapi.dtos.jugador.JugadorReadDTO;
import com.example.tpajedrezapi.services.IJugadorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("jugador")
@Validated
public class JugadorController {
    @Qualifier("jugadorServiceImpl")
    @Autowired
    IJugadorService jugadorService;

    @GetMapping("/get/{id}")
    public ResponseEntity<JugadorReadDTO> get(@PathVariable Long id) throws ResponseStatusException {
        try{
            return  ResponseEntity.ok(jugadorService.get(id));
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<JugadorReadDTO>> getAll() throws ResponseStatusException {
        try{
            return ResponseEntity.ok(jugadorService.getAll());
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

    @PostMapping("/save")
    public ResponseEntity<JugadorReadDTO> save(@RequestBody @Valid JugadorCreateDTO jugadorDto) throws ResponseStatusException {
        try{
            return ResponseEntity.ok(jugadorService.save(jugadorDto));
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody JugadorCreateDTO jugadorDto) throws ResponseStatusException {
        try{
            return ResponseEntity.ok(jugadorService.update(id, jugadorDto));
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BooleanResponse> delete(@PathVariable Long id) throws ResponseStatusException{
        try{
            return ResponseEntity.ok(jugadorService.delete(id));

        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}