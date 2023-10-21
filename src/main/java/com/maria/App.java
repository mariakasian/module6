package com.maria;

import java.io.IOException;

import static com.maria.DatabaseMigrationService.migrateDb;
import static com.maria.DatabaseQueryService.*;

public class App {
    public static void main(String[] args) throws IOException {
        Database db = Database.getInstance();

        migrateDb(db);

        findMaxSalaryWorker(db);
        findMaxProjectsClient(db);
        findLongestProject(db);
        findYoungestEldestWorkers(db);
        printProjectPrices(db);

        db.connectionClose();
    }
}