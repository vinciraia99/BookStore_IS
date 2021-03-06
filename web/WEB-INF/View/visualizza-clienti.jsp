
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Gestisci utenti"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>Gestisci utenti</h2>
        </div>
        <c:if test="${utenti.size() == 0}">
            <div class="card">
                <h3>Non ci sono utenti</h3>
            </div>
        </c:if>
        <c:if test="${utenti.size() != 0}">
        <div class="card">
            <div class="tableorder" style="overflow-x:auto;">
                <table>
                    <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>Email</th>
                        <th>Tipo</th>
                        <th>Account disabilitato?</th>
                    </tr>
                    <c:forEach items="${utenti}" var="utente">
                        <tr>
                            <td>${utente.username}</td>
                            <td>${utente.nome}</td>
                            <td>${utente.cognome}</td>
                            <td>${utente.email}</td>
                            <td>${utente.tipo}</td>
                            <td><c:if test="${utente.getAbilitato()== false}">Si</c:if><c:if test="${utente.getAbilitato() == true}">No</c:if></td>
                            <c:if test="${utente.getAbilitato() == true}"><td><a href="disabilitautente?id=${utente.username}&flag=0">Disabilita account</a></td></c:if>
                            <c:if test="${utente.getAbilitato() == false}"><td><a href="disabilitautente?id=${utente.username}&flag=1">Abilita account</a></td></c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        </c:if>
    </div>
    <jsp:include page="footererightcollum.jsp"/>
