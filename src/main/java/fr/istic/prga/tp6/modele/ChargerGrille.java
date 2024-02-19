package fr.istic.prga.tp6.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class ChargerGrille {
	private static final String host = "mysqln.istic.univ-rennes1.fr";
	private static final String dbName = "base_bousse";
	private static final String username = "user_sseck";
	private static final String password = "Saliou1996-@";
	private static final String url = "jdbc:mysql://" + host + "/" + dbName + "?useSSL=false";

	public ChargerGrille() throws SQLException {
		connection();
	}

	private static void connection() {
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Vous êtes connecté à la base de données");
			conn.close();
		} catch (SQLException ex) {
			System.out.println("Une erreur s'est produite lors de la connexion à la base de données");
			ex.printStackTrace();
		}
	}

	public Map<Integer, String> grillesDisponibles() throws SQLException {
		Map<Integer, String> grilles = new HashMap<>();
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM TP5_GRILLE");
		while (rs.next()) {
			String nomGrille = rs.getString("nom_grille");
			int numGrille = rs.getInt("num_grille"), hauteur = rs.getInt("largeur"), largeur = rs.getInt("hauteur");
			grilles.put(numGrille, nomGrille + " (" + hauteur + " x " + largeur + ")" );
		}
		return grilles;

	}


	public MotsCroisesTP6 extraireGrille(int numGrille) throws SQLException {
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM TP5_GRILLE where num_grille ="+ numGrille);
		int hauteur, largeur;
		if (rs.next()) {
			hauteur = rs.getInt("hauteur");
			largeur = rs.getInt("largeur");
		} else {
			throw new IllegalArgumentException("La grille : n°" + numGrille + " n'existe pas");
		}
		MotsCroisesTP6 mc = new MotsCroisesTP6(hauteur, largeur);
		rs = stmt.executeQuery("SELECT * FROM TP5_MOT where num_grille =" + numGrille);
		while (rs.next()) {
			int ligne = rs.getInt("ligne"), colonne = rs.getInt("colonne");
			boolean horiz = rs.getBoolean("horizontal");
			String def = rs.getString("definition"), sol = rs.getString("solution").toUpperCase();
			setCase(mc, ligne, colonne, horiz, def, sol);
		}
		conn.close();
		return mc;

	}

	private void setCase(MotsCroisesTP6 mc, int ligne, int colonne, boolean horiz, String def, String sol) {

		mc.setCaseNoire(ligne,colonne, false);

		mc.setDefinition(ligne, colonne, horiz, def);

		int i = 0;
		if (horiz) {
			while (i < sol.length()) {
				mc.setSolution(ligne, colonne + i, sol.charAt(i));
				i++;
			}
		} else {
			while (i < sol.length()) {
				mc.setSolution(ligne + i, colonne, sol.charAt(i));
				i++;
			}
		}

	}
	
	public static void afficheTable() throws SQLException {
		
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM TP5_GRILLE");
		
		//On récupère les méta-données
		ResultSetMetaData resultMeta = result.getMetaData();
		System.out.println("\n*****************************************************************************************");
		//On affiche le nom des colonnes
		for(int i = 1 ; i <= resultMeta.getColumnCount() ; i++) 
			System.out.print("   " +resultMeta.getColumnName(i).toUpperCase() + "\t * ");
		System.out.println("\n*****************************************************************************************");			
		
		while (result.next()) {
			for(int i = 1 ; i <= resultMeta.getColumnCount() ; i++) 
				System.out.print("\t" + result.getObject(i).toString() + "\t |");
			System.out.println("\n-----------------------------------------------------------------------------------------");
			
		}
		result.close();
		conn.close();
		}
	


}
