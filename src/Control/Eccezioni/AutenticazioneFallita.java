package Control.Eccezioni;

import javax.servlet.ServletException;

/**
 * @author Vincenzo Raia
 * @version 0.1
 * @since 01/03/2021
 */

public class AutenticazioneFallita extends ServletException {
    private static final long serialVersionUID = -8976023136478643816L;

    public AutenticazioneFallita(String message) {
        super(message);
    }

}
