package com.example.tpajedrezapi.domain;

public class Caballo extends Pieza {
    public Caballo(Posicion p, String color) {
        this.setPosicion(p);
        this.setColor(color);
    }
    @Override
    public boolean puedeMover(Posicion posFinal) {


        // ARRIBA - DERECHA
        Posicion posicionActual =new Posicion(getPosicion().columnaEnNumero()+1, getPosicion().getFilaNumero()+2);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }


        // ARRIBA - IZQUIERDA

        posicionActual =new Posicion(getPosicion().columnaEnNumero()-1, getPosicion().getFilaNumero()+2);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }

        // ABAJO - DERECHA

        posicionActual =new Posicion(getPosicion().columnaEnNumero()+1, getPosicion().getFilaNumero()-2);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }


        // ABAJO - IZQUIERDA
        posicionActual =new Posicion(getPosicion().columnaEnNumero()-1, getPosicion().getFilaNumero()-2);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }

        // DERECHA - ARRIBA

        posicionActual =new Posicion(getPosicion().columnaEnNumero()+2, getPosicion().getFilaNumero()+1);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }

        // DERECHA - ABAJO

        posicionActual =new Posicion(getPosicion().columnaEnNumero()+2, getPosicion().getFilaNumero()-1);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }

        // IZQUIERDA - ARRIBA
        posicionActual =new Posicion(getPosicion().columnaEnNumero()-2, getPosicion().getFilaNumero()+1);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }


        // IZQUIERDA - ABAJO
        posicionActual =new Posicion(getPosicion().columnaEnNumero()-2, getPosicion().getFilaNumero()-1);
        if(posicionActual.igualPosicion(posFinal)){
            return true;
        }

        return false;
    }

    @Override
    public boolean puedeComer(Posicion posFinal, Pieza piezaAComer) {
        return false;
    }
}
