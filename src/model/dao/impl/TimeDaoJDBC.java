package model.dao.impl;

import model.Entity.Time;
import model.dao.TimeDao;

import java.sql.Connection;
import java.util.List;

public class TimeDaoJDBC implements TimeDao {
    private Connection conn;

    public TimeDaoJDBC(Connection c) {
        this.conn = c;
    }

    @Override
    public void insert(Time arg) {

    }

    @Override
    public void update(Time arg) {

    }

    @Override
    public Time findById(Integer id) {
        return null;
    }

    @Override
    public List<Time> findAll() {
        return List.of();
    }
}
