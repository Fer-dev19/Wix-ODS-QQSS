package BusinessLogic;

import java.io.Serializable;

public class Ahorcado implements Serializable {
    private String enunciado;
    private int timer;
    private String dificultad;
    private String respuesta;
    private Hanged hanged;

    public Ahorcado(){}

    public void setEnunciado(String enunciado){
        this.enunciado = enunciado;
    }
    public String getEnunciado(){
        return this.enunciado;
    }
    public void setTimer(int timer){
        this.timer = timer;
    }
    public int getTimer(){
        return this.timer;
    }
    public void setDificultad(String dificultad){
        this.dificultad = dificultad;
    }
    public String getDificultad(){
        return this.dificultad;
    }
    public void setRespuesta(String respuesta){
        this.respuesta = respuesta;
    }
    public String getRespuesta(){
        return this.respuesta;
    }

    public void setHanged(Hanged hanged){
        this.hanged = hanged;
    }
    public Hanged getHanged(){ return this.hanged; }
}
