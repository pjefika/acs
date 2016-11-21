/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvp.controller.auth;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import cvp.controller.routes.HomeController;
import cvp.dal.UserDAO;
import cvp.model.entities.Usuario;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author G0034481
 */
@Controller
@RequestScoped
public class UsuarioController {

    @Inject
    private Result result;   

    @Inject
    private SessionUsuario sessionUsuario;

    @Inject
    private UserDAO userDAO;
    
    public void create() {

    }

    public void logar(Usuario usuario) {
        
        try {

            usuario = this.userDAO.listarUsuarioEspecifico(usuario);
            
            if (!usuario.getLogin().isEmpty()) {
                                
                System.out.println("usrControl: " + usuario.getLogin());
                
                this.sessionUsuario.setUsuarioSession(usuario);
                
                result.redirectTo(HomeController.class).index();

            } else {

                result.include("mensagemFalha", "Login/Senha incorretos");
                result.forwardTo(this).create();

            }

        } catch (Exception e) {

            result.include("mensagemFalha", e.getMessage());
            result.forwardTo(this).create();

        }

    }

    public void logout() {

        this.sessionUsuario.setUsuarioSession(new Usuario());
        this.result.forwardTo(UsuarioController.class).create();

    }

    public void restrito() {

        this.result.include("mensagem", "Acesso restrito!");

    }

}
