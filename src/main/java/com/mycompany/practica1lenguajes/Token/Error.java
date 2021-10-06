
package com.mycompany.practica1lenguajes.Token;

/**
 *
 * @author Melanni Tzul
 */

//Clase Error
public class Error {
    private String texto;
    private int fila;
    private int columna;
    
    //Constructor de la clase Error
    public Error(String texto, int fila, int columna){
    
        this.texto=texto;
        this.fila=fila;
        this.columna=columna;
    
    
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    
}
