package br.mackenzie.pos.works.persistenceandclientserver.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.mackenzie.pos.works.persistenceandclientserver.domain.util.DomainEntity;

// Commented to reduce the complexity of the ORM.
//@Entity
//@Table(name = "authors")
public class Author {//implements DomainEntity<Long> {

//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "ath_id")
//    private Long id;
//
//    @Column(name = "ath_name")
//    private String name;
//
//    @Override
//    public Long getId() {
//        return this.id;
//    }
//
//    public void setId(final Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(final String name) {
//        this.name = name;
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(final Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (!(obj instanceof Author)) {
//            return false;
//        }
//        final Author other = (Author) obj;
//        if (this.name == null) {
//            if (other.name != null) {
//                return false;
//            }
//        } else if (!this.name.equals(other.name)) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public boolean isNew() {
//        return this.id != null && !this.id.equals(0L);
//    }

}