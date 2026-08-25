package Main;

import Service.*;

import db.dbexception;
import enums.Classes;
import exeption.*;
import model.Entity.Aventureiro;
import model.Entity.AvtrMestre;
import model.Entity.Guilda;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
                    AvtrMestre mestre = null;
                    System.out.print("Nome do Guilda : ");
                    String nomeGuilda = sc.nextLine().trim();
                    System.out.println("Level da Guilda: ");
                    try {
                        int level = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Sua Guilda terá um Aventureiro mestre? y/n");
                        String escolha = sc.nextLine();

                        if (!escolha.equalsIgnoreCase("y") && !escolha.equalsIgnoreCase("n")) {throw new EscolhaerradaException("Escolha incorreta!");}
                        else if (escolha.equalsIgnoreCase("y")) {

                            System.out.print("Nome do Aventureiro: ");
                            String nomeAventureiro = sc.nextLine().trim();

                            System.out.print("Nível do Aventureiro: ");
                            int nivel = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Classe do Aventureiro:\n");
                            Classes[] classes = Classes.values();
                            for (var e : classes) {
                                System.out.println(e);
                            }
                            Classes classe = Classes.valueOf(sc.nextLine().trim().toUpperCase());
                            mestre = TorneioService.criarMestreGuilda(nomeAventureiro, nivel, classe);
                            service.cadastrarGuilda(nomeGuilda, level, mestre);
                        } else {
                            service.cadastrarGuilda(nomeGuilda, level, mestre);
                        }
                    } catch (NivelMinimoMestreException | GuildaDuplicadoException | dbexception | EscolhaerradaException e) {
                        System.out.println(e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Level invalido!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Escolha uma classe certa!");
                    }
                }

//                case 2 -> {
//                    // ── ADICIONAR Aventureiro ──
//                    System.out.print("Nome do Guilda: ");
//                    String nomeGuilda = sc.nextLine().trim();
//
//                    System.out.print("Nome do Aventureiro: ");
//                    String nomeAventureiro = sc.nextLine().trim();
//
//                    System.out.print("Posição do Aventureiro: ");
//                    String posicao = sc.nextLine().trim();
//
//                    System.out.print("Idade do Aventureiro: ");
//                    try {
//                        int idade = sc.nextInt();
//                        service.adicionarAventureiroGuilda(nomeGuilda, nomeAventureiro, idade, posicao);
//                        sc.nextLine();
//                    } catch (AventureiroDuplicadoException | GuildaNaoEncontradoException | dbexception e) {
//                        System.out.println(e.getMessage());
//                    } catch (InputMismatchException e) {
//                        System.out.println("Idade inválida!");
//                    }
//                }
//
//                case 3 -> {
//                    // ── REGISTRAR missão ──
//                    System.out.print("Nome do Guilda 1: ");
//                    String Guilda1 = sc.nextLine().trim();
//
//                    System.out.print("Nome do Guilda 2: ");
//                    String Guilda2 = sc.nextLine().trim();
//
//                    try {
//                        System.out.print("Ano da missão: ");
//                        int ano = Integer.parseInt(sc.nextLine().trim());
//                        System.out.print("Mês da missão: ");
//                        int mes = Integer.parseInt(sc.nextLine().trim());
//                        System.out.print("Dia da missão: ");
//                        int dia = Integer.parseInt(sc.nextLine().trim());
//                        var data = LocalDate.of(ano, mes, dia);
//
//                        System.out.print("Pontos do " + Guilda1 + ": ");
//                        int pontos1 = Integer.parseInt(sc.nextLine().trim());
//
//                        System.out.print("Pontos do " + Guilda2 + ": ");
//                        int pontos2 = Integer.parseInt(sc.nextLine().trim());
//
//                        service.registrarmissão(Guilda1, Guilda2, data, pontos1, pontos2);
//
//                    } catch (GuildaNaoEncontradoException | GuildaIncompletoException | TorneioFinalizadoException e) {
//                        System.out.println(e.getMessage());
//                    } catch (NumberFormatException e) {
//                        System.out.println("Valor inválido!");
//                    }
//                }
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