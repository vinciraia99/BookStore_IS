<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
  <jsp:param name="pageTitle" value="HomePage"/>
</jsp:include>
  <div class="card ricerca_mobile">
      <%@include file="search.html"%>
  </div>
  <div class="row">
      <div class="leftcolumn">
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

    </div>

      <jsp:include page="footererightcollum.jsp"/>
