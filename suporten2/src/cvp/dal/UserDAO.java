/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvp.dal;

import cvp.model.entities.Usuario;
import javax.persistence.Query;

/**
 *
 * @author G0034481
 */
public class UserDAO extends AbstractDAO {

    public UserDAO() {
    }

    public void editar(Usuario usuario) throws Exception {

        try {

            super.editar(usuario);

        } catch (Exception e) {

            throw new Exception(e.getMessage());

        }

    }

    public Usuario listarUsuarioEspecifico(Usuario usuario) {
        
        try {

            Query query = this.entityManager.createQuery("FROM Usuario u WHERE u.login =:param1 AND u.senha =:param2");
            query.setParameter("param1", usuario.getLogin());
            query.setParameter("param2", usuario.getSenha());
            return (Usuario) query.getSingleResult();

        } catch (Exception e) {

            return new Usuario();

        }

    }

}
