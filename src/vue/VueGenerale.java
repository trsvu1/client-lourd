package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Alume;



public class VueGenerale extends JFrame implements ActionListener
{
	private JButton btClients = new JButton("Clients");
	private JButton btCommande = new JButton("Commande");
	private JButton btDevis = new JButton("Devis");
	private JButton btEntreprise = new JButton("Entreprise");
	private JButton btProduit = new JButton("Produit");
	private JButton btTechnicien = new JButton("Technicien");
	private JButton btQuitter = new JButton("Quitter");

	private JPanel panelMenu = new JPanel ();
	
	private static PanelClients unPanelClients = new PanelClients();
	private static PanelCommande unPanelCommande = new PanelCommande();
	private static PanelDevis unPanelDevis = new PanelDevis();
	private static PanelEntreprise unPanelEntreprise = new PanelEntreprise();
	private static PanelProduit unPanelProduit = new PanelProduit();
	private static PanelTechnicien unPanelTechnicien = new PanelTechnicien();
	
	
	public VueGenerale() {
		
		this.setTitle("ALUME");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(100,100,1000,600);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(255,255,255));
		
		 ImageIcon uneImage = new ImageIcon("src/Images/footer.png");
		    JLabel unJLabel = new JLabel(uneImage);
		    unJLabel.setBounds(0, 400, 1500, 200);
		    this.add(unJLabel);
		
		//Construction du Panel
		this.panelMenu.setBackground(Color.darkGray);
		this.panelMenu.setBounds(50,10,900,40);
		this.panelMenu.setLayout(new GridLayout(1,6));
		this.panelMenu.add(this.btClients);
		this.panelMenu.add(this.btTechnicien);
		this.panelMenu.add(this.btCommande);
		this.panelMenu.add(this.btEntreprise);
		this.panelMenu.add(this.btDevis);
		this.panelMenu.add(this.btProduit);

		this.panelMenu.add(this.btQuitter);
		
		this.add(this.panelMenu);
		
		//rendre les boutons ecoutables
		
				this.btClients.addActionListener(this);
				this.btTechnicien.addActionListener(this);
				this.btCommande.addActionListener(this);
				this.btEntreprise.addActionListener(this);
				this.btDevis.addActionListener(this);
				this.btProduit.addActionListener(this);

				this.btQuitter.addActionListener(this);
				
				//Ajout des panels a la fenetre
				
				this.add(this.unPanelClients);
				this.add(this.unPanelCommande);
				this.add(this.unPanelTechnicien);
				this.add(this.unPanelEntreprise);
				this.add(this.unPanelDevis);
				this.add(this.unPanelProduit);


				this.setVisible(true);
		
	}
	
	public void afficherPanel (int choix) {
		this.unPanelClients.setVisible(false);
		this.unPanelTechnicien.setVisible(false);
		this.unPanelCommande.setVisible(false);
		this.unPanelEntreprise.setVisible(false);
		this.unPanelDevis.setVisible(false);
		this.unPanelProduit.setVisible(false);

		
		switch (choix) {
		case 1: this.unPanelClients.setVisible(true);break;
		case 2: this.unPanelTechnicien.setVisible(true);break;
		case 3: this.unPanelCommande.setVisible(true);break;
		case 4: this.unPanelEntreprise.setVisible(true);break;
		case 5: this.unPanelDevis.setVisible(true);break;
		case 6: this.unPanelProduit.setVisible(true);break;

		}
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btQuitter) {
			int retour =JOptionPane.showConfirmDialog(this, "Voulez-vous quitter l'application?",
					"Quitter l'application", JOptionPane.YES_NO_OPTION);
			if (retour == 0) {
			Alume.rendreVisible(true);//Ouverture de la connexion
			Alume.creerVueGenerale(false); //Fermeture du logiciel
		}
		
		
		}else if (e.getSource()==this.btClients) {
			this.afficherPanel(1);
		}
		else if (e.getSource()==this.btTechnicien) {
			this.afficherPanel(2);
		}
		else if (e.getSource()==this.btCommande) {
			this.afficherPanel(3);
		}
		else if (e.getSource()==this.btEntreprise) {
			this.afficherPanel(4);
		}
		else if (e.getSource()==this.btDevis) {
			this.afficherPanel(5);
		}
		else if (e.getSource()==this.btProduit) {
			this.afficherPanel(6);
		}
	}
}
