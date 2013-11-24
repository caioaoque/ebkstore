package br.mackenzie.pos.works.percistenceandclientserver.domain.order;

import java.io.Serializable;

public enum OrderStatus implements Serializable {

    OPENED, PAID, CANCELED;

    private static final long serialVersionUID = 1L;

}