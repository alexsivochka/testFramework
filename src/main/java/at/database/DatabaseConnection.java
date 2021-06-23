package at.database;

import org.jdbi.v3.core.Jdbi;

public class DatabaseConnection {

    private Jdbi jdbi;

    public Jdbi getConnection(String DBUrl, String DBUser, String DBPassword) {
        jdbi = Jdbi.create(DBUrl, DBUser, DBPassword);
        return jdbi;
    }

}
