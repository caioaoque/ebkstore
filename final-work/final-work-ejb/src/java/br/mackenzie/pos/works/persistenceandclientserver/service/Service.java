package br.mackenzie.pos.works.persistenceandclientserver.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.mackenzie.pos.works.persistenceandclientserver.domain.util.DomainEntity;
import br.mackenzie.pos.works.persistenceandclientserver.util.dto.DTO;

/**
 * A generic service superclass containing the basic CRUD operations.
 *
 * @param <E> the type of the entity handled by this service.
 * @param <T> the type of the {@link DTO} used to build the conditions of the
 * queries for the given entity type.
 */
public abstract class Service<E extends DomainEntity<?>, T extends DTO<E>> {

    private static final String QUERY_BASE = "SELECT %s FROM %s %s";
    protected static final String EMPTY = "";

    private T params;
    private boolean hasAnyCondition = false;
    private final Class<E> type;
    protected final String alias;
    protected final Map<String, Object> parameters = new HashMap<>();

    @PersistenceContext
    protected EntityManager em;

    protected Service(final Class<E> type, final String alias) {
        this.type = type;
        this.alias = alias;
    }

    // Methods used to query for T Objects
    public synchronized List<E> find(final T params) {
        this.reset(params);
        return this.getQuery().getResultList();
    }

    private void reset(final T newParams) {
        this.params = newParams;
        this.parameters.clear();
        this.hasAnyCondition = false;
    }

    private TypedQuery<E> getQuery() {
        final TypedQuery<E> query = this.em.createQuery(this.queryBase() + this.buildConditions(this.params), this.type);
        for (final Entry<String, Object> entry : this.parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query;
    }

    private String queryBase() {
        return String.format(QUERY_BASE, this.alias, this.type.getSimpleName(), this.alias);
    }

    /**
     * This method should be implemented in order to build the conditions of the
     * query.
     *
     * @param params the object which will be used to build the conditions and
     * set the parameters of the query.
     * @return a String object that represents the conditions of the query.
     */
    protected abstract String buildConditions(T params);

    /**
     * This method should be used to build the conditions. It will check if the
     * query being built has already a condition and put an AND clause,
     * otherwise it will put a WHERE clause.
     *
     * @param condition the new condition of the query. Note that it must not
     * start with an AND or WHERE keyword.
     * @return a String containing an and or where clause based on the given
     * condition.
     */
    protected String newCondition(final String condition) {
        if (condition != null && !condition.isEmpty()) {
            if (this.hasAnyCondition) {
                return " AND " + condition;
            } else {
                return " WHERE " + condition;
            }
        }
        return EMPTY;
    }

    // Helper methods to build some types of conditions
    protected String likeCondition(final String propertyName, final String propertyValue) {
        if (propertyValue != null && !propertyValue.isEmpty()) {
            this.parameters.put(propertyName, "%" + propertyValue + "%");
            return this.newCondition("LOWER(" + this.alias + "." + propertyName + ") like lower(:" + propertyName + ")");
        }
        return EMPTY;
    }

//    private String extractParameterName(String propertyName) {
//        if (propertyName.contains(".")) {
//            return propertyName.substring(0, propertyName.indexOf("."));
//        } else {
//            return propertyName;
//        }
//    }
//    protected String inCondition(final String propertyName, final Collection<?> propertyValue) {
//        if (propertyValue != null && !propertyValue.isEmpty()) {
//            this.parameters.put(propertyName, propertyValue);
//            return this.newCondition(this.alias + "." + propertyName + " in :" + extractParameterName(propertyName));
//        }
//        return EMPTY;
//    }
    public void save(final E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("The entity which will be saved must not be null.");
        }
        if (entity.isNew()) {
            this.em.persist(entity);
        } else {
            this.em.merge(entity);
        }
    }

    public void remove(final E entity) {
        if (entity == null || entity.isNew()) {
            throw new IllegalArgumentException("The entity which will be removed must not be null or a new one.");
        }
        if (!this.em.contains(entity)) {
            this.em.remove(this.em.find(this.type, entity.getId()));
        }
    }

}
