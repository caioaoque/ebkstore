package br.mackenzie.pos.works.persistenceandclientserver.domain.management;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "usr_address_line1", nullable = false)
    private String line1;

    @Column(name = "usr_address_line2")
    private String line2;

    @Column(name = "usr_address_zip_code", nullable = false)
    private String zipCode;

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

}
