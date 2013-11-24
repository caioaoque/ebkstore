package br.mackenzie.pos.works.percistenceandclientserver.bean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.mackenzie.pos.works.percistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.percistenceandclientserver.service.EbookService;

@Named
@RequestScoped
public class EbookEditMBean {

    @EJB
    private EbookService service;

    private Ebook ebook;

    public void save() {
        this.service.save(this.ebook);
    }

}
