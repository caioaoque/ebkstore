package br.mackenzie.pos.works.persistenceandclientserver.util.dto;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;

public class EbookDTO implements DTO<Ebook> {

    private String title;
    private String genre;

//    Commented to reduce the complexity of the ORM.
//    private List<Language> languages;
//    private List<Publisher> publishers;
//    private List<Author> authors;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

//    public List<Language> getLanguages() {
//        return this.languages;
//    }
//
//    public void setLanguages(final List<Language> languages) {
//        this.languages = languages;
//    }
//    public List<Publisher> getPublishers() {
//        return this.publishers;
//    }
//
//    public void setPublishers(final List<Publisher> publishers) {
//        this.publishers = publishers;
//    }
//
//    public List<Author> getAuthors() {
//        return this.authors;
//    }
//
//    public void setAuthors(final List<Author> authors) {
//        this.authors = authors;
//    }

}
