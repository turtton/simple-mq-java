package net.turtton.simplemq.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgresql {
    String url;
    String user;
    String password;

    public Postgresql() {
        var url = System.getenv("DATABASE_URL");
        if (url == null) {
            url = "jdbc:postgresql://localhost:5432/postgres";
        }
        this.url = url;
        var user = System.getenv("DATABASE_USER");
        if (user == null) {
            user = "postgres";
        }
        this.user = user;
        var password = System.getenv("DATABASE_PASSWORD");
        if (password == null) {
            password = "postgres";
        }
        this.password = password;

        try (var con = getConnection()) {
            var statement = con.createStatement();
            var schemaFile = this.getClass().getResourceAsStream("/schema.sql");
            var sql = new String(schemaFile.readAllBytes());
            System.out.println(sql);
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
