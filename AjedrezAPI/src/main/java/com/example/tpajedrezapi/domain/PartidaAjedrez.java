package com.example.tpajedrezapi.domain;

import com.example.tpajedrezapi.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartidaAjedrez {

    private Long id;
    private  Jugador jBlancas,jNegras;
    private List<Pieza> piezasNegras,piezasBlancas;
    private  GestorMovimientos gestor;
    private Long idJugadorUltimoMovimiento;
    private EstadosJuego estadosJuego;

    private Tablero tablero;

    public Long getId(){
        return this.id;
    }


    public PartidaAjedrez(Jugador jBlancas, Jugador jNegras) {
        this.jBlancas = jBlancas;
        this.jNegras = jNegras;
        this.tablero = new Tablero();
        this.tablero.inicializarTablero();
        this.piezasBlancas = tablero.getPiezasBlancas();
        this.piezasNegras = tablero.getPiezasNegras();
        this.gestor = new GestorMovimientos(piezasBlancas,piezasNegras);
    }
}
