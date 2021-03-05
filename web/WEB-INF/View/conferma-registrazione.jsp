<%--
  Created by IntelliJ IDEA.
  User: scarp
  Date: 03/03/2021
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Registrazione avvenuta con successo!"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>Registrazione avvenuta con successo!</h2>
            <h3>Riceverai una mail con le info per attivare l'account</h3>
        </div>
        <div class="card">
            <p>Per scopi di tempo il link che avrebbe ricevuto via mail è questo: <a href="confermaregistrazione?username=${username}&email=${email}">link mail</a></p>
        </div>
        <div class="card" id="homediv">
            <a href="${pageContext.request.contextPath}" id="home">Torna alla home</a>
        </div>
    </div>
    <jsp:include page="footererightcollum.jsp"/>

