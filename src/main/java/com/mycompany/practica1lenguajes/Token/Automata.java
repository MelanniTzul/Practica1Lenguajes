package com.mycompany.practica1lenguajes.Token;

import com.mycompany.practica1lenguajes.enums.Agrupacion;
import com.mycompany.practica1lenguajes.enums.Operador;
import com.mycompany.practica1lenguajes.enums.Puntuacion;
import com.mycompany.practica1lenguajes.enums.Tipo;
import com.mycompany.practica1lenguajes.ventanaGrafica.VentanaPrincipal;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Automata {

    private int[][] matrizAutomata;
    private final int EstadInicial = 0;
    private int EstadoActual = 0;
    private final int[] EstadosAceptacion = {1, 2, 3, 4, 5, 7};

    private String[] LineasTexto;
    private String[] PalabrasTexto;
    //Variables cuando se encuentre un erro o token para saber enque fila va
    private int fila;
    private int columna;
    private JTextArea AreaCargaArchivo;
    private ArrayList<Error> errores = new ArrayList<>();//Arraylist para los errores
    private ArrayList<Token> Tokens = new ArrayList<>();//Arraylist para los token
    private final Tipo InstanciasTipo[] = Tipo.values();//Arraylist de enums para acceder a los diferentes tipos de token
    private ArrayList<Lexema> lexemas = new ArrayList<>();// Arraylist de lexemas

    private int Letras = 1;
    private int Numero = 2;
    private int SignoOperacion = 3;
    private int SignoPuntuacion = 4;
    private int SignoAgrupacion = 5;
    private int Punto = 6;

    //Constructor de la clase Automata
    public Automata(JTextArea TextoEntrada) {
        this.AreaCargaArchivo = TextoEntrada;
        InicializarMatriz();
    }

    //Metodo de mi matriz inicializada
    private void InicializarMatriz() {
        //Inicializar matriz y dartle tamaño   
        int filas = 8;
        int columnas = 6;
        //Inicializar Matriz 
        matrizAutomata = new int[filas][columnas];
        matrizAutomata[0][0] = 2;
        matrizAutomata[0][1] = 5;
        matrizAutomata[0][2] = 1;
        matrizAutomata[0][3] = 4;
        matrizAutomata[0][4] = 3;
        matrizAutomata[0][5] = 4;

        matrizAutomata[1][0] = -1;
        matrizAutomata[1][1] = -1;
        matrizAutomata[1][2] = -1;
        matrizAutomata[1][3] = -1;
        matrizAutomata[1][4] = -1;
        matrizAutomata[1][5] = -1;

        matrizAutomata[2][0] = 2;
        matrizAutomata[2][1] = 2;
        matrizAutomata[2][2] = -1;
        matrizAutomata[2][3] = -1;
        matrizAutomata[2][4] = -1;
        matrizAutomata[2][5] = -1;

        matrizAutomata[3][0] = -1;
        matrizAutomata[3][1] = -1;
        matrizAutomata[3][2] = -1;
        matrizAutomata[3][3] = -1;
        matrizAutomata[3][4] = -1;
        matrizAutomata[3][5] = -1;

        matrizAutomata[4][0] = -1;
        matrizAutomata[4][1] = -1;
        matrizAutomata[4][2] = -1;
        matrizAutomata[4][3] = -1;
        matrizAutomata[4][4] = -1;
        matrizAutomata[4][5] = -1;

        matrizAutomata[5][0] = -1;
        matrizAutomata[5][1] = 5;
        matrizAutomata[5][2] = -1;
        matrizAutomata[5][3] = -1;
        matrizAutomata[5][4] = -1;
        matrizAutomata[5][5] = 6;

        matrizAutomata[6][0] = -1;
        matrizAutomata[6][1] = 7;
        matrizAutomata[6][2] = -1;
        matrizAutomata[6][3] = -1;
        matrizAutomata[6][4] = -1;
        matrizAutomata[6][5] = -1;

        matrizAutomata[7][0] = -1;
        matrizAutomata[7][1] = 7;
        matrizAutomata[7][2] = -1;
        matrizAutomata[7][3] = -1;
        matrizAutomata[7][4] = -1;
        matrizAutomata[7][5] = -1;

    }

    //Metodo para descomponer las lineas en palabras
    public void DescomponerLineasPalabras() {
        LineasTexto = AreaCargaArchivo.getText().split("\n");//Cada posicion del arreglo va ser una pasicion de mi texto de entrada
        for (int i = 0; i < LineasTexto.length; i++) {
            PalabrasTexto = LineasTexto[i].split(" ");

            for (int j = 0; j < PalabrasTexto.length; j++) {

                AnalizarPalabra(PalabrasTexto[j]);
                columna++;
            }
            fila++;
        }
    }

    //Función que me verifica que tipo de operador es
    private boolean IdentifacarOperador(char Op) {
        Operador[] InstanciasOperador = Operador.values();
        for (int i = 0; i < InstanciasOperador.length; i++) {
            if (InstanciasOperador[i].getOperador() == Op) {
                return true;
            }
        }
        return false;
    }

    //Función que me identifica que tipo de signo de  puntuacion es
    private boolean IdentifacarPuntuacion(char Pun) {
        Puntuacion[] InstanciaPuntuacion = Puntuacion.values();
        for (int i = 0; i < InstanciaPuntuacion.length; i++) {
            if (InstanciaPuntuacion[i].getPuntuacion() == Pun) {
                return true;

            }
        }
        return false;
    }

    //Función que me identifica que tipo de signo de agrupacion es    
    private boolean IdentifacarAgrupacion(char Agrup) {
        Agrupacion[] InstanciaAgrupacion = Agrupacion.values();
        for (int i = 0; i < InstanciaAgrupacion.length; i++) {
            if (InstanciaAgrupacion[i].getAgrupacion() == Agrup) {
                return true;
            }
        }
        return false;
    }

    //Metodo para analizar cada palabra que tenemos en nuestro tex area
    private void AnalizarPalabra(String PalabraTexto) {
        String Cadena = "";//Para al macenar palabra
        char[] CaracteresDePalabra = PalabraTexto.toCharArray();//Convertir una palabra a una cadena de char

        for (int i = 0; i < CaracteresDePalabra.length; i++) {
            columna++;
            //Al entrar a esta condicion me analiza si es una palabra entonces entra
            if (Character.isAlphabetic(CaracteresDePalabra[i])) {
                VentanaPrincipal.TransicionesjTextArea.append("Me moví del estado " + EstadoActual + " al estado " + matrizAutomata[EstadoActual][0] + " con " + CaracteresDePalabra[i] + "\n");//
                //Asignamos las pasiones de la matriz a la variable EstadoActual y como empieza desde 0 
                //al entrar en el if se va ir recorriendo y nos dara la posicion del estado de la matriz
                EstadoActual = matrizAutomata[EstadoActual][0];
            } else {
                //if que me recorre la matriz para analizar si es un digito
                if (Character.isDigit(CaracteresDePalabra[i])) {
                    VentanaPrincipal.TransicionesjTextArea.append("Me moví del estado " + EstadoActual + " al estado " + matrizAutomata[EstadoActual][1] + " con " + CaracteresDePalabra[i] + "\n");
                    EstadoActual = matrizAutomata[EstadoActual][1];
                } else {
                    //if que me recorre la matriz para analizar si es un Operador
                    if (IdentifacarOperador(CaracteresDePalabra[i])) {
                        VentanaPrincipal.TransicionesjTextArea.append("Me moví del estado " + EstadoActual + " al estado " + matrizAutomata[EstadoActual][3] + " con " + CaracteresDePalabra[i] + "\n");
                        EstadoActual = matrizAutomata[EstadoActual][3];
                    } else {
                        //if que me recorre la matriz para analizar si es un signo de puntuación
                        if (IdentifacarPuntuacion(CaracteresDePalabra[i]) && EstadoActual == 0) {
                            VentanaPrincipal.TransicionesjTextArea.append("Me moví del estado " + EstadoActual + " al estado " + matrizAutomata[EstadoActual][4] + " con " + CaracteresDePalabra[i] + "\n");
                            EstadoActual = matrizAutomata[EstadoActual][4];
                        } else {
                            //if que me recorre la matriz para analizar si es un signo de agrupación
                            if (IdentifacarAgrupacion(CaracteresDePalabra[i])) {
                                VentanaPrincipal.TransicionesjTextArea.append("Me moví del estado " + EstadoActual + " al estado " + matrizAutomata[EstadoActual][2] + " con " + CaracteresDePalabra[i] + "\n");
                                EstadoActual = matrizAutomata[EstadoActual][2];
                            } else {
                                if (CaracteresDePalabra[i] == '.' && EstadoActual == 5) {
                                    VentanaPrincipal.TransicionesjTextArea.append("Me moví del estado " + EstadoActual + " al estado " + matrizAutomata[EstadoActual][5] + " con " + CaracteresDePalabra[i] + "\n");
                                    EstadoActual = matrizAutomata[EstadoActual][5];

                                } else {
                                    EstadoActual = -1;
                                }

                            }
                        }

                    }

                }

            }

            //Analizandor errores
            Cadena += CaracteresDePalabra[i];
            if (EstadoActual == -1) {
                errores.add(new Error(Cadena, fila, columna));
                EstadoActual = 0;
                Cadena = "";

            }
        }

        //Analizador de tokens
        for (int i = 0; i < EstadosAceptacion.length; i++) {

            if (EstadosAceptacion[i] == EstadoActual) {
                Tokens.add(new Token(Cadena, InstanciasTipo[i].getTipo(), fila, columna));
                EstadoActual = 0;
                break;
            } else {
                if (i == EstadosAceptacion.length - 1) {
                    errores.add(new Error(Cadena, fila, columna));
                }

            }
        }
    }

    //Metodo que me permite saber que catidadd de palabras tengo de cada token
    public void ContarLexema() {
        lexemas.add(new Lexema(Tokens.get(0).getTexto(), Tokens.get(0).getTipo()));
        for (int i = 0; i < Tokens.size(); i++) {
            for (int j = 0; j < lexemas.size(); j++) {
                if (Tokens.get(i).getTexto().equals(lexemas.get(j).getLexema())) {
                    lexemas.get(j).setCantidad(lexemas.get(j).getCantidad() + 1);//Las suma
                    break;
                } else {
                    if (lexemas.size() - 1 == j) {
                        lexemas.add(new Lexema(Tokens.get(i).getTexto(), Tokens.get(i).getTipo()));                   
                }
            }
        }
    }

}

//Metodo que me permite imprimir los reportes en las tablas
public void ImprimirReportes() {
        if (errores.isEmpty()) {//Si mi array list esta vacio, entonces no tengo ningun error 
            DefaultTableModel tabla = new DefaultTableModel();
            VentanaPrincipal.ReporteDeTokensjTable2.setModel(tabla);
            //Dandole titulo a las columnas
            tabla.addColumn("Nombre Token");
            tabla.addColumn("Lexema");
            tabla.addColumn("Fila");
            tabla.addColumn("Columna");

            for (int i = 0; i < Tokens.size(); i++) {
                //Llenando tabla
                tabla.addRow(new Object[]{Tokens.get(i).getTipo(), Tokens.get(i).getTexto(), Tokens.get(i).getFila(), Tokens.get(i).getColumna()});
            }
            //Llamar al almeto que me permite contar los lexemas para poder llenar la tabla
            ContarLexema();
            //Llenar tabla de lexemas 
            DefaultTableModel tablaLexema = new DefaultTableModel();
            VentanaPrincipal.RecuentoDeLexemasjTable4.setModel(tablaLexema);

            //Dandole titulos a las columnas
            tablaLexema.addColumn("Lexema");
            tablaLexema.addColumn("Token");
            tablaLexema.addColumn("Cantidad");
            //Uso un for ach, utilizando mi clase Lexema permite crear una variable que a travez de ella accedo a sus atributos sin utilizar un iterador
            for (Lexema i : lexemas) {
                tablaLexema.addRow(new Object[]{i.getLexema(), i.getToken(), i.getCantidad()});
            }
        } else {
            DefaultTableModel tabla = new DefaultTableModel();
            VentanaPrincipal.ReporteDeErroresjTable1.setModel(tabla);
            //Dandole titulo a las columnas
            tabla.addColumn("Cadena de error");
            tabla.addColumn("Fila");
            tabla.addColumn("Columna");
            for (int i = 0; i < errores.size(); i++) {
                //Llenando tabla
                tabla.addRow(new Object[]{errores.get(i).getTexto(), errores.get(i).getFila(), errores.get(i).getColumna()});
            }

        }

    }

}
