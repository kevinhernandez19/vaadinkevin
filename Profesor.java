package com.jetbrains;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 6/07/2017.
 */
public class Profesor {
    private String Nombre;
    private String Especialidad;
    private List<Estudiante> Estudiantes;

    public Profesor() {
        Estudiantes = new ArrayList<>();
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(String especialidad) {
        Especialidad = especialidad;
    }

    public List<Estudiante> getEstudiantes() {
        return Estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        Estudiantes = estudiantes;
    }

    public void addEstudiante(Estudiante e) {
        Estudiantes.add(e);
    }

    public Estudiante estudianteMasDestacado(){
        float promedio = 0;

        //declaracion de metodo

        Estudiante estudiante = new Estudiante();

        for (Estudiante e:Estudiantes
             ) {
            if(promedio<e.promedioNotas()){
                promedio=e.promedioNotas();
                estudiante=e;
            }
        }

        return estudiante;
    }
}
