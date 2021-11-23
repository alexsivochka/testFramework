package at.database;

import org.jdbi.v3.core.Jdbi;

public class DatabaseConnection {

    public Jdbi getConnection(String dbUrl, String dbUser, String dbPassword) {
        return Jdbi.create(dbUrl, dbUser, dbPassword);
    }

}
