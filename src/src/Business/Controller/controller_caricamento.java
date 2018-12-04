package Business.Controller;

import java.util.ArrayList;
import Business.Model.Categoria;
import Business.Model.Opera;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class controller_caricamento {
	
	public static String categoria;
	
	public static ArrayList<Categoria> prendicategorie(){
		ArrayList<Categoria> elenco=Categoria.prendicategorie();
		return elenco;
	}
	
	public static String inseriscimetadati(TextField txtTitolo,TextField txtAnno,TextField txtAutore,TextField txtNpagine,ComboBox<String> combobox) {
		String categoria= (String)combobox.getValue();
		String titolo=txtTitolo.getText();
		String anno=txtAnno.getText();
		String autore=txtAutore.getText();
		String npagine=txtNpagine.getText();
		// controllo campi che nel database sono not null
		if (!(controllonotnull(titolo,anno,autore,npagine)))
			return "false";		
		int annoint=Integer.parseInt(anno);
		int npagineint=Integer.parseInt(npagine);
		// verifica la categoria
		String responso=verificacategoria(categoria);
		if(responso.equals("categoria") || responso.equals("false"))
			return responso;
		//associa l'id che la categoria ha nel db con la categoria scelta dall'utente nella combobox
		int ID_categoria=0;
		if(!(categoria.equals("Non ha categoria")))
			ID_categoria=associaid(categoria);
		boolean inserimento;
		inserimento = Opera.inseriscioperadb(titolo,annoint,autore,npagineint,ID_categoria);
		if(inserimento) {
			return "true";
		}else
			return "false";
	}
	
	private static boolean controllonotnull(String titolo, String anno,String autore, String npagine) {
		if (titolo.length()==0 || anno.length()==0 || autore.length()==0 || npagine.length()==0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore inserimento metedati opera");
			alert.setHeaderText("Compilare tutti i campi obbligatori(*)");
			alert.showAndWait();
			return false;
		}
		return true;
	}
	
	public static String verificacategoria(String categoria) {
		if(categoria==null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore inserimento metedati opera");
			alert.setHeaderText("Compilare la categoria!!");
			alert.showAndWait();
			return "false";
		}else {
			if (categoria.equals("Altro")) 
				return "categoria";
			else
				return "true";
		}	
	}
	
	private static int associaid(String categoria) {
		Categoria prendi=Categoria.prendiiddb(categoria);
		int idcategoria=0;
		if(!(prendi==null)) {
			idcategoria=prendi.getID();
		}	
		return idcategoria;
	}
	
	public static boolean inseriscicategoria(TextField txtcategoria) {
		categoria=txtcategoria.getText();
		ArrayList<Categoria> elenco=Categoria.prendicategorie();
		boolean contiene=true;
		String nome;
		for(Categoria c:elenco) {
			nome=c.getNome();
			if(categoria.equals(nome))
				contiene=false;
		}
		boolean inserisci = false;
		if (contiene) {
			inserisci=Categoria.inseriscidb(categoria);
		}	
		return inserisci;
	}
	
	
}
