<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Dati di pagamento"/>
</jsp:include>

        <div class="row verticalrow">
        <div class="card column" id="logindiv">
            <h2>Inserisci i dati di pagamento</h2>
            <div class="contact-container">
                <form method="post" action="effettuaordine">
                    <div class="row">
                        <div class="col-25">
                            <label for="nome">Nome intestatario</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="text"
                                    id="nome"
                                    name="nome"
                                    placeholder="Nome intestatario"
                                    autocomplete="on"
                                    required
                            />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="carta">Numero carta</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="number"
                                    id="carta"
                                    name="carta"
                                    placeholder="Numero carta"
                                    required
                            />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="cvv">CVV</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="number"
                                    id="cvv"
                                    name="cvv"
                                    placeholder="CVV"
                                    required
                            />
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="scadenza">Scedenza (MM-AA)</label>
                        </div>
                        <div class="col-75">
                            <input
                                    type="number"
                                    id="scadenza"
                                    name="scadenza"
                                    placeholder="Scedenza"
                                    required
                            />
                        </div>
                    </div>
                    <div class="row">
                    </div>
                        <input id="login" type="submit" value="Effettua ordine" />
                    </div>
                </form>
            </div>
        </div>





<%@include file="footer.html"%>
</body>
</html>
