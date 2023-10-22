package com.maria.client_service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement selectMaxIdSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement getAllSt;
    private PreparedStatement clearSt;

    public ClientService(Connection con) throws SQLException {
        createSt = con.prepareStatement(
                "INSERT INTO client (cname) VALUES(?)"
        );

        getByIdSt = con.prepareStatement(
                "SELECT cname FROM client WHERE client_id = ?"
        );

        selectMaxIdSt = con.prepareStatement(
                "SELECT max(client_id) AS maxId FROM client"
        );

        updateSt = con.prepareStatement(
                "UPDATE client SET cname = ? WHERE client_id = ?"
        );

        deleteByIdSt = con.prepareStatement(
                "DELETE FROM client WHERE client_id = ?"
        );

        getAllSt = con.prepareStatement(
                "SELECT client_id, cname FROM client"
        );

        clearSt = con.prepareStatement(
                "DELETE FROM client"
        );
    }

    public long create(String name) throws SQLException, InvalidNameException {
        nameValidation(name);

        createSt.setString(1, name);
        createSt.executeUpdate();

        long id;

        try(ResultSet rs = selectMaxIdSt.executeQuery()) {
            rs.next();
            id = rs.getLong("maxId");
        }
        return id;
    }

    public String getById(long id) throws SQLException, InvalidIdException {
        idValidation(id);

        getByIdSt.setLong(1, id);

        try(ResultSet rs = getByIdSt.executeQuery()) {
            if (!rs.next()) {
                return null;
            }

            return rs.getString("cname");
        }
    }

    public void setName(long id, String name) throws SQLException, InvalidIdException, InvalidNameException {
        idValidation(id);
        nameValidation(name);

        updateSt.setString(1, name);
        updateSt.setLong(2, id);
        updateSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException, InvalidIdException {
        idValidation(id);

        deleteByIdSt.setLong(1, id);
        deleteByIdSt.executeUpdate();
    }

    public List<Client> listAll() throws SQLException {
        try (ResultSet rs = getAllSt.executeQuery()) {
            List<Client> clients = new ArrayList<>();
            while (rs.next()) {
                Client client = new Client();
                client.setClient_id(rs.getLong("client_id"));
                client.setCname(rs.getString("cname"));
                clients.add(client);
            }
            return clients;
        }
    }

    public void clear() throws SQLException {
        clearSt.executeUpdate();
    }

    public static class InvalidNameException extends Exception {
        public InvalidNameException(String message) {
            super(message);
        }
    }

    public static class InvalidIdException extends Exception {
        public InvalidIdException(String message) {
            super(message);
        }
    }

    public void nameValidation(String name) throws InvalidNameException {
        if (name.length() < 2 || name.length() > 1000) {
            throw new InvalidNameException("Довжина імені повинна бути від 2 до 1000 символів.");
        }
    }

    public void idValidation(long id) throws InvalidIdException {
        if (id <= 0) {
            throw new InvalidIdException("Id має бути більше 0.");
        }
    }
}
