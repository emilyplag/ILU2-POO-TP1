package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {

	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois obelix = new Gaulois("Obélix", 25);
		//etal.libererEtal();
		//System.out.println("Fin du test");
		//l'erreur est nullpointer exception
		//etal.acheterProduit(0, obelix);
		//l'erreur est nullpointer exception
		try {
			etal.acheterProduit(-2,obelix);
		}
		catch (IllegalArgumentException e) {
		 e.printStackTrace();
		}
		catch (IllegalStateException e) {
			 e.printStackTrace();
			}
		
	}
}

