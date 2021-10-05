
package com.mycompany.practica1lenguajes.enums;


public enum Operador {
    SUMA ('+'),
    RESTA ('-'),
    MULTIPLICACION('*'),
    DIAGONAL('/'),
    PORCENTAJE('%');
    
    char Operador;

    private Operador(char Operador) {
        this.Operador = Operador;
    }

    public static Operador getSUMA() {
        return SUMA;
    }

    public static Operador getRESTA() {
        return RESTA;
    }

    public static Operador getMULTIPLICACION() {
        return MULTIPLICACION;
    }

    public static Operador getDIAGONAL() {
        return DIAGONAL;
    }

    public static Operador getPORCENTAJE() {
        return PORCENTAJE;
    }

    public char getOperador() {
        return Operador;
    }
       
}
