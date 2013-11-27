package br.mackenzie.pos.works.persistenceandclientserver.bean.product;

import br.mackenzie.pos.works.persistenceandclientserver.bean.management.UserSessionMBean;
import br.mackenzie.pos.works.persistenceandclientserver.domain.management.User;
import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Comment;
import javax.ejb.EJB;
import javax.inject.Named;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.persistenceandclientserver.service.EbookService;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class EbookEditMBean implements Serializable {

    @EJB
    private EbookService service;

    @Inject
    private EbookListMBean listBean;

    @Inject
    private UserSessionMBean userSessionBean;

    private Ebook ebook;
    private String newComment;
    private Part content;
    private Part cover;

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

    public Part getContent() {
        return content;
    }

    public void setContent(Part content) {
        this.content = content;
    }

    public Part getCover() {
        return cover;
    }

    public void setCover(Part cover) {
        this.cover = cover;
    }

    public String show(Long ebookId) {
        this.ebook = this.service.findById(ebookId);
        return "/product/show";
    }

    public String edit(Long ebookId) {
        this.ebook = this.service.findById(ebookId);
        return "/product/edit";
    }

    public String newEbook() {
        this.ebook = new Ebook();
        return "/product/edit";
    }

    public String save() throws IOException {
        byte[] contentByteArray = partToByteArray(this.content);
        if (contentByteArray != null) {
            this.ebook.setContent(contentByteArray);
        }
        byte[] coverByteArray = partToByteArray(this.cover);
        if (coverByteArray != null) {
            this.ebook.setCover(coverByteArray);
        }
        this.service.save(this.ebook);
        final List<Ebook> list = listBean.getList();
        if (!list.contains(this.ebook)) {
            list.add(this.ebook);
        } else {
            list.set(list.indexOf(this.ebook), this.ebook);
        }
        this.ebook = null;
        return "/product/list";
    }

    private byte[] partToByteArray(Part part) throws IOException {
        if (part != null && part.getSize() > 0) {
            byte[] file = new byte[(int) part.getSize()];
            final InputStream inputStream = part.getInputStream();
            for (int i = 0, b = inputStream.read(); b != -1; i++, b = inputStream.read()) {
                file[i] = (byte) b;
            }
            return file;
        }
        return null;
    }

    public void addComment() {
        final User user = this.userSessionBean.getService().getUser();
        final Comment comment = this.service.addComment(user, this.ebook, this.newComment);
        this.ebook.getComments().add(comment);
        final List<Ebook> list = this.listBean.getList();
        list.set(list.indexOf(ebook), ebook);
        this.newComment = null;
    }

}
