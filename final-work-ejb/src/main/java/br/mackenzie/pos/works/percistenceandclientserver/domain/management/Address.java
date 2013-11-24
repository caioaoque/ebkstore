package br.mackenzie.pos.works.percistenceandclientserver.domain.management;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.mackenzie.pos.works.percistenceandclientserver.domain.util.DomainEntity;

@Entity
@Table(name = "address")
public class Address implements DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "adr_id")
    private Long id;

    @Column(name = "adr_line1")
    private String line1;

    @Column(name = "adr_line2")
    private String line2;

    @Column(name = "adr_zip_code")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "usr_login")
    private Customer customer;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getLine1() {
        return this.line1;
    }

    public void setLine1(final String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return this.line2;
    }

    public void setLine2(final String line2) {
        this.line2 = line2;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean isNew() {
        return this.id != null && !this.id.equals(0L);
    }

}