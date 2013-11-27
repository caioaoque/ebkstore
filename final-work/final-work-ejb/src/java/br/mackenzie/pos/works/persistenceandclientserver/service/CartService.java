package br.mackenzie.pos.works.persistenceandclientserver.service;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

@Stateful
@SessionScoped
public class CartService {

    private final Map<Ebook, Integer> cart = new HashMap<>();

    public synchronized void addEbook(Ebook ebook) {
        if (this.cart.containsKey(ebook)) {
            this.incrementEbook(ebook);
        } else {
            this.cart.put(ebook, 1);
        }
    }

    public synchronized void incrementEbook(Ebook ebook) {
        if (this.cart.containsKey(ebook)) {
            this.cart.put(ebook, this.cart.get(ebook) + 1);
        }
    }

    public synchronized void removeEbook(Ebook ebook) {
        this.cart.remove(ebook);
    }

    public synchronized void decrementEbook(Ebook ebook) {
        if (this.cart.containsKey(ebook)) {
            final Integer quantity = this.cart.get(ebook);
            if (quantity > 1) {
                this.cart.put(ebook, quantity - 1);
            } else {
                this.removeEbook(ebook);
            }
        }
    }

    public int getCartSize() {
        return this.cart.size();
    }

    public Map<Ebook, Integer> getCart() {
        return Collections.unmodifiableMap(cart);
    }
}
