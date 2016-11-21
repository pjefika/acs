/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvp.controller.auth;

import cvp.model.entities.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author G0034481
 */
@SessionScoped
@Named(value = "sessao")
public class SessionUsuario implements Serializable {

    private Usuario usuarioSession;

    public SessionUsuario() {

        this.usuarioSession = new Usuario();

    }
    
    @Inject
    public SessionUsuario(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }  

    public Boolean isAdm() {

        return this.usuarioSession.getAdm();

    }

    public Boolean isLogado() {

        return this.usuarioSession.getLogin() != null;

    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }  

}
