package br.mackenzie.pos.works.percistenceandclientserver.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.mackenzie.pos.works.percistenceandclientserver.domain.util.DomainEntity;

@Entity
@Table(name = "publishers")
public class Publisher implements DomainEntity<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pbs_name")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
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
        if (!(obj instanceof Publisher)) {
            return false;
        }
        final Publisher other = (Publisher) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isNew() {
        return this.name != null && !this.name.isEmpty();
    }

    @Override
    public String getId() {
        return this.name;
    }

}