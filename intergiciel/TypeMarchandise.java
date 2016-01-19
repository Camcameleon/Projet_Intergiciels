import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public abstract class TypeMarchandise {
	@Id
	private String name;
	private int prix;
	/* Quantité échangée pour le prix ci-dessus */
	private int quantite;

	public TypeMarchandise(String name, int prix, int quantite) {
		this.name = name;
		this.prix = prix;
		this.quantite = quantite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public int getQuantite() {
		return quantite;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
