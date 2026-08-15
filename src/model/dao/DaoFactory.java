package model.dao;

import db.bancodados;
import model.dao.impl.JogadorDaoJDBC;
import model.dao.impl.PartidaDaoJDBC;
import model.dao.impl.TimeDaoJDBC;

public class DaoFactory {

    public static TimeDao createTimeDao() {
        return new TimeDaoJDBC(bancodados.getConnection());
    }

    public static PartidaDao createPartidaDao() {
        return new PartidaDaoJDBC(bancodados.getConnection());
    }

    public static JogadorDao createJogadorDao() {
        return new JogadorDaoJDBC(bancodados.getConnection());
    }
}
