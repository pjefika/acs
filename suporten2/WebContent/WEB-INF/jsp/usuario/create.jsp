<%-- 
    Document   : create
    Created on : 17/11/2016, 10:49:15
    Author     : G0034481
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        
        <form action="${linkTo[UsuarioController].logar}" method="post" class="form-inline">
            <div class="form-group">
                <label for="login">Login</label> <input
                    type="text" class="form-control" id="login" name="usuario.login"
                    placeholder="Login">
            </div>
            <div class="form-group">
                <label for="senha">Senha</label> <input
                    type="password" class="form-control" id="senha" name="usuario.senha"
                    placeholder="Senha">
            </div>
            <button type="submit" class="btn btn-default">Login</button>
        </form>
            
    </body>
</html>
