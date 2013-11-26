package br.mackenzie.pos.works.persistenceandclientserver.domain.util;

import java.io.Serializable;

public interface DomainEntity<T> extends Serializable {

    public boolean isNew();

    public T getId();

}
