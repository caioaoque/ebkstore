package br.mackenzie.pos.works.persistenceandclientserver.bean.order;

import br.mackenzie.pos.works.persistenceandclientserver.domain.order.Order;
import br.mackenzie.pos.works.persistenceandclientserver.service.CartService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class OrderEditMBean implements Serializable {

    @EJB
    private CartService cartService;
    private Order order;

    public OrderEditMBean() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
