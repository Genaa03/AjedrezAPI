package com.example.tpajedrezapi.dtos.partidaAjedrez;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovimientoDTOTest {
    @Test
    public void TestConstructor(){
        MovimientoDTO t = new MovimientoDTO("A",1,"B",2);

        Assertions.assertNotNull(t.getFilaFinal());
        Assertions.assertNotNull(t.getColumnaFinal());
        Assertions.assertNotNull(t.getFilaInicio());
        Assertions.assertNotNull(t.getColumnaInicio());
        Assertions.assertEquals("MovimientoDTO", t.getClass().getSimpleName());



    }
}
