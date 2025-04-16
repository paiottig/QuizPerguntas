package com.quizperguntas.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Jogador implements Comparable<Jogador> {
    private final String nome;
    private final int pontuacao;
    private final String tema;
    private final String data;
    
    public Jogador(String nome, int pontuacao, String tema){
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.tema = tema;
        this.data = new SimpleDateFormat("dd/mm/yyyy HH:mm").format(new Date());
    }
    
    public Jogador(String nome, int pontuacao, String tema, String data){
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.tema = tema;
        this.data = data;
    }
    
    @Override
    public int compareTo(Jogador outro){
        return Integer.compare(outro.pontuacao, this.pontuacao);
    }
    
    //getters
    public String getNome(){ return nome; }
    public int getPontuacao(){ return pontuacao; }
    public String getTema(){ return tema; }
    public String getData(){ return data; }
    
    @Override
    public String toString(){
        return nome.trim() + ";" + pontuacao + ";" + tema.trim() + ";" + data.trim();
    }
}
