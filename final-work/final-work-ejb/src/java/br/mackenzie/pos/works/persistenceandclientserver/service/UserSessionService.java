package br.mackenzie.pos.works.persistenceandclientserver.service;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.Customer;
import br.mackenzie.pos.works.persistenceandclientserver.domain.management.Role;
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
        final TypedQuery<User> query = this.em.createQuery(QUERY_BASE + " WHERE u.login = :login AND u.encryptedPassword = :pass",
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

    public void logout() {
        this.user = null;
    }

    public boolean hasRole(Role role) {
        return this.user != null && role.equals(this.user.getRole());
    }

    public boolean isLogged() {
        return this.user != null;
    }

    public void insert() {
        User newUser = new User();
        newUser.setLogin("admin");
        newUser.setEncryptedPassword("21232F297A57A5A743894A0E4A801FC3");
        newUser.setEmail("iasdjoisjd@daspkds.com");
        newUser.setName("Administrator");
        newUser.setPhone("4768187644");
        this.em.persist(newUser);
        
        Customer newCustomer = new Customer();
        newCustomer.setLogin("customer");
        newCustomer.setEncryptedPassword("81DC9BDB52D04DC20036DBD8313ED055");
        newCustomer.setEmail("asdasdad@adssahd.com");
        newCustomer.setName("Customer");
        newCustomer.setPhone("123123123123");
        this.em.persist(newCustomer);
    }

}
