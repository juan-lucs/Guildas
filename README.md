
# Tournament Management System

![Java](https://img.shields.io/badge/Java-17%2B-blue?style=flat-square&logo=openjdk)
![Status](https://img.shields.io/badge/Status-Complete-success?style=flat-square)
![Type](https://img.shields.io/badge/Type-Study%20Project-orange?style=flat-square)

A console-based tournament management system built in Java, developed as a hands-on study project to apply core Java concepts including Collections, Generics, Interfaces, and Object-Oriented Programming principles.

---

> **Leia em Portugues** na secao abaixo.

---

## Features

- Register teams with name and sport modality
- Add players to teams, preventing duplicates
- Record match results with automatic scorekeeping
- Display a ranked leaderboard sorted by points
- Export the final ranking to a `.txt` file
- Finalize tournaments with status control

## Concepts Applied

| Concept | Where it appears |
|---|---|
| `Set` | Preventing duplicate players per team and duplicate matches |
| `Map` | Storing and updating each team's points |
| `Generics` | Generic `Repository<T>` class for any model type |
| `Bounded Generics` | `<T extends Player>` method to display player lists |
| `Wildcards` | `List<? extends Team>` for flexible team listing |
| `hashCode / equals` | Ensuring uniqueness of teams and players |
| `Comparable` | Natural ordering of teams by name |
| `Comparator` | Sorting the ranking by points (descending) |
| `Interfaces` | `Rankable`, `Exportable`, `Statistical` |
| `Default Methods` | Shared behavior defined directly in interfaces |
| `Enum` | Sport modality and tournament status |
| `Custom Exceptions` | Domain-specific error handling |
| `LocalDate` | Match date tracking |
| `BufferedWriter` | Exporting the ranking to a file |

## Project Structure

```
src/
├── Main/
│   └── ProgramMain.java         # Entry point, user menu
├── model/
│   ├── Player.java
│   ├── Team.java
│   └── Match.java
├── Service/
│   └── TournamentService.java   # Business logic
├── Repository/
│   └── Repository.java          # Generic repository
├── Interfaces/
│   ├── Rankable.java
│   ├── Exportable.java
│   └── Statistical.java
├── Enums/
│   ├── Modality.java
│   └── TournamentStatus.java
├── Exception/
│   ├── DuplicatePlayerException.java
│   ├── DuplicateTeamException.java
│   ├── IncompleteTeamException.java
│   ├── TeamNotFoundException.java
│   └── TournamentFinalizedException.java
└── Util/
    └── Exporter.java            # File export with BufferedWriter
```

## How to Run

1. Clone the repository
2. Open the project in your preferred IDE (IntelliJ IDEA or Eclipse recommended)
3. Run `ProgramMain.java`
4. Interact with the console menu

**Requirements:** Java 17 or higher

## Author

Developed by **Juan** as part of a Java study curriculum covering Object-Oriented Programming and the Collections Framework.

---
---

# Sistema de Gerenciamento de Torneios

![Java](https://img.shields.io/badge/Java-17%2B-blue?style=flat-square&logo=openjdk)
![Status](https://img.shields.io/badge/Status-Concluido-success?style=flat-square)
![Tipo](https://img.shields.io/badge/Tipo-Projeto%20de%20Estudo-orange?style=flat-square)

Sistema de gerenciamento de torneios via console desenvolvido em Java, criado como projeto pratico de estudos para aplicar conceitos fundamentais da linguagem, incluindo Collections, Generics, Interfaces e Orientacao a Objetos.

---

## Funcionalidades

- Cadastrar times com nome e modalidade esportiva
- Adicionar jogadores aos times, impedindo duplicatas
- Registrar resultados de partidas com pontuacao automatica
- Exibir ranking ordenado por pontuacao
- Exportar o ranking final para um arquivo `.txt`
- Finalizar torneios com controle de status

## Conceitos Aplicados

| Conceito | Onde aparece |
|---|---|
| `Set` | Impedir jogadores e partidas duplicadas |
| `Map` | Armazenar e atualizar a pontuacao de cada time |
| `Generics` | Classe generica `Repositorio<T>` para qualquer modelo |
| `Generics delimitados` | Metodo `<T extends Jogador>` para listar jogadores |
| `Wildcards` | `List<? extends Time>` para listagem flexivel de times |
| `hashCode / equals` | Garantir unicidade de times e jogadores |
| `Comparable` | Ordenacao natural de times por nome |
| `Comparator` | Ordenacao do ranking por pontos (decrescente) |
| `Interfaces` | `Classificavel`, `Exportavel`, `Estatistico` |
| `Default Methods` | Comportamento compartilhado definido nas interfaces |
| `Enum` | Modalidade esportiva e status do torneio |
| `Excecoes personalizadas` | Tratamento de erros especificos do dominio |
| `LocalDate` | Registro da data das partidas |
| `BufferedWriter` | Exportacao do ranking para arquivo |

## Estrutura do Projeto

```
src/
├── Main/
│   └── ProgramMain.java         # Ponto de entrada, menu do usuario
├── model/
│   ├── Jogador.java
│   ├── Time.java
│   └── Partida.java
├── Service/
│   └── TorneioService.java      # Regras de negocio
├── Repository/
│   └── Repositorio.java         # Repositorio generico
├── Interfaces/
│   ├── Classificavel.java
│   ├── Exportavel.java
│   └── Estatistico.java
├── Enums/
│   ├── Modalidade.java
│   
├── Exeption/
│   ├── JogadorDuplicadoException.java
│   ├── TimeDuplicadoException.java
│   ├── TimeIncompletoException.java
│   ├── TimeNaoEncontradoException.java
│   └── TorneioFinalizadoException.java
└── Util/
    └── Exportador.java          # Exportacao com BufferedWriter
```

## Como Executar

1. Clone o repositorio
2. Abra o projeto na sua IDE (IntelliJ IDEA ou Eclipse recomendado)
3. Execute o arquivo `ProgramMain.java`
4. Interaja com o menu no console

**Requisitos:** Java 17 ou superior

## Autor

Desenvolvido por **Juan** como parte de um curriculo de estudos em Java, cobrindo Orientacao a Objetos e o Collections Framework.
=======
# Tournament Management System

![Java](https://img.shields.io/badge/Java-17%2B-blue?style=flat-square&logo=openjdk)
![Status](https://img.shields.io/badge/Status-Complete-success?style=flat-square)
![Type](https://img.shields.io/badge/Type-Study%20Project-orange?style=flat-square)

A console-based tournament management system built in Java, developed as a hands-on study project to apply core Java concepts including Collections, Generics, Interfaces, and Object-Oriented Programming principles.

---

> **Leia em Portugues** na secao abaixo.

---

## Features

- Register teams with name and sport modality
- Add players to teams, preventing duplicates
- Record match results with automatic scorekeeping
- Display a ranked leaderboard sorted by points
- Export the final ranking to a `.txt` file
- Finalize tournaments with status control

## Concepts Applied

| Concept | Where it appears |
|---|---|
| `Set` | Preventing duplicate players per team and duplicate matches |
| `Map` | Storing and updating each team's points |
| `Generics` | Generic `Repository<T>` class for any model type |
| `Bounded Generics` | `<T extends Player>` method to display player lists |
| `Wildcards` | `List<? extends Team>` for flexible team listing |
| `hashCode / equals` | Ensuring uniqueness of teams and players |
| `Comparable` | Natural ordering of teams by name |
| `Comparator` | Sorting the ranking by points (descending) |
| `Interfaces` | `Rankable`, `Exportable`, `Statistical` |
| `Default Methods` | Shared behavior defined directly in interfaces |
| `Enum` | Sport modality and tournament status |
| `Custom Exceptions` | Domain-specific error handling |
| `LocalDate` | Match date tracking |
| `BufferedWriter` | Exporting the ranking to a file |

## Project Structure

```
src/
├── Main/
│   └── ProgramMain.java         # Entry point, user menu
├── model/
│   ├── Player.java
│   ├── Team.java
│   └── Match.java
├── Service/
│   └── TournamentService.java   # Business logic
├── Repository/
│   └── Repository.java          # Generic repository
├── Interfaces/
│   ├── Rankable.java
│   ├── Exportable.java
│   └── Statistical.java
├── Enums/
│   ├── Modality.java
│   └── TournamentStatus.java
├── Exception/
│   ├── DuplicatePlayerException.java
│   ├── DuplicateTeamException.java
│   ├── IncompleteTeamException.java
│   ├── TeamNotFoundException.java
│   └── TournamentFinalizedException.java
└── Util/
    └── Exporter.java            # File export with BufferedWriter
```

## How to Run

1. Clone the repository
2. Open the project in your preferred IDE (IntelliJ IDEA or Eclipse recommended)
3. Run `ProgramMain.java`
4. Interact with the console menu

**Requirements:** Java 17 or higher

## Author

Developed by **Juan** as part of a Java study curriculum covering Object-Oriented Programming and the Collections Framework.

---
---

# Sistema de Gerenciamento de Torneios

![Java](https://img.shields.io/badge/Java-17%2B-blue?style=flat-square&logo=openjdk)
![Status](https://img.shields.io/badge/Status-Concluido-success?style=flat-square)
![Tipo](https://img.shields.io/badge/Tipo-Projeto%20de%20Estudo-orange?style=flat-square)

Sistema de gerenciamento de torneios via console desenvolvido em Java, criado como projeto pratico de estudos para aplicar conceitos fundamentais da linguagem, incluindo Collections, Generics, Interfaces e Orientacao a Objetos.

---

## Funcionalidades

- Cadastrar times com nome e modalidade esportiva
- Adicionar jogadores aos times, impedindo duplicatas
- Registrar resultados de partidas com pontuacao automatica
- Exibir ranking ordenado por pontuacao
- Exportar o ranking final para um arquivo `.txt`
- Finalizar torneios com controle de status

## Conceitos Aplicados

| Conceito | Onde aparece |
|---|---|
| `Set` | Impedir jogadores e partidas duplicadas |
| `Map` | Armazenar e atualizar a pontuacao de cada time |
| `Generics` | Classe generica `Repositorio<T>` para qualquer modelo |
| `Generics delimitados` | Metodo `<T extends Jogador>` para listar jogadores |
| `Wildcards` | `List<? extends Time>` para listagem flexivel de times |
| `hashCode / equals` | Garantir unicidade de times e jogadores |
| `Comparable` | Ordenacao natural de times por nome |
| `Comparator` | Ordenacao do ranking por pontos (decrescente) |
| `Interfaces` | `Classificavel`, `Exportavel`, `Estatistico` |
| `Default Methods` | Comportamento compartilhado definido nas interfaces |
| `Enum` | Modalidade esportiva e status do torneio |
| `Excecoes personalizadas` | Tratamento de erros especificos do dominio |
| `LocalDate` | Registro da data das partidas |
| `BufferedWriter` | Exportacao do ranking para arquivo |

## Estrutura do Projeto

```
src/
├── Main/
│   └── ProgramMain.java         # Ponto de entrada, menu do usuario
├── model/
│   ├── Jogador.java
│   ├── Time.java
│   └── Partida.java
├── Service/
│   └── TorneioService.java      # Regras de negocio
├── Repository/
│   └── Repositorio.java         # Repositorio generico
├── Interfaces/
│   ├── Classificavel.java
│   ├── Exportavel.java
│   └── Estatistico.java
├── Enums/
│   ├── Modalidade.java
│   
├── Exeption/
│   ├── JogadorDuplicadoException.java
│   ├── TimeDuplicadoException.java
│   ├── TimeIncompletoException.java
│   ├── TimeNaoEncontradoException.java
│   └── TorneioFinalizadoException.java
└── Util/
    └── Exportador.java          # Exportacao com BufferedWriter
```

## Como Executar

1. Clone o repositorio
2. Abra o projeto na sua IDE (IntelliJ IDEA ou Eclipse recomendado)
3. Execute o arquivo `ProgramMain.java`
4. Interaja com o menu no console

**Requisitos:** Java 17 ou superior

## Autor

Desenvolvido por **Juan** como parte de um curriculo de estudos em Java, cobrindo Orientacao a Objetos e o Collections Framework.
>>>>>>> e2b5f0c7e0100c53f397524f9cc9193044a10f09
