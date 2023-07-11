package com.example.tpajedrezapi.dtos.partidaAjedrez;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaAjedrezCreateDTO {

    @NotNull
    @NotEmpty
    private Long jBlancas;

    @NotNull
    @NotEmpty
    private Long jNegras;
}
