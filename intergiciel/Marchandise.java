import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Marchandise {
	
	private TypeMarchandise typemarchandise;
	
	@ManyToOne(mappedBy = "possede",fetch=FetchType.EAGER) 
	Joueur proprietaire;

	public Marchandise(TypeMarchandise typemarchandise) {
		this.typemarchandise = typemarchandise;
	}

	public TypeMarchandise getTypemarchandise() {
		return typemarchandise;
	}

	public void setTypemarchandise(TypeMarchandise typemarchandise) {
		this.typemarchandise = typemarchandise;
	}

}
