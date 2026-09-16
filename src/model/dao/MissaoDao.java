package model.dao;

import model.entity.Missao;

import java.util.List;

public interface MissaoDao {
    void insert(Missao arg);


    List<Missao> findAll();
}
