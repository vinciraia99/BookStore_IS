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
    <jsp:param name="pageTitle" value="Libro cancellato con successo!"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>Libro cancellato con successo!</h2>
        </div>
        <div class="card" id="homediv">
            <a href="${pageContext.request.contextPath}" id="home">Torna alla home</a>
        </div>
    </div>
    <jsp:include page="footererightcollum.jsp"/>

