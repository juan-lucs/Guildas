package model.dao.impl;

import model.Entity.Jogador;
import model.dao.JogadorDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            st.
            )
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
    public List<Jogador> findAll() {
        return List.of();
    }
}
