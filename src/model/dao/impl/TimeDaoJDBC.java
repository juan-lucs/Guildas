package model.dao.impl;

import db.bancodados;
import db.dbexception;
import model.Entity.Jogador;
import model.Entity.Time;
import model.dao.TimeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TimeDaoJDBC implements TimeDao {
    private Connection conn;

    public TimeDaoJDBC(Connection c) {
        this.conn = c;
    }

    @Override
    public void insert(Time arg) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO time " +
                    "(nome,Modalidade) " +
                    "VALUES " +
                    "(? , ?)",
                    st.RETURN_GENERATED_KEYS);
            st.setString(1, arg.getNome());
            st.setString(2, String.valueOf(arg.getModalidade()));
            // pontos são DEFAULT = 0

            int linhasafetadas = st.executeUpdate();

            if (linhasafetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                        if (rs.next()) {
                    Long id = rs.getLong(1);
                    arg.setId(id);
                    }
                bancodados.closeResultSet(rs);
            } else {
                throw new dbexception("Nenhuma linha alterada");
            }
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
        }
    }

    @Override
    public void updatePontos(Time arg) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE time " +
                            "SET pontos = (?)"
                            + " WHERE id = ?"
            );
            st.setInt(1, arg.getPontos());
            st.setLong(2, arg.getId());
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
        }
    }

    @Override
    public Time findByNome(Integer id) {
        return new Time();
    }

    @Override
    public List<Time> findAll() {
        return null;
    }

    @Override
    public List<String> findAllNomes() {
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            rs = st.executeQuery("SELECT nome FROM time");
            List<String> nometimes = new ArrayList<>();

            while(rs.next()) {
                nometimes.add(rs.getString(1));
                }
            return nometimes;
        }
        catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
            bancodados.closeResultSet(rs);
        }
    }
}
