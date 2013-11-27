package br.mackenzie.pos.works.persistenceandclientserver.service;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.Address;
import br.mackenzie.pos.works.persistenceandclientserver.domain.management.Role;
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

    public boolean login(final String login, final String encryptedPassword) {
        final TypedQuery<User> query = this.em.createQuery(QUERY_BASE + " WHERE u.login = :login AND u.encryptedPassword = :pass",
                User.class);
        query.setParameter("login", login);
        query.setParameter("pass", encryptedPassword);
        try {
            this.user = query.getSingleResult();
            return true;
        } catch (final Exception ex) {
            return false;
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
        newUser.setRole(Role.ADMINISTRATOR);
        Address address = new Address();
        address.setLine1("rua A, 123");
        address.setLine2("apartamento 10");
        address.setZipCode("01111000");
        newUser.setAddress(address);
        this.em.persist(newUser);

        User newCustomer = new User();
        newCustomer.setLogin("customer");
        newCustomer.setEncryptedPassword("81DC9BDB52D04DC20036DBD8313ED055");
        newCustomer.setEmail("asdasdad@adssahd.com");
        newCustomer.setName("Customer");
        newCustomer.setPhone("123123123123");
        newCustomer.setRole(Role.CUSTOMER);
        Address customerAddress = new Address();
        customerAddress.setLine1("rua B, 456");
        customerAddress.setLine2("apartamento 37");
        customerAddress.setZipCode("02222000");
        newCustomer.setAddress(customerAddress);
        this.em.persist(newCustomer);
    }

}
