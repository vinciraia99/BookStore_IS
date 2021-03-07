<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>
<div class="row">
    <div class="leftcolumn">
        <div class="card" id="titolo">
            <h2>Carrello</h2>
        </div>
        <c:if test = "${carrello.getLibri().size() == 0 || carrello == null}">
            <div class="card">
                <h3>Al momento non ci sono prodotti nel carrello</h3>
            </div>
        </c:if>
        <c:if test = "${carrello.getLibri().size() > 0}">
            <c:forEach items="${carrello.getLibri()}" var="libro">
                <div class="card" id="${libro.getLibro().getIsbn()}">
                    <div class="product_page">
                        <div class="card info_page cart">
                            <img onclick="location.href='visualizzalibro?id=${libro.getLibro().getIsbn()}'" src="./img/${libro.getLibro().getCopertina()}" alt="libro" height="215px" class="image" />
                            <div class="product_info">
                                <p class="title">${libro.getLibro().getTitolo()}</p>
                                <p class="description" id="descrizione_normale">${libro.getLibro().getTrama()}</p>
                                <p class="description" id="descrizione_corta">${libro.getLibro().getTrama()}</p>
                            </div>
                        </div>
                        <div class="card cartbox cart">
                            <p id="prezzoProdotto${libro.getLibro().getIsbn()}" class="price_view">${libro.getPrezzoString()}</p>
                            <div class="quantity">
                                <input class="quantita" id="modificaQuantita${libro.getLibro().getIsbn()}" type="number" value="${libro.getQuantita()}">
                                <a onclick='rimuovi("${libro.getLibro().getIsbn()}")'  class="remove_button">Rimuovi</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <<div class="card" id="totale" style="display: flow-root">
                <div class="total">
                    <p id="tot">
                        <b>Totale:</b> ${totale} €<br>
                    </p>
                     <c:if test = "${utente == null}">
                         <a href="login" id="paybutton">Per poter ordinare devi effettuare il login</a>
                     </c:if>
            <c:if test = "${utente != null && utente.getTipo() == \"C\"}">
                    <a href="effettuaordine" id="paybutton">Procedi al pagamento</a>
            </c:if>
                </div>
            </div>
        </c:if>
    </div>

    <script>
        function rimuovi(isbn){
            var xmlHttpReq = new XMLHttpRequest();
            xmlHttpReq.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    console.log(this.responseText);
                    var prezzi = this.responseText.split(" ");
                    var conferma = prezzi[0];
                    var totProdotti = prezzi[6];
                    if (conferma == "ok" && totProdotti != "0") {
                        var prezzoCarrelloNetto = prezzi[1];
                        var cod = "<b>Totale:</b>" + prezzoCarrelloNetto + "€";
                        $("#tot").html(cod);
                    }else if(conferma == "ok" && totProdotti == "0"){
                        $("#totale").fadeOut("normal", function() {
                            $(this).remove();
                        });
                        document.getElementById('titolo').insertAdjacentHTML('afterend', '<div class="card" id="carrellovuoto" style="display: none">\n' +
                            '                <h3>Al momento non ci sono prodotti nel carrello</h3>\n' +
                            '            </div>');
                        $("#carrellovuoto").fadeIn("slow");
                    }
                }
            }
            xmlHttpReq.open("GET", "rimuovicarrello?id=" + isbn , true);
            xmlHttpReq.send();
            $("#" + isbn).fadeOut("normal", function() {
                $(this).remove();
            });
        }

        $(document).ready(function(){




        $("input[type=\"number\"] ").change((event)=>{
            var id = (event.target.id).slice(16,event.target.id.lenght);
            var quantita = (event.target.value);
            $.ajax({
                url : "modificaquantitacarrello",
                data : {
                    id,
                    quantita
                },
                error:()=> console.error("errore Ajax Carrello"),
                success : (responseText)=>{
                    var prezzi = responseText.split(" ");
                    var totProdotti = prezzi[0];
                    var prezzoProdottoTot = prezzi[1];
                    var disponibili = prezzi[2];
                    var cod = "<b>Totale:</b> " + totProdotti + " €" + "<br>";
                    $("#tot").html(cod);
                    $("#prezzoProdotto" + id).text(prezzoProdottoTot + " €");

                    if(quantita.length > 8){
                        quantita =  quantita.substring(0,7);
                    }

                    if(parseInt(disponibili)<=parseInt(quantita)){
                       $("#modificaQuantita"+ id).val("" + disponibili);
                    }
                    if(quantita<=0 || quantita==""){
                        $("#modificaQuantita"+ id).val("1");
                    }
                }
            })
        })
        });
    </script>


<jsp:include page="footererightcollum.jsp"/>