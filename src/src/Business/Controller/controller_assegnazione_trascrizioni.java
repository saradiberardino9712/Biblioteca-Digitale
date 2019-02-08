package Business.Controller;

import java.util.ArrayList;

import Business.Model.Immagine;
import Business.Model.Notifica;
import Business.Model.Opera;
import Business.Model.Ruolo;
import Business.Model.TestoDigitale;
import Business.Model.Utente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class controller_assegnazione_trascrizioni {
	
	private static ArrayList<Opera> listaopere;
	private static ArrayList<Immagine> img;
	
	public static boolean verifica() {
		listaopere= Opera.prendiopere("pubblicata");
		if(listaopere.isEmpty())
			return false;
		else
			return true;
	}

	public static ObservableList<String> prendiopere() {
		ObservableList<String> lista=FXCollections.observableArrayList();
		String titolo;
		for(Opera o:listaopere) {
			titolo=o.getTitolo();
			lista.add(titolo);
		}
		return lista;
	}
	
	public static int prendiid(String r) {
		Ruolo ruolo=Ruolo.prendiiddb(r);
		int idruolo=0;
		if(!(ruolo==null))
			idruolo=ruolo.getID();
		return idruolo;
	}
	
	public static ObservableList<String> prenditrascrittori() {
		int idruolo=prendiid("Trascrittore");
		ArrayList<String> lista=Utente.prendiutenti(idruolo);
		ObservableList<String> list=FXCollections.observableArrayList();
		for(String s:lista) {
			list.add(s);
		}
		return list;
	}

	public static ObservableList<Integer> prendipagine(String opera) {
		img =Immagine.prendiimgopera(opera);
		boolean trovato = false;
		ArrayList<TestoDigitale> testi=TestoDigitale.verifica(null);
		ObservableList<Integer> list=FXCollections.observableArrayList();
		for(Immagine i:img) {
			for(TestoDigitale t:testi) {
				if(i.getID()==t.getIdimmagine()) {
					trovato=true;
					break;
				}else
					trovato=false;
			}
			if(!trovato) 
				list.add(i.getNumeropagina());
		}
		if(list.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Attenzione!!");
			alert.setHeaderText("Al momento non ci sono trascrizioni da fare su questa opera!!");
			alert.showAndWait();
		}
		return list;
	}

	public static boolean assegna(int pagina, ArrayList<String> elenco) {
		boolean notifica = false;
		int idimmagine = 0;
		for(Immagine i:img) {
			if(i.getNumeropagina()==pagina) {
				idimmagine=i.getID();
				break;
			}
		}
		for(String s:elenco) {
			int idutente=Utente.getIstance().getID();
			int idutentenot=Integer.parseInt(s.substring(0, 1));
			notifica= Notifica.creanotificatesto("E' stata assegnata una trascrizione!! Clicca qui o su \" Trascrivi \" ", idutentenot,0, idutente,idimmagine);
		}
		return notifica;
	}

}
