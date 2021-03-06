
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="I miei ordini"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card">
            <h2>I miei ordini</h2>
        </div>
        <c:if test="${ordini == null || ordini.size() == 0}">
            <div class="card">
                <h3>Non ci sono ordini</h3>
            </div>
        </c:if>
        <c:if test="${ordini != null && ordini.size() > 0}">
            <c:forEach items="${ordini}" var="ordine">
                <div class="card bookbox">
                    <div class="ordinebox">
                        <p>
                            <b>Ordine n.:</b> ${ordine.id}<br>
                            <b>Totale:</b> ${ordine.totale}€<br>
                            <b>Acquistato in data:</b>${ordine.getDataString()}<br>
                            <b>Quantità prodotti:</b> ${ordine.quantita}<br>
                        </p>
                        <a href="visualizzadettagliordine?id=${ordine.id}">Visualizza maggiori dettagli</a>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
    <jsp:include page="footererightcollum.jsp"/>
