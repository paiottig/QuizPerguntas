package com.quizperguntas;

import com.quizperguntas.modelo.Jogador;
import com.quizperguntas.modelo.Pergunta;
import com.quizperguntas.servicos.PerguntaService;
import com.quizperguntas.servicos.RankingService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class QuizPerguntas {
    private static final PerguntaService perguntaService = new PerguntaService();
    private static final RankingService rankingService = new RankingService();
    private static final Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            exibirMenuPrincipal();
        } finally {
            teclado.close();
        }
    }
    
    public static void exibirMenuPrincipal(){
        while(true){
            System.out.println("QUIZ DE PERGUNTAS - TEMATICO");
            System.out.println("1. Iniciar Quiz");
            System.out.println("2. Ver Ranking");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opcao: ");
            
            int opcao = lerInteiro(1, 3);
            
            switch(opcao){
                case 1 -> iniciarQuiz();
                case 2 -> rankingService.exibirRanking();
                case 3 -> {
                    System.out.println("Saindo do Quiz... Ate breve!");
                    System.exit(0);
                }
            }
        }
    }
    
    private static void iniciarQuiz(){
        System.out.println("\n---ESCOLHA UM TEMA---");
        perguntaService.getTemasDisponiveis().forEach((key, value)-> System.out.println(key + ". " + value));
        System.out.print("Digite o numero do tema: ");
        String temaId = String.valueOf(lerInteiro(1, 5));
        List<Pergunta> perguntas = perguntaService.carregarPerguntas(temaId);
        
        if (perguntas.isEmpty()) {
            System.out.println("\nNenhuma pergunta encontrada para esse tema.");
            return;
        }
        
        System.out.print("\nDigite seu nome: ");
        String nomeJogador = teclado.nextLine();
        
        System.out.printf("\nQuantas perguntas deseja responder? (1-%d)", perguntas.size());
        int totalPerguntas = lerInteiro(1, perguntas.size());
        
        int pontuacaoTotal = executarQuiz(perguntas.subList(0, totalPerguntas));
        
        rankingService.salvarPontuacao(new Jogador(
                nomeJogador,
                pontuacaoTotal,
                perguntaService.getNomeTema(temaId))
        );
        
        System.out.println("\nSeu desempenho: ");
        System.out.println("Pontuacao: " + pontuacaoTotal + "pontos");
        System.out.println("Tema: " + perguntaService.getNomeTema(temaId));
        
        rankingService.exibirRanking();
    }
    
    private static int executarQuiz(List<Pergunta> perguntas){
        int pontuacao = 0;
        
        for (int i = 0; i < perguntas.size(); i++) {
            Pergunta pergunta = perguntas.get(i);
            int pontos = pergunta.getDificuldade() * 10;
            
            System.out.printf("Pergunta %d/%d (%s - %d pts)\n", i + 1, perguntas.size(), getDificuldadeTexto(pergunta.getDificuldade()), pontos);
            System.out.println(pergunta.getTexto());
            
            for (int j = 0; j < pergunta.getAlternativas().size(); j++) {
                System.out.printf("%d) %s\n", j + 1, pergunta.getAlternativas().get(j));
            }
            
            System.out.print("Sua resposta: ");
            try {
                int resposta = lerInteiro(1, pergunta.getAlternativas().size());
                
                if (resposta == pergunta.getRespostaCorreta()) {
                    System.out.printf("\nCorreto!! +%d pontos\n", pontos);
                    pontuacao += pontos;
                } else {
                    System.out.printf("\nErrado! A resposta correta é: %d\n", pergunta.getRespostaCorreta());
                }
            } catch (InputMismatchException e){
                System.err.printf("\nEntrada invalida, digite um numero entre 1 e %02d", pergunta.getAlternativas().size());
            }
        }
        return pontuacao;
    }
    
    private static int lerInteiro(int min, int max){
        while(true){
            try{
                int valor = Integer.parseInt(teclado.nextLine());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.printf("\nDigite um numero entre %d e %d: ", min, max);
            } catch (NumberFormatException e){
                System.out.print("\nEntrada invalida, digite um numero: ");
            }
        }   
    }
    
    private static String getDificuldadeTexto(int nivel){
        return switch (nivel){
            case 1 -> "Fácil";
            case 2 -> "Médio";
            case 3 -> "Difícil";
            default -> "N/A";
        };
    }
}
