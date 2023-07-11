package com.example.tpajedrezapi.dtos.partidaAjedrez;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDTO {
    @NotNull
    @NotEmpty
    public String columnaInicio;

    @NotNull
    @NotEmpty
    public Integer filaInicio;

    @NotNull
    @NotEmpty
    public String columnaFinal;

    @NotNull
    @NotEmpty
    public Integer filaFinal;
}
