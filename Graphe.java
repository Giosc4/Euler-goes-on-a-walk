import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class Graphe extends JPanel {

	private Color sommetColor = Color.BLUE; // couleur par défaut pour les sommets
	private Color arreteColor = Color.BLACK; // couleur par défaut pour les arêtes
	private final Color sommetColorSelected = Color.RED; //couleur pour les sommet lorsquils sont selectionner

	private final Color areteColorSelected = Color.RED;//couleur pour les aretes lorsquils sont selectionner
	private int sommetSelect1 = -1;
	private int sommetSelect2 = -1;

	private List<Integer> selectArête = new ArrayList<>();

	private Matrice matrice;
	private List<Integer> xpoint=new ArrayList<>();
	private List<Integer> ypoint=new ArrayList<>();
	private List<String> nomSommet = new ArrayList<>();
    private boolean isPlaying;
    private Matrice matriceorigine;
	
	public Graphe(Matrice m) {
		isPlaying = true;
		matrice=m;
		matriceorigine = matrice.copie();
		if(matrice != null){
			for(int i=0; i<matrice.getSommet(); i++) {
				xpoint.add((int) (Math.random()* 250)+ 260);
				ypoint.add((int) (Math.random()* 250)+ 75);
			}
		}
	
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!isPlaying) return;
				int mouseX = e.getX();
				int mouseY = e.getY();
				for (int i = 0; i < xpoint.size(); i++) {
					int distX = mouseX - xpoint.get(i);
					int distY = mouseY - ypoint.get(i);
					double distance = Math.sqrt(distX * distX + distY * distY);
					if (distance <= 5) { // si la distance est inférieure ou égale à 5 (le rayon du cercle)
						
						if(sommetSelect1 == -1) {
							sommetSelect1 = i;
							selectArête.add(sommetSelect1);

						}else if(sommetSelect2 == -1) {
							sommetSelect2 = i;

							int val = matrice.getMatrice()[sommetSelect1][sommetSelect2];

							if(val != 1) {
							}else {
								selectArête.add(sommetSelect2);
								matrice.setMatrice(sommetSelect1, sommetSelect2, 2);
								sommetSelect1 = sommetSelect2;
								repaint();
							}
							sommetSelect2 = -1;
						}
						break;
					}
				}
				repaint();
			}
		});
		
	}

	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0; i<matrice.getSommet(); i++) {
			for(int j=0; j<matrice.getSommet(); j++) {
				if(matrice.getMatrice()[i][j] == 1) {
					g.setColor(arreteColor); // couleur de l'arête
					g.drawLine(xpoint.get(i),ypoint.get(i),xpoint.get(j),ypoint.get(j));
				}
				if(matrice.getMatrice()[i][j] == 2) {
					g.setColor(areteColorSelected);
					g.drawLine(xpoint.get(i),ypoint.get(i),xpoint.get(j),ypoint.get(j));
				}
			}
		}
		int decale=10;
		for(int i =0; i<matrice.getSommet(); i++) {
			if(selectArête.contains(i)) g.setColor(sommetColorSelected); 
			else g.setColor(sommetColor); // couleur du sommet
			g.fillOval(xpoint.get(i)-5,ypoint.get(i)-5 , 10, 10);
			if(nomSommet.size() == 0){
				g.drawString(String.valueOf(i), xpoint.get(i)+decale, ypoint.get(i)+decale);
			}else{
				g.drawString(nomSommet.get(i), xpoint.get(i)+decale, ypoint.get(i)+decale);
			}	
		}
	}
	
	public void reset() {
		repaint();
		selectArête.clear();
		sommetSelect1=-1;
		sommetSelect2=-1;
		for(int i=0; i<matrice.getSommet(); i++) {
			for(int j=0; j<matrice.getSommet(); j++) {
				if(matrice.getMatrice()[i][j] == 2) {
					matrice.getMatrice()[i][j] = 1;
				}
			}
		}		
	}

	public ArrayList<Integer> getVertex() {
		return (ArrayList<Integer>) selectArête;
	}
	
	public Matrice getMatrice() {
		return matrice;
	}

	public void setX(List<Integer>x){
		this.xpoint = x;
	}

	public void setY(List<Integer>y){
		this.ypoint = y;
	}

	public void setNom(List<String> nom){
		this.nomSommet = nom;
	}
	
	public void setPlayingFalse() {
		this.isPlaying = false;
	}
	
	public void setPlayingTrue() {
		this.isPlaying = true;
	}
	
	public void setSommetColor(Color color) {
		sommetColor = color;
	}
	
	public void setArreteColor(Color color) {
		arreteColor = color;
	}
	
	public int getSommetSelect2() {
		return sommetSelect1;
	}
	
	public void setMatrice(int[][] m) {
		this.matrice=new Matrice(m);
	}
	
	public Matrice getMatOri() {
		return this.matriceorigine;
	}
	
}
