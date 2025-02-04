package com.example.act2_unidad10_miguelangelruizaguilar;

public class Usuario {
    private int id;
    private String nombre;
    private int puntuacion;

    public Usuario(int id, String nombre, int puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.puntuacion = puntuacion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }
}

