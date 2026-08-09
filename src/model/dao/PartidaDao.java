package model.dao;

import model.Entity.Partida;

import java.util.List;

public interface PartidaDao {
    void insert(Partida arg);
//    void update(Partida arg);
    Partida findByData(Integer id);
    List<Partida> findAll();
}
