package model.dao.impl;

import db.bancodados;
import db.dbexception;
import model.Entity.Guilda;
import model.Entity.Missao;
import model.dao.MissaoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissaoDaoJDBC implements MissaoDao {
    private Connection conn;

    public MissaoDaoJDBC(Connection c) {
        this.conn = c;
    }


    @Override
    public void insert(Missao arg) {
        PreparedStatement st = null;
            try {
                st = conn.prepareStatement(
                        "INSERT INTO missao "
                               + "(name,dificuldade,participantes,guilda_id,equipe_recomendada,resultado) "
                                + "VALUES "
                        + "(?, ?, ?, ?, ?, ?)",
                        st.RETURN_GENERATED_KEYS);
            st.setString(1, arg.getNome());
            st.setInt(2, arg.getDificuldade());
            st.setString(3, arg.getParticipantes());
            st.setLong(4, arg.getGuilda().getId());
            st.setString(5, arg.getEquipeRecomendada());
            st.setString(6, arg.getResultado());
            int linhasafetadas = st.executeUpdate();

            if (linhasafetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    var id = rs.getLong(1);
                    arg.setId(id);
                }
                bancodados.closeResultSet(rs);
                }
            else {
                throw new dbexception("ERRO, NENHUMA LINHA ALTERADA");
            }

        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
    } finally {
            bancodados.closeStatement(st);
        }
    }

    @Override
    public List<Missao> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT missao.*, " +
                            "       g.name AS GuildaNome " +
                            "FROM missao " +
                            "INNER JOIN guilda g ON missao.guilda_id = g.id;"
            );
            rs = st.executeQuery();
            List<Missao> list = new ArrayList<>();
            Map<Long, Guilda> map = new HashMap<>();

            while(rs.next()) {
                var guildaId = map.get(rs.getLong("guilda_id"));
                if (guildaId == null) {
                    Guilda guilda = instanciarGuilda(rs, "guilda_id", "GuildaNome");
                    map.put(rs.getLong("guilda_id"), guildaId);
                }

                Missao missao = instanciarMissao(rs, guildaId);
                list.add(missao);
            }
            return list;
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
            bancodados.closeResultSet(rs);
        }
    }

    private Missao instanciarMissao(ResultSet rs, Guilda guilda) throws SQLException {
        Missao missao = new Missao();
        missao.setId(rs.getLong("id"));
        missao.setNome(rs.getString("name"));
        missao.setDificuldade(rs.getInt("dificuldade"));
        missao.setParticipantes(rs.getString("participantes"));
        missao.setGuilda(guilda);
        missao.setEquipeRecomendada(rs.getString("equipe_recomendada"));
        missao.setResultado(rs.getString("resultado"));
        return missao;
    }

    private Guilda instanciarGuilda(ResultSet rs, String colunaId, String colunaNome) throws SQLException {
        Guilda guilda = new Guilda();
        guilda.setId(rs.getLong(colunaId));
        guilda.setNome(rs.getString(colunaNome));
        return guilda;
    }
}


//    @Override
//    public void update(Missao arg) {
//
//    }


