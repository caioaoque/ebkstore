package br.mackenzie.pos.works.percistenceandclientserver.domain.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.mackenzie.pos.works.percistenceandclientserver.domain.util.DomainEntity;

@Entity
@Table(name = "order_histories")
public class OrderHistory implements DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oht_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ord_code")
    private Order order;

    @Column(name = "oht_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "oht_from", insertable = false, updatable = false)
    private Date from;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "oht_until", insertable = false, updatable = true)
    private Date until;

    public OrderHistory() {
        super();
    }

    public OrderHistory(final Order order, final OrderStatus status) {
        super();
        this.order = order;
        this.status = status;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(final OrderStatus status) {
        this.status = status;
    }

    public Date getFrom() {
        return this.from;
    }

    public void setFrom(final Date from) {
        this.from = from;
    }

    public Date getUntil() {
        return this.until;
    }

    public void setUntil(final Date until) {
        this.until = until;
    }

    @Override
    public boolean isNew() {
        return this.id != null && !this.id.equals(0L);
    }

}