/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.pos.works.persistenceandclientserver.service;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.User;
import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Comment;
import javax.ejb.Stateless;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.persistenceandclientserver.util.dto.EbookDTO;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

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

    public List<String> findGenres() {
        final String queryStr = String.format("SELECT DISTINCT %s.genre from %s %s order by %s.genre", alias, Ebook.class.getName(), alias, alias);
        final TypedQuery<String> query = this.em.createQuery(queryStr, String.class);
        final List<String> result = query.getResultList();
        return result == null ? new ArrayList<String>() : result;
    }

    public Comment addComment(User user, Ebook ebook, String newComment) {
        if (user != null && !user.isNew() && newComment != null && !newComment.isEmpty()) {
            Comment comment = new Comment();
            comment.setEbook(ebook);
            comment.setUser(user);
            comment.setText(newComment);
            this.em.persist(comment);
            return comment;
        }
        return null;
    }

}
