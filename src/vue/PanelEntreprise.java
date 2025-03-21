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
import controleur.Tableau;

public class PanelEntreprise extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm =new JPanel ();
	private JPanel panelListe = new JPanel ();

		
		private JTextField txtStatut = new JTextField();
		private JTextField txtNumsiret = new JTextField();
		private JTextField txtNomrepresentant = new JTextField();
		
		private JButton btAnnuler = new JButton("Annuler");
		private JButton btValider = new JButton("Valider");
		private JButton btSupprimer = new JButton("Supprimer");
		
		private JTable uneTable ; 
		private Tableau unTableau ; 
		
		private JPanel panelFiltre = new JPanel();
		private JTextField txtFiltre = new JTextField();
		private JButton btFiltrer= new JButton("Filtrer");
		
	public PanelEntreprise() {
		super("Gestion des Entreprise");
		
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
				
				this.panelForm.add(new JLabel("Statut Entreprise"));
				this.panelForm.add(this.txtStatut);
				
				this.panelForm.add(new JLabel("Siret Entreprise"));
				this.panelForm.add(this.txtNumsiret);
				
				this.panelForm.add(new JLabel("Nom du representant"));
				this.panelForm.add(this.txtNomrepresentant);
				
				this.panelForm.add(this.btAnnuler);
				this.panelForm.add(this.btValider);
				
		//On ajoute la formulaire dans la vue
				
				this.add(this.panelForm);
				
		//Rendre les boutons ecoutables
				this.btAnnuler.addActionListener(this);
				this.btValider.addActionListener(this);
				
		//installation de la JTable 
				
				String entetes [] = {"ID Entreprise","Statut", "Numero Siret", "Nom representant"};
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
							txtStatut.setText(unTableau.getValueAt(numLigne, 1).toString());
							txtNumsiret.setText(unTableau.getValueAt(numLigne,2).toString());
							txtNomrepresentant.setText(unTableau.getValueAt(numLigne, 4).toString());
							btSupprimer.setVisible(true);
							btValider.setText("Modifier");
						}
					}
				});
	}
	
	public Object[][] obtenirDonnees (String filtre){
		
		//récuperer les Techniciens de la base de données 
		
		ArrayList<Entreprise> lesEntreprises; 
		if (filtre.equals("")) {
			lesEntreprises = Controleur.selectAllEntreprise();
		}else {
			lesEntreprises = Controleur.selectLikeEntreprise(filtre);
		}
		
		//création d'une matrice de données 
		
		Object[][] matrice = new Object[lesEntreprises.size()][5]; 
		int i = 0; 
		for (Entreprise uneEntreprise : lesEntreprises) {
			matrice[i][0] = uneEntreprise.getIdentreprise(); 
			matrice[i][1] = uneEntreprise.getStatut(); 
			matrice[i][2] = uneEntreprise.getNumsiret(); 
			matrice[i][3] = uneEntreprise.getNomrepresentant();
			i++; 
		}
		return matrice ; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.btAnnuler) {
			this.txtStatut.setText("");
			this.txtNumsiret.setText("");
			this.txtNomrepresentant.setText("");
		
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
			
		}else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			
			//Recuperer les champs saisis
			
			String statut= this.txtStatut.getText();
			String numero_siret= this.txtNumsiret.getText();
			String nom_representant= this.txtNomrepresentant.getText();
			
			//instancier la classe Entreprise
			Entreprise uneEntreprise = new Entreprise(statut, numero_siret, nom_representant);
			
			//inserer le technicien dans la bdd
			Controleur.insertEntreprise(uneEntreprise);
			
			//on affiche un message d'insertion reussie
			JOptionPane.showMessageDialog(this, "Insertion réussie de l'entreprise");
			
			//on actualise l'affichage du tableau 
			
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			
			//on vide les champs
			this.txtStatut.setText("");
			this.txtNumsiret.setText("");
			this.txtNomrepresentant.setText("");

	}
else if (e.getSource() == this.btSupprimer) {
			
			//on recupere l'id du client a supprimer 
			
			int numLigne , identreprise ; 
			numLigne = this.uneTable.getSelectedRow(); 
			identreprise = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer cette entreprise ?", 
					"Suppression de l'entreprise", JOptionPane.YES_NO_OPTION);
			
			if (retour ==0) {
						//on supprime de la base de données 
				
						Controleur.deleteTechnicien(identreprise);
						
						//on actualise l'affichage 
						
						this.unTableau.setDonnees(this.obtenirDonnees(""));
						JOptionPane.showMessageDialog(this, "Suppression réussie de l'entreprise.");
						
						//on vide les champs 
						
						this.txtStatut.setText("");
						this.txtNumsiret.setText("");
						this.txtNomrepresentant.setText("");
						btSupprimer.setVisible(false);
						btValider.setText("Valider");
			}
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on récupère les données y compris l'id 
			
			int numLigne , identreprise ; 
			numLigne = this.uneTable.getSelectedRow(); 
			identreprise = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			String statut = this.txtStatut.getText(); 
			String numero_siret = this.txtNumsiret.getText();
			String nom_representant = this.txtNomrepresentant.getText();
			
			//on modifie dans la bdd 
			
			Entreprise uneEntreprise = new Entreprise(identreprise, statut, numero_siret, nom_representant ); 
			Controleur.updateEntreprise(uneEntreprise);
			
			//on actualise l'affichage du tableau 
			
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie de l'entreprise.");
			
			//message de confirmation et on vide les champs 
			
			this.txtStatut.setText("");
			this.txtNumsiret.setText("");
			this.txtNomrepresentant.setText("");
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
