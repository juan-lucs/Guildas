package Service;

import Interfaces.*;
import db.dbexception;
import enums.Classes;
import enums.Dificuldadeimcompatível;
import enums.resultadoMissao;
import model.Entity.Aventureiro;
import model.Entity.AvtrMestre;
import exeption.*;
import model.Entity.Guilda;
import model.dao.AventureiroDao;
import model.dao.DaoFactory;
import model.dao.GuildaDao;
import model.dao.MissaoDao;

import java.util.*;

public class TorneioService implements Exportavel, Classificavel, Estatistico {

    final AventureiroDao avntDao = DaoFactory.createAventureiroDao();
    final GuildaDao guildaDao = DaoFactory.createGuildaDao();
    final MissaoDao missaoDao = DaoFactory.createMissaoDao();

//    private Map<Guilda, Integer> pontosDeCadaGuilda = new HashMap<>();
    //private final Repositorio<Guilda> repositorio = new Repositorio<>();
    //private Set<Partida> partidas = new HashSet<>();
//    private StatusTorneio status = StatusTorneio.ABERTO;

    // CADASTRAR Guilda
    public void cadastrarGuilda(String nomeGuilda, int level) throws GuildaDuplicadoException, dbexception{

        List<String> Guildas = guildaDao.findAllNomes();
        for (String Guilda : Guildas) {
            if (Guilda.equalsIgnoreCase(nomeGuilda)) {
                throw new GuildaDuplicadoException("Já existe um Guilda com o nome '" + nomeGuilda + "'!");
            }
        }
        var guilda = new Guilda(nomeGuilda, level);
        guildaDao.insert(guilda);
        System.out.println("Guilda '" + nomeGuilda + "' cadastrada com sucesso!");
    }

    public void criarMestreGuilda(String n, int nivel, Classes classe, String nomeGuilda) throws NivelMinimoMestreException, guildaNaoEncontradaException {
        final int minimoDeNivel = 50;
        if (nivel < minimoDeNivel) {
            throw new NivelMinimoMestreException("O mestre deve possuir nível 50 ou maior");
        }
        var guilda = guildaDao.findByNome(nomeGuilda);
        var aven = new AvtrMestre(n, nivel, classe, guilda);
        avntDao.insert(aven); guilda.setMestre(aven);
        guildaDao.update(guilda);
        System.out.println("Mestre cadastrado com sucesso");
    }

    // ADICIONAR Aventureiro A Guilda
    public void adicionarAventureiroGuilda(String nomeGuilda, String nomej , int nivel, Classes classe) throws AventureiroDuplicadoException, dbexception, guildaNaoEncontradaException, AventureiroDuplicadoException {
        Guilda guilda = guildaDao.findByNome(nomeGuilda); // Se não existir ele vai passar reto com o exception
        var aven = new Aventureiro(nomej,nivel, classe);
        if (guildaDao.pesquisarAventureiro(guilda, aven.getNome())) {
            throw new AventureiroDuplicadoException("Aventureiro já está na guilda!");
        } else {
            aven.setGuilda(guilda);
        }
        avntDao.insert(aven);
        System.out.println("Jogador '" + aven.getNome() + "' adicionado ao Guilda '" + guilda.getNome() + "' com sucesso!");
    }

    // REGISTRAR PARTIDA
    public void registrarMissao(String nomeMissao, String nomeGuilda, ArrayList<String> participantes , int dificuldade, resultadoMissao resultado)
            throws Dificuldadeimcompatível, guildaNaoEncontradaException, dbexception, GuildavaziaException, AventureiroNaoExiste {

        if (dificuldade > 10 && dificuldade < 1) {
            throw new Dificuldadeimcompatível("Valor inválido para dificuldade!");
        }
        var guilda = guildaDao.findByNome(nomeGuilda);

        if (guilda.getAventureiros().isEmpty()) {
            throw new GuildavaziaException("O Guilda '" + guilda.getNome() + "' não tem jogadores cadastrados!");
        }
        participantes.forEach(participante -> {
            if (!guildaDao.pesquisarAventureiro(guilda, participante)) {
                throw new AventureiroNaoExiste("O aventureiro " + participante + " não está na guilda " + guilda.getNome());
        }
        });
        System.out.println("Missão registrada com sucesso!");
    }

//    // RANKING (decrescente)
//    public List<Map.Entry<Guilda, Integer>> rankingTorneio() {
//        List<Map.Entry<Guilda, Integer>> pontosList = new ArrayList<>(pontosDeCadaGuilda.entrySet());
//        pontosList.sort(Comparator.comparingInt(Map.Entry<Guilda, Integer>::getValue).reversed());
//        return pontosList;
//    }
//
//    // RESUMO DO TORNEIO
//    public void resumoTorneio() {
//        System.out.println("\n=== RESUMO DO TORNEIO ===");
//        System.out.println("Guildas participando: " + repositorio.tamanho());
//        System.out.println("Partidas jogadas:   " + partidas.size());
//    }
//
//    // FINALIZAR TORNEIO
//    public void finalizarTorneio() throws TorneioFinalizadoException {
//        verificarTorneioAberto();
//        status = StatusTorneio.FINALIZADO;
//        System.out.println("Torneio finalizado com sucesso! Não é mais possível cadastrar Guildas, jogadores ou partidas.");
//    }
//
//    public StatusTorneio getStatus() {
//        return status;
//    }
//
//    // MÉTODO GENÉRICO DELIMITADO
//    public <T extends Jogador> void exibirJogadores(List<T> jogadores) {
//        for (T j : jogadores) {
//            System.out.println("  - " + j.getNome() + " | " + j.getIdade() + " anos");
//        }
//    }
//
//    // MÉTODO COM WILDCARD
//    public void listarGuildas(List<? extends Guilda> Guildas) {
//        for (Guilda t : Guildas) {
//            System.out.println("  - " + t.getNome() + " [" + t.getModalidade() + "]");
//        }
//    }
//
//    // HELPER PRIVADO
//    private void verificarTorneioAberto() throws TorneioFinalizadoException {
//        if (status == StatusTorneio.FINALIZADO) {
//            throw new TorneioFinalizadoException("Esta ação não é permitida: o torneio já foi finalizado!");
//        }
//    }
//
//    // GETTERS ÚTEIS
//    public List<Guilda> getGuildas() {
//        return repositorio.listarTodos();
//    }
//
    // INTERFACES
    @Override
    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RANKING DO TORNEIO ===\n");
        int pos = 1;
//        for (Map.Entry<Guilda, Integer> entry : rankingTorneio()) {
//            sb.append(pos++).append("º ").append(entry.getKey().getNome())
//                    .append(" - ").append(entry.getValue()).append(" pontos\n");
//        }
        return sb.toString();
    }

    @Override
    public int getPontuacao() {
        // retorna a maior pontuação do torneio
        return 2;
    }

    @Override
    public String getTotalPartidas() {
        return null;
//        return String.valueOf(partidas.size());
    }
}