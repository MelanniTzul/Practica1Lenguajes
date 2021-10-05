
package com.mycompany.practica1lenguajes.enums;

//Clase Enum
public enum Tipo {
IDENTIFICADOR ("ID: "),
NUMERO("NUMERO: "),
DECIMAL("DECIMAL: "),
PUNTUACION ("PUNTUACION:"),
OPERADOR("OPERADOR:"),
AGRUPACION("AGRUPACION: ");

     String tipo;// Declaramos una variable para poder acceder a los enums
    //Constructor de la variable string tipo
    private Tipo(String tipo){
        this.tipo = tipo;
    }
    
    //Funcion de la variable tipo
    public String getTipo(){
        return this.tipo;
    }
    
}

