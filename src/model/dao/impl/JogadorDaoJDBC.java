package model.dao.impl;

import db.bancodados;
import db.dbexception;
import model.Entity.Jogador;
import model.Entity.Time;
import model.dao.JogadorDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogadorDaoJDBC implements JogadorDao {
    private Connection conn;

    public JogadorDaoJDBC(Connection c) {
        this.conn = c;
    }

    @Override
    public void insert(Jogador arg) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO jogador " +
                            "(nome,idade,posicao,time_id) " +
                            "VALUES "
                    + "(?,?,?,?)",
                    st.RETURN_GENERATED_KEYS);
            st.setString(1, arg.getNome());
            st.setInt(2, arg.getIdade());
            st.setString(3, arg.getPosicao());
            st.setLong(4, arg.getTime().getId());

            int linhasafetadas = st.executeUpdate();

            if (linhasafetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                    if (rs.next()) {
                    var id = rs.getLong(1);
                    arg.setId(id);
                    }
                bancodados.closeResultSet(rs);
                }   else {
                throw new dbexception("ERRO, NENHUMA LINHA ALTERADA");
                bancodados.closeResultSet(rs);
            }
        } catch (SQLException e ) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
        }
    }

    @Override
    public void update(Jogador arg) {
    }

    @Override
    public Jogador findById(Integer id) {
        return null;
    }

    @Override
    public List<Jogador> findAllOnATeam(Time time) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM jogador WHERE time_id = ?");
            st.setLong(1, time.getId());
            rs = st.executeQuery();

            List<Jogador> list = new ArrayList<>();

            while(rs.next()) {
                Jogador arg = new Jogador();
                arg.setNome(rs.getString("nome"));
                arg.setIdade(rs.getInt("idade"));
                arg.setPosicao(rs.getString("posicao"));
                arg.setTime(time);
                list.add(arg);
            }
            return list;
        }
        catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
            bancodados.closeResultSet(rs);
        }

    }

    @Override
    public void adicionarAoTime(Jogador jogador, Time time) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE jogador SET time_id = ? WHERE id = ?"
            );

            st.setLong(1, time.getId());
            st.setLong(2, jogador.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
        }
    }
}
