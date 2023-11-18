/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 *
 * @author Ger
 */
public class datos {

    private int x;
    private int y;
    private double semilla;
    private int celulas[][];
    private int avance;
    private String born;
    private String stayAlive;

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) { 
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the semilla
     */
    public double getSemilla() {
        return semilla;
    }

    /**
     * @param semilla the semilla to set
     */
    public void setSemilla(double semilla) {
        this.semilla = semilla;
    }

    /**
     * @return the celulas
     */
    public int[][] getCelulas() {
        return celulas;
    }

    /**
     * @param celulas the celulas to set
     */
    public void setCelulas(int[][] celulas) {
        this.celulas = celulas;
    }

    /**
     * @variable int fila
     * @variable int columna
     * @variable_global int[][] celulas
     * @variable_global double semilla
     * @metodo que calcula la primagenea generacion de manera aleatoria
     */
    public void primigenea_Generacion() {
        int fila, columna;
        this.celulas = new int[this.x][this.y];
        for (fila = 0; fila < this.x; fila++) {
            for (columna = 0; columna < this.y; columna++) {
                /*Marco exterior*/
                if ((fila == 0) || (columna == 0) || (x == fila + 1) || (y == columna + 1)) {
                    this.celulas[fila][columna] = 0;
                } else if (Math.random() < this.semilla) {
                    this.celulas[fila][columna] = 1;
                } else {
                    this.celulas[fila][columna] = 0;
                }
            }
        }
    }

    /**
     * @param origen int[][]
     * @param destino int[][]
     * @metodo que copia una matriz origen a otra matriz de destino
     */
    public void copiar_Matriz_De_Origen_A_Destino(int[][] origen, int[][] destino) {
        for (int fila = 1; fila < x - 1; fila++) {
            System.arraycopy(origen[fila], 1, destino[fila], 1, y - 1 - 1);
        }
    }

    /**
     * @variable int fila
     * @variable int columna
     * @variable_global int[][] celulas
     * @variable_global int[][] celulas_clon
     * @metodo copiar_Matriz_De_Origen_A_Destino(int[][] origen, int[][]
     * destino)
     * @metodo suma_De_Celdas_Alrededor(int fila,int columna)
     * @metodo que calcula la evolucion de las generaciones con las reglas de la
     * vida
     */
    public void evolucion_De_Generaciones() {
        int fila, columna, hacer_X_Generaciones;
        int[][] celulas_clon = new int[x][y];

        for (hacer_X_Generaciones = 0; this.avance > hacer_X_Generaciones; hacer_X_Generaciones++) {
            copiar_Matriz_De_Origen_A_Destino(this.celulas, celulas_clon);
            for (fila = 1; fila < x - 1; fila++) {
                for (columna = 1; columna < y - 1; columna++) {

                    if (celulas_Para_Nacimiento(fila, columna) == true) {
                        celulas_clon[fila][columna] = 1;
                    }
                    if ((celulas[fila][columna] == 1) && (suma_De_Celdas_Alrededor(fila, columna) <= 2)) {
                        celulas_clon[fila][columna] = 0;
                    }
                    if ((celulas[fila][columna] == 1) && (suma_De_Celdas_Alrededor(fila, columna) >= 4)) {
                        celulas_clon[fila][columna] = 0;
                    }
                    if ((celulas[fila][columna] == 1) && (celulas_Para_Mantener_Vivas(fila, columna)) == true) {
                        celulas_clon[fila][columna] = 1;
                    }

                }
            }
            copiar_Matriz_De_Origen_A_Destino(celulas_clon, this.celulas);
        }

    }

    /**
     * @return the avance
     */
    public int getAvance() {
        return avance;
    }

    /**
     * @param avance the avance to set
     */
    public void setAvance(int avance) {
        this.avance = avance;
    }

    /**
     * @variable String numero_Mantenimiento
     * @variable primer_numero
     * @param fila
     * @param columna
     * @return boolean seMantiene
     * @metodo Recoge la cadena y recoge cada numero para saber si coincide con
     * el sumatorio_De_Celdas_Alrededor
     */
    public boolean celulas_Para_Mantener_Vivas(int fila, int columna) {
        int primer_numero;
        boolean seMantiene = false;
        for (int contador = 0; contador < this.stayAlive.length(); contador++) {
            String numero_Mantenimiento = this.stayAlive.substring(contador, contador + 1);
            primer_numero = Integer.parseInt(numero_Mantenimiento);
            if (suma_De_Celdas_Alrededor(fila, columna) == primer_numero) {
                seMantiene = true;
            }
        }
        return seMantiene;
    }

    /**
     * @variable String numero_Born
     * @variable int primer_numero
     * @param fila
     * @param columna
     * @return boolean nace
     * @metodo Recoge la cadena y recoge cada numero para saber si coincide con
     * el sumatorio_De_Celdas_Alrededor
     */
    public boolean celulas_Para_Nacimiento(int fila, int columna) {
        int primer_numero;
        boolean nace = false;
        for (int contador = 0; contador < this.born.length(); contador++) {
            String numero_Born = this.born.substring(contador, contador + 1);
            primer_numero = Integer.parseInt(numero_Born);
            if (suma_De_Celdas_Alrededor(fila, columna) == primer_numero) {
                nace = true;
            }
        }
        return nace;
    }

    /**
     * @param fila
     * @param columna
     * @variable_global int[][] celulas
     * @return int sumatorio_Celdas_Vivas
     * @metodo suma de todas celdas vivas de alrededor de una posicion
     */
    public int suma_De_Celdas_Alrededor(int fila, int columna) {
        int sumatorio_Celdas_Vivas = celulas[fila - 1][columna - 1] + celulas[fila - 1][columna] + celulas[fila - 1][columna + 1] + celulas[fila][columna - 1] + celulas[fila][columna + 1] + celulas[fila + 1][columna - 1] + celulas[fila + 1][columna] + celulas[fila + 1][columna + 1];
        return sumatorio_Celdas_Vivas;
    }

    /**
     * @param fila
     * @param columna
     * @return celula[][]
     * @metodo para saber el estado de la celula, viva o muerta
     */
    public int saber_Estado(int fila, int columna) {
        return this.celulas[fila][columna];
    }

    /**
     * @return the born
     */
    public String getBorn() {
        return born;
    }

    /**
     * @param born the born to set
     */
    public void setBorn(String born) {
        this.born = born;
    }

    /**
     * @return the stayAlive
     */
    public String getStayAlive() {
        return stayAlive;
    }

    /**
     * @param stayAlive the stayAlive to set
     */
    public void setStayAlive(String stayAlive) {
        this.stayAlive = stayAlive;
    }
}
