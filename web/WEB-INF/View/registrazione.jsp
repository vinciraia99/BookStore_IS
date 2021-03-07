<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Registrati"/>
</jsp:include>

        <div class="row verticalrow">
            <div class="card column" id="logindiv">
                <h2>Registrati</h2>
                <div class="contact-container">
                    <form action="registrazionecliente" method="post">
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
                                        pattern="[A-Za-z]{2,}$" title="Questo campo puó contenere solo caratteri"
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
                                        pattern="[A-Za-z]{2,}$" title="Questo campo puó contenere solo caratteri"
                                        required
                                />
                            </div>
                        </div>
                            <div class="row">
                            <div class="col-25">
                                <label for="username">Username</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="text"
                                        id="username"
                                        name="username"
                                        placeholder="Username"
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
                                <label for="password">Password</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="password"
                                        id="password"
                                        name="password"
                                        placeholder="Password"
                                        pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$" title="La password é mal formata! La password deve essere lunga 8 caratteri e al massimo 32. Deve contenere una lettere maiuscola e una minuscola. Deve contenere un numero"
                                        minlength="8"
                                        required
                                />
                            </div>
                            </div>
                            <div class="row">
                            <div class="col-25">
                                <label for="password">Conferma password</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="password"
                                        id="passwordconfirm"
                                        name="passwordconfirm"
                                        placeholder="Conferma password"
                                        required
                                />
                            </div>
                            </div>

                </div>
                </div>

            <div class="card column" id="submitdiv">
                <h2>Immetti informazioni indirizzo</h2>
                <div class="contact-container">
                <div class="row">
                    <div class="col-25">
                        <label for="Via">Via</label>
                    </div>
                    <div class="col-75">
                        <input
                                type="text"
                                id="via"
                                name="via"
                                placeholder="Es . Via Roma 21"
                                autocomplete="on"
                                required
                        />
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="comune">Comune</label>
                    </div>
                    <div class="col-75">
                        <input
                                type="text"
                                id="comune"
                                name="comune"
                                placeholder="Es . Ostia"
                                autocomplete="on"
                                pattern="[A-Za-z]{2,}$"
                                required
                        />
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="provincia">Provincia</label>
                    </div>
                    <div class="col-75">
                        <input
                                type="text"
                                id="provincia"
                                name="provincia"
                                placeholder="Es . Roma"
                                autocomplete="on"
                                pattern="[A-Za-z]{2,}$"
                                required
                        />
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="cap">CAP</label>
                    </div>
                    <div class="col-75">
                        <input
                                type="text"
                                id="cap"
                                name="cap"
                                placeholder="Es . 84012"
                                autocomplete="on"
                                pattern="[0-9]{2,6}$"
                                required
                        />
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="note">Note per il corriere</label>
                    </div>
                    <div class="col-75">
                        <input
                                type="text"
                                id="note"
                                name="note"
                                placeholder="Es . Chiamare il numero 000-0000000"
                                autocomplete="on"
                        />
                    </div>
                </div>
                <input id="submit" type="submit" value="Registrati" />
                </form>
                </div>
            </div>
            </div>
        </div>





<%@include file="footer.html"%>
</body>
</html>
