
package com.mycompany.practica1lenguajes.enums;

public enum Puntuacion {
    PUNTO ('.'),
    COMA (','),
    PUNTOYCOMA(';'),
    DOSPUNTOS(':');
    
    char Puntuacion;

    private Puntuacion(char Puntuacion) {
        this.Puntuacion = Puntuacion;
    }

    public static Puntuacion getPUNTO() {
        return PUNTO;
    }

    public static Puntuacion getCOMA() {
        return COMA;
    }

    public static Puntuacion getPUNTOYCOMA() {
        return PUNTOYCOMA;
    }

    public static Puntuacion getDOSPUNTOS() {
        return DOSPUNTOS;
    }

    public char getPuntuacion() {
        return Puntuacion;
    }
    

    
    
    
    
    
    
}
