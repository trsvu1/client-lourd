package controleur;

public class Produit {
	private int idproduit;
	private String nomproduit, prix_unit, codecat;
	
	public Produit (int idproduit, String nomproduit, String prix_unit, String codecat) {
		
		this.idproduit=idproduit;
		this.nomproduit=nomproduit;
		this.prix_unit=prix_unit;
		this.codecat=codecat;
	}

	public Produit(String nomproduit, String prix_unit, String codecat) {
		super();
		this.idproduit = 0;
		this.nomproduit = nomproduit;
		this.prix_unit = prix_unit;
		this.codecat = codecat;
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public String getNomproduit() {
		return nomproduit;
	}

	public void setNomproduit(String nomproduit) {
		this.nomproduit = nomproduit;
	}

	public String getPrix_unit() {
		return prix_unit;
	}

	public void setPrix_unit(String prix_unit) {
		this.prix_unit = prix_unit;
	}

	public String getCodecat() {
		return codecat;
	}

	public void setCodecat(String codecat) {
		this.codecat = codecat;
	}
	
}
