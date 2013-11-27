package br.mackenzie.pos.works.persistenceandclientserver.bean.management;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.Role;
import br.mackenzie.pos.works.persistenceandclientserver.service.UserSessionService;
import br.mackenzie.pos.works.persistenceandclientserver.util.Encryptor;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SessionScoped
@Named(value = "userSessionMBean")
public class UserSessionMBean implements Serializable {

    private static final String HOME = "/home";

    @EJB
    private UserSessionService service;

    private String login;
    private String password;

    public UserSessionMBean() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasRole(String role) {
        return this.service.hasRole(Role.valueOf(role));
    }

    public boolean isLogged() {
        return this.service.isLogged();
    }

    public UserSessionService getService() {
        return service;
    }

    public String login() {
        try {
            if (this.service.login(this.login, Encryptor.encrypt(password))) {
                return HOME;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nome de usuário ou senha inválido."));
                return null;
            }
        } finally {
            this.password = null;
        }
    }

    public String logout() {
        this.login = null;
        this.password = null;
        this.service.logout();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return HOME;
    }

    public void insert() {
        this.service.insert();
    }
}
