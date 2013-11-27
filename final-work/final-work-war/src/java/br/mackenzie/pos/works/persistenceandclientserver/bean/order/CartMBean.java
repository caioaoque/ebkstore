package br.mackenzie.pos.works.persistenceandclientserver.bean.order;

import br.mackenzie.pos.works.persistenceandclientserver.bean.management.UserSessionMBean;
import br.mackenzie.pos.works.persistenceandclientserver.domain.order.Order;
import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.persistenceandclientserver.service.CartService;
import br.mackenzie.pos.works.persistenceandclientserver.service.OrderService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class CartMBean implements Serializable {

    private static final String EBOOK_LIST = "/product/list";

    @EJB
    private CartService service;
    @EJB
    private OrderService orderService;

    @Inject
    private UserSessionMBean userSessionBean;

    public CartMBean() {
    }

    public String buy(Ebook ebook) {
        this.service.addEbook(ebook);
        return "/order/new";
    }

    public String addToCart(Ebook ebook) {
        this.service.addEbook(ebook);
        return EBOOK_LIST;
    }

    public int getCartSize() {
        return this.service.getCartSize();
    }

    public Set<Ebook> getCartItems() {
        return this.service.getCart();
    }

    public double getTotal() {
        double total = 0D;
        for (Ebook ebook : getCartItems()) {
            total += ebook.getPrice();
        }
        return total;
    }

    public String saveOrder() {
        Order order = new Order();
        order.setEbooks(new ArrayList<>(this.service.getCart()));
        order.setUser(this.userSessionBean.getService().getUser());
        this.orderService.save(order);
        this.service.clearCart();
        return EBOOK_LIST;
    }
}
