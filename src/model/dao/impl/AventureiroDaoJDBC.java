package model.dao.impl;

import db.BancoDados;
import db.DbException;
import enums.Classes;
import model.entity.Aventureiro;
import model.entity.Guilda;
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
            st.setInt(2, arg.getNivel());
            st.setString(3, String.valueOf(arg.getClasse()));
            st.setLong(4, arg.getGuilda().getId());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                    if (rs.next()) {
                        arg.setId(rs.getInt(1 ));
                    }
                BancoDados.closeResultSet(rs);
                }   else {
                throw new DbException("ERRO, NENHUMA LINHA ALTERADA");

            }
        } catch (SQLException e ) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDados.closeStatement(st);
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
            st.setInt(2, arg.getNivel());
            st.setString(3, String.valueOf(arg.getClasse()));
            st.setLong(4, arg.getGuilda().getId());
            st.setLong(5, arg.getId());
            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            BancoDados.closeStatement(st);
        }
    }

    @Override
    public Aventureiro findById(Integer id) {
        return null;
    }

    @Override
    public List<Aventureiro> findAllOnAGuilda(Guilda guilda) {
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
                arg.setNivel(rs.getInt("nivel"));
                arg.setClasse(Classes.valueOf(rs.getString("classe")));
                arg.setGuilda(guilda);
                list.add(arg);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            BancoDados.closeStatement(st);
            BancoDados.closeResultSet(rs);
        }

    }


}
