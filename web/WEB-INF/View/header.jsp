<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><%@taglib prefix="c"
                                         uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <meta http-equiv="X-UA-Compatible" content="IE-edge" />
    <meta name="format-detection" content="telephone=no">
    <title>BookStore -  ${param.pageTitle}</title>
    <link rel="stylesheet" href="style.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<div class="header">
    <h1>BookStore</h1>
    <p>Tutti i libri al miglior prezzo!</p>
</div>

<div class="topnav">
    <a href="${pageContext.request.contextPath}">Home</a>
    <div class="dropdown">
        <button class="dropbtn">Categorie</button>
        <div class="dropdown-content">
            <c:forEach items="${categorie}" var="categoria">
                <a href="visualizzacategoria?id=<c:out value="${categoria.id}"/>"><c:out
                        value="${categoria.nome}" /></a>
            </c:forEach>
        </div>
    </div>
    <div class="righttopnav">
        <c:if test = "${utente != null}">
            <div class="dropdown">
                <c:if test = "${utente.tipo == \"M\"}">
                    <button class="dropbtn">Gestione Manager</button>
                </c:if>
                <c:if test = "${utente.tipo == \"R\"}">
                    <button class="dropbtn">Gestione responsabile catagolo</button>
                </c:if>
                <c:if test = "${utente.tipo == \"C\"}">
                    <button class="dropbtn">Gestione utente</button>
                </c:if>
                <div class="dropdown-content">
                    <a id="mieidati" href="visualizzaprofilo"/>I miei dati</a>
                    <c:if test = "${utente.tipo == \"C\"}">
                        <a id="mieiordini" href="visualizzaordini"/>I miei ordini</a>
                    </c:if>

                    <c:if test = "${utente.tipo == \"M\" || utente.tipo == \"R\"}">
                        <a href="aggiungilibro"/>Aggiungi libro</a>
                        <a href="aggiungicategoria">Aggiungi categoria</a>
                        <c:if test = "${utente.tipo == \"M\"}">
                            <a href="visualizzaordinidituttigliutenti"/>Gestisci ordini utenti</a>
                            <a href="visualizzautenti"/>Gestisci utenti</a>
                        </c:if>
                    </c:if>
                    <a href="logout"/>Esci</a>
                </div>
            </div>
        </c:if>
        <c:if test = "${utente == null}">
            <a href="login">Accedi</a>
            <a href="registrazionecliente">Registrati</a>
        </c:if>
        <c:if test = "${utente == null || (utente != null &&  utente.tipo == \"C\")}">
            <a href="visualizzacarrello" id="carrellonavbar">Carrello <c:if test = "${flag == null}">(<c:if test = "${carrello == null}">0</c:if><c:if test = "${carrello != null}">${carrello.totaleProdotti}</c:if>)</c:if></a>
        </c:if>

    </div>
</div>
