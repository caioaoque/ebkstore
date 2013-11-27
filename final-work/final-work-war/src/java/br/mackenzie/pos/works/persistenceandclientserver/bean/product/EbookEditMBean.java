package br.mackenzie.pos.works.persistenceandclientserver.bean.product;

import javax.ejb.EJB;
import javax.inject.Named;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.persistenceandclientserver.service.CartService;
import br.mackenzie.pos.works.persistenceandclientserver.service.EbookService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class EbookEditMBean implements Serializable {

    @EJB
    private EbookService service;

    @EJB
    private CartService cartService;
    private Ebook ebook;
    private String newComment;

    public Ebook getEbook() {
        return ebook;
    }

    public void setEbook(Ebook ebook) {
        this.ebook = ebook;
    }

    public String getNewComment() {
        return newComment;
    }

    public void setNewComment(String newComment) {
        this.newComment = newComment;
    }

    public String show(Long ebookId) {
        this.ebook = this.service.findById(ebookId);
        return "/product/show";
    }

    public void save() {
        this.service.save(this.ebook);
    }

    public void addComment() {
        this.service.addComment(this.newComment);
    }

}
