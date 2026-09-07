package model.dao.impl;

import db.bancodados;
import db.dbexception;
import model.Entity.Missao;
import model.dao.MissaoDao;

import java.sql.*;
import java.util.List;

public class MissaoDaoJDBC implements MissaoDao {
    private Connection conn;

    public MissaoDaoJDBC(Connection c) {
        this.conn = c;
    }


    @Override
    public void insert(Missao arg) {
        PreparedStatement stMissao = null;
        PreparedStatement stParticipante = null;
        ResultSet rs = null;
            try {
                conn.setAutoCommit(false);

                stMissao = conn.prepareStatement(
                        "INSERT INTO missao "
                               + "(name,dificuldade,guilda_id,resultado) "
                                + "VALUES "
                        + "(?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
            stMissao.setString(1, arg.getNome());
            stMissao.setInt(2, arg.getDificuldade());
            stMissao.setLong(3, arg.getGuilda().getId());
            stMissao.setString(4, String.valueOf(arg.getResultado()));
            int linhasafetadas = stMissao.executeUpdate();

            if (linhasafetadas == 0) {
             throw new dbexception("ERRO, NENHUMA LINHA ALTERADA");
            }
            rs = stMissao.getGeneratedKeys();
            if (rs.next()) {
             arg.setId(rs.getInt(1));
            }

            // AGORA QUE MISSAO FOI CRIADA E EXISTE UM ID, SE CRIA A TABELA DE PARTICIPANTES DESSA MISSÃO
            stParticipante = conn.prepareStatement("INSERT INTO participantesMissao (missao_id, aventureiro_id) " +
                    "VALUES (?, ?)");

                for (var av : arg.getParticipantes()) {
                    stParticipante.setInt(1, arg.getId());
                    stParticipante.setInt(2, av.getId());
                    stParticipante.addBatch(); // acumula, não executa ainda
                }
                stParticipante.executeBatch();

                conn.commit();
    }  catch (SQLException e) { // basicamente isso diz que se der um erro, é pra tentar dar roolback
                try {
                    conn.rollback(); // desfaz TUDO se der errado — a missão inserida também morre
                } catch (SQLException rollbackEx) {
                    // o rollback pode falhar, se isso acontecer fudeo de vez já era não há mais volta
                    throw new dbexception("Falha no rollback: " + rollbackEx.getMessage());
                }
                throw new dbexception(e.getMessage()); // isso daqui faz parte do catch ali de cima
                                                    // que está pegando o primeiro SQLException

            } finally {
                // sempre voltar o autocommit pro estado normal
                // porque o conn será usado de novo
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) { // Existe caso o conn esteja fechado, aí vai lançar uma exception
                    throw new dbexception(e.getMessage());
                }
                bancodados.closeResultSet(rs);
                bancodados.closeStatement(stMissao);
                bancodados.closeStatement(stParticipante);
            }
    }

    @Override
    public List<Missao> findAll() {
//        PreparedStatement st = null;
//        ResultSet rs = null;
//        try {
//            st = conn.prepareStatement(
//                    "SELECT missao.*, " +
//                            "       g.name AS GuildaNome " +
//                            "FROM missao " +
//                            "INNER JOIN guilda g ON missao.guilda_id = g.id;"
//            );
//            rs = st.executeQuery();
//            List<Missao> list = new ArrayList<>();
//            Map<Long, Guilda> map = new HashMap<>();
//
//            while(rs.next()) {
//                var guildaId = map.get(rs.getLong("guilda_id"));
//                if (guildaId == null) {
//                    Guilda guilda = instanciarGuilda(rs, "guilda_id", "GuildaNome");
//                    map.put(rs.getLong("guilda_id"), guildaId);
//                }
//
//                Missao missao = instanciarMissao(rs, guildaId);
//                list.add(missao);
//            }
//            return list;
//        } catch (SQLException e) {
//            throw new dbexception(e.getMessage());
//        } finally {
//            bancodados.closeStatement(st);
//            bancodados.closeResultSet(rs);
//        }
        return null;
    }

}


//    @Override
//    public void update(Missao arg) {
//
//    }


