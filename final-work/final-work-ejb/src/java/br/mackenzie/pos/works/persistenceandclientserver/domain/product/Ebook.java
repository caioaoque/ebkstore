package br.mackenzie.pos.works.persistenceandclientserver.domain.product;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.mackenzie.pos.works.persistenceandclientserver.domain.util.DomainEntity;
import javax.persistence.Cacheable;
import javax.persistence.FetchType;
import javax.persistence.Lob;

@Entity
@Cacheable(false)
@Table(name = "ebooks")
public class Ebook implements DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ebk_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ebk_title")
    private String title;

    @Column(name = "ebk_pages")
    private Integer pages;

    @Column(name = "ebk_desc")
    private String description;

    @Column(name = "ebk_price")
    private Double price;

//    Commented to reduce the complexity of the ORM.
//    @ElementCollection
//    @MapKeyJoinColumn(name = "lng_name", nullable = false)
//    @CollectionTable(name = "ebook_contents", joinColumns = @JoinColumn(name = "ebk_id"))
//    @AttributeOverrides({ @AttributeOverride(name = "value.cover", column = @Column(name = "cover")),
//            @AttributeOverride(name = "value.content", column = @Column(name = "content")) })
//    private final Map<Language, EbookForLanguage> ebooksForLanguage = new HashMap<Language, EbookForLanguage>();
    @Lob
    @Column(name = "ebk_cover")
    @Basic(fetch = FetchType.LAZY)
    private byte[] cover;

    @Lob
    @Column(name = "ebk_content")
    @Basic(fetch = FetchType.LAZY, optional = false)
    private byte[] content;

    @Column(name = "ebk_genre", nullable = false)
    private String genre;

    @Column(name = "ebk_author", nullable = false)
    private String author;

    @Column(name = "ebk_publisher", nullable = false)
    private String publisher;

    @Column(name = "ebk_language", nullable = false)
    private String language;

    @OneToMany(mappedBy = "ebook", cascade = CascadeType.REMOVE)
    private final List<Comment> comments = new ArrayList<>();

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

    public Integer getPages() {
        return this.pages;
    }

    public void setPages(final Integer pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

//    public Map<Language, EbookForLanguage> getEbooksForLanguage() {
//        return this.ebooksForLanguage;
//    }
//
//    public void setEbooksForLanguage(final Map<Language, EbookForLanguage> ebooksForLanguage) {
//        this.ebooksForLanguage.clear();
//        if (ebooksForLanguage != null) {
//            this.ebooksForLanguage.putAll(ebooksForLanguage);
//        }
//
//    }
    public byte[] getCover() {
        return this.cover;
    }

    public void setCover(final byte[] cover) {
        this.cover = cover;
    }

    public byte[] getContent() {
        return this.content;
    }

    public void setContent(final byte[] content) {
        this.content = content;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(final List<Comment> comments) {
        this.comments.clear();
        if (comments != null) {
            this.comments.addAll(comments);
        }
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
        return this.id == null || this.id.equals(0L);
    }
}
