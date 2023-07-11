package com.example.tpajedrezapi.entities;
import com.example.tpajedrezapi.domain.*;
import lombok.*;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "partidas")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaAjedrezEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private JugadorEntity jNegras;
    @ManyToOne
    private JugadorEntity jBlancas;
    @Column
    private Long idJugadorUltimoMovimiento;
    @Column
    private EstadosJuego estadosJuego;
    @Lob
    @Convert(converter = PiezasConverter.class)
    @Column
    private List<Pieza> piezasBlancas;
    @Lob
    @Convert(converter = PiezasConverter.class)
    @Column
    private List<Pieza> piezasNegras;

}
