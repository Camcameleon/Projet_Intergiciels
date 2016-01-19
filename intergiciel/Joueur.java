import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Joueur {
	private String nom;
	private String prenom;
	@Id
	private String pseudo; // clef primaire
	private String mot_de_passe;
	private String adresse_mail;
	private int argent;
	// private SimpleDateFormat date_insciption;
	
	// La liste des marchandises possédées par ce joueur
	@OneToMany(mappedBy = "possede", fetch=FetchType.EAGER)
	Collection<Marchandise> possede;
	@ManyToOne
	Partie partie;
	
	public Joueur(String nom, String prenom, String pseudo, String mot_de_passe, String adresse_mail) {
		assert(nom != null);
		this.nom = nom;
		assert(prenom != null);
		this.prenom = prenom;
		assert(pseudo != null);
		this.pseudo = pseudo;
		assert(mot_de_passe != null);
		this.mot_de_passe = mot_de_passe;
		assert(adresse_mail != null);
		this.adresse_mail = adresse_mail;
		// I faut se mettre d'accord sur la somme d'argent que l'on alloue à un nouveau joueur lors de la création.
		assert(argent >= 0);
		this.argent = 10000;
		// this.date_insciption = new SimpleDateFormat();
	}

	public String getAdresse_mail() {
		return adresse_mail;
	}

	public void setAdresse_mail(String adresse_mail) {
		assert(adresse_mail != null);
		this.adresse_mail = adresse_mail;
	}

	public String getNom() {
		return nom;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setMot_de_passe(String mot_de_passe) {
		assert(mot_de_passe != null);
		this.mot_de_passe = mot_de_passe;
	}

	public void setNom(String nom) {
		assert(nom != null);
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		assert(prenom != null);
		this.prenom = prenom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		assert(pseudo != null);
		this.pseudo = pseudo;
	}
	
	public int getArgent() {
		return argent;
	}
	
	public void setArgent(int argent) {
		assert(argent >= 0);
		this.argent = argent;
	}
	
}
