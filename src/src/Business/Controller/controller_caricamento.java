package Business.Controller;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import Business.Model.Categoria;
import Business.Model.Immagine;
import Business.Model.Notifica;
import Business.Model.Opera;
import Business.Model.Ruolo;
import Business.Model.Utente;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class controller_caricamento {
	
	public static String categoria;
	private static Opera cercaopera;
	private static int n;
	private static String url;
	private static int idutente;
	public static ArrayList<Opera> listaopere;
	public static ArrayList<Immagine> listaimmagini;
	
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
		inserimento = Opera.inseriscioperadb(titolo,anno,autore,npagineint,ID_categoria);
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
	
	public static boolean verifica(){
		listaopere=Opera.prendiopere();
		if(listaopere.isEmpty()) { // per verificare se ci sono opere
			return false;
		}
		listaimmagini=Immagine.immaginiacquisite();
		String titolo;
		int count=0;
		TreeMap<String,Integer> immaginiacquisite= new TreeMap<>();
		for(Immagine i:listaimmagini) {
			titolo=i.getTitoloOpera();
			if(!immaginiacquisite.containsKey(titolo)) {
				count=1;
			}else {
				count=immaginiacquisite.get(titolo)+1;
			}
			immaginiacquisite.put(titolo, count);
			if(i.getPagineOpera()==count) {
				remove(titolo);
			}
		}
		if(listaopere.isEmpty())// verificare che dopo il confronto tra pagine totali e numero immagini acquisite di una determianta opera ci siano ancora
			return false;		//opere che devono essere completamente acquisite
		return true;
	}
	
	private static void remove(String titolo) {
		int count = 0;
		for (Opera o: listaopere) {
			if(titolo.equals(o.getTitolo())) {
				break;
			}
			count++;
		}
		listaopere.remove(count);
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean controlloopera(ListView listViewOpere, ComboBox<String> comboboxNpag, TextField txtURL) {
		String titolo=(String) listViewOpere.getSelectionModel().getSelectedItem();
		String npag=comboboxNpag.getValue();
		url=txtURL.getText();
		if(!controllonotnullopera(titolo,npag,url)) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Errore inserimento pagina opera");
			alert.setHeaderText("Compilare tutti i campi!!");
			alert.showAndWait();
			return false;
		}
		n=Integer.parseInt(npag);
		for(Opera o:listaopere) {
			if(titolo.equals(o.getTitolo())) {
				cercaopera=o;
			}
		}
		return true;
	}

	public static boolean controllonotnullopera(String titolo, String npagina, String url) {
		if(titolo== null|| npagina==null || url.length()==0)
			return false;
		else 
			return true;
	}
	
	public static boolean carica() {
		int idopera=cercaopera.getID();
		idutente=Utente.getIstance().getID();
		ArrayList<Immagine> elim=controller_consenso_supervisione.imgeliminate;
		boolean carica = false;
		if(elim.isEmpty()) {
			carica=Immagine.caricaimmagine(url,n,"in caricamento",idopera,idutente);
		}else {
			for(Immagine img: elim) {
				if(!(img.getNumeropagina()==n && img.getUrl().equals(url))) {
					carica=Immagine.caricaimmagine(url,n,"in caricamento",idopera,idutente);
				}
			}
		}
		return carica;
	}
	
	public static int prendiidmanager() {
		Ruolo ruolo=Ruolo.prendiiddb("Manager");
		int idmanager=0;
		if(!(ruolo==null))
			idmanager=ruolo.getID();
		return idmanager;
	}
	
	public static ArrayList<Immagine> visualizzariepilogo() {
		ArrayList<Immagine> img=Immagine.prendiimg(idutente);
    	return img;
	}
	
	static ArrayList<Immagine> caricamento=new ArrayList<>();
	public static boolean caricadefinitiva(ArrayList<Immagine> images) throws InterruptedException {
		boolean b=false;
		boolean notifica=false;
		ArrayList<Boolean> n=new ArrayList<>();
		String titolo;
		int npag=0;
		for(Immagine i:images) {
			npag=i.getNumeropagina();
			titolo=i.getTitoloOpera();
			b= Immagine.updatestato("in acquisizione",npag,titolo);
			if(b) {
				int idutentenot=prendiidmanager();
				notifica=Notifica.creanotifica("E' stata caricata un'immagine!! E' richiesto il suo consenso!! Clicca qui o su \" Consenso supervisione \" ",idutentenot,idutente);
				n.add(notifica);
				TimeUnit.SECONDS.sleep(1);
			}else {
				caricamento.add(i);
				n.add(false);
			}
		}
		if(!caricamento.isEmpty() || n.contains(false)) {
			return false;
		}
		return true;
	}
	
	public static void annulla() {
		Immagine.remove(caricamento);
		caricamento.clear();
	}
}
