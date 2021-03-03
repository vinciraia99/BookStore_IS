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
    <div class="card">
        <h2>Cambia password</h2>
        <div class="contact-container" style="display: grid">
                <div id="errorformmessage" style="display: none"><p></p></div>
                <div id="okformmessage" style="display:none;"><p>Cambio password effettuato! Al prossimo accesso ti sará chiesta la nuova password</p></div>
            <form action="cambiapassword">
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
                <form onsubmit="return edit()">
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
                                    onchange="verificaemail()"
                                    required
                            />
                            <font color="red" size="2" style="display: none" id="erroremail">L'email da lei inserita é giá usata da un altro account</font>
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
                                    value="${utente.username}"
                                    onchange="verificausername()"
                                    required
                            />
                            <font color="red" size="2" style="display: none" id="errorusername">L'username da lei inserito é giá usato da un altro account</font>
                        </div>
                    </div>
                    <input id="send" type="submit" value="Aggiorna informazioni"/>
                </form>
            </div>
    </div>
        <script>
            var ceckpasswordflag = false;
            var expasswordflag = false;

            function confermapassword() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        if (this.responseText == "true") {
                            document.getElementById("errorexpassword").style.display = "";
                            document.getElementById("expassword").style.border = "2px solid red";
                            expasswordflag=true;
                        } else {
                            document.getElementById("expassword").style.border = "";
                            document.getElementById("errorexpassword").style.display = "none";
                            expasswordflag=false;
                        }
                    }
                };
                xhttp.open("GET", "ceckpassword?id=" + document.getElementById("expassword").value, true);
                xhttp.send();

            }

            function confrontapassword() {
                var password = document.getElementById("passwordsubmit").value;
                var confirm = document.getElementById("passwordsubmitconfirm").value;
                if(password == confirm){
                    document.getElementById("errorpassword").style.display = "none";
                    document.getElementById("passwordsubmitconfirm").style.border = "";
                    ceckpasswordflag = false;
                }else{
                    document.getElementById("errorpassword").style.display = "";
                    document.getElementById("passwordsubmitconfirm").style.border = "2px solid red";
                    ceckpasswordflag = true;
                }
            }

            function formceck() {
                confermapassword();
                confrontapassword();
                var error = "Sono stati trovati i seguenti errori:\n\n";
                if(expasswordflag){
                    error = error + "La password corrente non é corretta!\n";
                }
                if(ceckpasswordflag){
                    error = error + "La password e la conferma password sono diverse\n";
                }
                if(error.length>44){
                    document.getElementById("errorformmessage").innerText=error;
                    document.getElementById("errorformmessage").style.display="";
                    document.getElementById("okformmessage").style.display="none";
                }else {
                        var xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function () {
                            if (this.readyState == 4 && this.status == 200) {
                                if (this.responseText == "true") {
                                    document.getElementById("okformmessage").style.display = "";
                                    document.getElementById("errorformmessage").style.display="none";
                                } else {
                                    document.getElementById("errorformmessage").innerText=this.responseText;
                                    document.getElementById("errorformmessage").style.display="";
                                    document.getElementById("okformmessage").style.display="none";
                                }
                            }
                        };
                        xhttp.open("GET", "changepassword?id=" + document.getElementById("expassword").value + "&id1=" + document.getElementById("passwordsubmit").value + "&id2=" + document.getElementById("passwordsubmitconfirm").value, true);
                        xhttp.send();
                }
                return false;

            }

            var ceckusernameflag = false;
            var ceckemailflag =  false;

            function verificausername() {
                if("${utente.username}" != document.getElementById("usernamesubmit").value){
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        if (this.responseText == "true") {
                            document.getElementById("errorusername").style.display = "";
                            document.getElementById("usernamesubmit").style.border = "2px solid red";
                            ceckusernameflag=true;
                        } else {
                            document.getElementById("errorusername").style.display = "none";
                            document.getElementById("usernamesubmit").style.border = "";
                            ceckusernameflag=false;
                        }
                    }
                };
                xhttp.open("GET", "ceckusername?id=" + document.getElementById("usernamesubmit").value, true);
                xhttp.send();
                }

            }

            function verificaemail() {
                if("${utente.email}" != document.getElementById("email").value){
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        if (this.responseText == "true") {
                            document.getElementById("erroremail").style.display = "";
                            document.getElementById("email").style.border = "2px solid red";
                            ceckemailflag=true;
                        } else {
                            document.getElementById("email").style.border = "";
                            document.getElementById("erroremail").style.display = "none";
                            ceckemailflag=false;
                        }
                    }
                };
                xhttp.open("GET", "ceckemail?id=" + document.getElementById("email").value, true);
                xhttp.send();
                }
            }

            function edit() {
                verificaemail();
                verificausername();
                var error = "Sono stati trovati i seguenti errori:\n\n";
                if(ceckemailflag){
                    error = error + "L'email da lei inserita é giá usata da un altro account\n";
                }
                if(ceckusernameflag){
                    error = error + "L'username da lei inserito é giá usato da un altro account\n";
                }
                if(error.length>44){
                    document.getElementById("errorformmessage1").innerText=error;
                    document.getElementById("errorformmessage1").style.display="";
                    document.getElementById("okformmessage1").style.display="none";
                }else {
                    var xhttp = new XMLHttpRequest();
                    xhttp.onreadystatechange = function () {
                        if (this.readyState == 4 && this.status == 200) {
                            if (this.responseText == "true") {
                                document.getElementById("okformmessage1").style.display = "";
                                document.getElementById("errorformmessage1").style.display="none";
                                document.getElementById("profile").getElementsByTagName("li")[0].innerHTML="<b>Nome:</b> "+document.getElementById("nome").value;
                                document.getElementById("profile").getElementsByTagName("li")[1].innerHTML="<b>Cognome:</b> "+document.getElementById("cognome").value;
                                document.getElementById("profile").getElementsByTagName("li")[3].innerHTML="<b>Username:</b> "+document.getElementById("usernamesubmit").value;
                                document.getElementById("profile").getElementsByTagName("li")[4].innerHTML="<b>Email:</b> "+document.getElementById("email").value;
                            } else {
                                document.getElementById("errorformmessage1").innerText=this.responseText;
                                document.getElementById("errorformmessage1").style.display="";
                                document.getElementById("okformmessage1").style.display="none";
                            }
                        }
                    };
                    xhttp.open("GET", "changeinfo?id=" + document.getElementById("nome").value + "&id1=" + document.getElementById("cognome").value + "&id2=" + document.getElementById("email").value + "&id3="+ document.getElementById("usernamesubmit").value, true);
                    xhttp.send();
                }
                return false;

            }




        </script>
    </div>
<jsp:include page="footererightcollum.jsp"/>

