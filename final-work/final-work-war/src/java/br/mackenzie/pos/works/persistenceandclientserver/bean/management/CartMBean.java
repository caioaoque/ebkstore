package br.mackenzie.pos.works.persistenceandclientserver.bean.management;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.persistenceandclientserver.service.CartService;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class CartMBean implements Serializable {

    @EJB
    private CartService service;

    public CartMBean() {
    }

    public String buy(Ebook ebook) {
        this.service.addEbook(ebook);
        return "/order/new";
    }

    public String addToCart(Ebook ebook) {
        this.service.addEbook(ebook);
        return "/product/list";
    }

    public int getCartSize() {
        return this.service.getCartSize();
    }
    
    public Set<Map.Entry<Ebook, Integer>> getCartItems() {
        return this.service.getCart().entrySet();
    }
}
