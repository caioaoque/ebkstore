package br.mackenzie.pos.works.percistenceandclientserver.domain.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.mackenzie.pos.works.percistenceandclientserver.domain.util.DomainEntity;

@Entity
@Table(name = "ebooks")
public class Ebook implements DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ebk_id")
    private Long id;

    @Column(name = "ebk_title")
    private String title;

    @Column(name = "pages")
    private int pages;

    @Column(name = "ebk_desc")
    private String description;

    @Column(name = "ebk_price")
    private double price;

    @ElementCollection
    @MapKeyJoinColumn(name = "lng_name", nullable = false)
    @CollectionTable(name = "ebook_contents", joinColumns = @JoinColumn(name = "ebk_id"))
    @AttributeOverrides({ @AttributeOverride(name = "value.cover", column = @Column(name = "cover")),
            @AttributeOverride(name = "value.content", column = @Column(name = "content")) })
    private final Map<Language, EbookForLanguage> ebooksForLanguage = new HashMap<Language, EbookForLanguage>();

    @ManyToMany
    @Basic(optional = false)
    @JoinTable(name = "ebook_genres", joinColumns = @JoinColumn(name = "ebk_id"), inverseJoinColumns = @JoinColumn(name = "gnr_name"))
    private final List<Genre> genres = new ArrayList<Genre>();

    @ManyToMany
    @Basic(optional = false)
    @JoinTable(name = "ebook_authors", joinColumns = @JoinColumn(name = "ebk_id"), inverseJoinColumns = @JoinColumn(name = "ath_id"))
    private final List<Author> authors = new ArrayList<Author>();

    @OneToMany(mappedBy = "ebook", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @JoinColumn(name = "pbs_name")
    private Publisher publisher;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(final int pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public Map<Language, EbookForLanguage> getEbooksForLanguage() {
        return this.ebooksForLanguage;
    }

    public void setEbooksForLanguage(final Map<Language, EbookForLanguage> ebooksForLanguage) {
        this.ebooksForLanguage.clear();
        if (ebooksForLanguage != null) {
            this.ebooksForLanguage.putAll(ebooksForLanguage);
        }

    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(final List<Genre> genres) {
        this.genres.clear();
        if (genres != null) {
            this.genres.addAll(genres);
        }
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(final List<Author> authors) {
        this.authors.clear();
        if (authors != null) {
            this.authors.addAll(authors);
        }
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments = comments;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public void setPublisher(final Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
        if (!(obj instanceof Ebook)) {
            return false;
        }
        final Ebook other = (Ebook) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isNew() {
        return this.id != null && !this.id.equals(0L);
    }
}