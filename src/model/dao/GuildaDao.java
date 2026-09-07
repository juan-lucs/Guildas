package model.dao;

import exeption.AventureiroNaoExiste;
import exeption.guildaNaoEncontradaException;
import model.Entity.Aventureiro;
import model.Entity.Guilda;

import java.util.List;
import java.util.Map;

public interface GuildaDao {
    void insert(Guilda arg);
    void update(Guilda arg);
    void updateReputacao(Guilda arg);
    Guilda findByNome(String nome) throws guildaNaoEncontradaException;

    Map<String, Aventureiro> findAventureirosByGuilda(Guilda guilda);

    List<Guilda> findAll();
    boolean pesquisarAventureiro(Guilda g, String nomes);
    List<String> findAllNomes();
}
