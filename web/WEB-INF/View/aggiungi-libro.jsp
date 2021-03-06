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
    <jsp:param name="pageTitle" value="Aggiungi libro"/>
</jsp:include>

<div class="card ricerca_mobile">
    <%@include file="search.html" %>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card profile">
            <h2>Aggiungi libro</h2>
        </div>
        <div class="card">
            <div class="contact-container">
                <form enctype="multipart/form-data"  action="aggiungilibro" method="post" id="addlibro">
                        <div class="row">
                            <div class="col-25">
                                <label id="lisbn" for="isbn">ISBN*</label>
                            </div>

                            <div class="col-75">
                                <input
                                        type="number"
                                        id="isbn"
                                        name="isbn"
                                        placeholder="ISBN"
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
                                    pattern="^(?=.*[^\W_])[\w ]*$"
                                    required
                            />

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="autore">Autori (Serparati con una virgola tra di loro)*</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="text"
                                    id="autore"
                                    name="autore"
                                    placeholder="Autore"
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
                          required
                  ></textarea>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-25">
                            <label id="lanno" for="anno">Anno di pubblicazione (formato DD-MM-AAAA)*</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="text"
                                    id="anno"
                                    name="anno"
                                    placeholder="Anno di pubblicazione"
                                    pattern="^(([1-9]|0[1-9]|[12]\d|3[01])-([1-9]|0[1-9]|1[0-2])-[12]\d{3})$"
                                    required
                            />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-25">
                            <label for="img">Carica copertina</label>
                        </div>
                        <div class="col-75">
                            <input type="file"  onchange="valida()" name="img" id="img" accept="image/*" required >
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
                                    step=0.01
                                    pattern="[0-9]"
                                    required
                            />
                        </div>
                    </div>
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
                                        min="1"
                                        required
                                />
                            </div>
                        </div>




                    <div class="row">
                        <div class="col-25">
                            <label id="categoria">Categoria(Puoi anche inserirne più di una)*</label>
                        </div>
                        <div class="col-75">
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

    <script>
        var errore = "";
        $('#addlibro').submit(function() {
            errore ="";
            if ($("input[type=checkbox]").is(
                ":checked")) {
            } else {
                errore = errore + "Devi selezionare almeno una categoria\n";
                $("#categoria").css("color", "red");
            }

            if(errore.length > 2){
                errore = "Sono stati trovati i seguenti errori:\n\n" + errore;
                alert(errore);
                return false;
            }else{
                return true;
            }
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

