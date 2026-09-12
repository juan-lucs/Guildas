package Service;
import db.dbexception;
import enums.Classes;
import enums.resultadoMissao;
import model.Entity.Aventureiro;
import model.Entity.AvtrMestre;
import exeption.*;
import model.Entity.Guilda;
import model.Entity.Missao;
import model.dao.AventureiroDao;
import model.dao.DaoFactory;
import model.dao.GuildaDao;
import model.dao.MissaoDao;

import java.util.*;

public class TorneioService {

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
    public void registrarMissao(String nomeMissao, String nomeGuilda, List<String> participantes , int dificuldade, resultadoMissao resultado)
            throws Dificuldadeimcompativel, guildaNaoEncontradaException, dbexception, GuildavaziaException, AventureiroNaoExiste {

        if (dificuldade > 10 || dificuldade < 1) {
            throw new Dificuldadeimcompativel("Valor inválido para dificuldade!");
        }

        var guilda = guildaDao.findByNome(nomeGuilda);
        Map<String, Aventureiro> aventureiros = guildaDao.findAventureirosByGuilda(guilda);
        guilda.setAventureiros(aventureiros);
        if (guilda.getAventureiros().isEmpty()) {
            throw new GuildavaziaException("O Guilda '" + guilda.getNome() + "' não tem jogadores cadastrados!");
        }
        Map<String, Aventureiro> aventureirosnaMissao = new HashMap<>();
        for (var participante : participantes) {
            if (!aventureiros.containsKey(participante)) {
                throw new AventureiroNaoExiste("O aventureiro " + participante +" não está na guilda " +guilda.getNome());
            } else {
               aventureirosnaMissao.put(participante, aventureiros.get(participante));
            }
        }
        if (resultado == resultadoMissao.D || resultado == resultadoMissao.DERROTA) {
            guilda.setReputacao(guilda.getReputacao() - dificuldade * 100);
        } else {
            guilda.setReputacao(guilda.getReputacao() + dificuldade * 100);
        }
        guildaDao.updateReputacao(guilda);
        missaoDao.insert(new Missao(nomeMissao,dificuldade, aventureirosnaMissao, guilda, resultado));
        System.out.println("Missão registrada com sucesso!");
    }

    // RANKING (decrescente)
    public List<Guilda> rankingTorneio() {
        var guildas = guildaDao.findAll();
        guildas.sort(Comparator.comparing((Guilda g) -> g.getReputacao()).thenComparing(g -> g.getNome()));
        return guildas;
    }
//
//    // RESUMO DO TORNEIO
//    public void resumoTorneio() {
//        System.out.println("\n=== RESUMO DO TORNEIO ===");
//        System.out.println("Guildas participando: " + repositorio.tamanho());
//        System.out.println("Partidas jogadas:   " + partidas.size());
//    }

}