package com.quizperguntas.servicos;

import com.quizperguntas.modelo.Pergunta;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PerguntaService {
    private static final LinkedHashMap<String, String> TEMAS = new LinkedHashMap<>(){{
        put("1","Biologia");
        put("2","Geografia");
        put("3","Historia");
        put("4","Portugues");
        put("5","Quimica");
    }};
    
    public List<Pergunta> carregarPerguntas(String temaId){
        String nomeTema = TEMAS.get(temaId);
        if (nomeTema == null) {
            return Collections.emptyList();
        }
        
        String arquivo = "perguntas/" + nomeTema.toLowerCase() + ".txt";
        List<Pergunta> perguntas = new ArrayList<>();
        
        try (InputStream is = getClass().getResourceAsStream("/" + arquivo);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            
            String linha;
            String textoPergunta = null;
            List<String> alternativas = null;
            int respostaCorreta = -1;
            int dificuldade = 1;
            
            while((linha = br.readLine()) != null){
                if(linha.startsWith("P:")){
                    textoPergunta = linha.substring(2).trim();
                    alternativas = new ArrayList<>();
                } else if(linha.startsWith("A:")){
                    alternativas.add(linha.substring(2).trim());
                } else if(linha.startsWith("R:")){
                    respostaCorreta = Integer.parseInt(linha.substring(2).trim());
                } else if(linha.startsWith("D:")){
                    dificuldade = Integer.parseInt(linha.substring(2).trim());
                    perguntas.add(new Pergunta(textoPergunta, alternativas, respostaCorreta, dificuldade, nomeTema));
                }
            }
        } catch (Exception e){
            System.err.println("\nErro ao carregar perguntas: " + e.getMessage());
        }
        
        Collections.shuffle(perguntas);
        return perguntas;
    }
    
    public String getNomeTema(String temaId){
        return TEMAS.getOrDefault(temaId, "Desconhecido");
    }
    
    public Map<String, String> getTemasDisponiveis(){
        return TEMAS;
    }
}
