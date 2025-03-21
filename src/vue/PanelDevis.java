package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Controleur;
import controleur.Devis;
import controleur.Tableau;
import controleur.Technicien;

public class PanelDevis extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm =new JPanel ();
	private JPanel panelListe = new JPanel ();

		
		private JTextField txtDatedevis = new JTextField();
		private JComboBox<String> txtEtatdevis = new JComboBox<String>();
		private JComboBox<String> txtIdClient = new JComboBox<String>();
		
		private JButton btAnnuler = new JButton("Annuler");
		private JButton btValider = new JButton("Valider");
		private JButton btSupprimer = new JButton("Supprimer");
		
		private JTable uneTable ; 
		private Tableau unTableau ; 
		
		private JPanel panelFiltre = new JPanel();
		private JTextField txtFiltre = new JTextField();
		private JButton btFiltrer= new JButton("Filtrer");
		
	public PanelDevis() {
		super("Gestion des devis");
		
		//installation du bouton supprimer 
		
				this.btSupprimer.setBounds(40, 340, 300, 40);
				this.add(this.btSupprimer); 
				this.btSupprimer.setVisible(false);
				this.btSupprimer.setBackground(Color.red);
				this.btSupprimer.addActionListener(this);
				
				//Remplir les ComboBox specialite et role
				this.txtEtatdevis.addItem("Acceptee");
				this.txtEtatdevis.addItem("Annulee");
				
				//installation du panel formulaire
				this.panelForm.setBackground(new Color(59, 125, 221));
				this.panelForm.setBounds(40,80,300,220);
				this.panelForm.setLayout(new GridLayout (4,2));
				
				this.panelForm.add(new JLabel("Date du devis"));
				this.panelForm.add(this.txtDatedevis);
				
				this.panelForm.add(new JLabel("Etat du devis"));
				this.panelForm.add(this.txtEtatdevis);
				
				
				this.panelForm.add(new JLabel("Le Client :"));
				this.panelForm.add(this.txtIdClient);
				
				this.panelForm.add(this.btAnnuler);
				this.panelForm.add(this.btValider);
				
				//On ajoute la formulaire dans la vue
				
				this.add(this.panelForm);
				
				 remplirCBXIdClient ();
				
				//Rendre les boutons ecoutables
				this.btAnnuler.addActionListener(this);
				this.btValider.addActionListener(this);
				
				//installation de la JTable 
				
				String entetes [] = {"ID Devis","Date du devis", "Etat du devis", "ID Client" };
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
							txtDatedevis.setText(unTableau.getValueAt(numLigne, 1).toString());
							btSupprimer.setVisible(true);
							btValider.setText("Modifier");
						}
					}
				});
				
				//Installation du panel filtre
				this.panelFiltre.setBackground(new Color(59, 125, 221));
				this.panelFiltre.setBounds(400, 50, 500, 20);
				this.panelFiltre.setLayout(new GridLayout(1,3));
				this.panelFiltre.add(new JLabel ("Filtre les clients par :"));
				this.panelFiltre.add(this.txtFiltre);
				this.panelFiltre.add(this.btFiltrer);
				this.btFiltrer.addActionListener(this);
				this.add(this.panelFiltre);
	}
	
	public void remplirCBXIdClient () {
		ArrayList<Client> lesClients = Controleur.selectAllClients();
		
		for (Client unClient: lesClients) {
			this.txtIdClient.addItem(unClient.getIdclient()+ "-" + unClient.getNom());
		}
	}

	public Object[][] obtenirDonnees (String filtre){
		
		//récuperer les Techniciens de la base de données 
		
		ArrayList<Devis> lesDevis; 
		if (filtre.equals("")) {
			lesDevis= Controleur.selectAllDevis();
		}else {
			lesDevis = Controleur.selectLikeDevis(filtre);
		}
		
		//création d'une matrice de données 
		
		Object[][] matrice = new Object[lesDevis.size()][4]; 
		int i = 0; 
		for (Devis unDevis : lesDevis) {
			matrice[i][0] = unDevis.getIddevis(); 
			matrice[i][1] = unDevis.getDatedevis(); 
			matrice[i][2] = unDevis.getEtatdevis(); 
			matrice[i][3] = unDevis.getIdclient(); 
			i++; 
		}
		return matrice ; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtDatedevis.setText("");
			
		
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
			
		}else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			
			//Recuperer les champs saisis
			
			String datedevis= this.txtDatedevis.getText();
			String etatdevis= this.txtEtatdevis.getSelectedItem().toString();

			String tab[] = this.txtIdClient.getSelectedItem().toString().split("-");
			int idclient = Integer.parseInt(tab[0]);
			//instancier la classe Devis
			Devis unDevis = new Devis(idclient, etatdevis, datedevis, idclient);
			
			//inserer le technicien dans la bdd
			Controleur.insertDevis(unDevis);
			
			//on affiche un message d'insertion reussie
			JOptionPane.showMessageDialog(this, "Insertion réussie du devis");
			
			//on actualise l'affichage du tableau 
			
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			
			//on vide les champs
			this.txtDatedevis.setText("");
		}
		else if (e.getSource() == this.btSupprimer) {
			
			//on recupere l'id du client a supprimer 
			
			int numLigne , iddevis ; 
			numLigne = this.uneTable.getSelectedRow(); 
			iddevis = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer le devis ?", 
					"Suppression du evis", JOptionPane.YES_NO_OPTION);
			
			if (retour ==0) {
						//on supprime de la base de données 
				
						Controleur.deleteTechnicien(iddevis);
						
						//on actualise l'affichage 
						
						this.unTableau.setDonnees(this.obtenirDonnees(""));
						JOptionPane.showMessageDialog(this, "Suppression réussie du devis.");
						
						//on vide les champs 
						
						this.txtDatedevis.setText("");
	
						btSupprimer.setVisible(false);
						btValider.setText("Valider");
			}
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on récupère les données y compris l'id 
			
			int numLigne , iddevis ; 
			numLigne = this.uneTable.getSelectedRow(); 
			iddevis = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			String datedevis = this.txtDatedevis.getText(); 
			String etatdevis = this.txtEtatdevis.getSelectedItem().toString();
			String tab[] = this.txtIdClient.getSelectedItem().toString().split("-");
			int idclient = Integer.parseInt(tab[0]);
			//on modifie dans la bdd 
			
			Devis unDevis = new Devis(iddevis, etatdevis, datedevis , idclient); 
			Controleur.updateDevis(unDevis);
			
			//on actualise l'affichage du tableau 
			
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie du technicien.");
			
			//message de confirmation et on vide les champs 
			
			this.txtDatedevis.setText("");
			
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

