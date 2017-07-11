package com.jetbrains;

/**
 * Created by kevin on 6/07/2017.
 */
public class Asignatura {

    private String Nombre;
    private int Nota;
    private int cantEvaluaciones;

    public Asignatura(String nombre, int nota, int cantEvaluaciones) {
        Nombre = nombre;
        Nota = nota;
        this.cantEvaluaciones = cantEvaluaciones;
    }

    public String getNombre() { return Nombre; }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getNota() {
        return Nota;
    }

    public void setNota(int nota) {
        Nota = nota;
    }

    public int getCantEvaluaciones() {
        return cantEvaluaciones;
    }

    public void setCantEvaluaciones(int cantEvaluaciones) {
        this.cantEvaluaciones = cantEvaluaciones;
    }
}
