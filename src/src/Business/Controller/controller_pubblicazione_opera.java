package Business.Controller;

import java.util.ArrayList;

import Business.Model.Immagine;
import Business.Model.Notifica;
import Business.Model.Opera;
import View.FrontController.SupervisorePageController;

public class controller_pubblicazione_opera {
	
	private static ArrayList<Immagine> listaimg;
	private static ArrayList<Opera> opere;

	public static ArrayList<String> prendi(){
		String titolo;
		ArrayList<String> listatitoli= new ArrayList<>();
		opere=Opera.prendiopere("in fase di acquisizione");
		listaimg=Immagine.verifica("acquisito");
		for(Immagine i:listaimg) {
			titolo=i.getTitoloOpera();
			for(Opera o:opere) {
				if(titolo.equals(o.getTitolo())) {
					if(!(listatitoli.contains(titolo))) {
						listatitoli.add(titolo);
					}
				}
			}
		}
		return listatitoli;
	}

	public static boolean pubblica(String titolo) {
		boolean update = false;
		boolean vista=false;
		for(Opera o:opere) {
			if(titolo.equals(o.getTitolo())) {
				update=Opera.updatestato("pubblicata", titolo);
				String not=SupervisorePageController.notifica;
				String desc="Pubblica \" ";
				int d=not.indexOf(desc);
				String orario=not.substring(d+desc.length());
				vista=Notifica.updatevistadb("Pubblica",orario);
			}
		}
		if(update && vista) {
			return true;
		}else
			return false;
	}

	public static ArrayList<Immagine> prendiimg(String titolo) {
		ArrayList<Immagine> img=new ArrayList<>();
		for(Immagine i:listaimg) {
			if(titolo.equals(i.getTitoloOpera())){
				img.add(i);
			}
		}
		return img;
	}

}
