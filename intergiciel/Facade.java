import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Singleton
public class Facade {
	
	@PersistenceContext
	EntityManager entitymanager;
	
	public synchronized void acheter(int q, Joueur joueur, Marchandise marchandise) {
		assert q > 0;
		if (!joueur.possede.contains(marchandise)) {
			joueur.possede.add(marchandise);
			marchandise.getTypemarchandise().setQuantite(q);
		}
		int presente = marchandise.getTypemarchandise().getQuantite();
		int nouvelle_quantite = presente + q;
		marchandise.getTypemarchandise().setQuantite(nouvelle_quantite);
	}
	
	public synchronized void vendre(int q, Joueur joueur, Marchandise marchandise) {
		assert q > 0;
		assert q <= marchandise.getTypemarchandise().getPrix();
		int presente = marchandise.getTypemarchandise().getQuantite();
		int nouvelle_quantite = presente - q;
		marchandise.getTypemarchandise().setQuantite(nouvelle_quantite);
	}
	
	public void produire() {
		
	}
	
	public void ameliorer(Joueur joueur, int id_usine) {
		/* 
		 * il faut donc une classe usine ou serait enregistré le prix de tous les différents niveaux d'amélioration
		 * et leurs caratéristiques.
		 */
	}
	
	public void afficher_historique_marchandises(Marchandise marchandise) {
		/*
		 * il faudrait une hashmap par marchandise, dans laquelle il y aurait le prix à chaque instant caratéristique du jeu.
		 * On aurait ensuite plus on  qu'a les afficher sous forme de graphique. 
		 */
	}
	
	public void afficher_historique_partie(Partie partie) {
		/*
		 * il faut se mettre d'accord sur ce que fait réellement cette fonction, en gros
		 * qu'est ce que l'on affiche.
		 */
	}
	
	public void connection(String pseudo, String mot_de_passe) {
		/*
		 * avec le mot de passe et le pseudo on retrouve le joueur correspondant et on lui affiche tout le 
		 * truc de facon swag et jolie et tout et tout.
		 */
		Joueur joueur = entitymanager.find(Joueur.class, pseudo);
		if (!joueur.equals(null)) {
			if ((joueur.getMot_de_passe()).equals(mot_de_passe)) {
				/* on retourne quoi ???
				 * est-ce que ce n'est pas dans la page que l'on fait ceci ???
				 */
			}
		}
		else {
			System.out.println("Ce joueur n'existe pas");
		}
	}
	
	public void inscription(String nom, String prenom, String pseudo, String mot_de_passe, String adresse_mail) {
		Joueur joueur = entitymanager.find(Joueur.class, pseudo);
		if (joueur.equals(null)) {
			Joueur j =  new Joueur(nom, prenom, pseudo, mot_de_passe, adresse_mail);
			entitymanager.persist(j);
		}
		else {
			System.out.println("Ce pseudo de joueur existe déjà, veuillez en trouver un autre!");
		}
	}
	
	public Coalition creer_coalition(String nom, int nombre_membre) {
		return null;
		/*
		 * se mettre d'accord sur les attributs d'une coalition et sur ses caractéristiques (nombres de membres, etc...)
		 */
	}
	
	public void rejoindre_coalition(Joueur joueur, Coalition coalition) {
		
	}
	
}
