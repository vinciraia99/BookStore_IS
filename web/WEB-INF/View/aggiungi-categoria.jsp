<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Aggiungi Categoria"/>
</jsp:include>
<div class="card ricerca_mobile">
    <%@include file="search.html" %>
</div>
<div class="row">
    <div class="leftcolumn">
        <div class="card profile">
            <h2>Aggiungi Categoria</h2>
        </div>
        <div class="card">
            <div class="contact-container">
                <form method="post" action="aggiungicategoria">
                    <div class="row">
                        <div class="col-25">
                            <label for="titolo">Nome categoria*</label></div>
                        <div class="col-75">
                            <input
                                    type="text"
                                    id="titolo"
                                    name="titolo"
                                    placeholder="Categoria"
                                    minlength="2"
                                    required
                            />

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="descrizione" >Descrizione*</label>
                        </div>
                        <div class="col-75">
                  <textarea
                          id="descrizione"
                          name="descrizione"
                          placeholder="Scrivi qui"
                          style="height: 200px;"
                          minlength="2"
                          required
                  ></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <input  type="submit" id="submitform" value="Pubblica"/>
                    </div>
                </form>
                <h5 class="check">I campi segnati con * sono obbligatori</h5>
            </div>
        </div>
    </div>
    <jsp:include page="footererightcollum.jsp"/>

