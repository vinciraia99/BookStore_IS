
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${categoria.nome}"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card" id="divcategorie">
            <h2>${categoria.nome}</h2>
            <c:if test = "${utente != null && utente.getTipo() != \"C\"}">
                <div id="diveditcategorie"><a href="modificacategoria?id=${categoria.id}" class="button" id="buttoncategorieedit">Modifica categoria</a>
                    <a href="eliminacategoria?id=${categoria.id}" class="button" id="buttoncategoriedelete">Elimina categoria</a></div>
            </c:if>
        </div>
        <div class="card description">
            <p>${categoria.descrizione}</p>
        </div>
        <c:if test="${libri.size() == 0}">
        <div class="card">
            <h3>Al momento non ci sono libri in questa sezione</h3>
        </div>
        </c:if>
        <c:if test="${libri.size() > 0}">
        <c:forEach items="${libri}" var="libro">
            <div class="card bookbox">
                <img onclick="location.href='visualizzalibro?id=${libro.isbn}'" src="${pageContext.request.contextPath}/img/${libro.copertina}" alt="libro" height="215px" class="image" />
                <div class="book">
                    <h3>
                            ${libro.titolo}
                    </h3>
                    <h4>
                            ${libro.prezzo}€
                    </h4>
                    <p class="description">${libro.trama}</p>
                    <a href="visualizzalibro?id=${libro.isbn}">Vai alla scheda tecnica</a>
                </div>
            </div>
        </c:forEach>
        </c:if>
    </div>
    <jsp:include page="footererightcollum.jsp"/>
