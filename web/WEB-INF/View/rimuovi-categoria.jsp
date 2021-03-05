<%--
  Created by IntelliJ IDEA.
  User: vinciraia99
  Date: 19/05/2020
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Categoria rimossa con successo!"/>
</jsp:include>
<div class="row">
        <div class="card">
            <h3>Categoria rimossa con successo!</h3>
        </div>
        <div class="card">
            <h3>La categoria è stata rimossa dal sito!</h3>
        </div>
        <div class="card" id="homediv">
            <a href="${pageContext.request.contextPath}" id="home">Torna alla home</a>
        </div>
</div>
<%@include file="footer.html"%>
</body>

</html>
</html>
