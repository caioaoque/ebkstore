/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mackenzie.pos.works.persistenceandclientserver.service;

import br.mackenzie.pos.works.persistenceandclientserver.domain.management.Customer;
import javax.ejb.Stateless;

import br.mackenzie.pos.works.persistenceandclientserver.util.dto.CustomerDTO;

@Stateless
public class CustomerService extends Service<Customer, CustomerDTO> {

    public CustomerService() {
        super(Customer.class, "customer");
    }

    @Override
    protected String buildConditions(final CustomerDTO params) {
        final StringBuilder sb = new StringBuilder();

        if (params != null) {
            sb.append(this.likeCondition("login", params.getLogin()));
            sb.append(this.likeCondition("name", params.getName()));
//            sb.append(this.inCondition("languages", params.getLanguages()));
//            sb.append(this.inCondition("publishers", params.getPublishers()));
//            sb.append(this.inCondition("authors", params.getAuthors()));
        }
        return sb.toString();
    }

}
