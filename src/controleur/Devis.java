package controleur;

public class Devis {
	private int iddevis;
	private String datedevis, etatdevis;
	private int idclient; 
	
	public Devis(int iddevis, String etatdevis , String datedevis, int idclient) {
		super();
		this.iddevis = iddevis;
		this.datedevis = datedevis;
		this.etatdevis = etatdevis;
		this.idclient = idclient;
	}

	public Devis (String etatdevis, String datedevis , int idclient) {
		
		this.iddevis = 0;
		this.datedevis = datedevis;
		this.etatdevis = etatdevis;
		this.idclient = idclient; 
	}
	
     public int getIddevis() {
		return iddevis;
	}

	public void setIddevis(int iddevis) {
		this.iddevis = iddevis;
	}

	public String getDatedevis() {
		return datedevis;
	}

	public void setDatedevis(String datedevis) {
		this.datedevis = datedevis;
	}

	public String getEtatdevis() {
		return etatdevis;
	}

	public void setEtatdevis(String etatdevis) {
		this.etatdevis = etatdevis;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	
	
}
