

package com.mycompany.practica1lenguajes.enums;


public enum Agrupacion {
    
    PARENTESISCERRADO ('('),
    PARENTESISABIERTO (')'),
    CORCHETESABIERTO('['),
    CORCHETESCERRADO(']'),
    LLAVESABIERTA('{'),
    LLAVESCERRADA('}');
    
    char Agrupacion;

    private Agrupacion(char Agrupacion) {
        this.Agrupacion = Agrupacion;
    }

    public static Agrupacion getPARENTESISCERRADO() {
        return PARENTESISCERRADO;
    }

    public static Agrupacion getPARENTESISABIERTO() {
        return PARENTESISABIERTO;
    }

    public static Agrupacion getCORCHETESABIERTO() {
        return CORCHETESABIERTO;
    }

    public static Agrupacion getCORCHETESCERRADO() {
        return CORCHETESCERRADO;
    }

    public static Agrupacion getLLAVESABIERTA() {
        return LLAVESABIERTA;
    }

    public static Agrupacion getLLAVESCERRADA() {
        return LLAVESCERRADA;
    }

    public char getAgrupacion() {
        return Agrupacion;
    }
    
    
}
