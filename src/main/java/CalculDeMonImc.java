

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.exemple.Imc;

/**
 * Servlet implementation class CalculDeMonImc
 */
@WebServlet("/CalculDeMonImc")
public class CalculDeMonImc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculDeMonImc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String poidsStr = request.getParameter("poids");
            String tailleStr = request.getParameter("taille");

            if (poidsStr == null || tailleStr == null || poidsStr.isEmpty() || tailleStr.isEmpty()) {
                throw new IllegalArgumentException("Veuillez entrer des valeurs valides.");
            }

            double poids = Double.parseDouble(poidsStr);
            double taille = Double.parseDouble(tailleStr);

            if (poids <= 0 || taille <= 0) {
                throw new IllegalArgumentException("Le poids et la taille doivent être supérieurs à zéro.");
            }

            Imc imc = new Imc(taille, poids);
            double resultat = imc.calcul();

            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>IMC Calcul</title></head><body>");
            out.println("<h2>Votre IMC est : " + String.format("%.2f", resultat) + "</h2>");
            out.println("</body></html>");

        } catch (NumberFormatException e) {
            out.println("<h2>Erreur : Veuillez entrer des nombres valides.</h2>");
        } catch (IllegalArgumentException e) {
            out.println("<h2>Erreur : " + e.getMessage() + "</h2>");
        }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
