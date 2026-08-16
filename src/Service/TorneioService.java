package Service;

import Interfaces.*;
import db.dbexception;
import model.Entity.Jogador;
import model.Entity.Partida;
import model.Entity.Time;
import enums.StatusTorneio;
import enums.*;
import exeption.*;
import model.dao.DaoFactory;
import model.dao.JogadorDao;
import model.dao.PartidaDao;
import model.dao.TimeDao;
//import repository.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class TorneioService implements Exportavel, Classificavel, Estatistico {

    JogadorDao jogadorDao = DaoFactory.createJogadorDao();
    TimeDao timeDao = DaoFactory.createTimeDao();
    PartidaDao PartidaDao = DaoFactory.createPartidaDao();

//    private Map<Time, Integer> pontosDeCadaTime = new HashMap<>();
    //private final Repositorio<Time> repositorio = new Repositorio<>();
    //private Set<Partida> partidas = new HashSet<>();
//    private StatusTorneio status = StatusTorneio.ABERTO;

    // CADASTRAR TIME
    public void cadastrarTime(String nomeTime, Modalidade modalidade) throws TimeDuplicadoException, dbexception {
        System.out.println();
        List<String> times = timeDao.findAllNomes();
        for (String time : times) {
            if (time.equalsIgnoreCase(nomeTime)) {
                throw new TimeDuplicadoException("Já existe um time com o nome '" + nomeTime + "'!");
            }
        }
        timeDao.insert(nomeTime, modalidade);
        System.out.println("Time '" + nomeTime + "' cadastrado com sucesso!");
    }

    // ADICIONAR JOGADOR AO TIME
    public void adicionarJogadorTime(String nometime, String nomej , int idade, String posicao) throws TimeNaoEncontradoException, JogadorDuplicadoException, dbexception {
        Time time = timeDao.findByNome(nometime); // Se não existir ele vai passar reto com o exception
        var jogador = new Jogador(nomej, idade, posicao /*, time */ );
        jogadorDao.insert(jogador);
        if (!timeDao.pesquisarJogador(time, jogador)) {
            jogador.setTime(time);
        }
        jogadorDao.update(jogador);
        System.out.println("Jogador '" + jogador.getNome() + "' adicionado ao time '" + time.getNome() + "' com sucesso!");
    }
//
//    // REGISTRAR PARTIDA
//    public void registrarPartida(String time1, String time2, LocalDate dataPartida, int pntstime1, int pntstime2)
//            throws TimeNaoEncontradoException, TimeIncompletoException, TorneioFinalizadoException {
//
//        verificarTorneioAberto();
//        Time t1 = buscarTimePorNome(time1);
//        Time t2 = buscarTimePorNome(time2);
//
//        if (t1.getJogadores().isEmpty()) {
//            throw new TimeIncompletoException("O time '" + t1.getNome() + "' não tem jogadores cadastrados!");
//        }
//        if (t2.getJogadores().isEmpty()) {
//            throw new TimeIncompletoException("O time '" + t2.getNome() + "' não tem jogadores cadastrados!");
//        }
//
//        partidas.add(new Partida(t1, t2, dataPartida));
//        pontosDeCadaTime.put(t1, pontosDeCadaTime.getOrDefault(t1, 0) + pntstime1);
//        pontosDeCadaTime.put(t2, pontosDeCadaTime.getOrDefault(t2, 0) + pntstime2);
//        System.out.println("Partida registrada com sucesso!");
//    }
//
//    // RANKING (decrescente)
//    public List<Map.Entry<Time, Integer>> rankingTorneio() {
//        List<Map.Entry<Time, Integer>> pontosList = new ArrayList<>(pontosDeCadaTime.entrySet());
//        pontosList.sort(Comparator.comparingInt(Map.Entry<Time, Integer>::getValue).reversed());
//        return pontosList;
//    }
//
//    // RESUMO DO TORNEIO
//    public void resumoTorneio() {
//        System.out.println("\n=== RESUMO DO TORNEIO ===");
//        System.out.println("Times participando: " + repositorio.tamanho());
//        System.out.println("Partidas jogadas:   " + partidas.size());
//    }
//
//    // FINALIZAR TORNEIO
//    public void finalizarTorneio() throws TorneioFinalizadoException {
//        verificarTorneioAberto();
//        status = StatusTorneio.FINALIZADO;
//        System.out.println("Torneio finalizado com sucesso! Não é mais possível cadastrar times, jogadores ou partidas.");
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
//    public void listarTimes(List<? extends Time> times) {
//        for (Time t : times) {
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
//    public List<Time> getTimes() {
//        return repositorio.listarTodos();
//    }
//
    // INTERFACES
    @Override
    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RANKING DO TORNEIO ===\n");
        int pos = 1;
//        for (Map.Entry<Time, Integer> entry : rankingTorneio()) {
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