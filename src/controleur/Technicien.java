package controleur;

public class Technicien {
	private int idtechnicien;
	private String nom, prenom, specialite, email,mdp;
	
	public Technicien(int idtechnicien, String nom, String prenom, String specialite, String email, String mdp) {
		
		this.idtechnicien = idtechnicien;
		this.nom = nom;
		this.prenom = prenom;
		this.specialite = specialite;
		this.email = email;
		this.mdp = mdp;
		
	}

	public int getIdtechnicien() {
		return idtechnicien;
	}

	public void setIdtechnicien(int idtechnicien) {
		this.idtechnicien = idtechnicien;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Technicien( String nom, String prenom, String specialite, String email, String mdp) {
		
		this.idtechnicien = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.specialite = specialite;
		this.email = email;
		this.mdp = mdp;
	}
}
