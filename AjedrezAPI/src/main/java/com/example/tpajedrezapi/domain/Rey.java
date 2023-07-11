package com.example.tpajedrezapi.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Rey extends Pieza {

    public Rey(Posicion p, String color) {
        this.setPosicion(p);
        this.setColor(color);
    }
    @Override
    public boolean puedeMover(Posicion posFinal) {
        // ARRIBA
        Posicion posicionActual =new Posicion(getPosicion().columnaEnNumero(), getPosicion().getFilaNumero()+1);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }

        // ABAJO

        posicionActual =new Posicion(getPosicion().columnaEnNumero(), getPosicion().getFilaNumero()-1);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }

        // DERECHA

        posicionActual =new Posicion(getPosicion().columnaEnNumero()+1, getPosicion().getFilaNumero());
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }


        // IZQUIERDA
        posicionActual =new Posicion(getPosicion().columnaEnNumero()-1, getPosicion().getFilaNumero());
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }

        // DERECHA - ARRIBA

        posicionActual =new Posicion(getPosicion().columnaEnNumero()+1, getPosicion().getFilaNumero()+1);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }

        // DERECHA - ABAJO

        posicionActual =new Posicion(getPosicion().columnaEnNumero()+1, getPosicion().getFilaNumero()-1);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }

        // IZQUIERDA - ARRIBA
        posicionActual =new Posicion(getPosicion().columnaEnNumero()-1, getPosicion().getFilaNumero()+1);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }


        // IZQUIERDA - ABAJO
        posicionActual =new Posicion(getPosicion().columnaEnNumero()-1, getPosicion().getFilaNumero()-1);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }
        return false;
    }
    @Override
    public boolean puedeComer(Posicion posFinal, Pieza piezaAComer) {
        return false;
    }
    public List<Pieza> hayJaque(List<Pieza> piezasEnemigas, List<Pieza> piezaAliadas){
        int filaRey = getPosicion().getFilaNumero();
        int columnaRey = getPosicion().columnaEnNumero();
        List<Pieza> piezasEnJaque = new ArrayList<>();
        boolean agregarPieza = false;

    //QUE CLASE DE FICHA ES
    //IF QUE EN BASE A LA CLASE DIGA SI ESTA EN JAQUE O NO



    // ALFILES Y REYNA

        // Verificar diagonales superiores derechas
        for (int i = 1; filaRey + i < 8 && columnaRey + i < 8; i++) {
            Posicion posicionActual = new Posicion(columnaRey + i, filaRey + i);
            Pieza p = hayPieza(posicionActual, piezasEnemigas);

            if (p instanceof Queen || p instanceof Alfil) {
                // Verificar si hay una pieza aliada entre el rey y la pieza enemiga
                for (int j = 1; j <= i; j++) {
                    Posicion posicionIntermedia = new Posicion(columnaRey + j, filaRey + j);
                    Pieza aliada = hayPieza(posicionIntermedia, piezaAliadas);
                    if (aliada != null) {
                        // Se encontró una pieza aliada tapando el jaque, no es jaque
                        agregarPieza = false;
                        break;
                    }else {
                        agregarPieza = true;
                    }
                }
                if(agregarPieza){
                    piezasEnJaque.add(p);
                    break;
                }
            } else if (p != null) {
                break;
            }

        }



        // Verificar diagonales superiores izquierdas
        for (int i = 1; filaRey + i < 8 && columnaRey - i >= 0; i++) {
            Posicion posicionActual = new Posicion(columnaRey - i, filaRey + i);
            Pieza p = hayPieza(posicionActual,piezasEnemigas);
            if(p instanceof Queen || p instanceof Alfil){
                // Verificar si hay una pieza aliada entre el rey y la pieza enemiga
                for (int j = 1; j <= i; j++) {
                    Posicion posicionIntermedia = new Posicion(columnaRey - j, filaRey + j);
                    Pieza aliada = hayPieza(posicionIntermedia, piezaAliadas);
                    if (aliada != null) {
                        // Se encontró una pieza aliada tapando el jaque, no es jaque
                        agregarPieza = false;
                        break;
                    }else {
                        agregarPieza = true;
                    }
                }
                if(agregarPieza){
                    piezasEnJaque.add(p);
                    break;
                }
            } else if (p != null) {
                break;
            }

        }

        // Verificar diagonales inferiores derechas
        for (int i = 1; filaRey - i >= 0 && columnaRey + i < 8; i++) {
            Posicion posicionActual = new Posicion(columnaRey + i, filaRey - i);
            Pieza p = hayPieza(posicionActual,piezasEnemigas);
            if(p instanceof Queen || p instanceof Alfil){
                // Verificar si hay una pieza aliada entre el rey y la pieza enemiga
                for (int j = 1; j <= i; j++) {
                    Posicion posicionIntermedia = new Posicion(columnaRey + j, filaRey - j);
                    Pieza aliada = hayPieza(posicionIntermedia, piezaAliadas);
                    if (aliada != null) {
                        // Se encontró una pieza aliada tapando el jaque, no es jaque
                        agregarPieza = false;
                        break;
                    }else {
                        agregarPieza = true;
                    }
                }
                if(agregarPieza){
                    piezasEnJaque.add(p);
                    break;
                }
            } else if (p != null) {
                break;
            }
        }

        // Verificar diagonales inferiores izquierdas
        for (int i = 1; filaRey - i >= 0 && columnaRey - i >= 0; i++) {
            Posicion posicionActual = new Posicion(columnaRey - i, filaRey - i);
            Pieza p = hayPieza(posicionActual,piezasEnemigas);
            if(p instanceof Queen || p instanceof Alfil){
                // Verificar si hay una pieza aliada entre el rey y la pieza enemiga
                for (int j = 1; j <= i; j++) {
                    Posicion posicionIntermedia = new Posicion(columnaRey - j, filaRey - j);
                    Pieza aliada = hayPieza(posicionIntermedia, piezaAliadas);
                    if (aliada != null) {
                        // Se encontró una pieza aliada tapando el jaque, no es jaque
                        agregarPieza = false;
                        break;
                    }else {
                        agregarPieza = true;
                    }
                }
                if(agregarPieza){
                    piezasEnJaque.add(p);
                    break;
                }
            } else if (p != null) {
                break;
            }
        }


    //TORRES Y REYNA
        // Verificar hacia arriba
        for (int i = filaRey + 1; i < 8; i++) {
            Posicion posicionActual = new Posicion(columnaRey, i);
            Pieza p = hayPieza(posicionActual,piezasEnemigas);
            if(p instanceof Torre || p instanceof Queen){
                // Verificar si hay una pieza aliada entre el rey y la pieza enemiga
                for (int j = filaRey + 1; j < i; j++) {
                    Posicion posicionIntermedia = new Posicion(columnaRey, j);
                    Pieza aliada = hayPieza(posicionIntermedia, piezaAliadas);
                    if (aliada != null) {
                        // Se encontró una pieza aliada tapando el jaque, no es jaque
                        agregarPieza = false;
                        break;
                    }else {
                        agregarPieza = true;
                    }
                }
                if(agregarPieza){
                    piezasEnJaque.add(p);
                    break;
                }
            } else if (p != null) {
                break;
            }
        }

        // Verificar hacia abajo
        for (int i = filaRey - 1; i >= 0; i--) {
            Posicion posicionActual = new Posicion(columnaRey, i);
            Pieza p = hayPieza(posicionActual,piezasEnemigas);
            if(p instanceof Queen || p instanceof Torre){
                // Verificar si hay una pieza aliada entre el rey y la pieza enemiga
                for (int j = filaRey - 1; j > i; j--) {
                    Posicion posicionIntermedia = new Posicion(columnaRey, j);
                    Pieza aliada = hayPieza(posicionIntermedia, piezaAliadas);
                    if (aliada != null) {
                        // Se encontró una pieza aliada tapando el jaque, no es jaque
                        agregarPieza = false;
                        break;
                    }else {
                        agregarPieza = true;
                    }
                }
                if(agregarPieza){
                    piezasEnJaque.add(p);
                    break;
                }
            }
        }

        // Verificar hacia la derecha
        for (int i = columnaRey + 1; i < 8; i++) {
            Posicion posicionActual = new Posicion(i, filaRey);
            Pieza p = hayPieza(posicionActual,piezasEnemigas);
            if(p instanceof Queen || p instanceof Torre){
                // Verificar si hay una pieza aliada entre el rey y la pieza enemiga
                for (int j = columnaRey + 1; j < i; j++) {
                    Posicion posicionIntermedia = new Posicion(j, filaRey);
                    Pieza aliada = hayPieza(posicionIntermedia, piezaAliadas);
                    if (aliada != null) {
                        // Se encontró una pieza aliada tapando el jaque, no es jaque
                        agregarPieza = false;
                        break;
                    }else {
                        agregarPieza = true;
                    }
                }
                if(agregarPieza){
                    piezasEnJaque.add(p);
                    break;
                }
            } else if (p != null) {
                break;
            }
        }

        // Verificar hacia la izquierda
        for (int i = columnaRey - 1; i >= 0; i--) {
            Posicion posicionActual = new Posicion(i, filaRey);
            Pieza p = hayPieza(posicionActual,piezasEnemigas);
            if(p instanceof Queen || p instanceof Torre){
                // Verificar si hay una pieza aliada entre el rey y la pieza enemiga
                for (int j = filaRey - 1; j > i; j--) {
                    Posicion posicionIntermedia = new Posicion(j, filaRey);
                    Pieza aliada = hayPieza(posicionIntermedia, piezaAliadas);
                    if (aliada != null) {
                        // Se encontró una pieza aliada tapando el jaque, no es jaque
                        agregarPieza = false;
                        break;
                    }else {
                        agregarPieza = true;
                    }
                }
                if(agregarPieza){
                    piezasEnJaque.add(p);
                    break;
                }
            } else if (p != null) {
                break;
            }
        }


    // CABALLOS
        // ARRIBA - DERECHA
        int columnaActual = getPosicion().columnaEnNumero() + 1;
        int filaActual = getPosicion().getFilaNumero() + 2;
        if (columnaActual <= 7 && filaActual <= 7) {
            Posicion posicionActual = new Posicion(columnaActual, filaActual);
            Pieza p = hayPieza(posicionActual, piezasEnemigas);
            if (p instanceof Caballo) {
                piezasEnJaque.add(p);
            }
        }

        // ARRIBA - IZQUIERDA
        columnaActual = getPosicion().columnaEnNumero() - 1;
        filaActual = getPosicion().getFilaNumero() + 2;
        if (columnaActual >= 0 && filaActual <= 7) {
            Posicion posicionActual = new Posicion(columnaActual, filaActual);
            Pieza p = hayPieza(posicionActual, piezasEnemigas);
            if (p instanceof Caballo) {
                piezasEnJaque.add(p);
            }
        }

        // ABAJO - DERECHA
        columnaActual = getPosicion().columnaEnNumero() + 1;
        filaActual = getPosicion().getFilaNumero() - 2;
        if (columnaActual <= 7 && filaActual >= 0) {
            Posicion posicionActual = new Posicion(columnaActual, filaActual);
            Pieza p = hayPieza(posicionActual, piezasEnemigas);
            if (p instanceof Caballo) {
                piezasEnJaque.add(p);
            }
        }

        // ABAJO - IZQUIERDA
        columnaActual = getPosicion().columnaEnNumero() - 1;
        filaActual = getPosicion().getFilaNumero() - 2;
        if (columnaActual >= 0 && filaActual >= 0) {
            Posicion posicionActual = new Posicion(columnaActual, filaActual);
            Pieza p = hayPieza(posicionActual, piezasEnemigas);
            if (p instanceof Caballo) {
                piezasEnJaque.add(p);
            }
        }

        // DERECHA - ARRIBA
        columnaActual = getPosicion().columnaEnNumero() + 2;
        filaActual = getPosicion().getFilaNumero() + 1;
        if (columnaActual <= 7 && filaActual <= 7) {
            Posicion posicionActual = new Posicion(columnaActual, filaActual);
            Pieza p = hayPieza(posicionActual, piezasEnemigas);
            if (p instanceof Caballo) {
                piezasEnJaque.add(p);
            }
        }

        // DERECHA - ABAJO
        columnaActual = getPosicion().columnaEnNumero() + 2;
        filaActual = getPosicion().getFilaNumero() - 1;
        if (columnaActual <= 7 && filaActual >= 0) {
            Posicion posicionActual = new Posicion(columnaActual, filaActual);
            Pieza p = hayPieza(posicionActual, piezasEnemigas);
            if (p instanceof Caballo) {
                piezasEnJaque.add(p);
            }
        }

        // IZQUIERDA - ARRIBA
        columnaActual = getPosicion().columnaEnNumero() - 2;
        filaActual = getPosicion().getFilaNumero() + 1;
        if (columnaActual >= 0 && filaActual <= 7) {
            Posicion posicionActual = new Posicion(columnaActual, filaActual);
            Pieza p = hayPieza(posicionActual, piezasEnemigas);
            if (p instanceof Caballo) {
                piezasEnJaque.add(p);
            }
        }

        // IZQUIERDA - ABAJO
        columnaActual = getPosicion().columnaEnNumero() - 2;
        filaActual = getPosicion().getFilaNumero() - 1;
        if (columnaActual >= 0 && filaActual >= 0) {
            Posicion posicionActual = new Posicion(columnaActual, filaActual);
            Pieza p = hayPieza(posicionActual, piezasEnemigas);
            if (p instanceof Caballo) {
                piezasEnJaque.add(p);
            }
        }


        return piezasEnJaque;
    }
    public boolean hayJaqueMate(List<Pieza> piezasBlancas, List<Pieza> piezasNegras) {
        List<Pieza> piezasEnemigas, piezasAliadas;
        Rey reyTemp;

        if (this.getColor().equals("B")) {
            piezasAliadas = piezasBlancas;
            piezasEnemigas = piezasNegras;
        } else {
            piezasAliadas = piezasNegras;
            piezasEnemigas = piezasBlancas;
        }
        GestorMovimientos gestor = new GestorMovimientos(piezasBlancas, piezasNegras);
        if (hayJaque(piezasEnemigas, piezasAliadas).isEmpty()) {
            return false;
        }

        int filaRey = getPosicion().getFilaNumero();
        int columnaRey = getPosicion().columnaEnNumero();
        String colorRey = getColor();
        boolean jaqueMate = true;

        // ARRIBA - DERECHA
        Posicion posTemporal = new Posicion(columnaRey + 1, filaRey + 1);
        if (posicionValida(posTemporal)) {
            if(hayPieza(posTemporal,piezasAliadas) == null) {
                reyTemp = new Rey(new Posicion(columnaRey,filaRey), colorRey);
                reyTemp.setPosicion(posTemporal);
                if (reyTemp.hayJaque(piezasEnemigas, piezasAliadas).isEmpty()) {
                    return false;
                }
            }
        }

        // ABAJO - DERECHA
        posTemporal = new Posicion(columnaRey + 1, filaRey - 1);
        if (posicionValida(posTemporal)) {
            if(hayPieza(posTemporal,piezasAliadas) == null) {
                reyTemp = new Rey(new Posicion(columnaRey,filaRey), colorRey);
                reyTemp.setPosicion(posTemporal);
                if (reyTemp.hayJaque(piezasEnemigas, piezasAliadas).isEmpty()) {
                    return false;
                }
            }
        }

        // ARRIBA - IZQUIERDA
        posTemporal = new Posicion(columnaRey - 1, filaRey + 1);
        if (posicionValida(posTemporal)) {
            if(hayPieza(posTemporal,piezasAliadas) == null) {
                reyTemp = new Rey(new Posicion(columnaRey,filaRey), colorRey);
                reyTemp.setPosicion(posTemporal);
                if (reyTemp.hayJaque(piezasEnemigas, piezasAliadas).isEmpty()) {
                    return false;
                }
            }
        }

        // ABAJO - IZQUIERDA
        posTemporal = new Posicion(columnaRey - 1, filaRey - 1);
        if (posicionValida(posTemporal)) {
            if(hayPieza(posTemporal,piezasAliadas) == null) {
                reyTemp = new Rey(new Posicion(columnaRey,filaRey), colorRey);
                reyTemp.setPosicion(posTemporal);
                if (reyTemp.hayJaque(piezasEnemigas, piezasAliadas).isEmpty()) {
                    return false;
                }
            }
        }

        // DERECHA
        posTemporal = new Posicion(columnaRey + 1, filaRey);
        if (posicionValida(posTemporal)) {
            if(hayPieza(posTemporal,piezasAliadas) == null) {
                reyTemp = new Rey(new Posicion(columnaRey,filaRey), colorRey);
                reyTemp.setPosicion(posTemporal);
                if (reyTemp.hayJaque(piezasEnemigas, piezasAliadas).isEmpty()) {
                    return false;
                }
            }
        }

        // IZQUIERDA
        posTemporal = new Posicion(columnaRey - 1, filaRey);
        if (posicionValida(posTemporal)) {
            if(hayPieza(posTemporal,piezasAliadas) == null) {
                reyTemp = new Rey(new Posicion(columnaRey,filaRey), colorRey);
                reyTemp.setPosicion(posTemporal);
                if (reyTemp.hayJaque(piezasEnemigas, piezasAliadas).isEmpty()) {
                    return false;
                }
            }
        }

        // ARRIBA
        posTemporal = new Posicion(columnaRey, filaRey + 1);
        if (posicionValida(posTemporal)) {
            if(hayPieza(posTemporal,piezasAliadas) == null) {
                reyTemp = new Rey(new Posicion(columnaRey,filaRey), colorRey);
                reyTemp.setPosicion(posTemporal);
                if (reyTemp.hayJaque(piezasEnemigas, piezasAliadas).isEmpty()) {
                    return false;
                }
            }
        }

        // ABAJO
        posTemporal = new Posicion(columnaRey, filaRey - 1);
        if (posicionValida(posTemporal)) {
            if(hayPieza(posTemporal,piezasAliadas) == null) {
                reyTemp = new Rey(new Posicion(columnaRey,filaRey), colorRey);
                reyTemp.setPosicion(posTemporal);
                if (reyTemp.hayJaque(piezasEnemigas, piezasAliadas).isEmpty()) {
                    return false;
                }
            }
        }

        this.setPosicion(new Posicion(columnaRey, filaRey));

        List<Posicion> posi = espaciosCancelarJaque(hayJaque(piezasEnemigas, piezasAliadas));
        Set<Posicion> posi2 = gestor.posicionesPosibles(piezasAliadas, piezasEnemigas);
        for (Posicion pos : posi) {
            for (Posicion pos2 : posi2) {
                if (pos.igualPosicion(pos2)) {
                    return false;
                }
            }
        }

        return jaqueMate;
    }
    public boolean posicionValida(Posicion posicion) {
        int columna = posicion.columnaEnNumero();
        int fila = posicion.getFilaNumero();

        // Verificar si la columna y la fila están dentro de los límites válidos
        return columna >= 0 && columna <= 7 && fila >= 0 && fila <= 7;
    }
    private List<Posicion> espaciosCancelarJaque(List<Pieza> piezasJaque){
        List<Posicion> listaPosiciones = new ArrayList<>();
        int filaInicio = this.getPosicion().getFilaNumero();
        int columnaInicio = this.getPosicion().columnaEnNumero();
        int filaDestino;
        int columnaDestino;

        for(Pieza pieza : piezasJaque){
            listaPosiciones.add(pieza.getPosicion());
            filaDestino = pieza.getPosicion().getFilaNumero();
            columnaDestino = pieza.getPosicion().columnaEnNumero();
            if(columnaDestino == columnaInicio){ // MISMA COLUMA = JAQUE VERTICAL
                int paso = filaInicio < filaDestino ? 1 : -1;
                for (int fila = filaInicio + paso; fila != filaDestino; fila += paso) {
                    listaPosiciones.add(new Posicion(columnaInicio, fila));
                }
            }
            if (filaInicio == filaDestino) { // MISMA FILA = JAQUE HORIZONTAL
                int paso = columnaInicio < columnaDestino ? 1 : -1;
                for (int columna = columnaInicio + paso; columna != columnaDestino; columna += paso) {
                    listaPosiciones.add(new Posicion(columna, filaInicio));
                }
            }
            if (Math.abs(columnaInicio - columnaDestino) == Math.abs(filaInicio - filaDestino)) { // JAQUE VERTICAL
                int pasoFila = filaInicio < filaDestino ? 1 : -1;
                int pasoColumna = columnaInicio < columnaDestino ? 1 : -1;
                int fila = filaInicio + pasoFila;
                int columna = columnaInicio + pasoColumna;

                while (fila != filaDestino && columna != columnaDestino) {
                    listaPosiciones.add(new Posicion(columna, fila));
                    fila += pasoFila;
                    columna += pasoColumna;
                }
            }
        }

        return listaPosiciones;
    }
    private Pieza hayPieza(Posicion p, List<Pieza> piezas) {

        for(Pieza pieza :piezas){
            if(pieza.isViva()){
                if(pieza.mismaPosicion(p)){
                    return pieza;
                }
            }
        }
        return null;
    }
}
