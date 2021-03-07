<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Il mio profilo"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html"%>
</div>
<div class="row">
    <div class="leftcolumn">
        <c:if test = "${avviso != null}">
        <div class="card">
            <h1>${avviso}</h1>
        </div>
        </c:if>
        <div class="card profile">
            <h2>Il mio profilo</h2>
            <div id="image_profile">
                <img src="img/utentegenerico.png" height="150px"/>
            </div>
            <ul id="profile">
                <li><b>Nome: </b>${utente.nome}</li>
                <li><b>Cognome: </b>${utente.cognome}</li>
                <li><b>Username: </b>${utente.username}</li>
                <li><b>Email:</b>${utente.email}</li>
            </ul>
        </div>
        <c:if test = "${utente.getTipo() == \"C\"}">
            <div class="card">
                <h2>Il mio indirizzo</h2>
                <li><b>Via: </b>${utente.getIndirizzo().getVia()}</li>
                <li><b>Comune: </b>${utente.getIndirizzo().getComune()}</li>
                <li><b>Provincia: </b>${utente.getIndirizzo().getProvincia()}</li>
                <li><b>Cap: </b>${utente.getIndirizzo().getCap()}</li>
                <li><b>Note: </b>${utente.getIndirizzo().getNotecorriere()}</li>
            </div>
        </c:if>
    <div class="card">
        <h2>Cambia password</h2>
        <div class="contact-container" style="display: grid">
                <div id="errorformmessage" style="display: none"><p></p></div>
                <div id="okformmessage" style="display:none;"><p>Cambio password effettuato! Al prossimo accesso ti sará chiesta la nuova password</p></div>
            <form action="cambiapassword" method="post">
                <div class="row">
                    <div class="col-25">
                        <label for="expassword">Password corrente</label>
                    </div>
                    <div class="col-75">
                        <input
                                type="password"
                                id="expassword"
                                name="expassword"
                                placeholder="Password corrente"
                                required
                        />
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
                <input id="submit" type="submit" value="Cambia password" />
            </form>
        </div>
    </div>
        <div class="card">
            <h2>Cambia informazioni personali</h2>
            <div id="errorformmessage1" style="display: none"><p></p></div>
            <div id="okformmessage1" style="display:none;"><p>Cambio informazioni effettuato!</p></div>
            <div class="contact-container" style="display: grid">
                <form action="modificadatipersonali">
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
                                    value="${utente.nome}"
                                    autocomplete="on"
                                    pattern="[A-Za-z]+" title="Questo campo puó contenere solo caratteri"
                                    minlength="2"
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
                                    value="${utente.cognome}"
                                    placeholder="Cognome"
                                    autocomplete="on"
                                    pattern="[A-Za-z]+" title="Questo campo puó contenere solo caratteri"
                                    minlength="2"
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
                                    value="${utente.email}"
                                    placeholder="Email"
                                    autocomplete="on"
                                    required
                            />
                        </div>
                    </div>
                    <input id="send" type="submit" value="Aggiorna informazioni"/>
                </form>
            </div>
    </div>
        <c:if test = "${utente.getTipo() == \"C\"}">
            <div class="card">
                <h2>Modifica Indirizzo</h2>
                <div class="contact-container" style="display: grid">
                    <form action = "modificaindirizzo">
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
                                        value="${utente.getIndirizzo().getVia()}"
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
                                        value="${utente.getIndirizzo().getComune()}"
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
                                        value="${utente.getIndirizzo().getProvincia()}"
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
                                        value="${utente.getIndirizzo().getCap()}"
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
                                        value="${utente.getIndirizzo().getNotecorriere()}"
                                        placeholder="Es . Chiamare il numero 000-0000000"
                                        autocomplete="on"
                                />
                            </div>
                        </div>
                        <input id="invia" type="submit" value="Aggiorna indirizzo"/>
                    </form>
                </div>
            </div>
        </c:if>
    </div>

<jsp:include page="footererightcollum.jsp"/>

