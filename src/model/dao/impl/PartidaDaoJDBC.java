package model.dao.impl;

import db.bancodados;
import db.dbexception;
import model.Entity.Partida;
import model.Entity.Time;
import model.dao.PartidaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    var id = rs.getLong(1);
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
    public List<Partida> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT partida.*, " +
                            "       t1.nome AS Time1Nome, " +
                            "       t2.nome AS Time2Nome " +
                            "FROM partida " +
                            "INNER JOIN time t1 ON partida.time1_id = t1.id " +
                            "INNER JOIN time t2 ON partida.time2_id = t2.id;"
            );
            rs = st.executeQuery();
            List<Partida> list = new ArrayList<>();
            Map<Long, Time> map = new HashMap<>();

            while(rs.next()) {
                var time1Id = map.get(rs.getLong("t1_id"));
                if (time1Id == null) {
                    Time time1 = instanciarTime(rs, "time1_id", "Time1Nome");
                    map.put(rs.getLong("t1_id"), time1Id);
                }

                var time2Id= map.get(rs.getLong("t2_id"));
                if (time2Id == null) {
                    Time time2 = instanciarTime(rs, "time2_id", "Time2Nome");
                    map.put(rs.getLong("t2_id"), time2Id);
                }

                Partida partida = instanciarPartida(rs, time1Id, time2Id);
                list.add(partida);
            }
            return list;
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        } finally {
            bancodados.closeStatement(st);
            bancodados.closeResultSet(rs);
        }
    }

    private Partida instanciarPartida(ResultSet rs, Time time1Id, Time time2Id) throws SQLException {
        return new Partida(time1Id, time2Id, rs.getDate("data_partida").toLocalDate());
    }

    private Time instanciarTime(ResultSet rs, String colunaId, String colunaNome) throws SQLException {
        Time time = new Time();
        time.setId(rs.getLong(colunaId));
        time.setNome(rs.getString(colunaNome));
        return time;
    }


//    @Override
//    public void update(Partida arg) {
//
//    }

    @Override
    public Partida findByData(Integer id) {
       return null;
    }
}

