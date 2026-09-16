package model.dao;

import exception.AventureiroNaoExisteException;
import exception.GuildaNaoEncontradaException;
import model.entity.Aventureiro;
import model.entity.Guilda;

import java.util.List;
import java.util.Map;

public interface GuildaDao {
    void insert(Guilda arg);
    void update(Guilda arg);
    void updateReputacao(Guilda arg);
    Guilda findByNome(String nome) throws GuildaNaoEncontradaException;

    Map<String, Aventureiro> findAventureirosByGuilda(Guilda guilda);

    List<Guilda> findAll();
    boolean pesquisarAventureiro(Guilda g, String nomes);
    List<String> findAllNomes();
}
