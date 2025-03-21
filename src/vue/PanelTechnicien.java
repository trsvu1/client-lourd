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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Tableau;
import controleur.Technicien;

public class PanelTechnicien extends PanelPrincipal implements ActionListener
{
private JPanel panelForm =new JPanel ();
private JPanel panelListe = new JPanel ();

	
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JComboBox<String> txtSpecialite = new JComboBox<String>();
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();

	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");
	
	private JTable uneTable ; 
	private Tableau unTableau ; 
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer= new JButton("Filtrer");
	
	public PanelTechnicien() {
		super("Gestion des Techniciens");
		
	//installation du bouton supprimer 
		
		this.btSupprimer.setBounds(40, 340, 300, 40);
		this.add(this.btSupprimer); 
		this.btSupprimer.setVisible(false);
		this.btSupprimer.setBackground(Color.red);
		this.btSupprimer.addActionListener(this);
		
		//Remplir les ComboBox specialite et role
		this.txtSpecialite.addItem("Services");
		this.txtSpecialite.addItem("Ateliers");
		this.txtSpecialite.addItem("Autres");
		
		//installation du panel formulaire
				this.panelForm.setBackground(new Color(59, 125, 221));
				this.panelForm.setBounds(40,80,300,220);
				this.panelForm.setLayout(new GridLayout (7,2));
				
				this.panelForm.add(new JLabel("Nom Technicien"));
				this.panelForm.add(this.txtNom);
				
				this.panelForm.add(new JLabel("Prenom Technicien"));
				this.panelForm.add(this.txtPrenom);
				
				this.panelForm.add(new JLabel("Specialite"));
				this.panelForm.add(this.txtSpecialite);
				
				this.panelForm.add(new JLabel("Email"));
				this.panelForm.add(this.txtEmail);
				
				this.panelForm.add(new JLabel("Mdp"));
				this.panelForm.add(this.txtMdp);
				
				
				this.panelForm.add(this.btAnnuler);
				this.panelForm.add(this.btValider);

				//On ajoute la formulaire dans la vue
				
				this.add(this.panelForm);
				
				//Rendre les boutons ecoutables
				this.btAnnuler.addActionListener(this);
				this.btValider.addActionListener(this);
				
				//installation de la JTable 
				
				String entetes [] = {"ID Technicien","Nom", "Prénom", "Spécialité","Email" };
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
							txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
							txtPrenom.setText(unTableau.getValueAt(numLigne,2).toString());
							txtEmail.setText(unTableau.getValueAt(numLigne, 4).toString());
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
	
	public Object[][] obtenirDonnees (String filtre){
		
		//récuperer les Techniciens de la base de données 
		
		ArrayList<Technicien> lesTechniciens; 
		if (filtre.equals("")) {
			lesTechniciens = Controleur.selectAllTechnicien();
		}else {
			lesTechniciens = Controleur.selectLikeTechnicien(filtre);
		}
		
		//création d'une matrice de données 
		
		Object[][] matrice = new Object[lesTechniciens.size()][6]; 
		int i = 0; 
		for (Technicien unTechnicien : lesTechniciens) {
			matrice[i][0] = unTechnicien.getIdtechnicien(); 
			matrice[i][1] = unTechnicien.getNom(); 
			matrice[i][2] = unTechnicien.getPrenom(); 
			matrice[i][3] = unTechnicien.getSpecialite();
			matrice[i][4] = unTechnicien.getEmail(); 
			i++; 
		}
		return matrice ; 
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
			
		}else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			
			//Recuperer les champs saisis
			
			String nom= this.txtNom.getText();
			String prenom= this.txtPrenom.getText();
			String specialite= this.txtSpecialite.getSelectedItem().toString();
			String email= this.txtEmail.getText();
			String mdp= new String (this.txtMdp.getPassword());

			
			//instancier la classe Technicien
			Technicien unTechnicien = new Technicien(nom, prenom, specialite, email, mdp);
			
			//inserer le technicien dans la bdd
			Controleur.insertTechnicien(unTechnicien);
			
			//on affiche un message d'insertion reussie
			JOptionPane.showMessageDialog(this, "Insertion réussie du technicien");
			
			//on actualise l'affichage du tableau 
			
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			
			//on vide les champs
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
		else if (e.getSource() == this.btSupprimer) {
			
			//on recupere l'id du client a supprimer 
			
			int numLigne , idtechnicien ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idtechnicien = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			int retour = JOptionPane.showConfirmDialog(this, "Voulez Vous supprimer le Technicien ?", 
					"Suppression du Technicien", JOptionPane.YES_NO_OPTION);
			
			if (retour ==0) {
						//on supprime de la base de données 
				
						Controleur.deleteTechnicien(idtechnicien);
						
						//on actualise l'affichage 
						
						this.unTableau.setDonnees(this.obtenirDonnees(""));
						JOptionPane.showMessageDialog(this, "Suppression réussie du technicien.");
						
						//on vide les champs 
						
						this.txtNom.setText("");
						this.txtPrenom.setText("");
						this.txtEmail.setText("");
						this.txtMdp.setText("");
						btSupprimer.setVisible(false);
						btValider.setText("Valider");
			}
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on récupère les données y compris l'id 
			
			int numLigne , idtechnicien ; 
			numLigne = this.uneTable.getSelectedRow(); 
			idtechnicien = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText();
			String specialite = this.txtSpecialite.getSelectedItem().toString();
			String email = this.txtEmail.getText();
			String mdp = new String (this.txtMdp.getPassword()); 
			
			//on modifie dans la bdd 
			
			Technicien unTechnicien = new Technicien(idtechnicien, nom, prenom, specialite, email,mdp ); 
			Controleur.updateTechnicien(unTechnicien);
			
			//on actualise l'affichage du tableau 
			
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie du technicien.");
			
			//message de confirmation et on vide les champs 
			
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
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

