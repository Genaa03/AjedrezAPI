package com.example.tpajedrezapi.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GestorMovimientos {
    private final List<Pieza> listaBlancas, listaNegras;
    public GestorMovimientos(List<Pieza> listaBlancas, List<Pieza> listaNegras) {
        this.listaBlancas = listaBlancas;
        this.listaNegras = listaNegras;
    }
    public Set<Posicion> posicionesPosibles(List<Pieza> piezasAliadas, List<Pieza> piezasEnemigas) {
        Set<Posicion> posicionesPosibles = new HashSet<>();

        for (Pieza pieza : piezasAliadas) {
            Set<Posicion> posicionesPosiblesPieza = obtenerPosicionesPosiblesPieza(pieza, piezasAliadas, piezasEnemigas);
            posicionesPosibles.addAll(posicionesPosiblesPieza);
        }

        return posicionesPosibles;
    }
    private Set<Posicion> obtenerPosicionesPosiblesPieza(Pieza pieza, List<Pieza> piezasAliadas, List<Pieza> piezasEnemigas) {
        Set<Posicion> posicionesPosiblesPieza = new HashSet<>();
        Posicion posicionActual = pieza.getPosicion();

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Posicion posicionDestino = new Posicion(columna, fila);

                if (esMovimientoValido(pieza, posicionActual, posicionDestino, piezasAliadas, piezasEnemigas)) {
                    posicionesPosiblesPieza.add(posicionDestino);
                }
            }
        }

        return posicionesPosiblesPieza;
    }
    private boolean esMovimientoValido(Pieza pieza, Posicion posicionActual, Posicion posicionDestino, List<Pieza> piezasAliadas, List<Pieza> piezasEnemigas) {
        // REVISA POSICION (SI HAY ALIADA O ENEMIGA), CAMINO (SI HAY ALGUNA PIEZA), SI PUEDE HACER EL MOVIMIENTO
        // CASOS ESPECIALES PARA EL REY Y PEON


        boolean color = pieza.getColor().equals("B");
        if(!pieza.isViva()){
            return false;
        }

        // Verificar si la posición destino está ocupada por una pieza aliada
        for (Pieza piezaAliada : piezasAliadas) {
            if (piezaAliada.getPosicion().igualPosicion(posicionDestino) && piezaAliada.isViva()) {
                return false;
            }
        }

        // Verificar posiciones posibles del rey
        if(pieza instanceof Rey) {
            Posicion posRey = pieza.getPosicion();
            if(pieza.puedeMover(posicionDestino)){
                Posicion posicionTemporal = posicionDestino;
                Rey reyTemp = (Rey)pieza;
                reyTemp.setPosicion(posicionTemporal);
                if(!reyTemp.hayJaque(piezasEnemigas,piezasAliadas).isEmpty()){
                    pieza.setPosicion(posRey);
                    return false;
                }else{
                    pieza.setPosicion(posRey);
                    return true;
                }
            }else{
                return false;
            }
        }

        // Verificar si hay una pieza en el camino hacia la posición destino
        if (hayPiezaEnCamino(posicionActual, posicionDestino, piezasAliadas) && !(pieza instanceof Caballo)) {
            return false;
        }

        // Verificar si la posición destino está dentro del rango de movimiento de la pieza
        if(pieza instanceof Peon){
            if(!pieza.puedeMover(posicionDestino)){
                Pieza piezaEnemigaAComer;
                try {
                    piezaEnemigaAComer = piezaEnPosicion(posicionDestino, !color);
                } catch (IndexOutOfBoundsException e) {
                    piezaEnemigaAComer = null;
                }
                return pieza.puedeComer(posicionDestino, piezaEnemigaAComer);
            } else{
                if (pieza.getColor().equals("B")) {
                    return piezaEnPosicion(posicionDestino, false) == null;
                } else {
                    return piezaEnPosicion(posicionDestino, true) == null;
                }
            }

        }else{
            if (!pieza.puedeMover(posicionDestino)){
                return false;
            }
        }
        return true;
    }
    public boolean esValidoMovimiento(Pieza pieza, Posicion posicionActual, Posicion posicionDestino, List<Pieza> piezasAliadas, List<Pieza> piezasEnemigas) {
        // REVISA POSICION (SI HAY ALIADA O ENEMIGA), CAMINO (SI HAY ALGUNA PIEZA), SI PUEDE HACER EL MOVIMIENTO
        // CASOS ESPECIALES PARA EL REY Y PEON

        boolean color = pieza.getColor().equals("B");
        if (!pieza.isViva()) {
            throw new IllegalArgumentException("No hay pieza aliada en esa posición.");
        }

        // Verificar si la posición destino está ocupada por una pieza aliada
        for (Pieza piezaAliada : piezasAliadas) {
            if (piezaAliada.getPosicion().igualPosicion(posicionDestino) && piezaAliada.isViva()) {
                throw new IllegalArgumentException("Ya se encuentra una pieza aliada en ese lugar. Por favor, ingrese una nueva posición.");
            }
        }

        // Verificar posiciones posibles del rey
        if (pieza instanceof Rey) {
            Posicion posRey = pieza.getPosicion();
            if (pieza.puedeMover(posicionDestino)) {
                Posicion posicionTemporal = posicionDestino;
                Rey reyTemp = (Rey) pieza;
                reyTemp.setPosicion(posicionTemporal);
                if (!reyTemp.hayJaque(piezasEnemigas, piezasAliadas).isEmpty()) {
                    pieza.setPosicion(posRey);
                    throw new IllegalArgumentException("El rey estaría en jaque. Por favor, ingrese una nueva posición.");
                } else {
                    pieza.setPosicion(posRey);
                    return true;
                }
            } else {
                throw new IllegalArgumentException("La pieza no puede hacer ese movimiento. Por favor, ingrese un movimiento válido.");
            }
        }

        // Verificar si hay una pieza en el camino hacia la posición destino
        if (hayPiezaEnCamino(posicionActual, posicionDestino, piezasAliadas) && !(pieza instanceof Caballo)) {
            throw new IllegalArgumentException("Hay una pieza en el camino. Por favor, ingrese una nueva posición.");
        }

        // Verificar si la posición destino está dentro del rango de movimiento de la pieza
        if (pieza instanceof Peon) {
            if (!pieza.puedeMover(posicionDestino)) {
                Pieza piezaEnemigaAComer;
                try {
                    piezaEnemigaAComer = piezaEnPosicion(posicionDestino, !color);
                    if(piezaEnemigaAComer == null){
                        throw new IllegalArgumentException("La pieza no puede hacer ese movimiento. Por favor, ingrese un movimiento válido.");
                    }
                } catch (IndexOutOfBoundsException e) {
                    piezaEnemigaAComer = null;

                }
                if (pieza.puedeComer(posicionDestino, piezaEnemigaAComer)) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Esta pieza no puede matarla. Por favor, ingrese una nueva posición.");
                }
            } else {
                if (pieza.getColor().equals("B")) {
                    if (piezaEnPosicion(posicionDestino, false) != null) {
                        throw new IllegalArgumentException("Hay una pieza enemiga en ese lugar y no puede matarla. Por favor, ingrese una nueva posición.");
                    }
                } else {
                    if (piezaEnPosicion(posicionDestino, true) != null) {
                        throw new IllegalArgumentException("Hay una pieza enemiga en ese lugar y no puede matarla. Por favor, ingrese una nueva posición.");
                    }
                }
            }
        } else {
            if (!pieza.puedeMover(posicionDestino)) {
                throw new IllegalArgumentException("La pieza no puede hacer ese movimiento. Por favor, ingrese un movimiento válido.");
            }
        }
        return true;
    }

    public boolean hayPiezaEnCamino(Posicion posicionActual, Posicion posicionDestino, List<Pieza> piezas) {
        int filaInicio = posicionActual.getFilaNumero();
        int columnaInicio = posicionActual.columnaEnNumero();
        int filaDestino = posicionDestino.getFilaNumero();
        int columnaDestino = posicionDestino.columnaEnNumero();
        boolean color;

        color= piezas.get(0).getColor().equals("B");

        if(posicionActual.igualPosicion(posicionDestino)){
            return true;
        }


        // Verificar si el movimiento es vertical
        if (columnaInicio == columnaDestino) {
            int paso = filaInicio < filaDestino ? 1 : -1;
            for (int fila = filaInicio + paso; fila != filaDestino; fila += paso) {
                Posicion posicionIntermedia = new Posicion(columnaInicio, fila);
                Pieza pieza1 = piezaEnPosicion(posicionIntermedia, color);
                Pieza pieza2 = piezaEnPosicion(posicionIntermedia, !color);
                if ((pieza1 != null && pieza1.isViva()) || (pieza2 != null && pieza2.isViva())) {
                    return true; // Hay una pieza en el camino
                }
            }
        }

        // Verificar si el movimiento es horizontal
        if (filaInicio == filaDestino) {
            int paso = columnaInicio < columnaDestino ? 1 : -1;
            for (int columna = columnaInicio + paso; columna != columnaDestino; columna += paso) {
                Posicion posicionIntermedia = new Posicion(columna, filaInicio);
                Pieza pieza1 = piezaEnPosicion(posicionIntermedia, color);
                Pieza pieza2 = piezaEnPosicion(posicionIntermedia, !color);
                if ((pieza1 != null && pieza1.isViva()) || (pieza2 != null && pieza2.isViva())) {
                    return true; // Hay una pieza en el camino
                }
            }
        }

        // Verificar si el movimiento es diagonal
        if (Math.abs(columnaInicio - columnaDestino) == Math.abs(filaInicio - filaDestino)) {
            int pasoFila = filaInicio < filaDestino ? 1 : -1;
            int pasoColumna = columnaInicio < columnaDestino ? 1 : -1;
            int fila = filaInicio + pasoFila;
            int columna = columnaInicio + pasoColumna;

            while (fila != filaDestino && columna != columnaDestino) {
                Posicion posicionIntermedia = new Posicion(columna, fila);
                Pieza pieza1 = piezaEnPosicion(posicionIntermedia, color);
                Pieza pieza2 = piezaEnPosicion(posicionIntermedia, !color);
                if ((pieza1 != null && pieza1.isViva()) || (pieza2 != null && pieza2.isViva())) {
                    return true; // Hay una pieza en el camino
                }
                fila += pasoFila;
                columna += pasoColumna;
            }
        }
        return false; // No hay ninguna pieza en el camino
    }
    public Pieza piezaEnPosicion(Posicion pos, boolean blanco){


        if (blanco){
            for (Pieza ficha : listaBlancas) {
                if(ficha.isViva()) {
                    if (ficha.mismaPosicion(pos)) {
                        return ficha;
                    }
                }
            }
        }else{
            for (Pieza ficha : listaNegras) {
                if(ficha.isViva()) {
                    if (ficha.mismaPosicion(pos)) {
                        return ficha;
                    }
                }
            }
        }
        return null;

    }

    public boolean hayJaque(boolean blancas,List<Pieza> piezasNegras, List<Pieza> piezasBlancas){
        if(blancas){
            for (Pieza p:piezasNegras) {
                if(p instanceof Rey){
                    Rey rey = (Rey)p;
                    return !rey.hayJaque(piezasBlancas, piezasNegras).isEmpty();
                }
            }
        }
        else {
            for (Pieza p:piezasBlancas) {
                if(p instanceof Rey){
                    Rey rey = (Rey)p;
                    return !rey.hayJaque(piezasNegras, piezasBlancas).isEmpty();
                }
            }
        }
        return false;
    }
    public boolean hayJaqueMate(boolean blancas, List<Pieza> piezasNegras, List<Pieza> piezasBlancas){
        if(blancas){
            for (Pieza p:piezasNegras) {
                if(p instanceof Rey){
                    Rey rey = (Rey)p;
                    return rey.hayJaqueMate(piezasBlancas, piezasNegras);
                }
            }
        }
        else {
            for (Pieza p:piezasBlancas) {
                if(p instanceof Rey){
                    Rey rey = (Rey)p;
                    return rey.hayJaqueMate(piezasBlancas, piezasNegras);
                }
            }
        }
        return false;
    }

}
