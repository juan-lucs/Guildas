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


    private static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("src/db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new dbexception(e.getMessage());
        }
    }
}


