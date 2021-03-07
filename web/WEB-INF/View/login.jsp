<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Accedi"/>
</jsp:include>

        <div class="row verticalrow">
        <div class="card column" id="logindiv">
            <h2>Login</h2>
            <div class="contact-container">
                <form method="post" action="login">
                    <div class="row">
                        <div class="col-25">
                            <label for="username">Nome utente</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="text"
                                    id="username"
                                    name="username"
                                    placeholder="Nome utente"
                                    autocomplete="on"
                                    minlength="1"
                                    required
                            />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="password">Password</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="password"
                                    id="password"
                                    name="password"
                                    placeholder="Password"
                                    minlength="1"
                                    required
                            />
                        </div>
                    </div>
                    <div class="row">
                    </div>
                        <input id="login" type="submit" value="Accedi" />
                    </div>
                </form>
            </div>
        </div>





<%@include file="footer.html"%>
</body>
</html>
