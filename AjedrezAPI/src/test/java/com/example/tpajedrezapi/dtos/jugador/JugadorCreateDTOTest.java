package com.example.tpajedrezapi.dtos.jugador;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JugadorCreateDTOTest {

    @Test
    public void TestConstructor(){
        JugadorCreateDTO t = new JugadorCreateDTO("nombre");
        Assertions.assertNotNull(t.getNombre());
        Assertions.assertEquals("nombre", t.getNombre());
    }


}
