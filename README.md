# Quiz Temático em Java

Este projeto é um jogo de perguntas e respostas com temas variados, desenvolvido em Java puro para execução via terminal. O jogador escolhe um tema, responde perguntas e compete por uma posição no ranking com base na pontuação.

---

## Funcionalidades

- Escolha entre 5 temas: Biologia, Geografia, História, Português e Química
- Perguntas com diferentes níveis de dificuldade
- Cálculo de pontuação baseado na dificuldade da pergunta
- Registro de jogadores com nome, tema, pontuação e data/hora
- Ranking com os 10 melhores jogadores salvo em arquivo
- Persistência de dados em arquivos `.txt`

---

## Classes do Projeto

### `QuizPerguntas.java (Principal)`
Classe principal do projeto, responsável pela interface via terminal. Controla o menu principal, inicializa os serviços e executa o fluxo do jogo, incluindo seleção de tema, execução das perguntas e exibição do ranking.

### `Jogador.java`
Classe que representa um jogador. Armazena nome, pontuação, tema respondido e data/hora da partida. Implementa `Comparable` para permitir ordenação por pontuação no ranking.

### `Pergunta.java`
Classe que representa uma pergunta do quiz. Contém o texto da pergunta, uma lista de alternativas, o índice da resposta correta, a dificuldade (de 1 a 3) e a área de conhecimento (tema).

### `PerguntaService.java`
Classe responsável por carregar perguntas a partir de arquivos `.txt`, estruturados por tema. Constrói objetos `Pergunta` e embaralha a ordem antes de apresentá-las ao usuário.

### `RankingService.java`
Gerencia o ranking de jogadores. Permite salvar novas pontuações no arquivo `ranking.txt` e carregar as entradas para exibição ordenada. Também cria o arquivo caso ele ainda não exista.

---

## Pré-requisitos

- Java JDK 11 ou superior
- Terminal ou IDE.

---

## Como executar

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/quiz-java.git
cd quiz-java
```

2. Compile o projeto:
```bash
javac -d bin -sourcepath src src/com/quizperguntas/QuizPerguntas.java
```

3. Execute o programa:
```bash
java -cp bin com.quizperguntas.QuizPerguntas
```

---

## Formato dos Arquivos de Pergunta

Cada pergunta deve seguir o formato abaixo dentro dos arquivos `.txt`, um por tema:

```
P: Qual é a capital do Brasil?
A: São Paulo
A: Brasília
A: Rio de Janeiro
A: Belo Horizonte
R: 2
D: 1
```

- `P:` Texto da pergunta  
- `A:` Alternativas (uma por linha)  
- `R:` Índice da alternativa correta (base 1)  
- `D:` Dificuldade (1 = fácil, 2 = médio, 3 = difícil)

---
