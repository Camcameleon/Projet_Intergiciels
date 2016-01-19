import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Partie {
	
	// La liste des joueurs dans la partie
	@OneToMany(mappedBy = "joueur", fetch=FetchType.EAGER)
	Collection<Joueur> joueur;
	// La liste des coalitions présentent dans le jeu
	@OneToMany(mappedBy = "coalition", fetch=FetchType.EAGER)
	Collection<Coalition> coalition;

	public Partie() {
		// TODO Auto-generated constructor stub
	}

}
