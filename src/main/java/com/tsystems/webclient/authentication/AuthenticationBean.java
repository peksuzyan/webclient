package com.tsystems.webclient.authentication;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Map;
import javax.xml.ws.BindingProvider;

@ManagedBean(name = "authentication")
@SessionScoped
public class AuthenticationBean {

    private Map<String, Object> attributes =
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    private String email;
    private String password;

    public boolean isLoggedIn() {
        return attributes.get(BindingProvider.USERNAME_PROPERTY) != null
                && attributes.get(BindingProvider.PASSWORD_PROPERTY) != null;
    }

    public String login() {
        attributes.put(BindingProvider.USERNAME_PROPERTY, email);
        attributes.put(BindingProvider.PASSWORD_PROPERTY, password);
        return "/client/welcome.xhtml";
    }

    public String logout() {
        attributes.remove(BindingProvider.USERNAME_PROPERTY);
        attributes.remove(BindingProvider.PASSWORD_PROPERTY);
        return "/login.xhtml";
    }

    /**
     * Gets email.
     *
     * @return email email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return password password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
