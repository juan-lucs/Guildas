package model.dao.impl;

import model.Entity.Jogador;
import model.dao.JogadorDao;

import java.sql.Connection;
import java.util.List;

public class JogadorDaoJDBC implements JogadorDao {
    private Connection conn;

    public JogadorDaoJDBC(Connection c) {
        this.conn = c;
    }

    @Override
    public void insert(Jogador arg) {

    }

    @Override
    public void update(Jogador arg) {

    }

    @Override
    public Jogador findById(Integer id) {
        return null;
    }

    @Override
    public List<Jogador> findAll() {
        return List.of();
    }
}
