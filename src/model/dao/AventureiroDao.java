package model.dao;

import model.entity.Aventureiro;
import model.entity.Guilda;

import java.util.List;

public interface AventureiroDao {
    void insert(Aventureiro arg);
    void update(Aventureiro arg);
    Aventureiro findById(Integer id);

    List<Aventureiro> findAllOnAGuilda(Guilda guilda);

}


