
package com.mycompany.practica1lenguajes.Token;

//Clase Lexema
public class Lexema {
    private String Lexema;
    private String Token;
    private int Cantidad;

    public Lexema(String Lexema, String Token) {
        this.Lexema = Lexema;
        this.Token = Token;
        this.Cantidad=0;//Inicializar la cantidad
    }

    public String getLexema() {
        return Lexema;
    }

    public void setLexema(String Lexema) {
        this.Lexema = Lexema;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }
    
    
    
}
