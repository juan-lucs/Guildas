package Main;

import Service.*;

import db.dbexception;
import enums.Classes;
import enums.Dificuldadeimcompatível;
import enums.resultadoMissao;
import exeption.*;
import model.Entity.Aventureiro;
import model.Entity.AvtrMestre;
import model.Entity.Guilda;


import java.sql.Array;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.IntStream;

public class ProgramMain {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var service = new TorneioService();

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║   SISTEMA DE GUILDAS !!     ║");
        System.out.println("╚══════════════════════════════╝");

        var rodando = true;

        while (rodando) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar Guilda");
            System.out.println("2. Adicionar Aventureiro a um Guilda");
            System.out.println("3. Registrar missão");
            System.out.println("4. Ver ranking");
            System.out.println("6. Exportar ranking para arquivo");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao;
            try {

                opcao = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }
            try {
            switch (opcao) {

                case 1 -> {
                    // ── CADASTRAR Guilda ──
                    System.out.print("Nome do Guilda : ");
                    String nomeGuilda = sc.nextLine().trim();
                    System.out.println("Level da Guilda: ");
                    try {
                        int level = sc.nextInt();
                        sc.nextLine();
                        service.cadastrarGuilda(nomeGuilda, level);
                        System.out.println("Sua Guilda terá um Aventureiro mestre? y/n");
                        String escolha = sc.nextLine().toUpperCase();

                        if (!escolha.equalsIgnoreCase("Y") && !escolha.equalsIgnoreCase("N")) {throw new EscolhaerradaException("Escolha incorreta!");}
                        else if (escolha.equalsIgnoreCase("Y")) {

                            System.out.print("Nome do Aventureiro: ");
                            String nomeAventureiro = sc.nextLine().trim();

                            System.out.print("Nível do Aventureiro: ");
                            int nivel = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Classe do Aventureiro:\n");
                            Arrays.stream(Classes.values()).forEach(System.out::println);
                            Classes classe = Classes.valueOf(sc.nextLine().trim().toUpperCase());
                            service.criarMestreGuilda(nomeAventureiro, nivel, classe, nomeGuilda);
                        }
                    } catch (NivelMinimoMestreException | GuildaDuplicadoException | dbexception | EscolhaerradaException | guildaNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Level invalido!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Escolha uma classe certa!");
                    }
                }

                case 2 -> {
                    // ── ADICIONAR Aventureiro ──
                    System.out.print("Nome do Guilda: ");
                    String nomeGuilda = sc.nextLine().trim();

                    System.out.print("Nome do Aventureiro: ");
                    String nomeAventureiro = sc.nextLine().trim();

                    Arrays.stream(Classes.values()).forEach(System.out::println);
                    try {
                        System.out.print("Classe do Aventureiro: ");
                        String classe = sc.nextLine().trim().toUpperCase();
                        System.out.print("Nivel do Aventureiro: ");
                        int idade = sc.nextInt();
                        service.adicionarAventureiroGuilda(nomeGuilda, nomeAventureiro, idade, Classes.valueOf(classe));
                        sc.nextLine();
                    } catch (AventureiroDuplicadoException | guildaNaoEncontradaException | dbexception e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Idade inválida!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Escolha uma classe certa!");
                    }
                }

                case 3 -> {
                    // ── REGISTRAR missão ──
                    System.out.print("Nome da Missao: ");
                    String missaoNome = sc.nextLine().trim();

                    System.out.println("Nome da Guilda responsável pela Missão: ");
                    String guildaNome = sc.nextLine().trim();

                    System.out.println("Quantos aventureiros participaram da missão?");
                    try {
                        int quantidadeparti = sc.nextInt();
                        sc.nextLine();

                        var participantes = new ArrayList<String>();

                        if (quantidadeparti > 0) {
                            while (quantidadeparti > 0 ) {
                                System.out.println("Digite o nome do aventureiro: ");
                                participantes.add(sc.nextLine());
                                quantidadeparti =- 1;
                            }

                        } else {
                            throw new quantidadeParticipantesErradaException("Quantidade de participantes inválida!");
                        }

                        System.out.println("Dificuldade da missão: (1 a 10)");
                        int dificuldade = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Resultado da missão (Vitória ou derrota)");
                        resultadoMissao resultado = resultadoMissao.valueOf(sc.nextLine().toUpperCase());
                        service.registrarMissao(missaoNome, guildaNome, participantes, dificuldade, resultado);

                    } catch (AventureiroNaoExiste | GuildavaziaException | guildaNaoEncontradaException | Dificuldadeimcompatível | quantidadeParticipantesErradaException| EscolhaerradaException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Valor inválido!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Resultado imcompatível!");
                    }
                }
//
//                case 4 -> {
//                    // ── VER RANKING ──
//                    var ranking = service.rankingTorneio();
//                    if (ranking.isEmpty()) {
//                        System.out.println("Nenhum Guilda cadastrado ainda.");
//                    } else {
//                        System.out.println("\n=== RANKING ===");
//                        int pos = 1;
//                        for (var entry : ranking) {
//                            System.out.println(pos++ + "º " + entry.getKey().getNome() + " - " + entry.getValue() + " pontos");
//                        }
//                    }
//                }
//
//                case 6 -> {
//                    // ── EXPORTAR RANKING ──
//                    var ranking = service.rankingTorneio();
//                    if (ranking.isEmpty()) {
//                        System.out.println("Nenhuma missão registrada ainda. Não há ranking para exportar.");
//                    } else {
//                        try {
//                            Exportador.exportarRanking(ranking);
//                            System.out.println("Ranking exportado para 'ranking.txt' com sucesso!");
//                        } catch (Exception e) {
//                            System.out.println("Erro ao exportar: " + e.getMessage());
//                        }
//                    }
//                }
//
//

                case 0 -> {
                    System.out.println("Encerrando o sistema.....");
                    rodando = false;
                }

                default -> System.out.println("Opção inválida. Tente novamente.");
            }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        sc.close();
    }
}