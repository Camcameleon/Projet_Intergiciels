import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private Facade f;
	
    public Servlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if (action.equals("connexion")) {
			String pseudo = request.getParameter("pseudo");
			String mot_de_passe = request.getParameter("mot_de_passe");
			f.connection(pseudo, mot_de_passe);
			response.sendRedirect("connexion.csp");
		}
		if (action.equals("ajoutJoueur")) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String pseudo = request.getParameter("pseudo");
			String adresse_mail = request.getParameter("adresse_mail");
			String mot_de_passe = request.getParameter("mot_de_passe");
			f.inscription(nom, prenom, pseudo, mot_de_passe, adresse_mail);
			response.sendRedirect("p.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
