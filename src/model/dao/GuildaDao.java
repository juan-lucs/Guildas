package model.dao;

import exeption.AventureiroDuplicadoException;
import exeption.guildaNaoEncontradaException;
import model.Entity.Aventureiro;
import model.Entity.AvtrMestre;
import model.Entity.Guilda;

import java.util.List;

public interface GuildaDao {
    void insert(Guilda arg);
    void update(Guilda arg);
    void updateReputacao(Guilda arg);
    Guilda findByNome(String nome) throws guildaNaoEncontradaException;
    List<Guilda> findAll();
    boolean pesquisarAventureiro(Guilda g, Aventureiro a) throws AventureiroDuplicadoException;
    List<String> findAllNomes();
}
