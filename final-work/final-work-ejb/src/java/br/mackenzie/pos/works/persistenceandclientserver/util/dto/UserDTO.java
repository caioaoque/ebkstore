package br.mackenzie.pos.works.persistenceandclientserver.util.dto;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.User;

public class UserDTO implements DTO<User> {

    private String login;

    private String name;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
