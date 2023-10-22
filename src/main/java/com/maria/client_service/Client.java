package com.maria.client_service;

import java.util.Objects;

public class Client {
    private long client_id;
    private String cname;

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return client_id == client.client_id && Objects.equals(cname, client.cname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client_id, cname);
    }
}
