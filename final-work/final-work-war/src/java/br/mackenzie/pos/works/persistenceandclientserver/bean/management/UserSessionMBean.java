package br.mackenzie.pos.works.persistenceandclientserver.bean.management;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.Role;
import br.mackenzie.pos.works.persistenceandclientserver.service.UserSessionService;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
@Named(value = "userSessionMBean")
public class UserSessionMBean implements Serializable {

    @Inject
    private UserSessionService service;

    public UserSessionMBean() {
    }

    public boolean hasRole(String role) {
        return this.service.hasRole(Role.valueOf(role));
    }
    
    public boolean isLogged() {
        return this.service.isLogged();
    }
    
}
