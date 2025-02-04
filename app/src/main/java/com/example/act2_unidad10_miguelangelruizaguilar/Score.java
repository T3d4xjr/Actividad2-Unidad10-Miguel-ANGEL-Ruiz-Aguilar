package com.example.act2_unidad10_miguelangelruizaguilar;

public class Score {
    private int id;
    private String nombre;
    private int puntuacion;

    public Score(int id, String nombre, int puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
