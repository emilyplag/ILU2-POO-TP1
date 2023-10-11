package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.Village.VillageSansChefException;

public class ScenarioCasDegrade {

	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois obelix = new Gaulois("Obélix", 25);
		Village emily= new Village("emily",3,4);
		//etal.libererEtal();
		//System.out.println("Fin du test");
		//l'erreur est nullpointer exception
		//etal.acheterProduit(0, obelix);
		//l'erreur est nullpointer exception
		try {
			etal.acheterProduit(1,obelix);
		}
		catch (IllegalArgumentException e) {
		 e.printStackTrace();
		}
		catch (IllegalStateException e) {
			 e.printStackTrace();
			}
		finally {
		try {emily.afficherVillageois();}
			
		
		catch (VillageSansChefException e) {
			e.printStackTrace();
		}
	}
		
	}
}

