package com.example.odswix;

import java.io.Serializable;

public class Director implements Serializable {

    public Director(){}

    public void construirPreguntaFacil(Builder builder){
        builder.reset();
        builder.buildDificultad("Facil");
        builder.buildEnunciado();
        builder.buildTimer();
        builder.buildPuntosAcum();
        builder.buildRespuestas();
    }
    public void construirPreguntaMedio(Builder builder){
        builder.reset();
        builder.buildDificultad("Medio");
        builder.buildEnunciado();
        builder.buildTimer();
        builder.buildPuntosAcum();
        builder.buildRespuestas();
    }
    public void construirPreguntaDificil(Builder builder){
        builder.reset();
        builder.buildDificultad("Dificil");
        builder.buildEnunciado();
        builder.buildTimer();
        builder.buildPuntosAcum();
        builder.buildRespuestas();
    }
}
