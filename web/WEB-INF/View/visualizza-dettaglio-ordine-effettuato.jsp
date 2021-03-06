
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Dettaglio ordine"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>Ordine n.${ordine.id}</h2>
        </div>
        <div class="card">
                <p><b>Prodotti ordinati in data: </b>${data}
                <p><b>Pagato con carta di credito </b></p>
                <p><b>Totale prodotti ordinati: </b>${ordine.quantita}</p>
            <p><b>Totale </b>${ordine.totale}€</b></p>
            <c:if test = "${ordine.getStato() == false}"><p><b>Ordine non spedito</b></p></c:if>
            <c:if test = "${ordine.getStato() == true}"><p><b>Ordine spedito</b></p></c:if>


            <br>
           <p><b>Nome:</b>${utente.nome}</p>
                <p><b>Cognome:</b>${utente.cognome}</p>
                <p><b>Nome utente:</b>${utente.username}</p>

        </div>
            <c:forEach items="${libri}" var="libro">
                <div class="card" id="${libro.isbn}">
                    <div class="product_page">
                        <div class="card info_page cart">
                            <img onclick="location.href='libro?id=${libro.isbn}'" src="./img/${libro.copertina}" alt="libro" height="215px" class="image" />
                            <div class="product_info">
                                <p class="title">${libro.getTitolo()}</p>
                                <p class="description" id="descrizione_normale">${libro.trama}</p>
                                <p class="description" id="descrizione_corta">${libro.trama}</p>
                            </div>
                        </div>
                        <div class="card cartbox cart">
                            <p id="prezzoProdotto${libro.getIsbn()}" class="price_view">${libro.prezzo}€</p>
                            Quantità:${ordine.getQuantitaByIsbn(libro.getIsbn())}
                        </div>
                    </div>
                </div>
            </c:forEach>
    </div>
    <jsp:include page="footererightcollum.jsp"/>
