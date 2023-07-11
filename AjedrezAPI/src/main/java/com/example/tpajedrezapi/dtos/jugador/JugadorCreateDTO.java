package com.example.tpajedrezapi.dtos.jugador;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JugadorCreateDTO {
    @NotEmpty
    @NotNull
    private String nombre;
}
