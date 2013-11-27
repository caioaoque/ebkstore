/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.pos.works.persistenceandclientserver.service;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.User;
import javax.ejb.Stateless;

import br.mackenzie.pos.works.persistenceandclientserver.util.dto.UserDTO;

@Stateless
public class UserService extends Service<User, UserDTO> {

    public UserService() {
        super(User.class, "user");
    }

    @Override
    protected String buildConditions(final UserDTO params) {
        final StringBuilder sb = new StringBuilder();

        if (params != null) {
            sb.append(this.likeCondition("login", params.getLogin()));
            sb.append(this.likeCondition("name", params.getName()));
        }
        return sb.toString();
    }

}
