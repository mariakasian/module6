package com.maria.database;

import org.flywaydb.core.Flyway;

import java.io.IOException;

public class DatabaseMigrationService {
    public static void migrateDb(String conUrl) throws IOException {
        Flyway flyway = Flyway
                .configure()
                .dataSource(conUrl, null, null)
                .load();

        flyway.migrate();
    }
}
