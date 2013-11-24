package br.mackenzie.pos.works.percistenceandclientserver.domain.management;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.mackenzie.pos.works.percistenceandclientserver.domain.product.Comment;

@Table
@Entity(name = "customers")
public class Customer extends User {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public Customer() {
        super(Role.CUSTOMER);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj)) {
            return false;
        }
        return obj instanceof Customer;
    }

}