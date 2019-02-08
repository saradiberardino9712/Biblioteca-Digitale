package Business.Controller;

import java.util.ArrayList;
import Business.Model.Notifica;
import Business.Model.TestoDigitale;

public class controller_pubblicazione_trascrizione {
	
	public static ArrayList<TestoDigitale> traesaminare;

	public static boolean prendi() {
		traesaminare=TestoDigitale.verifica("trascritto");
		if(traesaminare.isEmpty())
			return false;
		return true;
	}

	public static String prenditesto(String selezione) {
		String testo = null;
		int npag;
		String titolo;
		for(TestoDigitale t:traesaminare) {
			npag=t.getNumpag();
			titolo=t.getTitoloOpera();
    		if(selezione.contains(titolo) && selezione.contains(Integer.toString(npag))) {
    			testo=t.getText();
    		}
    	}
		return testo;
	}

	public static boolean pubblica(String selezione) {
		boolean vista = false;
		boolean update=false;
		int idimmagine = 0;
		for(TestoDigitale t:traesaminare) {
			if(selezione.contains(t.getTitoloOpera()) && selezione.contains(Integer.toString(t.getNumpag()))) {
    			idimmagine=t.getIdimmagine();
    			vista=Notifica.updatevistadbtesto("Pubblica", null, idimmagine, 0);
    			update=TestoDigitale.updatestato("pubblicato", idimmagine);
    		}
		}
		if(vista && update)
			return true;
		else
			return false;
	}

}
