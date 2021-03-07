<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Errore"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2><%= exception.getMessage() %></h2>
        </div>
        <div class="card" id="homediv">
            <a href="javascript:history.go(-1)" id="home">Torna alla pagina precedente</a>
        </div>
    </div>
    <jsp:include page="footererightcollum.jsp"/>
