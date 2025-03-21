package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel 
{

	private Object [][] donnees; //Matrce des donnees du tableau d'affichage
	private String [] entetes; //Les noms des entetes des colonnes
	
	
	
	
	public Tableau(Object[][] donnees, String[] entetes) {
		super();
		this.donnees = donnees;
		this.entetes = entetes;
	}

	@Override
	public int getRowCount() {
		
		return this.donnees.length; //Nombre de lignes
	}

	@Override
	public int getColumnCount() {
		return this.entetes.length; //nombre de colonnes
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.donnees [rowIndex][columnIndex]; //	Retourne une valuer dans la matrice
	}
	public void setDonnees (Object [][] matrice) {
		this.donnees=matrice ;
		this.fireTableDataChanged();//Actualiser l'affichange.
	}

	@Override
	public String getColumnName(int column) {
		 
		return  this.entetes[column];
	}
	
	
}

