package com.quizperguntas;

import java.util.List;

public class Pergunta{
    private String texto;
    private List<String> alternativas;
    private int respostaCorreta;
    
    public Pergunta(String texto, List<String> alternativas, int respostaCorreta){
        this.texto = texto;
        this.alternativas = alternativas;
        this.respostaCorreta = respostaCorreta;
    }
    
    public String getTexto(){
        return texto;
    }
    
    public List<String> getAlternativas(){
        return alternativas;
    }
    
    public int getRespostaCorreta(){
        return respostaCorreta;
    }
}
