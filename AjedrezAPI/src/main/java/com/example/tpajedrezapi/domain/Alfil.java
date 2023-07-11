package com.example.tpajedrezapi.domain;

public class Alfil extends Pieza {
    public Alfil(Posicion p, String color) {
        this.setPosicion(p);
        this.setColor(color);
    }

    @Override
    public boolean puedeMover(Posicion posFinal) {
        boolean puedeMover=true;

        // Verificar diagonales superiores derechas
        for (int i = 1; this.getPosicion().getFilaNumero() + i < 8 && this.getPosicion().columnaEnNumero() + i < 8; i++) {
            Posicion posicionActual = new Posicion(this.getPosicion().columnaEnNumero()+i, this.getPosicion().getFilaNumero()+i);
            if(posicionActual.igualPosicion(posFinal)){
                return true;
            }else {
                puedeMover = false;
            }
        }

        // Verificar diagonales superiores izquierdas
        for (int i = 1; this.getPosicion().getFilaNumero() + i < 8 && this.getPosicion().columnaEnNumero() - i >= 0; i++) {
            Posicion posicionActual = new Posicion(this.getPosicion().columnaEnNumero()-i, this.getPosicion().getFilaNumero()+i);
            if(posicionActual.igualPosicion(posFinal)){
                return true;
            }else {
                puedeMover = false;
            }
        }

        // Verificar diagonales inferiores derechas
        for (int i = 1; this.getPosicion().getFilaNumero() - i >= 0 && this.getPosicion().columnaEnNumero() + i < 8; i++) {
            Posicion posicionActual = new Posicion(this.getPosicion().columnaEnNumero()+i, this.getPosicion().getFilaNumero()-i);
            if(posicionActual.igualPosicion(posFinal)){
                return true;
            }else {
                puedeMover = false;
            }
        }

        // Verificar diagonales inferiores izquierdas
        for (int i = 1; this.getPosicion().getFilaNumero() - i >= 0 && this.getPosicion().columnaEnNumero() - i >= 0; i++) {
            Posicion posicionActual = new Posicion(this.getPosicion().columnaEnNumero()-i, this.getPosicion().getFilaNumero()-i);
            if(posicionActual.igualPosicion(posFinal)){
                return true;
            }else {
                puedeMover = false;
            }
        }
        return puedeMover;
    }

    @Override
    public boolean puedeComer(Posicion posFinal, Pieza piezaAComer) {
        return false;
    }
}
