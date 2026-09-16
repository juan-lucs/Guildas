package model.dao;

import db.BancoDados;
import model.dao.impl.AventureiroDaoJDBC;
import model.dao.impl.GuildaDaoJDBC;
import model.dao.impl.MissaoDaoJDBC;

public class DaoFactory {

    public static GuildaDao createGuildaDao() {
        return new GuildaDaoJDBC(BancoDados.getConnection());
    }

    public static MissaoDao createMissaoDao() {
        return new MissaoDaoJDBC(BancoDados.getConnection());
    }

    public static AventureiroDao createAventureiroDao() {
        return new AventureiroDaoJDBC(BancoDados.getConnection());
    }
}
