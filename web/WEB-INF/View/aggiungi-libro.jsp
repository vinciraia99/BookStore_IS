<%--
  Created by IntelliJ IDEA.
  User: scarp
  Date: 03/03/2021
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${titolo}"/>
</jsp:include>

<div class="card ricerca_mobile">
    <%@include file="search.html" %>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card profile">
            <h2>${titolo}</h2>
        </div>
        <div class="card">
            <div class="contact-container">
                <form enctype="multipart/form-data"  action="caricalibro" method="post" id="addlibro">
                        <div class="row">
                            <div class="col-25">
                                <label id="lisbn" for="isbn">ISBN*</label>
                            </div>

                            <div class="col-75">
                                <input
                                        type="text"
                                        id="isbn"
                                        name="isbn"
                                        placeholder="ISBN"
                                        value="${libro.numberisbn}"
                                        required
                                        pattern="^(?=(?:\D*\d){10}(?:(?:\D*\d){3})?$)[\d-]+$"
                                        title="L'isbn deve essere lungo 13 caratteri e contenere solo numeri"
                                />
                            </div>

                        </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="titolo">Titolo*</label></div>
                        <div class="col-75">
                            <input
                                    type="text"
                                    id="titolo"
                                    name="titolo"
                                    placeholder="Titolo"
                                    value="${libro.titolo}"
                                    pattern="^(?=.*[^\W_])[\w ]*$"
                                    required
                            />

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="autore">Autori*</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="text"
                                    id="autore"
                                    name="autore"
                                    placeholder="Autore"
                                    value="${autore.nome}"
                                    pattern="^(?=.*[^\W_])[\w ]*$"
                                    required
                            />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="descrizione" >Descrizione libro*</label>
                        </div>
                        <div class="col-75">
                  <textarea
                          id="descrizione"
                          name="descrizione"
                          placeholder="Scrivi qui"
                          style="height: 200px;"
                          pattern="^[A-Za-z0-9 !@#$%‘]+$"
                          required
                  >${libro.descrizione}</textarea>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-25">
                            <label id="lanno" for="anno">Anno di pubblicazione*</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="number"
                                    id="anno"
                                    name="anno"
                                    placeholder="Anno di pubblicazione"
                                    pattern="^(([1-9]|0[1-9]|[12]\d|3[01])-([1-9]|0[1-9]|1[0-2])-[12]\d{3})$"
                                    value="${libro.anno_pubblicazione}"
                                    required
                            />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-25">
                            <label for="img">Carica copertina <c:if test = "${libro.numberisbn != null}">(Se non vuoi modificare la copertina lascia il campo vuoto)</c:if></label>
                        </div>
                        <div class="col-75">
                            <input type="file"  onchange="valida()" name="img" id="img" accept="image/*" required <c:if test = "${libro.numberisbn == null}">
                        </c:if>
                            <p id="progressNumber"></p>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-25">
                            <label for="prezzo">Prezzo*</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="number"
                                    id="prezzo"
                                    name="prezzo"
                                    placeholder="Prezzo"
                                    value="${libro.prezzo}"
                                    step=0.01
                                    pattern="[0-9]"
                                    required
                            />
                        </div>
                    </div>
                    <c:if test = "${libro== null}">
                        <div class="row">

                            <div class="col-25">
                                <label for="ndisp">Numero libri disponibili*</label>
                            </div>
                            <div class="col-75">
                                <input
                                        type="number"
                                        id="ndisp"
                                        name="ndisp"
                                        placeholder="Numero libri disponibili"
                                        value="${libro.numero_disponibili}"
                                        min="1"
                                        required
                                />
                            </div>
                        </div>
                    </c:if>



                    <div class="row">
                        <div class="col-25">
                            <label id="categoria">Categoria(Puoi anche inserirne più di una)*</label>
                        </div>
                        <div class="col-75">
                            <c:forEach items="${cecked}" var="cecked">
                                <input type="checkbox" id="${cecked.id}" name="${cecked.id}" value="${cecked.id}" checked>
                                <label for="${cecked.id}">${cecked.nome}</label><br>
                            </c:forEach>
                            <c:forEach items="${categorie}" var="categoria">
                                <input type="checkbox" id="${categoria.id}" name="${categoria.id}" value="${categoria.id}">
                                <label for="${categoria.id}">${categoria.nome}</label><br>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="row">
                        <input id="contatti" type="submit" value="Pubblica"/>
                    </div>
                </form>
                <h5 class="check">I campi segnati con * sono obbligatori</h5>
            </div>
        </div>
    </div>

    <script> $(document).ready(function(){

        $('#isbn').change(function () {
            $.get({url: "ceckisbn?id=" + $("#isbn").val(), success: function(result){
                    if(result == "no"){
                        $("#isbn").css("border","2px solid red");
                        $("#lisbn").css("color","red");
                        alert("Un libro con questo isbn esiste giá");
                    }else{
                        $("#isbn").css("border","");
                        $("#lisbn").css("color","black");
                    }
                }});

        });

        $('#anno').change(function () {
            if (parseInt($("#anno").val()) > new Date().getFullYear()) {
                $("#lanno").css("color", "red");
                $("#anno").css("border","2px solid red");
            }else{
                $("#anno").css("border","2px solid green");
                $("#lanno").css("color","black");
            }
        });


        $('#addlibro').submit(function() {
            var errore = "Sono stati trovati i seguenti errori:\n\n";

            $.get({url: "ceckisbn?id=" + $("#isbn").val(), success: function(result){
                    if(result == "no"){
                        $("#isbn").css("border","2px solid red");
                        $("#lisbn").css("color","red");
                        errore = errore + "Un libro con questo isbn esiste giá\n";
                    }else{
                        $("#isbn").css("border","");
                        $("#lisbn").css("color","black");
                    }
                }});

            if(parseInt($("#anno").val()) > new Date().getFullYear()){
                $("#lanno").css("color", "red");
                $("#anno").css("border","2px solid red;");
                errore = errore + "L'anno inserito da te non é valido, l'anno deve essere inferiore alla data attuale\n";
            }

            if ($("input[type=checkbox]").is(
                ":checked")) {
            } else {
                errore = errore + "Devi selezionare almeno una categoria\n";
                $("#categoria").css("color","red");
            }

            $.get({url: "ceckisbn?id=" + $("#isbn").val(), success: function(result){
                    console.log(result);
                    if(result == "no"){
                        errore = errore + "Un libro con questo isbn esiste giá \n";
                        $("#lisbn").css("color","red");
                    }
                }});

            if(errore.length > 42){
                alert(errore);
                errore = "Sono stati trovati i seguenti errori:\n\n";
                return false;
            }else{
                return true;
            }
        });


    });


    function valida() {
        var fileInput =
            document.getElementById('img');

        var filePath = fileInput.value;

        var allowedExtensions =
            /(\.jpg|\.jpeg|\.png)$/i;

        if (!allowedExtensions.exec(filePath)) {
            alert('Tipo di file non valido');
            fileInput.value = '';
            return false;
        }

    }</script>
    <jsp:include page="footererightcollum.jsp"/>

