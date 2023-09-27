package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	
	public Village(String nom, int nbVillageoisMaximum,int nbretals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche=new Marche(nbretals);
	}

	private class Marche{
		private int nbrEtals;
		private Etal[] etals;
		
		
		private Marche(int nbrEtals) {
			
			this.nbrEtals=nbrEtals;
			etals=new Etal[nbrEtals];
			for (int i=0;i<nbrEtals;i++){
				etals[i]=new Etal();}
			
			
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit,nbProduit);
		}
		
		private int trouverEtalLibre() {
			
			for (int n=0;n<nbrEtals;n++) {
				if (etals[n].isEtalOccupe()==false) {
					return n;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			Etal[] ListeEtalsVendeurs=new Etal[nbrEtals];
			int n=0;
			int j=0;
			while (n<nbrEtals) {
				if (etals[n].contientProduit(produit)) {
					ListeEtalsVendeurs[j]=etals[n];
					j=j+1;
				}
				n=n+1;
				
			}
			return ListeEtalsVendeurs;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			int n=0;
			while (n<nbrEtals) {
				if(etals[n].getVendeur().equals(gaulois)) {
					return etals[n];
						
				}
				n=n+1;
			}
			
		return null;
		}
		
		private String afficherMarche() {
			int etalsvides=0;
			StringBuilder chaine = new StringBuilder();

			for(int n=0;n<nbrEtals;n++) {
				if(etals[n].isEtalOccupe()==true) {
					chaine.append(etals[n].afficherEtal()+"\n");
				}
				else {
					etalsvides+=1;
				}
			}
			chaine.append("Il reste" +etalsvides+"etals non utilisés dans le marché");
			return chaine.toString();
		}
	}


	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		int n=marche.trouverEtalLibre();
		StringBuilder chaine = new StringBuilder();
		if (n==-1) {
			chaine.append(vendeur+"n'a pas trouvé d'endroit pour vendre ses"+nbProduit+" "+produit);
			return chaine.toString();
		}
		else {
			marche.utiliserEtal(n,vendeur,produit,nbProduit);
			chaine.append(false)
		}
	}
	
	
	
	
	
	
}