package br.mackenzie.pos.works.persistenceandclientserver.bean.order;

import br.mackenzie.pos.works.persistenceandclientserver.domain.product.Ebook;
import br.mackenzie.pos.works.persistenceandclientserver.service.CartService;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class NewOrderMBean implements Serializable {

    @EJB
    private CartService cartService;

    public NewOrderMBean() {
    }

}
