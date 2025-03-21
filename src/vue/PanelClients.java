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

import controleur.Client;
import controleur.Controleur;
import controleur.Tableau;

public class PanelClients extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm =new JPanel ();
	private JPanel panelListe= new JPanel();
	
	private JTextField txtNom = new JTextField();
	private JTextField txtVille = new JTextField();
	private JTextField txtCodepostale = new JTextField();
	private JTextField txtRue = new JTextField();
	private JTextField txtNumrue = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtTel = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	private JButton btSupprimer = new JButton("Supprimer");

	
	private JTable uneTable;
	private Tableau unTableau;
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer= new JButton("Filtrer");
	
	private JLabel lbNbClients = new JLabel ();

	public PanelClients() {
		super("Gestion des clients");
		
		//Installation du bouton supprimer
				this.btSupprimer.setBounds(40, 340, 300, 40);
				this.add(this.btSupprimer);
				this.btSupprimer.setVisible(false);
				this.btSupprimer.setBackground(Color.red);
				this.btSupprimer.addActionListener(this);

		
		//installation du panel formulaire
		this.panelForm.setBackground(new Color(59, 125, 221));
		this.panelForm.setBounds(40,80,300,220);
		this.panelForm.setLayout(new GridLayout (8,2));
		
		this.panelForm.add(new JLabel("Nom Client"));
		this.panelForm.add(this.txtNom);
		
		this.panelForm.add(new JLabel("Ville"));
		this.panelForm.add(this.txtVille);
		
		this.panelForm.add(new JLabel("Code Postale"));
		this.panelForm.add(this.txtCodepostale);
		
		this.panelForm.add(new JLabel("Email"));
		this.panelForm.add(this.txtEmail);
		
		this.panelForm.add(new JLabel("Telephone"));
		this.panelForm.add(this.txtTel);
		
		this.panelForm.add(new JLabel("Rue"));
		this.panelForm.add(this.txtRue);
		
		this.panelForm.add(new JLabel("Numero de la rue"));
		this.panelForm.add(this.txtNumrue);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);

		//On ajoute la formulaire dans la vue
		
		this.add(this.panelForm);
		
		//Rendre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//Installation de la JTable
				String entetes [] = {"ID Client", "Nom", "Ville", "Code Postale","Rue","Num rue", "Email", "Telephone" };
				this.unTableau = new Tableau (this.obtenirDonnees(""), entetes);
				this.uneTable= new JTable(this.unTableau);
				JScrollPane uneScroll = new JScrollPane(this.uneTable);
				uneScroll.setBounds(400, 80, 500, 220);
				this.add(uneScroll);
				
	
		//Implementatio du click sur une ligne de la table
			this.uneTable.addMouseListener(new MouseListener() {
				
			

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					int numLigne=0;
					
					if (e.getClickCount()>=1) {
						numLigne = uneTable.getSelectedRow();
						System.out.println(numLigne);
						txtNom.setText(unTableau.getValueAt(numLigne,1).toString());
						txtVille.setText(unTableau.getValueAt(numLigne,2).toString());
						txtCodepostale.setText(unTableau.getValueAt(numLigne,3).toString());
						txtRue.setText(unTableau.getValueAt(numLigne,4).toString());
						txtNumrue.setText(unTableau.getValueAt(numLigne,5).toString());
						txtEmail.setText(unTableau.getValueAt(numLigne,6).toString());
						txtTel.setText(unTableau.getValueAt(numLigne,7).toString());
						
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
			
			//installation du label Nb CLients
			this.lbNbClients.setBounds(550, 440, 400, 20);
			this.lbNbClients.setText("Nombre de clients :" + this.unTableau.getRowCount());
			this.add(this.lbNbClients);

		}
	public Object [] [] obtenirDonnees (String filtre){
		//recuperer les clients de la base de données
		ArrayList<Client> lesClients;
		if (filtre.equals("")) {
			lesClients = Controleur.selectAllClients();
		}else {
			lesClients = Controleur.selectLikeClients(filtre);
		}
		//création d'une matrice de données
		Object[][] matrice = new Object [lesClients.size()][8];
		int i=0;
		for (Client unClient : lesClients) {
			matrice[i][0]= unClient.getIdclient();
			matrice[i][1]= unClient.getNom();
			matrice[i][2]= unClient.getVille();
			matrice[i][3]= unClient.getCodepostal();
			matrice[i][4]= unClient.getRue();
			matrice[i][5]= unClient.getNumrue();
			matrice[i][6]= unClient.getEmail();
			matrice[i][7]= unClient.getTel();
			i++;
			
		}
		return matrice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtVille.setText("");
			this.txtCodepostale.setText("");
			this.txtRue.setText("");
			this.txtNumrue.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
			
		}else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			//Recuperer les champs saisis
			String nom= this.txtNom.getText();
			String ville= this.txtVille.getText();
			String codepostale= this.txtCodepostale.getText();
			String rue= this.txtRue.getText();
			String numrue= this.txtNumrue.getText();
			String email= this.txtEmail.getText();
			String tel= this.txtTel.getText();
			
			//instancier la classe client
			Client unClient = new Client(nom, ville, codepostale,  rue, numrue, email, tel);
			
			//inserer le client dans la bdd
			Controleur.insertClient(unClient);
			
			//on affiche un message d'insertion reussie
			JOptionPane.showMessageDialog(this, "Insertion réussie du client");
			
			//on actualise l'affichage du tableau 
			
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			this.lbNbClients.setText("Nombre de clients :" + this.unTableau.getRowCount());
			
			//on vide les champs
			this.txtNom.setText("");
			this.txtVille.setText("");
			this.txtCodepostale.setText("");
			this.txtRue.setText("");
			this.txtNumrue.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");

		}
		else if (e.getSource()== this.btSupprimer) {
			//Onrecupere l'id du client a supprimer
			int numLigne, idclient;
			numLigne= this.uneTable.getSelectedRow();
			idclient = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			
			int retour= JOptionPane.showConfirmDialog(this, "Voulez vous supprimer le client?", 
					"Suppression du client", JOptionPane.YES_NO_CANCEL_OPTION);
			if (retour==0) {
			//On supprime le client de la base de donnees
			Controleur.deleteClient(idclient);
			//On actualise l'affichage
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showConfirmDialog(this, "Suppression du client reussie");
			this.lbNbClients.setText("Nombre de clients :" + this.unTableau.getRowCount());
			
			// on vide les champs
			this.txtNom.setText("");
			this.txtVille.setText("");
			this.txtCodepostale.setText("");
			this.txtRue.setText("");
			this.txtNumrue.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			btSupprimer.setVisible(false);
			btValider.setText("Valider");
		}
	}
		else if (e.getSource()== this.btValider && this.btValider.getText().equals("Modifier")) {
			//On recupere les donnees y compris l'id du client
			
			int numLigne, idclient;
			numLigne= this.uneTable.getSelectedRow();
			idclient = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			
			String nom= this.txtNom.getText();
			String ville= this.txtVille.getText();
			String codepostale= this.txtCodepostale.getText();
			String rue= this.txtRue.getText();
			String numrue= this.txtNumrue.getText();
			String email= this.txtEmail.getText();
			String tel= this.txtTel.getText();
			
			//on modifie dans la base de donnees
			
			Client unClient = new Client(idclient, nom, ville, codepostale,  rue, numrue, email, tel);
			Controleur.updateClient(unClient);
			
			//on actualise l'affichage du tableau
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			JOptionPane.showMessageDialog(this, "Modification réussie du client");
			
			//message de confirmation et on vide les champs
			
			this.txtNom.setText("");
			this.txtVille.setText("");
			this.txtCodepostale.setText("");
			this.txtRue.setText("");
			this.txtNumrue.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
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
