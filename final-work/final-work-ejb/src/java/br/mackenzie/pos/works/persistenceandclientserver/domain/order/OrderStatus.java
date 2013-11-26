package br.mackenzie.pos.works.persistenceandclientserver.domain.order;

import java.io.Serializable;

public enum OrderStatus implements Serializable {

    OPENED, PAID, CANCELED;

    private static final long serialVersionUID = 1L;

}