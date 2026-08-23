package Service;

import Interfaces.*;
import db.dbexception;
import model.Entity.Guilda;
import exeption.*;
import model.dao.DaoFactory;
import model.dao.GuildaDao;
import java.util.*;

public class TorneioService implements Exportavel, Classificavel, Estatistico {

    JogadorDao jogadorDao = DaoFactory.createJogadorDao();
    GuildaDao GuildaDao = DaoFactory.createGuildaDao();
    PartidaDao PartidaDao = DaoFactory.createPartidaDao();

//    private Map<Guilda, Integer> pontosDeCadaGuilda = new HashMap<>();
    //private final Repositorio<Guilda> repositorio = new Repositorio<>();
    //private Set<Partida> partidas = new HashSet<>();
//    private StatusTorneio status = StatusTorneio.ABERTO;

    // CADASTRAR Guilda
    public void cadastrarGuilda(String nomeGuilda, Modalidade modalidade) throws GuildaDuplicadoException, dbexception {
        System.out.println();
        List<String> Guildas = GuildaDao.findAllNomes();
        for (String Guilda : Guildas) {
            if (Guilda.equalsIgnoreCase(nomeGuilda)) {
                throw new GuildaDuplicadoException("Já existe um Guilda com o nome '" + nomeGuilda + "'!");
            }
        }
        GuildaDao.insert(nomeGuilda, modalidade);
        System.out.println("Guilda '" + nomeGuilda + "' cadastrado com sucesso!");
    }

    // ADICIONAR JOGADOR AO Guilda
    public void adicionarJogadorGuilda(String nomeGuilda, String nomej , int idade, String posicao) throws GuildaNaoEncontradoException, JogadorDuplicadoException, dbexception {
        Guilda Guilda = GuildaDao.findByNome(nomeGuilda); // Se não existir ele vai passar reto com o exception
        var jogador = new Jogador(nomej, idade, posicao);
        jogadorDao.insert(jogador);
        if (!GuildaDao.pesquisarJogador(Guilda, jogador)) {
            jogador.setGuilda(Guilda);
        }
        jogadorDao.update(jogador);
        System.out.println("Jogador '" + jogador.getNome() + "' adicionado ao Guilda '" + Guilda.getNome() + "' com sucesso!");
    }

    // REGISTRAR PARTIDA
    public void registrarPartida(String nomeGuilda1, String nomeGuilda2, LocalDate dataPartida, int pntsGuilda1, int pntsGuilda2)
            throws GuildaNaoEncontradoException, GuildaIncompletoException, dbexception {

        Guilda t1 = GuildaDao.findByNome(nomeGuilda1);
        var t2 = GuildaDao.findByNome(nomeGuilda2);

        if (t1.getJogadores().isEmpty()) {
            throw new GuildaIncompletoException("O Guilda '" + t1.getNome() + "' não tem jogadores cadastrados!");
        }
        if (t2.getJogadores().isEmpty()) {
            throw new GuildaIncompletoException("O Guilda '" + t2.getNome() + "' não tem jogadores cadastrados!");
        }

        partidas.add(new Partida(t1, t2, dataPartida));
        pontosDeCadaGuilda.put(t1, pontosDeCadaGuilda.getOrDefault(t1, 0) + pntsGuilda1);
        pontosDeCadaGuilda.put(t2, pontosDeCadaGuilda.getOrDefault(t2, 0) + pntsGuilda2);
        System.out.println("Partida registrada com sucesso!");
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