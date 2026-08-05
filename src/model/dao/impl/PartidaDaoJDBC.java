package model.dao.impl;

import db.bancodados;
import db.dbexception;
import model.Entity.Partida;
import model.dao.PartidaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PartidaDaoJDBC implements PartidaDao {
    private Connection conn;

    public PartidaDaoJDBC(Connection c) {
        this.conn = c;
    }


    @Override
    public void insert(Partida arg) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO partida "
                           + "(time1_id,time2_id,data_partida) "
                            + "VALUES "
                    + "(?, ?, ?)",
                    st.RETURN_GENERATED_KEYS);
            st.setLong(1, arg.getTime1().getId());
            st.setLong(2, arg.getTime2().getId());
            st.setDate(3, java.sql.Date.valueOf(arg.getDataPartida()));
            int linhasafetadas = st.executeUpdate();

            if (linhasafetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    arg.setId(id);
                } else {
                    throw new dbexception("ERRO, NENHUMA LINHA ALTERADA");
                }
                bancodados.closeResultSet(rs);
            }
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
    } finally {
            bancodados.closeStatement(st);
        }
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
