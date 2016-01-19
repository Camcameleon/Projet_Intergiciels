import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Coalition {
	
	private ArrayList<Joueur> liste_joueur = null;
	private String nom;
	
	@ManyToOne
	Partie partie;
	@OneToMany
	Collection<Joueur> appartient;

	public Coalition(String nom, Joueur joueur) {
		this.nom = nom;
		liste_joueur.add(joueur);
	}

}
