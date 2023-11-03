import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Matrice {
	private int[][] matrice; // matrix of adjacency. he contains 1 if there is an edge between the two
								// vertices, 0 otherwise
	private List<Integer>[] list; // list of adjacency. he contains the list of vertices adjacent to the vertex i
									// SO HE HAVE THE SAME EDGES OF THE MATRIX
	// at the index 0 we have the vertex 0 and all of his edges.
	private ArrayList<Vertex> vertices; // list of vertices, it's work with the index. at the index 0 we have the vertex
										// 0

	private int sommet;

	public int[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(int[][] matrice) {
		this.matrice = matrice;
	}

	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	public int getSommet() {
		return sommet;
	}

	public void setSommet(int sommet) {
		this.sommet = sommet;
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	// Constructeur qui nous permet de crée la matrice a partir d'un fichier
	public Matrice(ArrayList<Vertex> vertices, ArrayList<String> lineMatrice) {
		this.vertices = vertices;
		this.sommet = vertices.size();
		matrice = new int[sommet][sommet];
		for (int i = 0; i < sommet; i++) {
			for (int j = 0; j < sommet; j++) {
				String[] split = lineMatrice.get(i).split(" ");
				matrice[i][j] = Integer.parseInt(split[j]);
			}
		}
	}

	public Matrice(List<Integer>[] l) {
		this.list = l;
		this.matrice = new int[list.length][list.length];
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[i].size(); j++) {
				matrice[i][list[i].get(j)] = 1;
			}
		}
		sommet = list.length;

	}

	public Matrice() {
		Random rd = new Random();
		this.sommet = rd.nextInt(9) + 2;// entre 2 et 10
		matrice = new int[sommet][sommet];
	}

	// ajout constructeur pour la methode copie
	public Matrice(int sommet) {
		this.sommet = sommet;
		matrice = new int[sommet][sommet];
		vertices = new ArrayList<>();
	}

	public void ajoutArete(int debut, int fin) {
		if (debut >= 0 && debut < sommet && fin >= 0 && fin < sommet) {
			matrice[debut][fin] = 1;
			matrice[fin][debut] = 1;
		} else {
			System.out.println("erreur depasse la limite");
		}
	}

	public void ajoutAreteAleatoire() {
		int debut = (int) (Math.random() * sommet);
		int fin = (int) (Math.random() * sommet);
		if (debut != fin) {
			matrice[debut][fin] = 1;
			matrice[fin][debut] = 1;
		}
	}

	public Matrice copie() {
		Matrice copie = new Matrice(this.getSommet());
		for (int i = 0; i < this.getSommet(); i++) {
			for (int j = 0; j < this.getSommet(); j++) {
				copie.getMatrice()[i][j] = this.getMatrice()[i][j];
			}
		}
		return copie;
	}

	public void supprimerArete(int u, int i) {
		matrice[u][i] = 0;
		matrice[i][u] = 0;
	}

	// afficher la matrice d'adjacence
	public void printMatrice() {
		System.out.println("Number of vertex: " + sommet);
		System.out.println("La matrice d'adjacence est : ");
		for (int i = 0; i < sommet; i++) {
			for (int j = 0; j < sommet; j++) {
				System.out.print(matrice[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void setMatrice(int u, int i, int v) {
		matrice[u][i] = v;
		matrice[i][u] = v;
	}

	public boolean deleteVertex(int index) {
		if (index >= 0 && index < vertices.size()) {
			vertices.remove(index);

			int[][] newMatrix = new int[sommet - 1][sommet - 1];
			for (int i = 0; i < sommet - 1; i++) {
				for (int j = 0; j < sommet - 1; j++) {
					// Matrix element is deleted by shifting indices.
					int ii = (i < index) ? i : i + 1;
					int jj = (j < index) ? j : j + 1;
					newMatrix[i][j] = matrice[ii][jj];
				}
			}
			matrice = newMatrix;
			sommet--;
			return true;
		} else {
			System.out.println("ERROR vertex not deleted");
			return false;
		}
	}

	public int addVertex(int x, int y, int radius) {
		Vertex v = new Vertex(x, y, radius);
		vertices.add(v);

		int[][] newMatrix = new int[sommet + 1][sommet + 1];
		for (int i = 0; i < sommet; i++) {
			for (int j = 0; j < sommet; j++) {
				newMatrix[i][j] = matrice[i][j];
			}
		}

		matrice = newMatrix;
		sommet++;
		return sommet;

	}

	public int getVertexIndexAt(int x, int y) {
		for (int i = 0; i < vertices.size(); i++) {
			Vertex vertex = vertices.get(i);
			if (vertex.contains(x, y)) {
				return i;
			}
		}
		return -1;
	}

	public Matrice getRandomMatrix(int sommet) {
		Matrice matriceRand = new Matrice(sommet);

		int[][] matrice = new int[sommet][sommet];
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();

		for (int i = 0; i < sommet; i++) {
			int x = new Random().nextInt(461) + 39;
			int y = new Random().nextInt(288) + 87;

			Vertex v = new Vertex(x, y, 10);
			vertices.add(v);
		}
		matriceRand.setVertices(vertices);

		for (int i = 0; i < sommet; i++) {
			for (int j = 0; j < sommet; j++) {
				if (i != j) {
					matrice[i][j] = (int) (Math.random() * 2);
					matrice[j][i] = matrice[i][j];
				}
			}
		}
		matriceRand.setMatrice(matrice);

		return matriceRand;
	}

	public Matrice(int[][] array) {
		Matrice matrice = new Matrice(array.length);
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				matrice.setMatrice(i, j, array[i][j]);
			}
		}
	}
	
	public boolean Si1() {
		for(int i=0; i<this.getSommet(); i++) {
			for(int j=0; j<this.getSommet(); j++) {
				if(this.getMatrice()[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Matrice transforme(Matrice m) {
		for(int i=0; i<this.getSommet(); i++) {
			for(int j=0; j<this.getSommet(); j++) {
				this.matrice[i][j] = m.matrice[i][j];
			}
		}
		return m;
	}
}