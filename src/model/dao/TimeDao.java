package model.dao;

import model.Entity.Time;

import java.util.List;

public interface TimeDao {
    void insert(Time arg);
    void updatePontos(Time arg);
    Time findByNome(Integer id);
    List<Time> findAll();
}

