package com.example.tpajedrezapi.dtos.partidaAjedrez;

import com.example.tpajedrezapi.domain.EstadosJuego;
import com.example.tpajedrezapi.domain.Pieza;
import com.example.tpajedrezapi.dtos.jugador.JugadorReadDTO;
import com.example.tpajedrezapi.entities.JugadorEntity;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaAjedrezReadDTO {
    private Long id;
    private EstadosJuego estadosJuego;
    private JugadorEntity jNegras;
    private JugadorEntity jBlancas;
    private Long idJugadorUltimoMovimiento;
    private List<Pieza> piezasBlancas,piezasNegras;


}
