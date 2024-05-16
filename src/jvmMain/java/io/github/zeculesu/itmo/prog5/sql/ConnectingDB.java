package io.github.zeculesu.itmo.prog5.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectingDB {

    public static Connection getConnection(String jdbcUrl, String username, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        return connection;
    }
}
