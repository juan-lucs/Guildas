package model.dao;

import db.bancodados;
import model.dao.impl.AventureiroDaoJDBC;
import model.dao.impl.GuildaDaoJDBC;
import model.dao.impl.MissaoDaoJDBC;

public class DaoFactory {

    public static GuildaDao createGuildaDao() {
        return new GuildaDaoJDBC(bancodados.getConnection());
    }

    public static MissaoDao createMissaoDao() {
        return new MissaoDaoJDBC(bancodados.getConnection());
    }

    public static AventureiroDao createAventureiroDao() {
        return new AventureiroDaoJDBC(bancodados.getConnection());
    }
}
