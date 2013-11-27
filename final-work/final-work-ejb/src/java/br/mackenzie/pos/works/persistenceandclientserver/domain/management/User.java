package br.mackenzie.pos.works.persistenceandclientserver.domain.management;

import br.mackenzie.pos.works.persistenceandclientserver.domain.order.Order;
import br.mackenzie.pos.works.persistenceandclientserver.domain.order.OrderStatus;
import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Comment;
import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.mackenzie.pos.works.persistenceandclientserver.domain.util.DomainEntity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User implements DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "usr_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "usr_login")
    private String login;

    @Column(name = "usr_enc_password", nullable = false)
    private String encryptedPassword;

    @Column(name = "usr_name", nullable = false)
    private String name;

    @Column(name = "usr_phone", nullable = false)
    private String phone;

    @Column(name = "usr_email", nullable = false, unique = true)
    private String email;

    @Column(name = "usr_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Address address = new Address();

    @OneToMany(mappedBy = "user")
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Order> orders = new ArrayList<>();

    @Transient
    private final Set<Ebook> ebooks = new LinkedHashSet<>();

    public User() {
        this.role = Role.ADMINISTRATOR;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getEncryptedPassword() {
        return this.encryptedPassword;
    }

    public void setEncryptedPassword(final String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments.clear();
        if (comments != null) {
            this.comments.addAll(comments);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders.clear();
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }

    public Set<Ebook> getEbooks() {
        for (Order order : orders) {
            if (OrderStatus.PAID.equals(order.getCurrentStatus())) {
                ebooks.addAll(order.getEbooks());
            }
        }
        return ebooks;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.login == null) ? 0 : this.login.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        final User other = (User) obj;
        if (this.login == null) {
            if (other.login != null) {
                return false;
            }
        } else if (!this.login.equals(other.login)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isNew() {
        return this.id == null || this.id.equals(0L);
    }

}
