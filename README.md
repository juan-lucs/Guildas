# Sistema de Gerenciamento de Guildas

Sistema de gerenciamento de guildas desenvolvido em **Java**, criado como projeto prático de estudos para aplicar e aprofundar conceitos de **Programação Orientada a Objetos, Collections, JDBC, banco de dados relacional e organização em camadas**.

O projeto surgiu originalmente como um **Sistema de Gerenciamento de Torneios**, sendo posteriormente refatorado para um sistema de guildas. A mudança mantém parte da estrutura e dos conceitos já desenvolvidos, mas introduz um novo domínio e novas regras de negócio envolvendo **aventureiros, guildas, missões, reputação e progressão**.

> **Projeto em desenvolvimento.**
> As funcionalidades descritas no roadmap serão implementadas progressivamente durante os estudos.

---

## Objetivo

O sistema tem como objetivo permitir o gerenciamento de guildas e seus aventureiros.

Cada guilda poderá possuir diversos membros com diferentes níveis e, futuramente, classes. As guildas poderão participar de missões cuja conclusão dependerá das características dos aventureiros selecionados.

O desempenho nas missões concederá **reputação**, utilizada para determinar o progresso e o rank das guildas.

---

# Funcionalidades

## Implementado

* Cadastro de guildas (nome e level)
* Criação de um mestre para a guilda (aventureiro com nível mínimo de 50)
* Cadastro de aventureiros e associação a uma guilda existente
* Prevenção de guildas e aventureiros duplicados
* Registro de missões, com participantes, dificuldade (1 a 10) e resultado (vitória/derrota)
* Atualização automática da reputação da guilda após cada missão
* Visualização do ranking de guildas por reputação
* Persistência dos dados utilizando MySQL + JDBC, com transações e rollback no registro de missões
* Tratamento de erros através de exceções personalizadas para cada regra de negócio

## Ainda não implementado

* Remoção de aventureiros
* Exportação do ranking para arquivo (existe um rascunho pronto no código, comentado, mas ainda não está funcional)
* Cálculo automático do resultado da missão a partir do nível dos aventureiros (hoje o resultado é digitado manualmente por quem está usando o sistema)
* Sistema de ranks nomeados (Bronze, Prata, Ouro...) — o ranking hoje mostra só o número da reputação
* Controle de status da missão (impedir que a mesma missão seja concluída duas vezes)

---

# Regras de Negócio

As regras abaixo definem o comportamento esperado do sistema conforme seu desenvolvimento.

### Guildas

**RN01 — Nome único** ✅ *Implementado*

Uma guilda deve possuir um nome único dentro do sistema.

**RN02 — Associação de aventureiros** ✅ *Implementado*

Um aventureiro pode pertencer a apenas uma guilda por vez.

**RN03 — Guilda existente** ✅ *Implementado*

Um aventureiro somente pode ser associado a uma guilda previamente cadastrada.

**RN04 — Mestre da guilda** ✅ *Implementado*

Uma guilda poderá possuir um de seus aventureiros como mestre.

O mestre obrigatoriamente deve pertencer à própria guilda.

**RN05 — Mestre único** 🚧 *Parcial*

Uma guilda poderá possuir apenas um mestre por vez.

Hoje isso só é garantido porque o menu só oferece a opção de criar o mestre no momento em que a guilda é cadastrada — não existe uma verificação explícita no código impedindo que outro mestre seja definido depois.

---

### Aventureiros

**RN06 — Nível** ✅ *Implementado*

Cada aventureiro possuirá um nível que representará parte de sua capacidade dentro da guilda.

**RN07 — Classe** ✅ *Implementado*

Os aventureiros poderão possuir diferentes classes, como:

* Guerreiro
* Mago
* Arqueiro
* Clérigo
* Ladino

As classes poderão futuramente ser utilizadas como requisitos para determinadas missões.

---

### Missões

**RN08 — Associação da missão** ✅ *Implementado*

Cada missão deve estar associada a uma guilda existente.

**RN09 — Dificuldade** ✅ *Implementado*

Cada missão possuirá uma dificuldade utilizada para determinar se a equipe enviada possui capacidade suficiente para concluí-la. Hoje a dificuldade só é validada dentro do intervalo de 1 a 10.

**RN10 — Participantes** ✅ *Implementado*

Uma missão poderá ser realizada por um conjunto de aventureiros pertencentes à guilda responsável.

Apenas aventureiros pertencentes à guilda poderão participar da missão.

**RN11 — Força da equipe** ⏳ *Planejado*

A força utilizada na missão será calculada a partir das características dos aventureiros selecionados.

Inicialmente, o nível dos aventureiros será o principal fator utilizado no cálculo. Hoje o nível dos aventureiros ainda não influencia o resultado da missão.

**RN12 — Resultado** ⏳ *Planejado*

O sucesso ou fracasso de uma missão será determinado pelos dados da equipe e pelos requisitos da missão, evitando que o resultado dependa apenas de aleatoriedade.

Hoje o resultado (vitória ou derrota) é digitado manualmente por quem está usando o sistema ao registrar a missão, e não calculado a partir da equipe.

**RN13 — Missão concluída** ⏳ *Planejado*

Uma missão já concluída não poderá ser concluída novamente. Hoje não existe um controle de status que impeça isso.

---

### Reputação e Rank

**RN14 — Reputação** ✅ *Implementado*

Guildas receberão reputação pela conclusão bem-sucedida de missões. Hoje o valor é a dificuldade da missão multiplicada por 100, somado em caso de vitória e subtraído em caso de derrota.

**RN15 — Reputação não negativa** ⏳ *Planejado*

A reputação de uma guilda não poderá possuir valor negativo. Hoje não existe essa trava — uma sequência de derrotas pode deixar a reputação negativa.

**RN16 — Rank** ⏳ *Planejado*

O rank de uma guilda será determinado por sua reputação acumulada. Hoje o ranking mostra apenas o número da reputação, sem os rótulos abaixo.

Exemplo inicial de progressão:

| Reputação | Rank     |
| --------: | -------- |
|    0 – 99 | Bronze   |
| 100 – 249 | Prata    |
| 250 – 499 | Ouro     |
| 500 – 999 | Platina  |
|     1000+ | Lendária |

Os valores poderão ser alterados conforme o desenvolvimento das regras do sistema.

---

# Modelo do Domínio

A estrutura planejada do sistema pode ser representada inicialmente como:

```text
Guilda
│
├── Aventureiros
│     ├── Nível
│     └── Classe
│
├── Mestre
│
├── Reputação
│     └── Rank
│
└── Missões
      ├── Dificuldade
      ├── Participantes
      ├── Status
      └── Recompensa
```

Uma guilda possui vários aventureiros, enquanto cada aventureiro pertence a uma única guilda.

Futuramente, aventureiros poderão participar de várias missões e cada missão poderá possuir vários aventureiros, introduzindo um relacionamento **N:N**.

---

# Roadmap

O desenvolvimento está dividido em etapas para que novas funcionalidades sejam adicionadas conforme novos conceitos são estudados.

## Versão 1 — Guildas e Aventureiros

* [x] Entidade Guilda
* [x] Entidade Aventureiro
* [x] Cadastro de guildas
* [x] Cadastro de aventureiros
* [x] Associação entre aventureiro e guilda
* [x] Persistência utilizando JDBC e MySQL
* [x] Nível dos aventureiros
* [x] Mestre da guilda
* [ ] Remoção de aventureiros

---

## Versão 2 — Sistema de Missões

* [x] Refatorar a entidade Missão
* [x] Associar missão a uma guilda
* [x] Dificuldade da missão
* [x] Recompensa em reputação
* [x] Atualização da reputação após uma missão
* [ ] Status da missão
* [ ] Cálculo de força a partir do nível dos aventureiros
* [ ] Determinação automática de sucesso ou fracasso (hoje o resultado é digitado manualmente)

---

## Versão 3 — Progressão

* [x] Classes de aventureiros
* [ ] Sistema de ranks das guildas (Bronze, Prata, Ouro...)
* [ ] Requisitos específicos para missões
* [ ] Missões limitadas por rank
* [ ] Regras baseadas na composição da equipe

---

## Versão 4 — Equipes de Missão

* [x] Seleção dos aventureiros participantes
* [x] Relacionamento N:N entre aventureiros e missões
* [x] Tabela associativa (`participantesMissao`)
* [x] Validação de que os participantes pertencem à guilda
* [ ] Cálculo da força apenas dos participantes

Relação implementada:

```text
AVENTUREIRO
     N
     │
     │
     N
   MISSAO
```

No banco de dados:

```text
participantesMissao
├── aventureiro_id
└── missao_id
```

---

## Versão 5 — Consistência e Transações

* [x] Utilização de transações JDBC
* [x] Atualização da reputação da guilda
* [x] Rollback em caso de falha
* [ ] Atualização do status da missão
* [ ] Testes das principais regras de negócio

Uma conclusão de missão deverá ser tratada como uma única operação:

```text
Concluir missão
      │
      ├── Atualizar status
      ├── Calcular resultado
      └── Atualizar reputação
              │
              ▼
         COMMIT / ROLLBACK
```

---

## Futuro — JPA, Hibernate e Spring

Após a conclusão e compreensão da implementação utilizando JDBC, o projeto poderá ser migrado gradualmente para tecnologias utilizadas no desenvolvimento de aplicações Java modernas.

Planejado:

* [ ] JPA
* [ ] Hibernate
* [ ] Spring Boot
* [ ] Spring Data JPA
* [ ] API REST
* [ ] Endpoints para gerenciamento das entidades
* [ ] Testes automatizados

A intenção é realizar essa migração somente após consolidar os conceitos de persistência e acesso ao banco implementados manualmente com JDBC.

---

# Conceitos Aplicados

| Conceito                    | Aplicação                                               |
| --------------------------- | ------------------------------------------------------- |
| Orientação a Objetos        | Modelagem de guildas, aventureiros e missões            |
| Encapsulamento              | Controle do estado das entidades                        |
| Collections                 | Gerenciamento de conjuntos e listas de entidades        |
| `Set`                       | Controle de elementos que não devem se repetir          |
| `List`                      | Manipulação e retorno de conjuntos ordenados de dados   |
| `hashCode / equals`         | Identificação e comparação de entidades                 |
| `Comparable` / `Comparator` | Ordenação de entidades e futuros rankings               |
| Interfaces                  | Definição de contratos entre componentes                |
| Enums                       | Representação de estados, classes, ranks e dificuldades |
| Exceções personalizadas     | Tratamento de erros específicos do domínio              |
| JDBC                        | Comunicação entre a aplicação Java e o banco            |
| `PreparedStatement`         | Execução parametrizada de comandos SQL                  |
| `ResultSet`                 | Leitura dos resultados retornados pelo banco            |
| DAO                         | Separação da lógica de persistência                     |
| Service                     | Centralização das regras de negócio                     |
| MySQL                       | Persistência dos dados                                  |
| Chaves estrangeiras         | Relacionamento entre as entidades                       |
| `LocalDate`                 | Representação das datas das missões                     |
| Streams                     | Cálculos e consultas sobre grupos de aventureiros       |

---

# Estrutura do Projeto

A aplicação é organizada de forma a separar as responsabilidades entre as diferentes partes do sistema.

```text
src/
├── main/
│   └── ProgramMain.java
│
├── service/
│   └── TorneioService.java
│
├── model/
│   ├── entity/
│   │   ├── Guilda.java
│   │   ├── Aventureiro.java
│   │   ├── AventureiroMestre.java
│   │   └── Missao.java
│   └── dao/
│       ├── GuildaDao.java
│       ├── AventureiroDao.java
│       ├── MissaoDao.java
│       ├── DaoFactory.java
│       └── impl/
│           ├── GuildaDaoJDBC.java
│           ├── AventureiroDaoJDBC.java
│           └── MissaoDaoJDBC.java
│
├── exception/
│   └── ...
│
├── enums/
│   └── ...
│
├── db/
│   ├── BancoDados.java
│   └── DbException.java
│
├── repository/
│   └── Repositorio.java   (não utilizado atualmente — código comentado)
│
└── util/
    └── Exportador.java    (não utilizado atualmente — código comentado)
```

> A estrutura poderá ser alterada durante o desenvolvimento conforme novas responsabilidades forem adicionadas ao sistema.

**Convenção de nomes:** classes, enums e exceções em `PascalCase`; métodos e variáveis em `camelCase`; pacotes em letras minúsculas.

---

# Banco de Dados

Atualmente, a persistência é realizada utilizando **MySQL + JDBC**.

Modelo atual (nomes de coluna exatamente como usados nas queries — hoje é uma mistura de português e inglês):

```text
GUILDA
├── id
├── name
├── level
├── reputacao
└── mestre_id


AVENTUREIRO
├── id
├── name
├── nivel
├── classe
└── guilda_id


MISSAO
├── id
├── name
├── dificuldade
├── guilda_id
└── resultado
```

A relação entre aventureiros e missões já existe, através de uma tabela associativa:

```text
PARTICIPANTESMISSAO
├── missao_id
└── aventureiro_id
```

O registro de uma missão (linha em `missao` + linhas em `participantesMissao`) é feito dentro de uma transação: se algo falhar no meio do processo, o JDBC faz rollback e nada fica salvo pela metade.

---

# Evolução do Projeto

O projeto foi iniciado originalmente como um sistema de gerenciamento de torneios.

A primeira versão trabalhava com:

```text
Time → Jogadores → Partidas → Pontos → Ranking
```

Após a refatoração do domínio, a estrutura passou a representar:

```text
Guilda → Aventureiros → Missões → Reputação → Rank
```

A mudança tem como objetivo manter os conceitos já estudados e implementados enquanto permite a criação de regras de negócio mais variadas.

Parte da estrutura original pôde ser reaproveitada:

```text
Time       → Guilda
Jogador    → Aventureiro
Partida    → Missão
Pontos     → Reputação
Ranking    → Rank / classificação de guildas
```

A partir dessa base, o projeto continuará evoluindo com novas regras específicas do domínio de guildas.

---

# Como Executar

### Requisitos

* Java 21 ou superior
* MySQL
* Driver JDBC do MySQL (`mysql-connector-j`, já configurado no `pom.xml`)

### Execução

1. Clone o repositório.
2. Configure a conexão com o banco de dados.
3. Crie o banco e as tabelas necessárias.
4. Abra o projeto na IDE de sua preferência.
5. Execute `ProgramMain.java`.
6. Utilize o menu através do console.

---

# Motivação

Este projeto é utilizado como ambiente de estudo para aplicar conceitos além de exercícios isolados.

A proposta é evoluir a mesma aplicação gradualmente, passando por diferentes etapas do desenvolvimento backend:

```text
Java
  ↓
Orientação a Objetos
  ↓
Collections
  ↓
Banco de Dados
  ↓
JDBC
  ↓
DAO
  ↓
Regras de Negócio
  ↓
Transações
  ↓
Testes
  ↓
JPA / Hibernate
  ↓
Spring
  ↓
API REST
```

Dessa forma, cada nova tecnologia é introduzida para resolver problemas que já existem no próprio sistema.

---

## Autor

Desenvolvido por **Juan** como projeto de estudo em Java e Engenharia de Software.
