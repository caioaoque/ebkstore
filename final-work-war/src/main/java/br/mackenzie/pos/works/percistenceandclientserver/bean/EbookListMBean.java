package br.mackenzie.pos.works.percistenceandclientserver.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.mackenzie.pos.works.percistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.percistenceandclientserver.service.EbookService;
import br.mackenzie.pos.works.percistenceandclientserver.util.dto.EbookDTO;

@Named
@RequestScoped
public class EbookListMBean {

    @EJB
    private EbookService service;

    private EbookDTO ebookParams;

    public EbookDTO getEbookParams() {
        return this.ebookParams;
    }

    public void setEbookParams(final EbookDTO ebookParams) {
        this.ebookParams = ebookParams;
    }

    public List<Ebook> find() {
        return this.service.find(this.ebookParams);
    }
}
