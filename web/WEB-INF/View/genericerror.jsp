<%--
  Created by IntelliJ IDEA.
  User: vinciraia99
  Date: 19/05/2020
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Errore"/>
</jsp:include>
<div class="row">
        <div class="card">
            <h3>Siamo spiacenti si è verificato un errore!</h3>
        </div>
        <div class="card">
            <h3>Errore numero ${requestScope['javax.servlet.error.status_code']}</h3>
        </div>
        <div class="card" id="homediv">
            <a href="${pageContext.request.contextPath}" id="home">Torna alla home</a>
        </div>
</div>
<%@include file="footer.html"%>
</body>

</html>
</html>
