package controleur;

import java.util.ArrayList;

import modele.Modele;


public class Controleur {
	/**********************Gestion des clients *****************************/
	public static void insertClient (Client unClient) {
		//on va controller les données avant insertion
		
		
		//on appelle le modele pour insertion
		Modele.insertClient(unClient);
	}
	
	public static void deleteClient (int idclient) {
		Modele.deleteClient(idclient);
	}
	
	public static void updateClient(Client unClient) {
		Modele.updateClient(unClient);
	}
	
	public static Client selectWhereClient(int idclient) {
		return Modele.selectWhereClient(idclient);
	}
	
	public static ArrayList<Client> selectAllClients(){
		return Modele.selectAllClients();
	}
	
	public static ArrayList<Client> selectLikeClients(String filtre){
		return Modele.selectLikeClients(filtre);
	}
	
	
	
	
	
	
	
	/**********************Gestion des commandes *****************************/
	public static void insertCommande (Commande uneCommande) {
		//on va controller les données avant insertion
		
		
		//on appelle le modele pour insertion
		Modele.insertCommande(uneCommande);
	}
	
	public static void deleteCommande (int idcommande) {
		Modele.deleteCommande(idcommande);
	}
	
	public static void updateCommande(Commande uneCommande) {
		Modele.updateCommande(uneCommande);
	}
	
	public static Commande selectWhereCommande(int uneCommande) {
		return Modele.selectWhereCommande(uneCommande);
	}
	
	public static ArrayList<Commande> selectAllCommandes(){
		return Modele.selectAllCommandes();
	}
	
	public static ArrayList<Commande> selectLikeCommandes(String filtre){
		return Modele.selectLikeCommande(filtre);
	}
	
	
	/**********************Gestion des Devis *****************************/
	public static void insertDevis (Devis unDevis) {
		//on va controller les données avant insertion
		
		
		//on appelle le modele pour insertion
		Modele.insertDevis(unDevis);
	}
	
	public static void deleteDevis (int iddevis) {
		Modele.deleteDevis(iddevis);
	}
	
	public static void updateDevis(Devis unDevis) {
		Modele.updateDevis(unDevis);
	}
	
	public static Devis selectWhereDevis(int unDevis) {
		return Modele.selectWhereDevis(unDevis);
	}
	
	public static ArrayList<Devis> selectAllDevis(){
		return Modele.selectAllDevis();
	}
	
	public static ArrayList<Devis> selectLikeDevis(String filtre){
		return Modele.selectLikeDevis(filtre);
	}
	
	
	
	/**********************Gestion des Entreprises *****************************/
	public static void insertEntreprise (Entreprise uneEntreprise) {
		//on va controller les données avant insertion
		
		
		//on appelle le modele pour insertion
		Modele.insertEntreprise(uneEntreprise);
	}
	
	public static void deleteEntreprise (int identreprise) {
		Modele.deleteEntreprise(identreprise);
	}
	
	public static void updateEntreprise(Entreprise uneEntreprise) {
		Modele.updateEntreprise(uneEntreprise);
	}
	
	public static Entreprise selectWhereEntreprise(int uneEntreprise) {
		return Modele.selectWhereEntreprise(uneEntreprise);
	}
	
	public static ArrayList<Entreprise> selectAllEntreprise(){
		return Modele.selectAllEntreprises();
	}
	
	public static ArrayList<Entreprise> selectLikeEntreprise(String filtre){
		return Modele.selectLikeEntreprise(filtre);
	}
	
	
	
	
	/**********************Gestion des Produits *****************************/
	public static void insertProduit (Produit unProduit) {
		//on va controller les données avant insertion
		
		
		//on appelle le modele pour insertion
		Modele.insertProduit(unProduit);
	}
	
	public static void deleteProduit (int idproduit) {
		Modele.deleteProduit(idproduit);
	}
	
	public static void updateProduit(Produit unProduit) {
		Modele.updateProduit(unProduit);
	}
	
	public static Produit selectWhereProduit(int unProduit) {
		return Modele.selectWhereProduit(unProduit);
	}
	
	public static ArrayList<Produit> selectAllProduit(){
		return Modele.selectAllProduits();
	}
	
	public static ArrayList<Produit> selectLikeProduit(String filtre){
		return Modele.selectLikeProduit(filtre);
	}
	
	
	
	
	/**********************Gestion des Technicien *****************************/
	public static void insertTechnicien (Technicien unTechnicien) {
		//on va controller les données avant insertion
		
		
		//on appelle le modele pour insertion
		Modele.insertTechnicien(unTechnicien);
	}
	
	public static void deleteTechnicien (int idtechnicien) {
		Modele.deleteTechnicien(idtechnicien);
	}
	
	public static void updateTechnicien(Technicien idtechnicien) {
		Modele.updateTechnicien(idtechnicien);
	}
	
	public static Technicien selectWhereTechnicien(int unTechnicien) {
		return Modele.selectWhereTechnicien(unTechnicien);
	}
	
	public static Technicien selectWhereTechnicien(String email, String mdp) {
		return Modele.selectWhereTechnicien(email, mdp);
	} 
	
	public static ArrayList<Technicien> selectAllTechnicien(){
		return Modele.selectAllTechniciens();
	}
	
	public static ArrayList<Technicien> selectLikeTechnicien(String filtre){
		return Modele.selectLikeTechnicien(filtre);
	}
	
	
}