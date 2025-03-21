package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Entreprise;
import controleur.Produit;
import controleur.Tableau;

public class PanelProduit extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm =new JPanel ();
	private JPanel panelListe = new JPanel ();

		
		private JTextField txtNomproduit = new JTextField();
		private JTextField txtPrix_unit = new JTextField();
		private JTextField txtCode_cat = new JTextField();
		
		private JButton btAnnuler = new JButton("Annuler");
		private JButton btValider = new JButton("Valider");
		private JButton btSupprimer = new JButton("Supprimer");
		
		private JTable uneTable ; 
		private Tableau unTableau ; 
		
		private JPanel panelFiltre = new JPanel();
		private JTextField txtFiltre = new JTextField();
		private JButton btFiltrer= new JButton("Filtrer");
		 
	public PanelProduit() {
		super("Gestion des produits");
		
		//installation du bouton supprimer 
		
		this.btSupprimer.setBounds(40, 340, 300, 40);
		this.add(this.btSupprimer); 
		this.btSupprimer.setVisible(false);
		this.btSupprimer.setBackground(Color.red);
		this.btSupprimer.addActionListener(this);
		
//installation du panel formulaire
		
		this.panelForm.setBackground(new Color(59, 125, 221));
		this.panelForm.setBounds(40,80,300,220);
		this.panelForm.setLayout(new GridLayout (7,2));
		
		this.panelForm.add(new JLabel("Nom produit"));
		this.panelForm.add(this.txtNomproduit);
		
		this.panelForm.add(new JLabel("Prix Unite"));
		this.panelForm.add(this.txtPrix_unit);
		
		this.panelForm.add(new JLabel("Code categorie"));
		this.panelForm.add(this.txtCode_cat);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		
//On ajoute la formulaire dans la vue
		
		this.add(this.panelForm);
		
//Rendre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
//installation de la JTable 
		
		String entetes [] = {"ID Produit","Nom produit", "Prix unit", "Code categorie"};
		this.unTableau = new Tableau (this.obtenirDonnees(""), entetes); 
		this.uneTable = new JTable(this.unTableau); 
		JScrollPane uneScroll = new JScrollPane(this.uneTable); 
		uneScroll.setBounds(400, 80, 500, 220);
		this.add(uneScroll); 
		
		//implementation du click sur une ligne de la table 
		
		this.uneTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne = 0 ; 
				if (e.getClickCount() >= 1) {
					numLigne = uneTable.getSelectedRow(); 
					txtNomproduit.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtPrix_unit.setText(unTableau.getValueAt(numLigne,2).toString());
					txtCode_cat.setText(unTableau.getValueAt(numLigne, 4).toString());
					btSupprimer.setVisible(true);
					btValider.setText("Modifier");
				}
			}
		});
	}
public Object[][] obtenirDonnees (String filtre){
		
		//récuperer les Techniciens de la base de données 
		
		ArrayList<Produit> lesProduits; 
		if (filtre.equals("")) {
			lesProduits = Controleur.selectAllProduit();
		}else {
			lesProduits = Controleur.selectLikeProduit(filtre);
		}
		
		//création d'une matrice de données 
		
		Object[][] matrice = new Object[lesProduits.size()][5]; 
		int i = 0; 
		for (Produit unProduit : lesProduits) {
			matrice[i][0] = unProduit.getIdproduit(); 
			matrice[i][1] = unProduit.getNomproduit(); 
			matrice[i][2] = unProduit.getPrix_unit(); 
			matrice[i][3] = unProduit.getCodecat();
			i++; 
		}
		return matrice ; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.btAnnuler) {
			this.txtNomproduit.setText("");
			this.txtPrix_unit.setText("");
			this.txtCode_cat.setText("");
		
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
			
		}else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			
			//Recuperer les champs saisis
			
			String nomproduit= this.txtNomproduit.getText();
			String prix_unit= this.txtPrix_unit.getText();
			String codecat= this.txtCode_cat.getText();
			
			//instancier la classe Entreprise
			Produit unProduit = new Produit(nomproduit, prix_unit, codecat);
			
			//inserer le technicien dans la bdd
			Controleur.insertProduit(unProduit);
			
			//on affiche un message d'insertion reussie
			JOptionPane.showMessageDialog(this, "Insertion réussie du produit");
			
			//on actualise l'affichage du tableau 
			
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			
			//on vide les champs
			this.txtNomproduit.setText("");
			this.txtPrix_unit.setText("");
			this.txtCode_cat.setText("");

	}
else if (e.getSource() == this.btSupprimer) {
			
			//on recupere l'id du client a supprimer 
			
			int numLigne , idproduit ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idproduit = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer ce produit ?", 
					"Suppression du produit", JOptionPane.YES_NO_OPTION);
			
			if (retour ==0) {
						//on supprime de la base de données 
				
						Controleur.deleteTechnicien(idproduit);
						
						//on actualise l'affichage 
						
						this.unTableau.setDonnees(this.obtenirDonnees(""));
						JOptionPane.showMessageDialog(this, "Suppression réussie du produit.");
						
						//on vide les champs 
						
						this.txtNomproduit.setText("");
						this.txtPrix_unit.setText("");
						this.txtCode_cat.setText("");
						btSupprimer.setVisible(false);
						btValider.setText("Valider");
			}
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on récupère les données y compris l'id 
			
			int numLigne , idproduit ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idproduit = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			String nomproduit = this.txtNomproduit.getText(); 
			String prix_unit = this.txtPrix_unit.getText();
			String codecat = this.txtCode_cat.getText();
			
			//on modifie dans la bdd 
			
			Produit unProduit = new Produit(idproduit, nomproduit, prix_unit, codecat ); 
			Controleur.updateProduit(unProduit);
			
			//on actualise l'affichage du tableau 
			
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie du produit.");
			
			//message de confirmation et on vide les champs 
			
			this.txtNomproduit.setText("");
			this.txtPrix_unit.setText("");
			this.txtCode_cat.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
			else if (e.getSource() == this.btFiltrer) {
			//Recuperer le filtre
			String filtre = this.txtFiltre.getText();
			//On actualise l'affichage avec les clients trouvés
			this.unTableau.setDonnees(this.obtenirDonnees(filtre));
			
			
		}
	
	}
}
