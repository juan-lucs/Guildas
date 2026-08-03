package db;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class bancodados {

    private static Connection conn =  null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e) {
                throw new dbexception(e.getMessage());
            }
        }
        return conn;
    }

    private static Properties loadProperties() {
        try(var fs = new FileInputStream("src/db.properties")) { // abre o arquivo de properties, FS VAI REPRESENTAR O ARQUIVO (db.properties) ABERTO
            var props = new Properties(); // CRIANDO UM OBJETO VAZIO
            props.load(fs); //basicamente o objeto props pede para o FS ler os arquivos (de db.properties), e depois o props interpreta isso como propriedades do banco (.load). assim o pros não fica mais vazio e tem todas as propriedades.
            return props;
        } catch (IOException e) {
            throw new dbexception(e.getMessage());
        } // quando o bloco TRY termina, o FS é imediamente fechado com FS.CLOSE()
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            throw new dbexception(e.getMessage());
        }
    }

}


