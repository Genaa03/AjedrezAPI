package com.example.tpajedrezapi.entities;

import lombok.*;
import javax.persistence.*;

@Entity(name = "jugadores")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JugadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int partidasGanadas;
    private int cantidadMovimientos;
}