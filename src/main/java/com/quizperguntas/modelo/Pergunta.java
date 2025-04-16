package com.quizperguntas.modelo;

import java.util.List;

public class Pergunta{
    private String texto;
    private List<String> alternativas;
    private int respostaCorreta;
    private int dificuldade;
    private String areaConhecimento;
    
    public Pergunta(String texto, List<String> alternativas, int respostaCorreta, int dificuldade, String areaConhecimento){
        this.texto = texto;
        this.alternativas = alternativas;
        this.respostaCorreta = respostaCorreta;
        this.dificuldade = dificuldade;
        this.areaConhecimento = areaConhecimento;
    }
    
    //Getters
    public String getTexto(){ return texto; }
    public List<String> getAlternativas(){ return alternativas; }
    public int getRespostaCorreta(){ return respostaCorreta; }
    public int getDificuldade(){ return dificuldade; }
    public String getAreaConhecimento(){ return areaConhecimento; }
}
