package model.dao;

import model.Entity.Time;

import java.util.List;

public interface TimeDao {
    void insert(Time arg);
    void update(Time arg);
    Time findById(Integer id);
    List<Time> findAll();
}

