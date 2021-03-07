<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${libro.titolo}"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card bookbox_page">
            <img src="${pageContext.request.contextPath}/img/${libro.copertina}" alt="libro" height="215px" class="image" />
            <div class="book" id="product">
                <h4>
                    Titolo: ${libro.titolo}<hr>
                    Autore/i: ${autori}<hr>
                    Prezzo: ${libro.prezzo}€<hr>
                    Pezzi disponibili: ${libro.quantita}
                </h4>
            </div>
        </div>
        <div class="card buybox">
            <div class="book">
                <c:if test = "${utente == null || (utente != null && utente.getTipo() == \"C\")}">
                    <a href="aggiungicarrello?id=${libro.isbn}" class="button">Aggiungi al carrello</a>
                </c:if>
                <c:if test = "${utente != null && utente.getTipo() != \"C\"}">
                    <a href="modificalibro?id=${libro.isbn}" class="button">Modifica libro</a>
                    <a href="eliminalibro?id=${libro.isbn}" class="button">Elimina libro</a>
                </c:if>
            </div>
        </div>
        <div class="card bookbox descriptor">
            <h3>Descrizione</h3><hr>
            <p class="description">${libro.trama}</p>
        </div>
        <div class="card bookbox descriptor">
            <h3>ISBN:</h3>
            <p class="description">${libro.isbn}</p><hr>
            <h3>Anno di pubblicazione:</h3>
            <p class="description">${annopubblicazione}</p><hr>
            <h3>Categoria/e:</h3>
            <p class="description">${categorieassociate}</p><br>
        </div>
    </div>


    <jsp:include page="footererightcollum.jsp"/>
