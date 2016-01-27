import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			request.getRequestDispatcher("site.jsp").forward(request, response);
		}
		if (action.equals("ajoutJoueur")) {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String pseudo = request.getParameter("pseudo");
			String adresse_mail = request.getParameter("adresse_mail");
			String mot_de_passe = request.getParameter("mot_de_passe");
			f.inscription(nom, prenom, pseudo, mot_de_passe, adresse_mail);
			// création d'une session associée au joueur
			HttpSession session = request.getSession(); 
			request.setAttribute(pseudo, f.trouver(pseudo));
			response.sendRedirect("p.html");
		}
		if (action.equals("classement")) {
			response.sendRedirect("classement.html");
		}
		if (action.equals("partie")) {
			// On récupère le joueur associée à la bonne session
			HttpSession session = request.getSession(false);
			Joueur joueur = (Joueur) session.getAttribute("pseudo");
			request.setAttribute("nom", joueur.getNom());
			request.setAttribute("prenom", joueur.getPrenom());
			request.setAttribute("pseudo", joueur.getPseudo());
			request.setAttribute("adresse_mail", joueur.getAdresse_mail());
			request.setAttribute("argent", joueur.getArgent());
			request.setAttribute("nom_coalition", joueur.getNom_coalition());
			response.sendRedirect("partie.html");
			//request.setAttribute("lp", lp);
			/*Collection<Personne> lp = f.listePersonnes();
			Collection<Adresse> la = f.listeAdresses();
			request.setAttribute("lp", lp);
			request.setAttribute("la", la);
			request.getRequestDispatcher("saisie_associer.jsp").forward(request, response);*/

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
