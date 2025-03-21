package controleur;

public class Commande {
	private int idcommande;
	private String  etatcom, codedevis;
	
	public Commande(int idcommande, String etatcom, String codedevis) {
		
		this.idcommande = idcommande;
		this.etatcom = etatcom;
		this.codedevis = codedevis;	
	}
    public Commande(  String etatcom, String codedevis) {
		
		this.idcommande = 0;
		this.etatcom = etatcom;
		this.codedevis = codedevis;
	}

	public int getIdcommande() {
		return idcommande;
	}

	public void setIdcommande(int idcommande) {
		this.idcommande = idcommande;
	}

	public String getEtatcom() {
		return etatcom;
	}

	public void setEtatcom(String etatcom) {
		this.etatcom = etatcom;
	}

	public String getCodedevis() {
		return codedevis;
	}

	public void setCodedevis(String codedevis) {
		this.codedevis = codedevis;
	}

	
	
}
