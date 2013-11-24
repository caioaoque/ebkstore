/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.pos.works.percistenceandclientserver.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.mackenzie.pos.works.percistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.percistenceandclientserver.util.dto.EbookDTO;

@Stateless
public class EbookService extends Service<Ebook, EbookDTO> {

    @Inject
    public EbookService(final EntityManager em) {
        super(Ebook.class, "ebook", em);
    }

    @Override
    protected String buildConditions(final EbookDTO params) {
        final StringBuilder sb = new StringBuilder();

        if (params != null) {
            sb.append(this.likeCondition("title", params.getTitle()));
            sb.append(this.inCondition("genres", params.getGenres()));
            sb.append(this.inCondition("languages", params.getLanguages()));
            sb.append(this.inCondition("publishers", params.getPublishers()));
            sb.append(this.inCondition("authors", params.getAuthors()));
        }
        return sb.toString();
    }

}