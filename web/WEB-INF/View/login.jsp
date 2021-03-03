<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Accedi o registrati"/>
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
            <div class="card column" id="submitdiv">
                <h2>Registrati</h2>
                <c:if test = "${fomrerror != null}">
                <div id="errorserver"><p>${fomrerror}</p></div>
                </c:if>
                <div class="contact-container">
                    <form  onsubmit="return formceck()" action="login" method="post">
                        <div class="row">
                            <div class="col-25">
                                <label for="nome">Nome</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="text"
                                        id="nome"
                                        name="nome"
                                        placeholder="Nome"
                                        autocomplete="on"
                                        pattern="[A-Za-z]+" title="Questo campo puó contenere solo caratteri"
                                        required
                                />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-25">
                                <label for="cognome">Cognome</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="text"
                                        id="cognome"
                                        name="cognome"
                                        placeholder="Cognome"
                                        autocomplete="on"
                                        pattern="[A-Za-z]+" title="Questo campo puó contenere solo caratteri"
                                        required
                                />
                            </div>
                        </div>
                            <div class="row">
                            <div class="col-25">
                                <label for="usernamesubmit">Nome utente</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="text"
                                        id="usernamesubmit"
                                        name="usernamesubmit"
                                        placeholder="Nome utente"
                                        autocomplete="on"
                                        required
                                />
                            </div>
                            </div>
                        <div class="row">
                            <div class="col-25">
                                <label for="email">Email</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="email"
                                        id="email"
                                        name="email"
                                        placeholder="Email"
                                        autocomplete="on"
                                        required
                                />
                            </div>
                        </div>
                            <div class="row">
                            <div class="col-25">
                                <label for="sesso">Sesso</label>
                            </div>
                            <div class="col-75">
                                <select name="sesso" id="sesso">
                                    <option value="Maschio">Maschio</option>
                                    <option value="Femmina">Femmina</option>
                                </select>
                            </div>
                            </div>
                            <div class="row">
                            <div class="col-25">
                                <label for="passwordsubmit">Password</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="password"
                                        id="passwordsubmit"
                                        name="passwordsubmit"
                                        placeholder="Password"
                                        pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$" title="La password é mal formata! La password deve essere lunga 8 caratteri e al massimo 32. Deve contenere una lettere maiuscola e una minuscola. Deve contenere un numero"
                                        required
                                />
                            </div>
                            </div>
                            <div class="row">
                            <div class="col-25">
                                <label for="passwordsubmitconfirm">Conferma password</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="password"
                                        id="passwordsubmitconfirm"
                                        name="passwordsubmitconfirm"
                                        placeholder="Conferma password"
                                        required
                                />
                            </div>
                            </div>
                        <input id="submit" type="submit" value="Registrati" />
                </div>
                </div>
        </div>





<%@include file="footer.html"%>
</body>
</html>
