package model.dao;

import model.Entity.Aventureiro;
import model.Entity.Guilda;

import java.util.List;

public interface AventureiroDao {
    void insert(Aventureiro arg);
    void update(Aventureiro arg);
    Aventureiro findById(Integer id);

    List<Aventureiro> findAllOnAGuilda(Guilda guilda);
//    void adicionarAGuilda(Aventureiro Aventureiro, Guilda guilda);
}


