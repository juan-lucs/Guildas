package model.dao.impl;

import db.bancodados;
import db.dbexception;
import enums.Modalidade;
import exeption.TimeNaoEncontradoException;
import model.Entity.Jogador;
import model.Entity.Time;
import model.dao.TimeDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TimeDaoJDBC implements TimeDao {
    private Connection conn;

    public TimeDaoJDBC(Connection c) {
        this.conn = c;
    }

    @Override
    public void insert(String nome, Modalidade modalidade) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO time " +
                    "(nome,Modalidade) " +
                    "VALUES " +
                    "(? , ?)",
                    st.RETURN_GENERATED_KEYS);
            st.setString(1, nome);
            st.setString(2, String.valueOf(modalidade));
            // pontos são DEFAULT = 0

            int linhasafetadas = st.executeUpdate();
            if (linhasafetadas < 0) {
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
    public Time findByNome(String nome) throws TimeNaoEncontradoException{
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * " +
                            "FROM time " +
                            "JOIN jogador " +
                            "ON jogador.time_id = time.id " +
                            "WHERE time.nome = ?"
            );
            st.setString(1, nome);

            rs = st.executeQuery();
            var time = new Time();
            var j = new Jogador();
            Set<Jogador> jogadores = new HashSet<>();
            if (rs.next()) {
                time.setId(rs.getLong("id"));
                time.setNome(rs.getString("nome"));
                j.setNome("jogador.nome");
                jogadores.add(j);
            } else {
                throw new TimeNaoEncontradoException("time nao encontrado");
            }
            time.setJogadores(jogadores);
            return time;
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
            bancodados.closeResultSet(rs);
        }
    }

    @Override
    public List<Time> findAll() {
        return null;
    }

    @Override
    public List<String> findAllNomes() {
        ResultSet rs = null;
        Statement st = null;
        try {
            st = conn.createStatement();
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
