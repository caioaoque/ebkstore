package br.mackenzie.pos.works.persistenceandclientserver.domain.product;

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

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.User;
import br.mackenzie.pos.works.persistenceandclientserver.domain.util.DomainEntity;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment implements DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ebk_id", nullable = false)
    private Ebook ebook;

    @Column(name = "cmt_text", nullable = false)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cmt_creation", insertable = false, updatable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private Date creation;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
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
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public boolean isNew() {
        return this.id != null && !this.id.equals(0L);
    }
}
