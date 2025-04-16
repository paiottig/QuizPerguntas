package com.quizperguntas.servicos;

import com.quizperguntas.modelo.Jogador;
import java.io.*;
import java.util.*;

public class RankingService {
    private static final String ARQUIVO_RANKING = "ranking.txt";
    
    public void salvarPontuacao(Jogador jogador){
        criarArquivoRanking();
        
        try(PrintWriter out = new PrintWriter(new FileWriter(ARQUIVO_RANKING, true))){
            out.println(jogador.getNome()+";" + jogador.getPontuacao() + ";" + jogador.getTema() + ";" + jogador.getData());
        } catch (IOException e) {
            System.err.println("Erro ao salvar ranking: " + e.getMessage());
        }
    }
    
    public List<Jogador> carregarRanking(){
        criarArquivoRanking();
        List<Jogador> ranking = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_RANKING))){
            String linha;
            while ((linha = br.readLine()) != null){
                linha = linha.trim();
                if (linha.isEmpty()) continue;
                
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    try{
                        String nome = dados[0].trim();
                        int pontuacao = Integer.parseInt(dados[1].trim());
                        String tema = dados[2].trim();
                        String data = dados[3].trim();
                        
                        ranking.add(new Jogador(nome, pontuacao, tema, data));
                }catch (NumberFormatException e) {
                    System.err.println("Formato invalido para pontuacao na linha: " + linha);
                }
 
                }
            }
        } catch (IOException e){
            System.err.println("Erro ao carregar ranking: " + e.getMessage());
        }
        
        Collections.sort(ranking);
        return ranking;
    }
    
    public void exibirRanking(){
        List<Jogador> ranking = carregarRanking();
        System.out.println("\n RANKING DE JOGADORES (TOP 10)");
        System.out.println("----------------------------------------------");
        System.out.printf("%20s %10s %-12s %s\n", "NOME", "PONTUACAO", "TEMA", "DATA");
        System.out.println("----------------------------------------------");
        
        ranking.stream()
                .limit(10)
                .forEach(j -> System.out.printf("%20s %10s %-12s %s\n", 
                    j.getNome(), j.getPontuacao(), j.getTema(), j.getData()));
    }
    
    private void criarArquivoRanking(){
        try {
            File ranking = new File(ARQUIVO_RANKING);
            if(!ranking.exists()){
                ranking.createNewFile();
            }
        } catch (IOException e){
            System.err.println("Erro ao criar o arquivo de ranking: " + e.getMessage());
        }
    }
}
