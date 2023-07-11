package com.example.tpajedrezapi.domain;

public class Posicion {
    private String columnaLetra;
    private Integer filaNumero;

    public Posicion(int columnaNumero, int filaNumero) {
        this.columnaLetra = cambiarALetra(columnaNumero);
        this.filaNumero = filaNumero;
    }

    public Posicion(String columnaLetra, int filaNumero) {
        this.columnaLetra = columnaLetra;
        this.filaNumero = filaNumero;
    }

    @Override
    public String toString(){
        return this.columnaLetra + this.filaNumero;
    }
    public String getColumnaLetra() {
        return columnaLetra;
    }

    public void setColumnaLetra(String columnaLetra) {
        this.columnaLetra = columnaLetra;
    }

    public int getFilaNumero() {
        return filaNumero;
    }

    public void setFilaNumero(int filaNumero) {
        this.filaNumero = filaNumero;
    }

    public String cambiarALetra(int numX){
        switch (numX){
            case 0: return "A";
            case 1: return "B";
            case 2: return "C";
            case 3: return "D";
            case 4: return "E";
            case 5: return "F";
            case 6: return "G";
            case 7: return "H";
            default: return null;
        }
    }
    public int columnaEnNumero(){
        switch (columnaLetra.toUpperCase()){
            case "A": return 0;
            case "B": return 1;
            case "C": return 2;
            case "D": return 3;
            case "E": return 4;
            case "F": return 5;
            case "G": return 6;
            case "H": return 7;
            default: return Integer.parseInt(null);
        }
    }

    public boolean igualPosicion(Posicion p){
        return p.getFilaNumero() == this.getFilaNumero() && p.getColumnaLetra().equals(this.getColumnaLetra());
    }
}
