package com.maria;

import org.flywaydb.core.Flyway;

import java.io.IOException;

public class DatabaseMigrationService {
    public static void migrateDb(Database db) throws IOException {
        String connectionUrl = new Prefs().getString(Prefs.DB_URL);

        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();

        flyway.migrate();
    }
}
