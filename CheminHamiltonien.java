import java.util.ArrayList;
import java.util.Random;

public class CheminHamiltonien {
    
    public static Matrice MatriceAleaHamiltonien() {
        Matrice matrice;
        do {
            matrice = new Matrice();
            int sommet = matrice.getSommet();
            Random random = new Random();
            int areteAlea = random.nextInt(matrice.getSommet() * (matrice.getSommet()-1)/2) +1;
            for(int i=0; i<=areteAlea; i++) {
                matrice.ajoutAreteAleatoire();
            }   
        } while(!CheminHamiltonien.connexe(matrice) || !CheminHamiltonien.hamiltonien(matrice));
        return matrice;
    }/*la complexité de cet algorithme dépend du nombre d'itérations de la boucle do-while. À chaque itération, nous créons une matrice aléatoire de taille n x n (où n est le nombre de sommets), nous ajoutons un nombre aléatoire d'arêtes, et nous vérifions si la matrice est connexe et hamiltonienne. La complexité du calcul de la connexité est O(n^2) pour une recherche en profondeur (DFS), et la complexité de la recherche d'un chemin hamiltonien est O(n!) dans le pire des cas. En supposant que le nombre d'itérations est petit par rapport à n, la complexité de MatriceAleaHamiltonien() est d'environ O(n^3 * (n!)). */
    
    public static boolean connexe(Matrice matrice) {
        boolean[] visiteSommet = new boolean[matrice.getSommet()];
        int cmpt = DFS(matrice, 0, visiteSommet);
        return cmpt == matrice.getSommet();
    }/*la complexité de cet algorithme est O(n^2) pour une recherche en profondeur (DFS), où n est le nombre de sommets dans la matrice. */
    
    public static int DFS(Matrice matrice, int s, boolean[] visiteSommet) {
		visiteSommet[s] = true;
		int cmpt = 1;
		for (int i = 0; i < matrice.getSommet(); i++) {
			if (matrice.getMatrice()[s][i] != 0 && !visiteSommet[i]) {
				cmpt += DFS(matrice, i, visiteSommet);
			}
		}
		return cmpt;
	}/*la complexité de cet algorithme est O(n^2) dans le pire des cas, où n est le nombre de sommets dans la matrice. */
    
    public static boolean hamiltonien(Matrice matrice) {
		ArrayList<Integer> chemin = new ArrayList<Integer>();
		boolean[] visiteSommet = new boolean[matrice.getSommet()];
		hamiltonienBis(matrice, 0, visiteSommet, chemin);
		return chemin.size() == matrice.getSommet();
	}/*la complexité de cet algorithme est O(n!) dans le pire des cas, où n est le nombre de sommets dans la matrice. */
    
    public static boolean hamiltonienBis(Matrice matrice, int sommet, boolean[] visiteSommet, ArrayList<Integer> chemin) {
		visiteSommet[sommet] = true;
		chemin.add(sommet);
		if (chemin.size() == matrice.getSommet()) {
			return true;
		}
		for (int i = 0; i < matrice.getSommet(); i++) {
			if (matrice.getMatrice()[sommet][i] != 0 && !visiteSommet[i]) {
				if (hamiltonienBis(matrice, i, visiteSommet, chemin)) {
					return true;
				}
			}
		}
		visiteSommet[sommet] = false;
		chemin.remove(chemin.size() - 1);
		return false;
	}/*la complexité de cet algorithme est O(n!) dans le pire des cas, où n est le nombre de sommets dans la matrice. */

    public static ArrayList<Integer> cheminHamilton(Matrice matrice) {
		if (!hamiltonien(matrice) || !connexe(matrice)) {
			System.out.println("Le graphe n'est pas hamiltonien");
			return null;
		}
		ArrayList<Integer> chemin = new ArrayList<Integer>();
		boolean[] visiteSommet = new boolean[matrice.getSommet()];
		hamiltonienBis(matrice, 0, visiteSommet, chemin);
		return chemin;
	}/*la complexité de cet algorithme est O(n!) dans le pire des cas, où n est le nombre de sommets dans la matrice. */
    
    public static int getDeg(Matrice matrice, int sommetDepart) {
        int deg = 0;
        for(int i=0; i<matrice.getSommet(); i++){
            deg += matrice.getMatrice()[sommetDepart][i];
        }
        return deg;
    }/*la complexité de cet algorithme est O(n), où n est le nombre de sommets dans la matrice. */

    public static ArrayList<Integer> cheminHamiltonienPartiel(Matrice matrice) {
        int sommetDepart = -1;
        for(int i=0; i<matrice.getSommet(); i++) {
            if(getDeg(matrice, i) >= 2) {
                sommetDepart = i;
                break;
            }
        }
        if(sommetDepart == -1) {
            return null; // Le graphe ne contient pas de sommet avec un degré >= 2
        }
        ArrayList<Integer> resultat = new ArrayList<Integer>();
        resultat.add(sommetDepart);
        boolean[] visiteSommet = new boolean[matrice.getSommet()];
        visiteSommet[sommetDepart] = true;
        if(cheminHamiltonienPartielBis(matrice, sommetDepart, visiteSommet, resultat)) {
            return resultat;
        } else {
            return null;
        }
    }/*la complexité de cet algorithme dépend du nombre d'itérations de la boucle for qui recherche le sommet de départ avec un degré de 2 ou plus. La complexité de la recherche de ce sommet est O(n), où n est le nombre de sommets dans la matrice. Ensuite, nous exécutons l'algorithme cheminHamiltonienPartielBis() pour trouver un chemin hamiltonien à partir de ce sommet. La complexité de cet algorithme est O(n!) dans le pire des cas, où n est le nombre de sommets dans la matrice. En supposant que le nombre d'itérations est petit par rapport à n, la complexité de cheminHamiltonienPartiel() est d'environ O(n * (n!)). */
    
    public static boolean cheminHamiltonienPartielBis(Matrice matrice, int sommet, boolean[] visiteSommet, ArrayList<Integer> resultat) {
        if(resultat.size() == matrice.getSommet()) {
            return true; // Tous les sommets ont été visités, un chemin hamiltonien a été trouvé
        }
        for(int i=0; i<matrice.getSommet(); i++) {
            if(matrice.getMatrice()[sommet][i] != 0 && !visiteSommet[i]) {
                visiteSommet[i] = true;
                resultat.add(i);
                if(cheminHamiltonienPartielBis(matrice, i, visiteSommet, resultat)) {
                    return true;
                }
                visiteSommet[i] = false;
                resultat.remove(resultat.size()-1);
            }
        }
        return false; // Aucun chemin hamiltonien trouvé à partir de ce sommet
    }/*La complexité en temps de cet algorithme est de O(n!), où n est le nombre de sommets dans le graphe. Cela est dû au fait que l'algorithme essaie toutes les permutations possibles des sommets pour trouver un chemin hamiltonien. Le facteur n! vient du fait qu'il y a n façons de choisir le premier sommet, (n-1) façons de choisir le deuxième sommet, et ainsi de suite, ce qui donne un total de n x (n-1) x (n-2) x ... x 2 x 1 = n! permutations possibles. */

    public static boolean valider(ArrayList<Integer> chemin){ // on suppose que la taille du chemin est égale au nombre de sommets
        boolean [] tab = new boolean[chemin.size()];
        for(int i = 0; i<chemin.size(); i++){
            if(tab[chemin.get(i)] == true){
                return false;
            }
            tab[chemin.get(i)]= true;
        }

        for(int i = 0; i<tab.length; i++){
            if(tab[i] == false){
                return false;
            }
        }
        return true;
    }
    

}
