package controleur;

public class Client {
	private int idclient;

	
private String nom, ville, codepostal, rue, numrue, email, tel;
	
	public Client(int idClient, String nom, String ville, String codepostal, String rue, String numrue, String email,String tel) {
		
		this.idclient = idClient;
		this.nom = nom;
		this.ville = ville;
		this.codepostal = codepostal;
		this.rue = rue;
		this.numrue = numrue;
		this.email = email;
		this.tel = tel;
	}

	public Client( String nom,String ville, String codepostal, String rue, String numrue, String email,
			String tel) {
		
		this.idclient = 0;
		this.nom = nom;
		this.ville = ville;
		this.codepostal = codepostal;
		this.rue = rue;
		this.numrue = numrue;
		this.email = email;
		this.tel = tel;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodepostal() {
		return codepostal;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getNumrue() {
		return numrue;
	}

	public void setNumrue(String numrue) {
		this.numrue = numrue;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
