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
		public int nbrEtals;
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
			int nbrEtalsQuiOntProduit=0;
			for (int i=0;i<nbrEtals;i++) {
				if (etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					nbrEtalsQuiOntProduit+=1;
				}
			}
			
			Etal[] ListeEtalsVendeurs=new Etal[nbrEtalsQuiOntProduit];
			for (int n=0, a=0;n<nbrEtals;n++) {
				if (etals[n].isEtalOccupe() && etals[n].contientProduit(produit)) {
					ListeEtalsVendeurs[a]=etals[n];
					a=a+1;
				}
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
			chaine.append("Il reste " +etalsvides+" etals non utilises dans le marche");
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
	
	public class VillageSansChefException extends RuntimeException {
		private static final long serialVersionUIDD=1L;
		public VillageSansChefException() {
			
		}
	}
	
	
	public String afficherVillageois() throws VillageSansChefException {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		int n=marche.trouverEtalLibre();
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom()+" cherche un endroit pour vendre "+nbProduit+" "+produit+"\n");
		if (n==-1) {
			chaine.append(vendeur.getNom()+" n'a pas trouve d'endroit pour vendre ses"+nbProduit+" "+produit);
			return chaine.toString();
		}
		else {
			marche.utiliserEtal(n,vendeur,produit,nbProduit);
			chaine.append("Le vendeur "+vendeur.getNom()+" vend des "+produit+" à l'étal n°"+n);
		}
		return chaine.toString();
		
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des "+produit+ " sont :\n");
		Etal[] ListeEtalsVendeurs=marche.trouverEtals(produit);
		for (int i=0;i<ListeEtalsVendeurs.length;i++){
			chaine.append((ListeEtalsVendeurs[i].getVendeur()).getNom()+"\n");
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		Etal etalV=rechercherEtal(vendeur);
		chaine.append(etalV.libererEtal());
		return chaine.toString();
	}
	
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village" +getNom()+" possede plusieurs etals :\n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
		
		}
	
	
	}
	
	
	
	