package model.dao.impl;

import model.Entity.Partida;
import model.dao.PartidaDao;

import java.sql.Connection;
import java.util.List;

public class PartidaDaoJDBC implements PartidaDao {
    private Connection conn;

    public PartidaDaoJDBC(Connection c) {
        this.conn = c;
    }


    @Override
    public void insert(Partida arg) {

    }

    @Override
    public void update(Partida arg) {

    }

    @Override
    public Partida findById(Integer id) {
        return null;
    }

    @Override
    public List<Partida> findAll() {
        return List.of();
    }
}
