package com.example.tpajedrezapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Tablero {

    private  final Integer FILAS = 8;
    private  final Integer COLUMNAS = 8;
    private  final String NADA = " ";
    private  final String[][] tablero;
    private  List<Pieza> piezasBlancas = new ArrayList<>();
    private  List<Pieza> piezasNegras = new ArrayList<>();

    public String[][] getTablero() {
        return tablero;
    }
    public List<Pieza> getPiezasBlancas() {
        return piezasBlancas;
    }
    public List<Pieza> getPiezasNegras() {
        return piezasNegras;
    }
    public Tablero() {
        this.tablero = new String[FILAS][COLUMNAS];
    }
    public void actualizarTablero(List<Pieza> lBlancas, List<Pieza> lNegras){
        for(int i=0; i<8; i++){
            for (int j =0;j<8;j++){
                this.tablero[i][j] = NADA;
            }
        }
        for (Pieza p:lBlancas) {
            if(p.isViva()) {
                this.tablero[p.getPosicion().getFilaNumero()][p.getPosicion().columnaEnNumero()] = p.toStringForUser();
            }
        }
        for (Pieza p:lNegras) {
            if(p.isViva()) {
                this.tablero[p.getPosicion().getFilaNumero()][p.getPosicion().columnaEnNumero()] = p.toStringForUser();
            }
        }
    }
    public StringBuilder mostrarTableroBlanco() {
        StringBuilder sb = new StringBuilder();
        sb.append("    A   B   C   D   E   F   G   H\n");
        sb.append("  ---------------------------------\n");
        for (int f = 8; f > 0; f--) {
            sb.append(f + " |");
            for (int c = 0; c < COLUMNAS; c++) {
                sb.append(" " + this.tablero[f - 1][c] + " |");
            }
            sb.append("  " + f + "\n");
            sb.append("  ---------------------------------\n");
        }
        sb.append("    A   B   C   D   E   F   G   H\n");
        return sb;
    }

    public StringBuilder mostrarTableroNegro() {
        StringBuilder sb = new StringBuilder();
        sb.append("    H   G   F   E   D   C   B   A\n");
        sb.append("  ---------------------------------\n");
        for (int f = 1; f <= FILAS; f++) {
            sb.append(f + " |");
            for (int c = COLUMNAS - 1; c >= 0; c--) {
                sb.append(" " + this.tablero[f - 1][c] + " ");
                sb.append("|");
            }
            sb.append("  " + f + "\n");
            sb.append("  ---------------------------------\n");
        }
        sb.append("    H   G   F   E   D   C   B   A\n");

        return sb;
    }
    public void inicializarTablero(){
        piezasBlancas.clear();
        piezasNegras.clear();

        //EQUIPO BLANCO
        piezasBlancas.add(new Rey(new Posicion("E",0),"B"));
        piezasBlancas.add(new Queen(new Posicion("D",0),"B"));
        piezasBlancas.add(new Alfil(new Posicion("C",0),"B"));
        piezasBlancas.add(new Alfil(new Posicion("F",0),"B"));
        piezasBlancas.add(new Torre(new Posicion("A",0),"B"));
        piezasBlancas.add(new Torre(new Posicion("H",0),"B"));
        piezasBlancas.add(new Caballo(new Posicion("B",0),"B"));
        piezasBlancas.add(new Caballo(new Posicion("G",0),"B"));
        piezasBlancas.add(new Peon(new Posicion("A",1),"B"));
        piezasBlancas.add(new Peon(new Posicion("B",1),"B"));
        piezasBlancas.add(new Peon(new Posicion("C",1),"B"));
        piezasBlancas.add(new Peon(new Posicion("D",1),"B"));
        piezasBlancas.add(new Peon(new Posicion("E",1),"B"));
        piezasBlancas.add(new Peon(new Posicion("F",1),"B"));
        piezasBlancas.add(new Peon(new Posicion("G",1),"B"));
        piezasBlancas.add(new Peon(new Posicion("H",1),"B"));


        //EQUIPO NEGRO
        piezasNegras.add(new Rey(new Posicion("E",7),"N"));
        piezasNegras.add(new Queen(new Posicion("D",7),"N"));
        piezasNegras.add(new Alfil(new Posicion("C",7),"N"));
        piezasNegras.add(new Alfil(new Posicion("F",7),"N"));
        piezasNegras.add(new Torre(new Posicion("A",7),"N"));
        piezasNegras.add(new Torre(new Posicion("H",7),"N"));
        piezasNegras.add(new Caballo(new Posicion("B",7),"N"));
        piezasNegras.add(new Caballo(new Posicion("G",7),"N"));
        piezasNegras.add(new Peon(new Posicion("A",6),"N"));
        piezasNegras.add(new Peon(new Posicion("B",6),"N"));
        piezasNegras.add(new Peon(new Posicion("C",6),"N"));
        piezasNegras.add(new Peon(new Posicion("D",6),"N"));
        piezasNegras.add(new Peon(new Posicion("E",6),"N"));
        piezasNegras.add(new Peon(new Posicion("F",6),"N"));
        piezasNegras.add(new Peon(new Posicion("G",6),"N"));
        piezasNegras.add(new Peon(new Posicion("H",6),"N"));

        actualizarTablero(piezasBlancas,piezasNegras);
    }

}
