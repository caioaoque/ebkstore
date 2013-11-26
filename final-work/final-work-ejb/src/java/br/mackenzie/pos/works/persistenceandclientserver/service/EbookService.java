/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.pos.works.persistenceandclientserver.service;

import javax.ejb.Stateless;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.persistenceandclientserver.util.dto.EbookDTO;

@Stateless
public class EbookService extends Service<Ebook, EbookDTO> {

    public EbookService() {
        super(Ebook.class, "ebook");
    }

    @Override
    protected String buildConditions(final EbookDTO params) {
        final StringBuilder sb = new StringBuilder();

        if (params != null) {
            sb.append(this.likeCondition("title", params.getTitle()));
            sb.append(this.likeCondition("genre", params.getGenre()));
//            sb.append(this.inCondition("languages", params.getLanguages()));
//            sb.append(this.inCondition("publishers", params.getPublishers()));
//            sb.append(this.inCondition("authors", params.getAuthors()));
        }
        return sb.toString();
    }

}