package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import controleur.Alume;
import controleur.Controleur;

import controleur.Technicien;

public class VueConnexion extends JFrame implements ActionListener {
	private JButton btSeConnecter = new JButton("Se Connecter");
	private JButton btAnnuler = new JButton("Annuler");
	private JTextField txtEmail = new JTextField("a@gmail.com");
	private JPasswordField txtMdp = new JPasswordField("123");
	private JLabel txtInfo = new JLabel("Mot de passe oublier ?");
	 private JLabel txtTitre = new JLabel("S'identifier", SwingConstants.CENTER);
	 private JLabel txtMessage = new JLabel(
			    "Connectez-vous pour gérer votre compte !"
			  );
	 private JPanel panelConnexion = new JPanel();
	
	private JPanel panelForm = new JPanel ();
	
	public VueConnexion() {
	    this.setTitle("Alume_2025");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    this.setBounds(15,20,1000,700);
	    this.getContentPane().setBackground(new Color(255, 255, 255));
	    this.setLayout(null);

	    ImageIcon uneImage = new ImageIcon("src/Images/footer.png");
	    JLabel unJLabel = new JLabel(uneImage);
	    unJLabel.setBounds(0, 500, 1500, 200);
	    this.add(unJLabel);

	    ImageIcon uneImageLogo = new ImageIcon("src/Images/ampoule.png");
	    JLabel unJLabelLogo = new JLabel(uneImageLogo);
	    unJLabelLogo.setBounds(50, 50, 450, 200);
	    this.add(unJLabelLogo);

	    //Pour notre titre
	    this.txtTitre.setBounds(613, 120, 280, 200);
	    this.txtTitre.setFont(new Font("Serif", Font.BOLD, 55));
	    this.txtTitre.setForeground(new Color(59, 125, 221)); //blue
	    this.add(this.txtTitre);

	    //Pour notre titre
	    this.txtMessage.setBounds(630, 170, 280, 200);
	    this.txtMessage.setPreferredSize(new Dimension(250, 150));
	    this.add(this.txtMessage);

	    this.panelConnexion.setBounds(630, 300, 280, 200);
	    this.panelConnexion.setBackground(new Color(255, 255, 255));
	    this.panelConnexion.setLayout(new GridLayout(7, 0));

	    //Pour L'input Email
	    this.panelConnexion.add(new JLabel("Email"));
	    this.panelConnexion.add(this.txtEmail);
	    this.txtEmail.setBackground(new Color(59, 125, 221)); //Couleur de fond blue
	    this.txtEmail.setForeground(new Color(255, 255, 255)); //couleur de text White
	    Border LoweredBevelBorder = BorderFactory.createLoweredBevelBorder();
	    this.txtEmail.setBorder(LoweredBevelBorder);

	    //Pour l'input MDP
	    this.panelConnexion.add(new JLabel("Mot de passe"));
	    this.panelConnexion.add(this.txtMdp);
	    this.txtMdp.setBackground(new Color(59, 125, 221)); //blue
	    this.txtMdp.setForeground(new Color(255, 255, 255)); //couleur de text White
	    this.txtMdp.setBorder(LoweredBevelBorder);

	    //Pour le text MDP oublié
	    this.panelConnexion.add(new JLabel(""));
	    this.panelConnexion.add(this.txtInfo);
	    this.txtMdp.setBackground(new Color(59, 125, 221)); //blue

	    //Pour le bouton
	    this.panelConnexion.add(this.btSeConnecter);
	    this.btSeConnecter.setBackground(new Color(255, 200, 57));
	    this.btSeConnecter.setBounds(540, 500, 250, 100);
	    Border EtchedBorderLowered = BorderFactory.createEtchedBorder(
	      EtchedBorder.LOWERED
	    );
	    this.btSeConnecter.setBorder(EtchedBorderLowered);
	    this.btSeConnecter.addActionListener(this);

	    this.add(this.panelConnexion);

	    this.setVisible(true);
	  }
	

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtEmail.setText("");
            this.txtMdp.setText("");
        } else if (e.getSource() == this.btSeConnecter) {
            String email = this.txtEmail.getText();
            String mdp = new String(this.txtMdp.getPassword());

            // Vérification de la présence du technicien dans la base de données
            Technicien unTechnicien = Controleur.selectWhereTechnicien(email, mdp);

            if (unTechnicien == null) {
                JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants !",
                        "Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Bienvenue  " + unTechnicien.getNom()+" " + "  " + unTechnicien.getPrenom(),
                        "Connexion à Orange Application", JOptionPane.INFORMATION_MESSAGE);

                Alume.rendreVisible(false); // Fermeture de la connexion
                Alume.creerVueGenerale(true); // Ouverture du logiciel
            }
        }
    }
}
