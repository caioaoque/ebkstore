/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.pos.works.persistenceandclientserver.service;

import br.mackenzie.pos.works.persistenceandclientserver.domain.order.Order;
import javax.ejb.Stateless;

import br.mackenzie.pos.works.persistenceandclientserver.util.dto.DTO;

@Stateless
public class OrderService extends Service<Order, DTO<Order>> {

    public OrderService() {
        super(Order.class, "order");
    }

    @Override
    protected String buildConditions(final DTO<Order> params) {
        return EMPTY;
    }

}
