package com.example.tpajedrezapi.entities;

import com.example.tpajedrezapi.domain.*;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class PiezasConverter implements AttributeConverter<List<Pieza>, String> {
    private static final String  SPLIT_CHAR=";";

    @Override
    public String convertToDatabaseColumn(List<Pieza> piezas){
        if(piezas == null) return "";
        return piezas.stream().map(Pieza::toString).collect(Collectors.joining(SPLIT_CHAR));
    }


    public Pieza createPiezaFromDb(String[] posicionMap){
        Pieza newPieza;
        Posicion pos = new Posicion(String.valueOf(posicionMap[0].charAt(0)), Integer.parseInt(posicionMap[0].substring(1)));
        String color = posicionMap[1];
        boolean viva = posicionMap[2].equals("true");
        switch(posicionMap[3]){
            case "Alfil":
                newPieza = new Alfil(pos,color);
                newPieza.setViva(viva);
                break;
            case "Caballo":
                newPieza= new Caballo(pos,color);
                newPieza.setViva(viva);
                break;
            case "Peon":
                newPieza= new Peon(pos,color);
                newPieza.setViva(viva);
                break;
            case "Queen":
                newPieza= new Queen(pos,color);
                newPieza.setViva(viva);
                break;
            case "Rey":
                newPieza= new Rey(pos,color);
                newPieza.setViva(viva);
                break;
            case "Torre":
                newPieza= new Torre(pos,color);
                newPieza.setViva(viva);
                break;
            default:
                throw new IllegalArgumentException("La pieza solicitada es inválida");
        }
        return newPieza;
    }
    @Override
    public List<Pieza> convertToEntityAttribute(String data){
        return Arrays.stream(data.split(SPLIT_CHAR)).map(s -> {
            String [] posicionMap = s.split("_");
            return createPiezaFromDb(posicionMap);
        }).collect(Collectors.toList());
    }
}
