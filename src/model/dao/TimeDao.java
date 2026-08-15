package model.dao;

import enums.Modalidade;
import model.Entity.Time;

import java.util.List;

public interface TimeDao {
    void insert(String nometime, Modalidade modalidade);
    void updatePontos(Time arg);
    Time findByNome(Integer id);
    List<Time> findAll();
    List<String> findAllNomes();
}

