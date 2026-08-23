package model.dao.impl;

import db.bancodados;
import db.dbexception;
import enums.Classes;
import model.Entity.Aventureiro;
import model.Entity.Guilda;
import model.dao.AventureiroDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AventureiroDaoJDBC implements AventureiroDao {
    private Connection conn;

    public AventureiroDaoJDBC(Connection c) {
        this.conn = c;
    }

    @Override
    public void insert(Aventureiro arg) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO aventureiro " +
                            "(name,nivel,classe,guilda_id) " +
                            "VALUES "
                    + "(?,?,?,?)",
                    st.RETURN_GENERATED_KEYS);
            st.setString(1, arg.getNome());
            st.setInt(2, arg.getnivel());
            st.setString(3, String.valueOf(arg.getclasse()));
            st.setLong(4, arg.getGuilda().getId());

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

            }
        } catch (SQLException e ) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
        }
    }

    @Override
    public void update(Aventureiro arg) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE aventureiro "
                            + "SET name = ?, nivel = ?, classe = ?, guilda_id = ? "
                            + "WHERE Id = ?");

            st.setString(1, arg.getNome());
            st.setInt(2, arg.getnivel());
            st.setString(3, String.valueOf(arg.getclasse()));
            st.setLong(4, arg.getGuilda().getId());
            st.setLong(5, arg.getId());
            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new dbexception(e.getMessage());
        }
        finally {
            bancodados.closeStatement(st);
        }
    }

    @Override
    public Aventureiro findById(Integer id) {
        return null;
    }

    @Override
    public List<Aventureiro> findAllOnATeam(Guilda guilda) {
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM aventureiro WHERE guilda_id = ?");
            st.setLong(1, guilda.getId());
            rs = st.executeQuery();

            List<Aventureiro> list = new ArrayList<>();

            while(rs.next()) {
                Aventureiro arg = new Aventureiro();
                arg.setNome(rs.getString("name"));
                arg.setnivel(rs.getInt("nivel"));
                arg.setclasse(Classes.valueOf(rs.getString("classe")));
                arg.setGuilda(guilda);
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
    public void adicionarAoTime(Aventureiro aventureiro, Guilda guilda) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE aventureiro SET guilda_id = ? WHERE id = ?"
            );

            st.setLong(1, guilda.getId());
            st.setLong(2, aventureiro.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
        }
    }

}
