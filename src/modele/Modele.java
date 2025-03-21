package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Client;
import controleur.Commande;
import controleur.Devis;
import controleur.Entreprise;
import controleur.Produit;
import controleur.Technicien;

public class Modele {
	private static Connexion uneConnexion= new Connexion ("localhost", "alume", "root", "");
	
	/************************Gestion des clients*************************/
	
	public static void insertClient (Client unClient) {
		String requete ="insert into client values (null,'"+ unClient.getNom()+"','"+unClient.getVille() + "','"+unClient.getCodepostal()+ "','" +unClient.getRue()+ "','"+unClient.getNumrue()+ "','" + unClient.getEmail()+ "','"+ unClient.getTel()+"');";
		executerRequete(requete);
	}
	
	public static void executerRequete(String requete) {
		try {
			uneConnexion.seConnecter();
			
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
	}

	public static void deleteClient (int idclient) {
		String requete ="delete from client where idclient ="+ idclient +";";
		executerRequete (requete);
	}
	
	public static void updateClient (Client unClient) {
		String requete ="update client set nom = '" + unClient.getNom()
		+"', ville = '" + unClient.getVille() + "', rue = '" + unClient.getRue() 
		+ "',  email = '" + unClient.getEmail() + "', tel = '" + unClient.getTel() + "', numrue = '" + unClient.getNumrue()
		+ "' where idclient = " +unClient.getIdclient()+";";
		executerRequete (requete); 
	}
	
	public static Client selectWhereClient (int idclient) {
		String requete = "select * from client where idclient =" + idclient +";";
		Client unClient =null;
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch
			
			// parcours des resultats et extraction d'un client
			
			if (lesResultats.next()) {
				//instanciation d'un client
				unClient = new Client (
						lesResultats.getInt ("idclient"), lesResultats.getString("nom"),
						lesResultats.getString ("ville"), lesResultats.getString("codepostal"),
						lesResultats.getString ("rue"), lesResultats.getString("numrue"),
						lesResultats.getString("email"), lesResultats.getString("tel")
							);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return unClient;
		
	}
	
	public static ArrayList<Client> selectAllClients () {
		
		
		String requete = "select * from client; ";
		ArrayList<Client> lesClients = new ArrayList<Client>();
	
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
			
			// parcours des resultats et extraction des clients
			
			while (lesResultats.next()) {
				//instanciation d'un client
				Client unClient = new Client (
						lesResultats.getInt ("idclient"), lesResultats.getString("nom"),
						lesResultats.getString ("ville"), lesResultats.getString("codepostal"),
						lesResultats.getString ("rue"), lesResultats.getString("numrue"),
						lesResultats.getString("email"), lesResultats.getString("tel")
						);
				//ajout du client dans lesClients
				lesClients.add(unClient);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return lesClients;
		
	}
	
	
	public static ArrayList<Client> selectLikeClients (String filtre) {
		
		
		String requete = "select * from client where nom like '%"+filtre+"%' or  "
				+ "ville like '%"+ filtre+"%' or  "
				+ "codepostal like '%"+ filtre+"%' or  "
				+ "rue like '%"+ filtre+"%' or  "
				+ "numrue like '%"+ filtre+"%' or  "
				+ "email like '%"+ filtre+"%' or  "
				 + "tel like '%"+ filtre+"%';";
		ArrayList<Client> lesClients = new ArrayList<Client>();
		
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
			
			// parcours des resultats et extraction des clients
			
			while (lesResultats.next()) {
				//instanciation d'un client
				Client unClient = new Client (
						lesResultats.getInt ("idclient"), lesResultats.getString("nom"),
						lesResultats.getString ("ville"), lesResultats.getString("codepostal"),
						lesResultats.getString ("rue"), lesResultats.getString("numrue"),
						lesResultats.getString("email"), lesResultats.getString("tel")
						);
				//ajut du client dans lesClients
				lesClients.add(unClient);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return lesClients;
			
		}
	
	
	
	
	
	
	
	
/************************Gestion des Techniciens*************************/
	
	public static void insertTechnicien (Technicien unTechnicien) {
		String requete ="insert into technicien values (null,'"+ unTechnicien.getNom()+"','"+unTechnicien.getPrenom() +
				"','"+unTechnicien.getSpecialite()+ "','" + unTechnicien.getEmail()
				+ "','"+unTechnicien.getMdp()
				+"');";
		executerRequete(requete);
		
	}
	
	public static void deleteTechnicien (int idtechnicien) {
		String requete ="delete from technicien where idtechnicien ="+ idtechnicien +";";
		executerRequete (requete);
	}
	public static void updateTechnicien (Technicien unTechnicien) {
		String requete ="update technicien set nom='"+ unTechnicien.getNom()+"', prenom='"+unTechnicien.getPrenom() +
				"',specialite='"+unTechnicien.getSpecialite()+ "',email='" + unTechnicien.getEmail()+ "' where idtechnicien = " +unTechnicien.getIdtechnicien()+";";
		executerRequete(requete);
		
	}
	
	
	public static Technicien selectWhereTechnicien (int idtechnicien) {
		String requete = "select * from technicien where idtechnicien =" + idtechnicien +";";
		Technicien unTechnicien =null;
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch
			
			// parcours des resultats et extraction d'un client
			
			if (lesResultats.next()) {
				//instanciation d'un client
				unTechnicien = new Technicien (
							lesResultats.getInt ("idtechnicien"), lesResultats.getString("nom"),
							lesResultats.getString ("prenom"), lesResultats.getString("specialite"),
							lesResultats.getString ("email"), requete
							);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return unTechnicien;
		
	}
	
	public static Technicien selectWhereTechnicien (String email, String mdp) {
		String requete = "select * from technicien where email= '"+email+"'and mdp='"+mdp+"';";
		Technicien unTechnicien =null;
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch
			
			// parcours des resultats et extraction d'un client
			
			if (lesResultats.next()) {
				//instanciation d'un client
				unTechnicien = new Technicien (
							lesResultats.getInt ("idtechnicien"), lesResultats.getString("nom"),
							lesResultats.getString ("prenom"), lesResultats.getString("specialite"),
							lesResultats.getString ("email"), requete
							);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return unTechnicien;
		
	}
	
	public static ArrayList<Technicien> selectAllTechniciens () {
		
		
		String requete = "select * from technicien; ";
		ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>();
	
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
			
			// parcours des resultats et extraction des clients
			
			while (lesResultats.next()) {
				//instanciation d'un client
				Technicien unTechnicien = new Technicien (
						lesResultats.getInt ("idtechnicien"), lesResultats.getString("nom"),
						lesResultats.getString ("prenom"), lesResultats.getString("specialite"),
						lesResultats.getString ("email"), requete
						);
				 
				//ajout du client dans lesTechniciens
				lesTechniciens.add(unTechnicien);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return lesTechniciens;
		
	}
	
public static ArrayList<Technicien> selectLikeTechnicien (String filtre) {
	
	
	String requete = "select * from technicien where nom like '%"+filtre+"%' or  "
			+ "prenom like '%"+ filtre+"%' or  "
			+ "adresse like '%"+ filtre+"%' or  "
			 + "specialite like '%"+ filtre+"%'; ";
	
	ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>();
	
	try {
		uneConnexion.seConnecter();
		Statement unStat = uneConnexion.getMaConnexion().createStatement();
		ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
		
		// parcours des resultats et extraction des clients
		
		while (lesResultats.next()) {
			//instanciation d'un client
			Technicien unTechnicien = new Technicien (
					lesResultats.getInt ("idtechnicien"), lesResultats.getString("nom"),
					lesResultats.getString ("prenom"), lesResultats.getString("specialite"),
					lesResultats.getString ("email"), requete
					);
			//ajout du client dans les Techniciens
			lesTechniciens.add(unTechnicien);
		}
		unStat.close();
		uneConnexion.seDeconnecter();
	}
	
	catch (SQLException exp) {
		System.out.println ("Erreur d'execution de la requette : "+ requete);
	}
	return lesTechniciens;
		
	}



/**********************Gestion des devis*****************************/

public static void insertDevis (Devis unDevis) {
	String requete ="insert into devis values (null,'"+unDevis.getDatedevis() + "','"+unDevis.getEtatdevis()+"','"+unDevis.getIdclient() + "');";
	executerRequete(requete);
}

public static void deleteDevis (int iddevis) {
	String requete ="delete from devis where idclient ="+ iddevis +";";
	executerRequete (requete);
}

public static void updateDevis (Devis unDevis) {
	String requete ="update devis set datedevis = '" + unDevis.getDatedevis() + "', etatcom = '" + unDevis.getEtatdevis()+";";
	executerRequete (requete); 
}

public static Devis selectWhereDevis (int iddevis) {
	String requete = "select * from devis where idclient =" + iddevis +";";
	Devis unDevis =null;
	try {
		uneConnexion.seConnecter();
		Statement unStat = uneConnexion.getMaConnexion().createStatement();
		ResultSet lesResultats = unStat.executeQuery(requete); //fetch
		
		// parcours des resultats et extraction d'un client
		
		if (lesResultats.next()) {
			//instanciation d'un devis
			unDevis = new Devis (
					lesResultats.getInt ("iddevis"), 
					lesResultats.getString ("etatdevis"), lesResultats.getString("datedevis"), lesResultats.getInt("idclient")
						);
		}
		unStat.close();
		uneConnexion.seDeconnecter();
	}
	
	catch (SQLException exp) {
		System.out.println ("Erreur d'execution de la requette : "+ requete);
	}
	return unDevis;
	
}

public static ArrayList<Devis> selectAllDevis () {
	
	
	String requete = "select * from devis; ";
	ArrayList<Devis> lesDevis = new ArrayList<Devis>();

	try {
		uneConnexion.seConnecter();
		Statement unStat = uneConnexion.getMaConnexion().createStatement();
		ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
		
		// parcours des resultats et extraction des devis
		
		while (lesResultats.next()) {
			//instanciation d'un client
			Devis unDevis = new Devis (
					lesResultats.getInt ("iddevis"), 
					lesResultats.getString ("etatdevis"), lesResultats.getString("datedevis"), lesResultats.getInt("idclient")
					);
			//ajout du client dans lesClients
			lesDevis.add(unDevis);
		}
		unStat.close();
		uneConnexion.seDeconnecter();
	}
	
	catch (SQLException exp) {
		System.out.println ("Erreur d'execution de la requette : "+ requete);
	}
	return lesDevis;
	
}


public static ArrayList<Devis> selectLikeDevis (String filtre) {
	
	
	String requete = "select * from devis where datedevis like '%"+filtre+"%' or  "
			+ "etatdevis like '%"+ filtre+"%';";
	ArrayList<Devis> lesDevis= new ArrayList<Devis>();
	
	try {
		uneConnexion.seConnecter();
		Statement unStat = uneConnexion.getMaConnexion().createStatement();
		ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
		
		// parcours des resultats et extraction des clients
		
		while (lesResultats.next()) {
			//instanciation d'un client
			Devis unDevis = new Devis (
					lesResultats.getInt ("iddevis"), 
					lesResultats.getString ("etatdevis"), lesResultats.getString("datedevis"), lesResultats.getInt("idclient")
							);
			//ajut du client dans lesClients
			lesDevis.add(unDevis);
		}
		unStat.close();
		uneConnexion.seDeconnecter();
	}
	
	catch (SQLException exp) {
		System.out.println ("Erreur d'execution de la requette : "+ requete);
	}
	return lesDevis;
		
	}







/**********************Gestion des Commandes*****************************/

public static void insertCommande (Commande uneCommande) {
	String requete ="insert into commande values (null,'"+uneCommande.getEtatcom() + "','"+uneCommande.getCodedevis()+"');";
	executerRequete(requete);
}


public static void deleteCommande (int idcommande) {
	String requete ="delete from commande where idcommande ="+ idcommande +";";
	executerRequete (requete);
}

public static void updateCommande (Commande uneCommande) {
	String requete ="update commande set codedevis = '" + uneCommande.getCodedevis() + "', etatcom = '" + uneCommande.getEtatcom()+
			" where idcommande = " +uneCommande.getIdcommande()+";";
	executerRequete (requete); 
}

public static Commande selectWhereCommande (int idcommande) {
	String requete = "select * from commande where idcommande =" + idcommande +";";
	Commande uneCommande =null;
	try {
		uneConnexion.seConnecter();
		Statement unStat = uneConnexion.getMaConnexion().createStatement();
		ResultSet lesResultats = unStat.executeQuery(requete); //fetch
		
		// parcours des resultats et extraction d'un client
		
		if (lesResultats.next()) {
			//instanciation d'un client
			uneCommande = new Commande (
						lesResultats.getInt ("idcommande"), 
						lesResultats.getString ("etatcom"), lesResultats.getString("codedevis")
						);
		}
		unStat.close();
		uneConnexion.seDeconnecter();
	}
	
	catch (SQLException exp) {
		System.out.println ("Erreur d'execution de la requette : "+ requete);
	}
	return uneCommande;
	
}

public static ArrayList<Commande> selectAllCommandes () {
	
	
	String requete = "select * from commande; ";
	ArrayList<Commande> lesCommandes = new ArrayList<Commande>();

	try {
		uneConnexion.seConnecter();
		Statement unStat = uneConnexion.getMaConnexion().createStatement();
		ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
		
		// parcours des resultats et extraction des clients
		
		while (lesResultats.next()) {
			//instanciation d'un client
			Commande uneCommande = new Commande (
					lesResultats.getInt ("idcommande"), 
					lesResultats.getString ("etatcom"), lesResultats.getString("codedevis")
					);
			//ajout du client dans lesClients
			lesCommandes.add(uneCommande);
		}
		unStat.close();
		uneConnexion.seDeconnecter();
	}
	
	catch (SQLException exp) {
		System.out.println ("Erreur d'execution de la requette : "+ requete);
	}
	return lesCommandes;
	
}


	public static ArrayList<Commande> selectLikeCommande (String filtre) {
	
	
	String requete = "select * from commande where etatcom like '%"+ filtre+"%' or  "
			+ "codedevis like '%"+ filtre+"%';";
	
	ArrayList<Commande> lesCommandes= new ArrayList<Commande>();
	
	try {
		uneConnexion.seConnecter();
		Statement unStat = uneConnexion.getMaConnexion().createStatement();
		ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
		
		// parcours des resultats et extraction des clients
		
		while (lesResultats.next()) {
			//instanciation d'un client
			Commande uneCommande = new Commande (
							lesResultats.getInt ("idcommande"), 
							lesResultats.getString ("etatcom"), lesResultats.getString("codedevis")
							);
			//ajut du client dans lesClients
			lesCommandes.add(uneCommande);
		}
		unStat.close();
		uneConnexion.seDeconnecter();
	}
	
	catch (SQLException exp) {
		System.out.println ("Erreur d'execution de la requette : "+ requete);
	}
	return lesCommandes;
		
	}





/************************Gestion des Entreprises*************************/

public static void insertEntreprise (Entreprise uneEntreprise) {
	String requete ="insert into entreprise values (null,'"+ uneEntreprise.getStatut()+"','"+uneEntreprise.getNumsiret() +
			"','"+uneEntreprise.getNomrepresentant()+ "');";
	executerRequete(requete);
	
}

public static void deleteEntreprise (int identreprise) {
	String requete ="delete from entreprise where identreprise ="+ identreprise +";";
	executerRequete (requete);
}
public static void updateEntreprise (Entreprise uneEntreprise) {
	String requete ="update entreprise set nom='"+ uneEntreprise.getStatut()+"', prenom='"+uneEntreprise.getNumsiret() +
			"',specialite='"+uneEntreprise.getNomrepresentant()+" where identreprise = " +uneEntreprise.getIdentreprise()+";";
	executerRequete(requete);
	
}


public static Entreprise selectWhereEntreprise (int identreprise) {
	String requete = "select * from entreprise where identreprise =" + identreprise+";";
	Entreprise uneEntreprise =null;
	try {
		uneConnexion.seConnecter();
		Statement unStat = uneConnexion.getMaConnexion().createStatement();
		ResultSet lesResultats = unStat.executeQuery(requete); //fetch
		
		// parcours des resultats et extraction d'un client
		
		if (lesResultats.next()) {
			//instanciation d'un client
			uneEntreprise = new Entreprise (
						lesResultats.getInt ("identreprise"), lesResultats.getString("statut"),
						lesResultats.getString ("numsiret"), lesResultats.getString("nomrepresentant")
					);
		}
		unStat.close();
		uneConnexion.seDeconnecter();
	}
	
	catch (SQLException exp) {
		System.out.println ("Erreur d'execution de la requette : "+ requete);
	}
	return uneEntreprise;
	
}

public static ArrayList<Entreprise> selectAllEntreprises () {
	

	String requete = "select * from entreprise; ";
	ArrayList<Entreprise> lesEntreprises = new ArrayList<Entreprise>();

	try {
		uneConnexion.seConnecter();
		Statement unStat = uneConnexion.getMaConnexion().createStatement();
		ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
		
		// parcours des resultats et extraction des clients
		
		while (lesResultats.next()) {
			//instanciation d'une entreprise 
			
			Entreprise uneEntreprise = new Entreprise (
					lesResultats.getInt ("identreprise"), lesResultats.getString("statut"),
					lesResultats.getString ("numsiret"), lesResultats.getString("nomrepresentant")
					);
			 
			//ajout du client dans lesTechniciens
			lesEntreprises.add(uneEntreprise);
		}
		unStat.close();
		uneConnexion.seDeconnecter();
	}
	
	catch (SQLException exp) {
		System.out.println ("Erreur d'execution de la requette : "+ requete);
	}
	return lesEntreprises;
	
}

	public static ArrayList<Entreprise> selectLikeEntreprise (String filtre) {


		String requete = "select * from entreprise where statut like '%"+filtre+"%' or  "
		+ "numsiret like '%"+ filtre+"%' or  "
		+ "nomrepresentant like '%"+ filtre+"%'; ";

		ArrayList<Entreprise> lesEntreprises = new ArrayList<Entreprise>();

		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
	
	// parcours des resultats et extraction des clients
	
	while (lesResultats.next()) {
		//instanciation d'un client
		Entreprise uneEntreprise = new Entreprise (
				lesResultats.getInt ("identreprise"), lesResultats.getString("statut"),
				lesResultats.getString ("numsiret"), lesResultats.getString("nomrepresentant")
				);
		//ajout du client dans les Techniciens
		lesEntreprises.add(uneEntreprise);
	}
	unStat.close();
	uneConnexion.seDeconnecter();
	}

		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
	return lesEntreprises;
	
	}
	
	
	
	
	
	
/************************Gestion des Produits*************************/
	
	public static void insertProduit (Produit unProduit) {
		String requete ="insert into produit values (null,'"+ unProduit.getNomproduit()+"','"+unProduit.getPrix_unit() + "','"+unProduit.getCodecat()+ "');";
		executerRequete(requete);
	}
	
	

	public static void deleteProduit (int idproduit) {
		String requete ="delete from produit where idproduit ="+ idproduit +";";
		executerRequete (requete);
	}
	
	public static void updateProduit (Produit unProduit) {
		String requete ="update produit set nomprodtuit = '" + unProduit.getNomproduit()
		+"', prix_unit = '" + unProduit.getPrix_unit() + "', codecat = '" + unProduit.getCodecat() 
		+ ";";
		executerRequete (requete); 
	}
	
	public static Produit selectWhereProduit (int idproduit) {
		String requete = "select * from produit where idproduit =" + idproduit +";";
		Produit unProduit =null;
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetch
			
			// parcours des resultats et extraction d'un client
			
			if (lesResultats.next()) {
				//instanciation d'un produit
				 unProduit = new Produit (
						lesResultats.getInt ("idproduit"), lesResultats.getString("nomproduit"),
						lesResultats.getString ("prix_unit"), lesResultats.getString("codecat")
						);
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return unProduit;
		
	}
	
	public static ArrayList<Produit> selectAllProduits () {
		
		
		String requete = "select * from produit; ";
		ArrayList<Produit> lesProduits = new ArrayList<Produit>();
	
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
			
			// parcours des resultats et extraction des clients
			
			while (lesResultats.next()) {
				//instanciation d'un client
				Produit unProduit = new Produit (
						lesResultats.getInt ("idproduit"), lesResultats.getString("nomproduit"),
						lesResultats.getString ("prix_unit"), lesResultats.getString("codecat")
						);
				//ajout du client dans lesClients
				lesProduits.add(unProduit);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return lesProduits;
		
	}
	
	
	public static ArrayList<Produit> selectLikeProduit (String filtre) {
		
		
		String requete = "select * from produit where nomproduit like '%"+filtre+"%' or  "
				+ "prix_unit like '%"+ filtre+"%' or  "
				+ "codecat like '%"+ filtre+"%';";
		ArrayList<Produit> lesProduits = new ArrayList<Produit>();
		
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //fetchAll
			
			// parcours des resultats et extraction des clients
			
			while (lesResultats.next()) {
				//instanciation d'un client
				Produit unProduit = new Produit (
						lesResultats.getInt ("idproduit"), lesResultats.getString("nomproduit"),
						lesResultats.getString ("prix_unit"), lesResultats.getString("codecat")
						);
				//ajut du client dans lesClients
				lesProduits.add(unProduit);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		
		catch (SQLException exp) {
			System.out.println ("Erreur d'execution de la requette : "+ requete);
		}
		return lesProduits;
			
		}
}
