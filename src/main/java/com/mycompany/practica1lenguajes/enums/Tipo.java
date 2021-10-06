
package com.mycompany.practica1lenguajes.enums;

//Clase Enum
public enum Tipo {   
AGRUPACION("AGRUPACION: "),
IDENTIFICADOR ("ID: "),
PUNTUACION ("PUNTUACION:"),
OPERADOR("OPERADOR:"),
NUMERO("NUMERO: "),
DECIMAL("DECIMAL: ");

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

