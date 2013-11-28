package br.mackenzie.pos.works.persistenceandclientserver.domain.order;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.persistenceandclientserver.domain.util.DomainEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "orders")
public class Order implements DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ord_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usr_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ord_creation", insertable = false, updatable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date creation;

    @ManyToMany
    @JoinTable(name = "order_ebooks", joinColumns = @JoinColumn(name = "ord_id", referencedColumnName = "ord_id"),
            inverseJoinColumns = @JoinColumn(name = "ebk_id", referencedColumnName = "ebk_id"))
    private final List<Ebook> ebooks = new ArrayList<>();

//    Commented to reduce the complexity of ORM.
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    private final List<OrderHistory> history;
    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreation() {
        return this.creation;
    }

    public void setCreation(final Date creation) {
        this.creation = creation;
    }

    public List<Ebook> getEbooks() {
        return this.ebooks;
    }

    public void setEbooks(final List<Ebook> ebooks) {
        this.ebooks.clear();
        if (ebooks != null) {
            this.ebooks.addAll(ebooks);
        }
    }

//    Commented to reduce the complexity of ORM.
//    public List<OrderHistory> getHistory() {
//        return this.history;
//    }
//
//    public void setHistory(final List<OrderHistory> history) {
//        this.history = history;
//    }
//    @PreUpdate
//    @PrePersist
//    void onUpdate() {
//        if (this.history.isEmpty() || (this.currentStatus != null && !this.currentStatus.equals(this.history.get(this.history.size() - 1).getStatus()))) {
//            this.history.add(new OrderHistory(this, this.currentStatus));
//        }
//    }
    @Override
    public boolean isNew() {
        return this.id == null || this.id.equals(0L);
    }

}
