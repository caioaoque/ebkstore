package br.mackenzie.pos.works.persistenceandclientserver.bean.product;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.persistenceandclientserver.service.EbookService;
import br.mackenzie.pos.works.persistenceandclientserver.util.dto.EbookDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class EbookListMBean implements Serializable {

    private static final String LIST_PAGE = "/product/list";

    @EJB
    private EbookService service;
    private EbookDTO params = new EbookDTO();
    private List<Ebook> list = new ArrayList<>();
    private String title;

    public EbookDTO getParams() {
        return this.params;
    }

    public void setParams(final EbookDTO params) {
        this.params = params;
    }

    public List<Ebook> getList() {
        return list;
    }

    public void setList(List<Ebook> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String findAll() {
        this.params = new EbookDTO();
        this.list = this.service.find(this.params);
        return LIST_PAGE;
    }

    public List<String> findGenres() {
        return service.findGenres();
    }

    public String findByGenre(String genre) {
        this.params = new EbookDTO();
        this.params.setGenre(genre);
        this.list = this.service.find(params);
        return LIST_PAGE;
    }
    
    public String findByTitle() {
        this.params = new EbookDTO();
        this.params.setTitle(this.title);
        this.list = this.service.find(params);
        this.title = null;
        return LIST_PAGE;
    }

}
