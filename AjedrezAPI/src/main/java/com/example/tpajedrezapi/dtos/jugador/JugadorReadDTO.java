package com.example.tpajedrezapi.dtos.jugador;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JugadorReadDTO {
    @NotEmpty
    private Long id;
    private String nombre;
    private int partidasGanadas;
    private int cantidadMovimientos;
}
