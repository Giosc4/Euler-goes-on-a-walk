import java.util.ArrayList;
import java.util.Random;

public class CheminEuler {
	
	
	public static Matrice MatriceAleaEuler() {
		Matrice matrice;
		do {
			matrice = new Matrice();
			int sommet = matrice.getSommet();
			Random random = new Random();
			int areteAlea = random.nextInt(matrice.getSommet() * (matrice.getSommet()-1)/2) +1; //avec la formule n(n-1)/2 pour connaitre le nombre max darete
			for(int i=0; i<=areteAlea; i++) {
				matrice.ajoutAreteAleatoire();
			}
			  
	    	
		}while(!CheminEuler.connexe(matrice) || !CheminEuler.euler(matrice));
		
		return matrice;
	} /*La fonction MatriceAleaEuler a une complexité en temps approximative de O(n^2) puisqu'elle parcourt une matrice de taille n x n, où n est le nombre de sommets. Dans le pire des cas, elle peut avoir une complexité plus élevée si le graphe n'est pas connexe ou n'est pas eulérien et doit donc en créer plusieurs pour satisfaire les conditions.*/
	
	public static boolean connexe(Matrice matrice) {
        boolean[] visiteSommet = new boolean[matrice.getSommet()];
        int cmpt = DFS(matrice, 0, visiteSommet);
        return cmpt == matrice.getSommet();
    }/*La fonction connexe appelle la fonction DFS pour effectuer une recherche en profondeur, ce qui prend un temps proportionnel au nombre d'arêtes. Sa complexité en temps est donc O(n^2).*/

    public static int DFS(Matrice matrice, int s, boolean[] visiteSommet) {
        visiteSommet[s] = true;
        int cmpt = 1;
        for(int i=0; i<matrice.getSommet(); i++){
            if(matrice.getMatrice()[s][i] != 0 && !visiteSommet[i]) {
                cmpt += DFS(matrice, i, visiteSommet);
            }
        }
        return cmpt;
    }/*La fonction DFS parcourt tous les sommets du graphe en effectuant une recherche en profondeur à partir du sommet s, et retourne le nombre de sommets visités. Sa complexité en temps est donc O(n^2).*/

    public static boolean euler(Matrice matrice) {
        int degImpair = 0;
        for(int i=0; i<matrice.getSommet(); i++) {
            int deg = 0;
            for(int j=0; j<matrice.getSommet(); j++) {
                deg += matrice.getMatrice()[i][j];
            }
            if(deg%2 != 0) {
                degImpair++;
                if(degImpair > 2) {
                    return false;
                }
            }
        }
        return true;
    }/*La fonction euler parcourt tous les sommets du graphe pour calculer leur degré. Elle a donc une complexité en temps de O(n^2).*/

    public static ArrayList<Integer> cheminEuler(Matrice matrice) {
        if(!euler(matrice) || !connexe(matrice)) {
        	System.out.println("Le graphe n'est pas eulériens");
            return null;
        }

        int sommetDepart = 0;
        for(int i=0; i<matrice.getSommet(); i++) {
            if(getDeg(matrice, i)%2 != 0) {
                sommetDepart=i;
                break;
            }
        }
        ArrayList<Integer> resultat = new ArrayList<Integer>();
        resultat.add(sommetDepart);
        cheminEulerBis(matrice, sommetDepart, resultat);
        return resultat;
    }/*La fonction cheminEuler appelle la fonction cheminEulerBis pour parcourir le graphe. Sa complexité en temps dépend donc de la complexité de cheminEulerBis. Dans le pire des cas, cheminEulerBis parcourt toutes les arêtes du graphe, ce qui donne une complexité en temps de O(n^2).*/
    
    
    

    public static ArrayList<Integer> cheminEulerPartielle(Matrice matrice, int j) {
        if(!euler(matrice) || !connexe(matrice)) {
            return null;
        }
        if(j%2 == 0) return new ArrayList<Integer>();
        ArrayList<Integer> resultat = new ArrayList<Integer>();
        resultat.add(j);
        cheminEulerBis(matrice, j, resultat);
        return resultat;
    }/*La fonction cheminEulerPartielle a une complexité en temps similaire à celle de cheminEuler(Matrice matrice).*/
 
    
    public static void cheminEulerBis(Matrice matrice, int sommetDepart, ArrayList<Integer> resultat) {
        for(int i=0; i<matrice.getSommet(); i++) {
            if(matrice.getMatrice()[sommetDepart][i] != 0) {
                resultat.add(i);
                matrice.getMatrice()[sommetDepart][i]--;
                matrice.getMatrice()[i][sommetDepart]--;
                cheminEulerBis(matrice, i, resultat);
            }
        }
    }/*La fonction cheminEulerBis parcourt toutes les arêtes du graphe. Sa complexité en temps est donc de O(n2).*/


    public static int getDeg(Matrice matrice, int sommetDepart) {
        int deg = 0;
        for(int i=0; i<matrice.getSommet(); i++){
            deg += matrice.getMatrice()[sommetDepart][i];
        }
        return deg;
    }/*La fonction getDeg parcourt tous les sommets du graphe pour calculer le degré d'un sommet. Sa complexité en temps est donc de O(n).*/

}
