import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class Facade implements TimedObject {
	
	@PersistenceContext
	EntityManager entitymanager;
	
	@Resource
	TimerService timerservice;
	
	public synchronized void acheter(int q, Joueur joueur, Marchandise marchandise) {
		if (Partie.peut_jouer) {
			assert q > 0;
			if (!joueur.possede.contains(marchandise)) {
				joueur.possede.add(marchandise);
				marchandise.getTypemarchandise().setQuantite(q);
			}
			int presente = marchandise.getTypemarchandise().getQuantite();
			int nouvelle_quantite = presente + q;
			marchandise.getTypemarchandise().setQuantite(nouvelle_quantite);
		}
	}
	
	public synchronized void vendre(int q, Joueur joueur, Marchandise marchandise) {
		if (Partie.peut_jouer) {
			assert q > 0;
			assert q <= marchandise.getTypemarchandise().getPrix();
			int presente = marchandise.getTypemarchandise().getQuantite();
			int nouvelle_quantite = presente - q;
			marchandise.getTypemarchandise().setQuantite(nouvelle_quantite);
		}
	}
	
	public void produire() {
		/*
		 * Ce que fait cette fonction est peut être directement dans les page html.
		 */
	}
	
	public void ameliorer(Joueur joueur, int id_usine) {
		/* 
		 * il faut donc une classe usine ou serait enregistré le prix de tous les différents niveaux d'amélioration
		 * et leurs caratéristiques.
		 */
	}
	
	public Joeur[] best_players(){
		/*
		 * pour prendre les 5 joeurs les plus riches
		*/
		return entitymanager.createQuery("SELECT TOP 5 * from joueur order by argent desc", Joueur.class).getResultList();	
	}
	
	public Marchandise[] (Marchandise m){
		/*
		 * pour prendre tous les marchandises m
		*/
		return.entitymanager.createQuery("SELECT * from Historique_Marchandise where name = "+m.getName(),Marchandise.class).getResultList();
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
		/*else {
			System.out.println("Ce joueur n'existe pas");
		}*/
	}
	
	public void inscription(String nom, String prenom, String pseudo, String mot_de_passe, String adresse_mail) {
		Joueur joueur = entitymanager.find(Joueur.class, pseudo);
		if (joueur.equals(null)) {
			Joueur j =  new Joueur(nom, prenom, pseudo, mot_de_passe, adresse_mail);
			entitymanager.persist(j);
		}
		/*else {
			System.out.println("Ce pseudo de joueur existe déjà, veuillez en trouver un autre!");
		}*/
	}
	
	public void creer_coalition(String nom, Joueur joueur) {
		/*
		 * se mettre d'accord sur les attributs d'une coalition et sur ses caractéristiques (nombres de membres, etc...)
		 */
		// le nombre de joueur dans une coalition est totalement arbitraire.
		assert(nom != null);
		Coalition coalition = entitymanager.find(Coalition.class, nom);
		if (coalition.equals(null)) {
			Coalition c = new Coalition(nom, joueur);
			entitymanager.persist(c);
		}
		/*else {
			System.out.println("Ce nom de coalition à déjà été choisi.");
		}*/
	}
	
	public void rejoindre_coalition(Joueur joueur, Coalition coalition) {
		assert(coalition != null && joueur != null && coalition.getNombre_joueur() < 10);
		coalition.appartient.add(joueur);
		joueur.setNom_coalition(coalition.getNom());
	}
	
	public void supprimer_joueur(Joueur joueur) {
		if (joueur != null) {
			if (joueur.getNom_coalition() != null) {
				Coalition c = entitymanager.find(Coalition.class, joueur.getNom_coalition());
				c.appartient.remove(joueur);
			}
			joueur.partie.joueur.remove(joueur);
			joueur.possede.clear();
			entitymanager.remove(joueur);
		}
	}
	
	public void ajouter_marchandise(Marchandise m1, Marchandise m2) {
		/*
		 * il faudrait enregistrer toutes les combinaisons possibl d'assemblage des marchandises et checker avant si la combinaison existe.
		 */
	}
	
	@PostConstruct
	public void creerTimer() {
		ScheduleExpression scheduleExp = new ScheduleExpression().second("*").minute("*").hour("*/24");
		Timer timer = timerservice.createCalendarTimer(scheduleExp);
	}
	
	public void ejbTimeout(Timer timer) {
		// c'est ici que l'on fait les appels au focntions pour mettre à jour le bordel.
	}
	
	public Joueur trouver(String pseudo) {
		Joueur joueur = entitymanager.find(Joueur.class, pseudo);
		return joueur;
	}
	
	public Collection<Marchandise> liste_marchandise() {
		return possede.values;
	}
}









