package br.mackenzie.pos.works.persistenceandclientserver.bean.management;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.User;
import javax.ejb.EJB;
import javax.inject.Named;

import br.mackenzie.pos.works.persistenceandclientserver.service.UserService;
import br.mackenzie.pos.works.persistenceandclientserver.util.Encryptor;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@Named
@SessionScoped
public class UserEditMBean implements Serializable {

    private static final String LIST = "/management/userList";
    private static final String EDIT = "/management/userEdit";

    @EJB
    private UserService service;

    @Inject
    private UserListMBean listBean;

    private User user;
    private String password;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String save() {
        if (this.user.isNew()) {
            this.user.setEncryptedPassword(Encryptor.encrypt(this.password));
            this.password = null;
        }
        this.service.save(this.user);
        final List<User> list = listBean.getList();
        if (!list.contains(this.user)) {
            list.add(this.user);
        } else {
            list.set(list.indexOf(this.user), user);
        }
        this.user = null;
        return LIST;
    }

    public String edit(Long id) {
        this.user = this.service.findById(id);
        return EDIT;
    }

    public String newUser() {
        this.user = new User();
        return EDIT;
    }

    public String remove(User user) {
        this.service.remove(user);
        this.listBean.getList().remove(user);
        return LIST;
    }

}
