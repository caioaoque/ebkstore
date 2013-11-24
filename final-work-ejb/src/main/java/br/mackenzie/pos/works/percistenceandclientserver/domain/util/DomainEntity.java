package br.mackenzie.pos.works.percistenceandclientserver.domain.util;

import java.io.Serializable;

public interface DomainEntity<T> extends Serializable {

    public boolean isNew();

    public T getId();

}
