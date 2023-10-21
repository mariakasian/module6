package com.maria;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


class ClientServiceTests {
    private Connection connection;
    private ClientService clientService;

    @BeforeEach
    public void beforeEach() throws SQLException, IOException {
        final String connectionUrl = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        new DatabaseMigrationService().migrateDb(connectionUrl);
        connection = DriverManager.getConnection(connectionUrl);
        clientService = new ClientService(connection);
        clientService.clear();
    }

    @AfterEach
    public void afterEach() throws SQLException {
        connection.close();
    }

    @Test
    void testThatClientCreatedAndGetByIdWorksCorrectly() throws SQLException {
        String name = "Sviatoslav Vakarchuk";
        long id = clientService.create(name);
        String created = clientService.getById(id);

        Assertions.assertEquals(name, created);
    }

    @Test
    void testThatSetNameWorksCorrectly() throws SQLException {
        String name = "Sviatoslav Vakarchuk";
        long id = clientService.create(name);

        String newName = "Lady Gaga";
        clientService.setName(id, newName);
        String updated = clientService.getById(id);

        Assertions.assertEquals(newName, updated);
    }

    @Test
    public void testDelete() throws SQLException {
        String name = "Sviatoslav Vakarchuk";
        long id = clientService.create(name);

        clientService.deleteById(id);

        Assertions.assertNull(clientService.getById(id));
    }

    @Test
    public void getAllTest() throws SQLException {
        List<Client> expectedClients = new ArrayList<>();
        String name = "Sviatoslav Vakarchuk";
        Client expected = new Client();
        expected.setCname(name);
        long id = clientService.create(name);
        expected.setClient_id(id);
        expectedClients.add(expected);

        List<Client> actualClients = clientService.listAll();

        Assertions.assertEquals(expectedClients, actualClients);
    }
}
