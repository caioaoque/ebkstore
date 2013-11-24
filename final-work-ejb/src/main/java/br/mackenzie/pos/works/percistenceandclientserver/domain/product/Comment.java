package br.mackenzie.pos.works.percistenceandclientserver.domain.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.mackenzie.pos.works.percistenceandclientserver.domain.management.Customer;
import br.mackenzie.pos.works.percistenceandclientserver.domain.util.DomainEntity;

@Entity
@Table(name = "comments")
public class Comment implements DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usr_login")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ebk_id")
    private Ebook ebook;

    @Column(name = "cmt_text")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public Ebook getEbook() {
        return this.ebook;
    }

    public void setEbook(final Ebook ebook) {
        this.ebook = ebook;
    }

    public String getText() {
        return this.text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public Date getCreation() {
        return this.creation;
    }

    public void setCreation(final Date creation) {
        this.creation = creation;
    }

    @Override
    public boolean isNew() {
        return this.id != null && !this.id.equals(0L);
    }
}