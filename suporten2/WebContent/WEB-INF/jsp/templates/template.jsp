<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
           prefix="decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <title><decorator:title default="CVP" /></title>
        <meta charset="utf-8">
        <link
            href="${pageContext.request.contextPath}/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"
            rel="stylesheet" />
        <link
            href="${pageContext.request.contextPath}/resources/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css"
            rel="stylesheet" />

        <link
            href="${pageContext.request.contextPath}/resources/custom/custom.css" rel="stylesheet" />

        <script
        src="${pageContext.request.contextPath}/resources/jquery-3.1.1/jquery-3.1.1.min.js"></script>
        <script
        src="${pageContext.request.contextPath}/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>


        <decorator:head/>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Project name</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="${linkTo[HomeController].index}">Início</a></li>

                        <c:if test="${sessao.isLogado()}">

                            <li><a href="#about">Menu 1</a></li>

                            <c:if test="${sessao.isAdm()}">

                                <li><a href="#contact">Gerenciar</a></li>
                                <li><a href="#contact">Gráfico</a></li>

                            </c:if>

                        </c:if>

                    </ul>
                    <ul class="nav navbar-nav navbar-right">

                        <c:choose>
                            <c:when test="${empty sessao.usuarioSession.login}">
                                <li><a href="${linkTo[UsuarioController].create}">Login</a></li>
                                </c:when>    
                                <c:otherwise>
                                <li>
                                    <a href="${linkTo[UsuarioController].logout}">${sessao.usuarioSession.login}, Logout</a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        <div class="container">
            <br/>
            <br/>
            <br/>
            <br/>
            <c:if test="${not empty mensagem}">
                <span class="alert alert-success" role="alert"> <span
                        class="glyphicon glyphicon glyphicon-ok" aria-hidden="true"></span>
                    ${mensagem}
                </span>
            </c:if>
            <c:if test="${not empty mensagemFalha}">
                <span class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon glyphicon-remove" aria-hidden="true"></span> 
                    ${mensagemFalha}
                </span>
            </c:if>
            <decorator:body />
        </div>
        <!-- /container -->

    </body>
</html>