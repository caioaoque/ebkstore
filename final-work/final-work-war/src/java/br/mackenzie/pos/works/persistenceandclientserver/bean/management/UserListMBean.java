package br.mackenzie.pos.works.persistenceandclientserver.bean.management;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.User;
import br.mackenzie.pos.works.persistenceandclientserver.service.UserService;
import br.mackenzie.pos.works.persistenceandclientserver.util.dto.UserDTO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class UserListMBean implements Serializable {

    private static final String LIST = "/management/userList";

    @EJB
    private UserService service;
    private List<User> list;

    public UserListMBean() {
    }

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public String findAll() {
        this.list = this.service.find(new UserDTO());
        return LIST;
    }

    public int getCountUsers() {
        List<User> allUsers = this.service.find(new UserDTO());
        return allUsers == null ? 0 : allUsers.size();
    }
}
