package model.dao;

import model.Entity.Missao;

import java.util.List;

public interface MissaoDao {
    void insert(Missao arg);
//    void update(Missao arg);

    List<Missao> findAll();
}
