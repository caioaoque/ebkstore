/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.pos.works.persistenceandclientserver.service;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.User;
import br.mackenzie.pos.works.persistenceandclientserver.domain.order.Order;
import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import javax.ejb.Stateless;

import br.mackenzie.pos.works.persistenceandclientserver.util.dto.DTO;
import javax.persistence.TypedQuery;

@Stateless
public class OrderService extends Service<Order, DTO<Order>> {

    public OrderService() {
        super(Order.class, "ord");
    }

    @Override
    protected String buildConditions(final DTO<Order> params) {
        return EMPTY;
    }

    public Ebook findOrderEbookByEbookIdAndUser(Long ebookId, User user) {
        Ebook ebook = this.em.find(Ebook.class, ebookId);
        String queryStr = "SELECT DISTINCT true FROM %s %s WHERE :ebook MEMBER OF ord.ebooks AND %s.user = :user";
        queryStr = String.format(queryStr, Order.class.getName(), alias, alias);
        final TypedQuery<Integer> query = this.em.createQuery(queryStr, Integer.class);
        query.setParameter("ebook", ebook);
        query.setParameter("user", user);
        try {
            final Integer result = query.getSingleResult();
            if (result == 1) {
                return ebook;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

}
