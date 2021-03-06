package Control.Libri;

import Control.Eccezioni.ErroreLibro;
import Control.Eccezioni.ErroreSuiDati;
import Control.Eccezioni.MyServletException;
import Entities.Account;
import Entities.Autore;
import Entities.Categoria;
import Entities.Libro;
import Manager.ManagerCategorie;
import Manager.ManagerLibri;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Raffaele Scarpa
 * @version 0.1
 * @since 04/03/2021
 */
@MultipartConfig
@WebServlet("/aggiungilibro")
public class AggiungiLibroServlet extends HttpServlet {

    private static final String CARTELLA_UPLOAD = "img";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("utente");
        if (user != null && (!user.getTipo().equals("C"))) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/View/aggiungi-libro.jsp");
            dispatcher.forward(request,response);
        }else{
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("utente");
        if (user != null && (!user.getTipo().equals("C"))) {
            String errore = "";
            ManagerLibri managerLibri =  new ManagerLibri();
            String isbn = request.getParameter("isbn");
            if(isbn == null || isbn.length()<10 || isbn.length()>13 || isbn.matches("^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$" ) == false){
                errore = errore + "Isbn non valido o vuoto";
            }
            if(isbn!=null && managerLibri.acquisisciLibro(isbn)!=null){
                throw new ErroreLibro("Libro già esistente");
            }
            String titolo = request.getParameter("titolo");
            if(titolo == null || titolo.length()<1 || titolo.length()>100 || titolo.matches("^(?=.*[^\\W_])[\\w ]*$" ) == false){
                errore = errore + "Titolo non valido o vuoto";
            }
            String autorelist = request.getParameter("autore");
            if(autorelist == null || autorelist.length()<1 || autorelist.length()>100){
                errore = errore + "Autore non valido o vuoto";
            }
            String trama = request.getParameter("descrizione");
            if(trama == null || trama.length()<1 || trama.length()>500){
                errore = errore + "Trama non valida o vuota";
            }
            String data = request.getParameter("anno");
            if(data == null || data.matches("^(([1-9]|0[1-9]|[12]\\d|3[01])-([1-9]|0[1-9]|1[0-2])-[12]\\d{3})$" ) == false){
                errore = errore + "Anno non valido o vuoto";
            }
            Part copertina = request.getPart("img");
            if(copertina == null || (copertina.getContentType().endsWith("jpg") == false && copertina.getContentType().endsWith("jpeg") == false && copertina.getContentType().endsWith("png") == false)){
                errore = errore + "Copertina non valida o vuota";
            }
            String quantita = request.getParameter("ndisp");
            if(quantita == null || quantita.length()<1){
                errore = errore + "Quantita non valida o vuota";
            }
            String prezzo = request.getParameter("prezzo");
            if(prezzo == null || prezzo.length()<1){
                errore = errore + "Prezzo non valido o vuoto";
            }
            if(errore.length()>4){
                throw new ErroreSuiDati(errore);
            }
            int quantitadouble =-1;
            float prezzofloat=-1;
            try{
                quantitadouble = Integer.parseInt(quantita);
                prezzofloat = Float.parseFloat(prezzo);
            }catch (NumberFormatException e){
                errore = errore + "Valori di quantita o prezzo non validi";
            }
            if(quantitadouble<=0 || prezzofloat<=0){
                errore = errore + "Valori di quantita o prezzo non validi";
            }
            if(errore.length()>4){
                throw new ErroreSuiDati(errore);
            }


            String[] lista = autorelist.split(",");
            List<Autore> Autori = new ArrayList<Autore>();
            for (String autori : lista) {
                Autore autore = new Autore(autori);
                Autori.add(autore);
            }

            String[] anno = data.split("-");
            GregorianCalendar pubblicazione = new GregorianCalendar();
            pubblicazione.set(GregorianCalendar.YEAR, Integer.parseInt(anno[2]));
            pubblicazione.set(GregorianCalendar.MONTH, Integer.parseInt(anno[1]));
            pubblicazione.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(anno[0]));


            List<Categoria> listCat = new ManagerCategorie().acquisisciTutteLeCategorie();
            ArrayList<Categoria> listCatform = new ArrayList<Categoria>();
            int id = -1;
            for (int i = 0; i < listCat.size(); i++) {
                String f = request.getParameter(String.valueOf(listCat.get(i).getId()));
                if (f!= null && listCat.get(i).getId() == Integer.parseInt(f)) {
                    listCatform.add(listCat.get(i));
                }
            }

            Libro libro = null;

            libro = new Libro(isbn,titolo,quantitadouble,trama,prezzofloat,copertina.getSubmittedFileName(),pubblicazione);

            if(copertina.getSize() != 0){
                String fileName = copertina.getSubmittedFileName();
                String destinazione = CARTELLA_UPLOAD + File.separator + fileName;
                Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
                for (int i = 2; Files.exists(pathDestinazione); i++) {
                    fileName = i + "_" + fileName;
                    destinazione = CARTELLA_UPLOAD + File.separator + fileName;
                    pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
                }
                InputStream fileInputStream = copertina.getInputStream();
                Files.createDirectories(pathDestinazione.getParent());
                Files.copy(fileInputStream, pathDestinazione);
                libro.setCopertina(fileName);
            }
            libro.setAutori(Autori);
            libro.setCategorie(listCatform);

            managerLibri.aggiuntaLibro(libro);
            response.sendRedirect("visualizzalibro?id="+ libro.getIsbn());


        } else {
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }
}