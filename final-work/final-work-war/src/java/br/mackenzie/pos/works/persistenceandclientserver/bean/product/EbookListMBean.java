package br.mackenzie.pos.works.persistenceandclientserver.bean.product;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.persistenceandclientserver.service.EbookService;
import br.mackenzie.pos.works.persistenceandclientserver.util.dto.EbookDTO;
import java.util.ArrayList;

@Named
@RequestScoped
public class EbookListMBean {

    @EJB
    private EbookService service;
    private EbookDTO params = new EbookDTO();
    private List<Ebook> list = new ArrayList<>();

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

    public List<Ebook> find() {
        return this.service.find(this.params);
    }

    public List<String> findGenres() {
        return service.findGenres();
    }

    public String findByGenre(String genre) {
        this.params = new EbookDTO();
        this.params.setGenre(genre);
        this.list = this.service.find(params);
        return "/products/list";
    }
}
