package com.example.tpajedrezapi.domain;

public class Peon extends Pieza {

    public Peon(Posicion p, String color) {
        this.setPosicion(p);
        this.setColor(color);
    }

    @Override
    public boolean puedeMover(Posicion posFinal) {

        if (this.getColor().equals("B")) { // BLANCO
            if (this.getPosicion().getFilaNumero() == 1) {
                Posicion posicionActual = new Posicion(getPosicion().columnaEnNumero(), getPosicion().getFilaNumero() + 1);
                if (posicionActual.igualPosicion(posFinal)) {
                    return true;
                }
                posicionActual = new Posicion(getPosicion().columnaEnNumero(), getPosicion().getFilaNumero() + 2);
                if (posicionActual.igualPosicion(posFinal)) {
                    return true;
                }
            } else {
                Posicion posicionActual = new Posicion(getPosicion().columnaEnNumero(), getPosicion().getFilaNumero() + 1);
                if (posicionActual.igualPosicion(posFinal)) {
                    return true;
                }
            }
            /*
            }*/
        } else { // NEGRO
            if (this.getPosicion().getFilaNumero() == 6) {
                Posicion posicionActual = new Posicion(getPosicion().columnaEnNumero(), getPosicion().getFilaNumero() - 1);
                if (posicionActual.igualPosicion(posFinal)) {
                    return true;
                }
                posicionActual = new Posicion(getPosicion().columnaEnNumero(), getPosicion().getFilaNumero() - 2);
                if (posicionActual.igualPosicion(posFinal)) {
                    return true;
                }
            } else {
                Posicion posicionActual = new Posicion(getPosicion().columnaEnNumero(), getPosicion().getFilaNumero() - 1);
                if (posicionActual.igualPosicion(posFinal)) {
                    return true;
                }
            }
            /*// Verificar si puede comer hacia la diagonal derecha

            }*/
        }

        return false;
    }

    @Override
    public boolean puedeComer(Posicion posFinal, Pieza piezaAComer) {
        if(piezaAComer == null){
            return false;
        }
        if (this.getColor().equals("B")) {
            // Verificar si puede comer hacia la diagonal derecha
            Posicion posicionDiagonalDerecha = new Posicion(getPosicion().columnaEnNumero() + 1, getPosicion().getFilaNumero() + 1);
            if (posicionDiagonalDerecha.igualPosicion(posFinal)) {
                if (piezaAComer.getPosicion().igualPosicion(posFinal)) {
                    return true;
                }
            }
            // Verificar si puede comer hacia la diagonal izquierda
            Posicion posicionDiagonalIzquierda = new Posicion(getPosicion().columnaEnNumero() - 1, getPosicion().getFilaNumero() + 1);
            if (posicionDiagonalIzquierda.igualPosicion(posFinal)) {
                if (piezaAComer.getPosicion().igualPosicion(posFinal)) {
                    return true;
                }
            }
        }
        else {
            // Verificar si puede comer hacia la diagonal derecha
            Posicion posicionDiagonalDerecha = new Posicion(getPosicion().columnaEnNumero() + 1, getPosicion().getFilaNumero() - 1);
            if (posicionDiagonalDerecha.igualPosicion(posFinal)) {
                if (piezaAComer.getPosicion().igualPosicion(posFinal)) {
                    return true;
                }
            }
            // Verificar si puede comer hacia la diagonal izquierda
            Posicion posicionDiagonalIzquierda = new Posicion(getPosicion().columnaEnNumero() - 1, getPosicion().getFilaNumero() - 1);
            if (posicionDiagonalIzquierda.igualPosicion(posFinal)) {
                if (piezaAComer.getPosicion().igualPosicion(posFinal)) {
                    return true;
                }
            }
        }
        return false;
    }
}

