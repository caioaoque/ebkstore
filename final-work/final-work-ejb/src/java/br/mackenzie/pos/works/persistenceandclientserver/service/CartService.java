package br.mackenzie.pos.works.persistenceandclientserver.service;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

@Stateful
@SessionScoped
public class CartService {

    private final Set<Ebook> cart = new HashSet<>();

    public synchronized void addEbook(Ebook ebook) {
        this.cart.add(ebook);
    }

    public synchronized void removeEbook(Ebook ebook) {
        this.cart.remove(ebook);
    }

    public int getCartSize() {
        return this.cart.size();
    }

    public Set<Ebook> getCart() {
        return Collections.unmodifiableSet(cart);
    }

    public void clearCart() {
        this.cart.clear();
    }
}
