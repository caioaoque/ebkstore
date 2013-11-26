package br.mackenzie.pos.works.persistenceandclientserver.service;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.User;

@Stateful
@SessionScoped
public class UserSessionService {

    private static final String QUERY_BASE = String.format("SELECT u FROM User u");

    @PersistenceContext
    private EntityManager em;
    private User user;

    public void login(final String login, final String encryptedPassword) {
        final TypedQuery<User> query = this.em.createQuery(QUERY_BASE + " WHERE u.login = :login AND u.encryptedPasssword = :pass",
                User.class);
        query.setParameter("login", login);
        query.setParameter("pass", encryptedPassword);
        try {
            this.user = query.getSingleResult();
        } catch (final Exception ex) {
            throw new IllegalArgumentException("Invalid user or password.", ex);
        }
    }

    public User getUser() {
        return this.user;
    }

    @Remove
    public void logout() {
        this.user = null;
    }

}