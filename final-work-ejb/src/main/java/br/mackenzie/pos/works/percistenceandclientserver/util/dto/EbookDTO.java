package br.mackenzie.pos.works.percistenceandclientserver.util.dto;

import java.util.List;

import br.mackenzie.pos.works.percistenceandclientserver.domain.product.Author;
import br.mackenzie.pos.works.percistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.percistenceandclientserver.domain.product.Genre;
import br.mackenzie.pos.works.percistenceandclientserver.domain.product.Language;
import br.mackenzie.pos.works.percistenceandclientserver.domain.product.Publisher;

public class EbookDTO implements DTO<Ebook> {

    private String title;
    private List<Genre> genres;
    private List<Language> languages;
    private List<Publisher> publishers;
    private List<Author> authors;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(final List<Genre> genres) {
        this.genres = genres;
    }

    public List<Language> getLanguages() {
        return this.languages;
    }

    public void setLanguages(final List<Language> languages) {
        this.languages = languages;
    }

    public List<Publisher> getPublishers() {
        return this.publishers;
    }

    public void setPublishers(final List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(final List<Author> authors) {
        this.authors = authors;
    }

}