package com.example.tpajedrezapi.dtos.jugador;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JugadorReadDTOTest {
    @Test
    public void TestConstructor(){
        JugadorReadDTO t = new JugadorReadDTO(1L,"nombre",1,1);
        Assertions.assertNotNull(t.getNombre());
        Assertions.assertEquals("nombre", t.getNombre());
        Assertions.assertEquals(1L, t.getId());
        Assertions.assertEquals(1, t.getPartidasGanadas());
        Assertions.assertEquals(1, t.getCantidadMovimientos());

    }
}
