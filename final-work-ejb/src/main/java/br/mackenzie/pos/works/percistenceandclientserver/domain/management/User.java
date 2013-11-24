package br.mackenzie.pos.works.percistenceandclientserver.domain.management;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.mackenzie.pos.works.percistenceandclientserver.domain.util.DomainEntity;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements DomainEntity<String> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "usr_login")
    private String login;

    @Column(name = "usr_enc_password", nullable = false)
    private String encryptedPassword;

    @Column(name = "usr_name", nullable = false)
    private String name;

    @Column(name = "usr_phone", nullable = false)
    private String phone;

    @Column(name = "usr_email", nullable = false, unique = true)
    private String email;

    @Column(name = "usr_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private final Role role;

    public User() {
        this.role = Role.ADMINISTRATOR;
    }

    protected User(final Role role) {
        this.role = role;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getEncryptedPassword() {
        return this.encryptedPassword;
    }

    public void setEncryptedPassword(final String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Role getRole() {
        return this.role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.login == null) ? 0 : this.login.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        final User other = (User) obj;
        if (this.login == null) {
            if (other.login != null) {
                return false;
            }
        } else if (!this.login.equals(other.login)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isNew() {
        return this.login != null && !this.login.isEmpty();
    }

    @Override
    public String getId() {
        return this.login;
    }

}