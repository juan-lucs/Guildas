package model.dao;

import model.Entity.Jogador;

import java.util.List;

public interface JogadorDao {
    void insert(Jogador arg);
    void update(Jogador arg);
    Jogador findById(Integer id);
    List<Jogador> findAll();
}
