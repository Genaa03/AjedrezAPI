package com.example.tpajedrezapi.domain;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jugador {
    private Long id;
    private String nombre;



    private int partidasGanadas = 0;
    private int cantidadMovimientos = 0;


    @Override
    public String toString(){
        return this.nombre;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCantidadMovimientos(int cantidadMovimientos) {
        this.cantidadMovimientos += cantidadMovimientos;
    }
    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas += partidasGanadas;
    }
    public int getPartidasGanadas() {
        return partidasGanadas;
    }
    public int getCantidadMovimientos() {
        return cantidadMovimientos;
    }

    public void sumarGanada() {
        this.partidasGanadas++;
    }
    public void sumarMovimiento() {
        this.cantidadMovimientos++;
    }
}
