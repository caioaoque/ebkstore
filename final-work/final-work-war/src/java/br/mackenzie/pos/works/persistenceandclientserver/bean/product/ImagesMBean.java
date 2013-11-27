package br.mackenzie.pos.works.persistenceandclientserver.bean.product;

import javax.ejb.EJB;
import javax.inject.Named;

import br.mackenzie.pos.works.persistenceandclientserver.service.EbookService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class ImagesMBean implements Serializable {

    @EJB
    private EbookService service;

    public OutputStream showImage(Long ebookId) throws IOException {
        final ByteArrayOutputStream result = new ByteArrayOutputStream();
        result.write(this.service.findById(ebookId).getCover());
        return result;
    }

}
