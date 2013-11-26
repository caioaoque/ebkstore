package br.mackenzie.pos.works.persistenceandclientserver.domain.order;

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

@Entity
@Table(name = "orders")
public class Order implements DomainEntity<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ord_code")
    private String code;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ord_creation", insertable = false, updatable = false)
    private Date creation;

    @Column(name = "ord_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus currentStatus;

    @ManyToMany
    @Basic(optional = false)
    @JoinTable(name = "order_ebooks", joinColumns = @JoinColumn(name = "ord_code"), inverseJoinColumns = @JoinColumn(name = "ebk_id"))
    private final List<Ebook> ebooks = new ArrayList<>();

//    Commented to reduce the complexity of ORM.
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    private final List<OrderHistory> history;
    public String getCode() {
        return this.code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Date getCreation() {
        return this.creation;
    }

    public void setCreation(final Date creation) {
        this.creation = creation;
    }

    public OrderStatus getCurrentStatus() {
        return this.currentStatus;
    }

    public void setCurrentStatus(final OrderStatus currentStatus) {
        this.currentStatus = currentStatus;
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
        return this.code != null && !this.code.isEmpty();
    }

    @Override
    public String getId() {
        return this.code;
    }

}
