package controleur;

public class Entreprise {
	private int identreprise;
	
	private String statut, numsiret, nomrepresentant;
	
	public Entreprise (int identreprise, String statut, String numsiret, String nomrepresentant) {
		
		this.identreprise= identreprise;
		this.statut= statut;
		this.numsiret= numsiret;
		this.nomrepresentant= nomrepresentant;
	}
	
    public Entreprise(String statut, String numsiret, String nomrepresentant) {
		
		this.identreprise = 0;
		this.statut = statut;
		this.numsiret = numsiret;
		this.nomrepresentant = nomrepresentant;
	}

	public int getIdentreprise() {
		return identreprise;
	}

	public void setIdentreprise(int identreprise) {
		this.identreprise = identreprise;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getNumsiret() {
		return numsiret;
	}

	public void setNumsiret(String numsiret) {
		this.numsiret = numsiret;
	}

	public String getNomrepresentant() {
		return nomrepresentant;
	}

	public void setNomrepresentant(String nomrepresentant) {
		this.nomrepresentant = nomrepresentant;
	}

	
}
