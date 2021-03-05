package Control.Libri;

import Control.MyServletException;
import Entities.Account;
import Entities.Autore;
import Entities.Categoria;
import Entities.Libro;
import Manager.ManagerCategorie;
import Manager.ManagerLibri;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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

@WebServlet("/aggiungilibro")
public class AggiungiLibroServlet extends HttpServlet {

    private static final String CARTELLA_UPLOAD = "img";

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

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
            float prezzo = Integer.parseInt(request.getParameter("prezzo"));
            double quantita = Integer.parseInt(request.getParameter("ndisp"));
            String errore = "";
            String ISBN = request.getParameter("isbn");
            String Titolo = request.getParameter("titolo");
            String[] lista = request.getParameter("autore").split(",");
            List<Autore> Autori = new ArrayList<Autore>();
            for (String autori : lista) {
                Autore autore = new Autore(autori);
                Autori.add(autore);
            }
            String Descrizione = request.getParameter("descrizione");
            String[] anno = request.getParameter("anno").split("-");
            GregorianCalendar pubblicazione = null;
            pubblicazione.set(GregorianCalendar.YEAR, Integer.parseInt(anno[2]));
            pubblicazione.set(GregorianCalendar.MONTH, Integer.parseInt(anno[1]));
            pubblicazione.set(GregorianCalendar.DATE, Integer.parseInt(anno[0]));


            List<Categoria> listCat = new ManagerCategorie().acquisisciTutteLeCategorie();
            ArrayList<Categoria> listCatform = new ArrayList<Categoria>();
            int id = -1;
            for (int i = 0; i < listCat.size(); i++) {
                String f = request.getParameter(String.valueOf(listCat.get(i).getNome()));
                if (listCat.get(i).getNome().equals(f)) {
                    listCatform.add(listCat.get(i));
                }
            }

            if(Titolo.length() == 0){
                errore = errore + "Il campo titolo non può essere vuoto<br>";
            }

            if(Autori.size() == 0){
                errore = errore + "Il campo autori non può essere vuoto<br>";
            }

            if(prezzo == 0){
                errore = errore + "Il campo prezzo non può essere vuoto<br>";
            }

            if(ISBN.matches("/^[0-9]{13}$/g")){
                errore = errore + "Il campo isbn deve contenere solo numeri e deve essere lungo 13 caratteri<br>";
            }

            ManagerLibri manager = new ManagerLibri();
            if(manager.acquisisciLibro(ISBN) != null){
                errore = errore + "Esiste già un libro con questo isbn<br>";
            }

            if(pubblicazione == null){
                errore = errore + "Il campo anno pubblicazioni non può essere vuoto<br>";
            }

            if(quantita ==0){
                errore = errore + "Il campo quantità non può essere vuoto<br>";
            }


            Part filePart = request.getPart("img");
            if(filePart.getSize() == 0){
                errore = errore + "Non hai inserito alcuna copertina<br>";
            }else if( filePart.getContentType().endsWith("jpg") == false && filePart.getContentType().endsWith("jpeg") == false && filePart.getContentType().endsWith("png") == false){
                errore = errore + "La copertina non ha un estensione valida<br>";
            }

            if(errore.length() > 0) {
                throw new MyServletException("Sono stati trovati i seguenti errori:<br><br>" + errore);
            }

            Libro libro = null;

            if(filePart.getSize() != 0){
                String fileName = Paths.get(filePart.getName()).getFileName().toString();
                String destinazione = CARTELLA_UPLOAD + File.separator + fileName;
                Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
                for (int i = 2; Files.exists(pathDestinazione); i++) {
                    fileName = i + "_" + fileName;
                    destinazione = CARTELLA_UPLOAD + File.separator + fileName;
                    pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
                }
                InputStream fileInputStream = filePart.getInputStream();
                Files.createDirectories(pathDestinazione.getParent());
                Files.copy(fileInputStream, pathDestinazione);
                libro.setCopertina(fileName);
            }


            libro.setIsbn(ISBN);
            libro.setAutori(Autori);
            libro.setTitolo(Titolo);
            libro.setCategorie(listCatform);
            libro.setQuantita(quantita);
            libro.setTrama(Descrizione);
            libro.setData_pubblicazione(pubblicazione);
            libro.setPrezzo(prezzo);

            manager.aggiuntaLibro(libro);
            response.sendRedirect("visualizzalibro?isbn="+ libro.getIsbn());

        } else {
            throw new MyServletException("Sezione dedicata ai soli amministratori, perfavore prima fai il login");
        }
    }
}