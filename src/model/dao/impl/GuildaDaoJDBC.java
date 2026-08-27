package model.dao.impl;

import db.bancodados;
import db.dbexception;
import exeption.AventureiroDuplicadoException;
import exeption.guildaNaoEncontradaException;
import model.Entity.Aventureiro;
import model.Entity.AvtrMestre;
import model.Entity.Guilda;
import model.dao.GuildaDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuildaDaoJDBC implements GuildaDao {
    private Connection conn;

    public GuildaDaoJDBC(Connection connection) {
        this.conn = connection;
    }

    @Override
    public void insert(Guilda arg) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO guilda " +
                    "(name,level) " +
                    "VALUES " +
                    "(? , ?)",
                    st.RETURN_GENERATED_KEYS);
            st.setString(1, arg.getNome());
            st.setInt(2, arg.getLevel());
            // reputacao é DEFAULT = 0

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
    public void update(Guilda arg) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE guilda " +
                            "SET name = ? , level = ?, mestre_id = ?"
                            + " WHERE id = ?"
            );
            st.setString(1, arg.getNome());
            st.setInt(2, arg.getLevel());
            st.setLong(3, arg.getMestre().getId());
            st.setLong(4, arg.getId());
            int linhas = st.executeUpdate();
            if (linhas == 0) {
                throw new dbexception("Nenhuma guilda foi atualizada.");
            }
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
        }
    }


    @Override
    public void updateReputacao(Guilda arg) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE guilda " +
                            "SET reputacao = (?)"
                            + " WHERE id = ?"
            );
            st.setInt(1, arg.getReputacao());
            st.setLong(2, arg.getId());
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
        }
    }

    @Override
    public Guilda findByNome(String nome) throws guildaNaoEncontradaException {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * " +
                    "FROM guilda " +
                    "WHERE name = ?"

            );
            st.setString(1, nome);

            rs = st.executeQuery();
            var guilda = new Guilda();
            if (rs.next()) {
                guilda.setId(rs.getLong("id"));
                guilda.setNome(rs.getString("name"));
            } else {
                throw new guildaNaoEncontradaException("guilda nao encontrada");
            }
            return guilda;
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
            bancodados.closeResultSet(rs);
        }
    }

    @Override
    public List<Guilda> findAll() {
        return null;
    }

    @Override
    public boolean pesquisarAventureiro(Guilda g, Aventureiro a) throws AventureiroDuplicadoException {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * " +
                    "FROM aventureiro " +
                    "WHERE id = ? AND guilda_id = ?");
            st.setLong(1, a.getId());
            st.setLong(2, g.getId());
            rs = st.executeQuery();

            if(rs.next()) {
                throw new AventureiroDuplicadoException("Aventureiro já está cadastrado nessa Guilda!");
            }
            return false;
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        }
    }
    @Override
    public List<String> findAllNomes() {
        ResultSet rs = null;
        Statement st = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT name FROM guilda");
            List<String> nomeguildas = new ArrayList<>();

            while(rs.next()) {
                nomeguildas.add(rs.getString(1));
                }
            return nomeguildas;
        }
        catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
            bancodados.closeResultSet(rs);
        }
    }

}
