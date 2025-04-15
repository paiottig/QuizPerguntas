package com.quizperguntas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizPerguntas {
    public static void main(String[] args) {
        List<Pergunta> perguntas = carregarPerguntas();
        if(perguntas.isEmpty()){
            System.out.println("Nenhuma perunta carregada, verifique o arquivo");
            return;
        }
        
        Scanner teclado = new Scanner(System.in);
        int pontuacao = 0;
        
        System.out.println("=== BEM VINDO AO QUIZ ===");
        System.out.println("Responda as perguntas:\n");
        
        for (int i = 0; i < perguntas.size(); i++) {
            Pergunta pergunta = perguntas.get(i);
            System.out.println((i + 1) + ". " + pergunta.getTexto()) ;
            
            for (int j = 0; j < pergunta.getAlternativas().size(); j++) {
                System.out.println((j + 1) + ") " + pergunta.getAlternativas().get(j));
            }
            
            System.out.print("Sua resposta (1-" + pergunta.getAlternativas().size() + "): ");
            int respostaUsuario = teclado.nextInt();
            
            if (respostaUsuario == pergunta.getRespostaCorreta()) {
                System.out.println("Correto\n");
                pontuacao++ ;
            } else {
                System.out.println("Errado! A resposta correta era: " + pergunta.getRespostaCorreta() + "\n");
            }
        }
        
        System.out.println("Quiz finalizado! Sua pontuação foi: " + pontuacao);
    }

    private static List<Pergunta> carregarPerguntas() {
        List<Pergunta> perguntas = new ArrayList();
        
        try (InputStream inputStream = QuizPerguntas.class.getResourceAsStream("/perguntas.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
            
            String linha;
            String textoPergunta = null;
            List<String> alternativas = null;
            int respostaCorreta = -1;
            
            while((linha = br.readLine()) != null){
                if(linha.startsWith("P:")){
                    textoPergunta = linha.substring(2).trim();
                    alternativas = new ArrayList<>();
                } else if (linha.startsWith("A:")){
                    alternativas.add(linha.substring(2).trim());
                } else if (linha.startsWith("R:")){
                    respostaCorreta = Integer.parseInt(linha.substring(2).trim());
                    perguntas.add(new Pergunta(textoPergunta, alternativas, respostaCorreta));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NullPointerException e){
            System.out.println("Arquivo 'perguntas.txt' não encontrado em 'resources/'.");
        }
        
        return perguntas;
    }
}
