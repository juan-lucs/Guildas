package Main;

import model.Entity.Jogador;
import Service.*;

import enums.Modalidade;
import exeption.*;
import util.Exportador;

import java.time.LocalDate;
import java.util.Scanner;

public class ProgramMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TorneioService service = new TorneioService();

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║   SISTEMA DE TORNEIOS !!     ║");
        System.out.println("╚══════════════════════════════╝");

        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar time");
            System.out.println("2. Adicionar jogador a um time");
            System.out.println("3. Registrar partida");
            System.out.println("4. Ver ranking");
            System.out.println("5. Exibir resumo do torneio");
            System.out.println("6. Exportar ranking para arquivo");
            System.out.println("7. Finalizar torneio");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao;
            try {
                opcao = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                continue;
            }

            switch (opcao) {

                case 1 -> {
                    // ── CADASTRAR TIME ──
                    System.out.print("Nome do time : ");
                    String nomeTime = sc.nextLine().trim();

                    System.out.println("Modalidade:");
                    Modalidade[] modalidades = Modalidade.values();
                    for (int i = 0; i < modalidades.length; i++) {
                        System.out.println((i + 1) + " - " + modalidades[i]);
                    }
                    System.out.print("Escolha: ");

                    try {
                        int idxModalidade = Integer.parseInt(sc.nextLine().trim()) - 1;
                        Modalidade modalidade = modalidades[idxModalidade];
                        service.cadastrarTime(nomeTime, modalidade);
                    } catch (TimeDuplicadoException | TorneioFinalizadoException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        System.out.println("Modalidade inválida!");
                    }
                }

                case 2 -> {
                    // ── ADICIONAR JOGADOR ──
                    System.out.print("Nome do time: ");
                    String nomeTime = sc.nextLine().trim();

                    System.out.print("Nome do jogador: ");
                    String nomeJogador = sc.nextLine().trim();

                    System.out.print("Posição do jogador: ");
                    String posicao = sc.nextLine().trim();

                    System.out.print("Idade do jogador: ");
                    try {
                        int idade = Integer.parseInt(sc.nextLine().trim());
                        Jogador jogador = new Jogador(posicao, nomeJogador, idade);
                        service.adicionarJogadorTime(nomeTime, jogador);
                    } catch (TimeNaoEncontradoException | JogadorDuplicadoException | TorneioFinalizadoException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println(" Idade inválida!");
                    }
                }

                case 3 -> {
                    // ── REGISTRAR PARTIDA ──
                    System.out.print("Nome do time 1: ");
                    String time1 = sc.nextLine().trim();

                    System.out.print("Nome do time 2: ");
                    String time2 = sc.nextLine().trim();

                    try {
                        System.out.print("Ano da partida: ");
                        int ano = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Mês da partida: ");
                        int mes = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Dia da partida: ");
                        int dia = Integer.parseInt(sc.nextLine().trim());
                        LocalDate data = LocalDate.of(ano, mes, dia);

                        System.out.print("Pontos do " + time1 + ": ");
                        int pontos1 = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Pontos do " + time2 + ": ");
                        int pontos2 = Integer.parseInt(sc.nextLine().trim());

                        service.registrarPartida(time1, time2, data, pontos1, pontos2);

                    } catch (TimeNaoEncontradoException | TimeIncompletoException | TorneioFinalizadoException e) {
                        System.out.println(e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido!");
                    }
                }

                case 4 -> {
                    // ── VER RANKING ──
                    var ranking = service.rankingTorneio();
                    if (ranking.isEmpty()) {
                        System.out.println("Nenhum time cadastrado ainda.");
                    } else {
                        System.out.println("\n=== RANKING ===");
                        int pos = 1;
                        for (var entry : ranking) {
                            System.out.println(pos++ + "º " + entry.getKey().getNome() + " - " + entry.getValue() + " pontos");
                        }
                    }
                }

                case 5 -> {
                    // ── RESUMO ──
                    service.resumoTorneio();
                }

                case 6 -> {
                    // ── EXPORTAR RANKING ──
                    var ranking = service.rankingTorneio();
                    if (ranking.isEmpty()) {
                        System.out.println("Nenhuma partida registrada ainda. Não há ranking para exportar.");
                    } else {
                        try {
                            Exportador.exportarRanking(ranking);
                            System.out.println("Ranking exportado para 'ranking.txt' com sucesso!");
                        } catch (Exception e) {
                            System.out.println("Erro ao exportar: " + e.getMessage());
                        }
                    }
                }

                case 7 -> {
                    // FINALIZAR
                    try {
                        service.finalizarTorneio();
                    } catch (TorneioFinalizadoException e) {
                        System.out.println(e.getMessage());
                    }
                }


                case 0 -> {
                    System.out.println("Encerrando o sistema.....");
                    rodando = false;
                }

                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close();
    }
}