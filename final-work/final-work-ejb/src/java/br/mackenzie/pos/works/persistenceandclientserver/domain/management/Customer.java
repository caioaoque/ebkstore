package br.mackenzie.pos.works.persistenceandclientserver.domain.management;

import br.mackenzie.pos.works.persistenceandclientserver.domain.order.Order;
import br.mackenzie.pos.works.persistenceandclientserver.domain.order.OrderStatus;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Comment;
import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.Transient;

@Table
@Entity(name = "customers")
public class Customer extends User {

    private static final long serialVersionUID = 1L;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "customer")
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private final List<Order> orders = new ArrayList<>();

    @Transient
    private final Set<Ebook> ebooks = new LinkedHashSet<>();

    public Customer() {
        super(Role.CUSTOMER);
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
        return prime * super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return obj instanceof Customer;
    }

}
