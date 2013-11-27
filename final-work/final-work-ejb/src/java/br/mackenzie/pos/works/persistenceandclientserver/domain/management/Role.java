package br.mackenzie.pos.works.persistenceandclientserver.domain.management;

import java.io.Serializable;

public enum Role implements Serializable {

    CUSTOMER("cliente"), ADMINISTRATOR("administrador");

    private static final long serialVersionUID = 1L;

    private final String translation;

    private Role(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return this.translation;
    }

}
