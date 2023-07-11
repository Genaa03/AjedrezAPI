package com.example.tpajedrezapi.dtos.partidaAjedrez;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class PartidaAjedrezCreateDTOTest {
    @Test
    public void TestConstructor(){
        PartidaAjedrezCreateDTO t = new PartidaAjedrezCreateDTO(1L,2L);

        Assertions.assertNotNull(t.getJBlancas());
        Assertions.assertNotNull(t.getJNegras());

        Assertions.assertEquals("PartidaAjedrezCreateDTO", t.getClass().getSimpleName());
    }
}
