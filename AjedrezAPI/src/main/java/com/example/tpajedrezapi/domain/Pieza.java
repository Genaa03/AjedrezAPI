package com.example.tpajedrezapi.domain;

public abstract class Pieza {

    private Posicion posicion;
    private String color;
    private boolean viva = true;
    private final String tipo = this.getClass().getSimpleName();

    public Posicion getPosicion() {
        return posicion;
    }

    public String getColor() {
        return color;
    }
    public String getTipo() { return this.tipo;}
    public void setColor(String color) {
        this.color = color;
    }
    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }
    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public String toString(){
        return this.posicion.toString()+"_"+this.color+"_"+this.viva+"_"+this.tipo;
    }

    public String toStringForUser() {
        if(color.equals("B")){
            return getClass().getSimpleName().substring(0,1).toUpperCase();
        }
        else{
            return getClass().getSimpleName().substring(0,1).toLowerCase();
        }
    }

    public boolean mismaPosicion(Posicion p){
         if (p.getColumnaLetra() != null && Character.isDigit(p.getColumnaLetra().charAt(0))) {
            int columnaNum = Integer.parseInt(p.getColumnaLetra());
            p.setColumnaLetra(p.cambiarALetra(columnaNum));
        }
        return p.getFilaNumero() == this.posicion.getFilaNumero() && p.getColumnaLetra().equals(this.posicion.getColumnaLetra());
    }

    public abstract boolean puedeMover(Posicion pos);

    public abstract boolean puedeComer(Posicion posFinal, Pieza piezaAComer);
}
