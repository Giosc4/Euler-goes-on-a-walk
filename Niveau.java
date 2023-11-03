import java.util.*;

public class Niveau {
    private List<Integer>[] list;
    private ArrayList<String> reponse = new ArrayList<>();
    private ArrayList<String> indices = new ArrayList<>();
    private List<Integer> xpoint = new ArrayList<>();
    private List<Integer> ypoint = new ArrayList<>();
    private ArrayList<String> nom = new ArrayList<>();
    private Matrice matrix;

    // les points xpoint et ypoint sont des coordonnées qui font partie de
    // Vertex.java

    PrintRead printRead = new PrintRead();

    /*
     * Constructeur de la classe Niveau
     * Qui permet de recuperer les informations d'un niveau precis
     * et le convertir en matrice puis l'afficher en graphe
     */
    public Niveau(int r, String fileName) {

        switch (r) {
            case 1:
                list = new List[3];
                for (int i = 0; i < 3; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(2);
                list[1].add(0);
                list[1].add(2);
                list[2].add(0);
                list[2].add(1);
                break;

            case 2:
                list = new List[4];
                for (int i = 0; i < 4; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(3);
                list[1].add(0);
                list[1].add(2);
                list[2].add(1);
                list[2].add(3);
                list[3].add(0);
                list[3].add(2);
                break;

            case 3:
                list = new List[4];
                for (int i = 0; i < 4; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(3);
                list[1].add(0);
                list[1].add(2);
                list[1].add(3);
                list[2].add(1);
                list[2].add(3);
                list[3].add(0);
                list[3].add(1);
                list[3].add(2);
                break;

            case 4:
                list = new List[5];
                for (int i = 0; i < 5; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(3);
                list[1].add(0);
                list[1].add(2);
                list[1].add(4);
                list[2].add(1);
                list[2].add(3);
                list[3].add(0);
                list[3].add(2);
                list[4].add(1);
                break;

            case 5:
                list = new List[6];
                for (int i = 0; i < 6; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(3);
                list[1].add(0);
                list[1].add(2);
                list[1].add(4);
                list[2].add(1);
                list[2].add(3);
                list[3].add(0);
                list[3].add(2);
                list[4].add(1);
                list[4].add(5);
                list[5].add(4);
                break;

            case 6:
            list = new List[6];
                for (int i = 0; i < 6; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(3);
                list[1].add(0);
                list[1].add(2);
                list[1].add(4);
                list[2].add(1);
                list[2].add(3);
                list[2].add(5);
                list[3].add(0);
                list[3].add(2);
                list[4].add(1);
                list[4].add(5);
                list[5].add(4);
                list[5].add(2);

                break;

            case 7:
            
                //Je lis le fichier er je le covertis en matrice
                matrix = printRead.readFromFile(fileName);
                if (matrix == null) {
                    System.out.println("File doesn't exist");
                    break;
                }
                // Je recupere les coordonnées des sommets
                for (int i = 0; i < matrix.getVertices().size(); i++) {
                    Vertex vertex = matrix.getVertices().get(i);
                    xpoint.add(vertex.getX());
                    ypoint.add(vertex.getY());
                }
                break;

            case 10:
                list = new List[3];
                for (int i = 0; i < 3; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(2);
                list[1].add(0);
                list[1].add(2);
                list[2].add(0);
                list[2].add(1);
                xpoint.add(250);
                ypoint.add(100);
                xpoint.add(450);
                ypoint.add(100);
                xpoint.add(300);
                ypoint.add(350);
                nom.add("L");
                nom.add("I");
                nom.add("T");
                reponse = new ArrayList<>();
                reponse.add("L");
                reponse.add("I");
                reponse.add("T");

                indices.add("Je suis souvent utilisé la nuit");
                indices.add("Je suis confortable et doux,");
                indices.add("On m'appelle souvent un meuble.");
                break;

            case 11:
                list = new List[5];
                for (int i = 0; i < 5; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(3);
                list[1].add(0);
                list[1].add(2);
                list[1].add(3);
                list[1].add(4);
                list[2].add(1);
                list[2].add(4);
                list[3].add(1);
                list[3].add(0);
                list[4].add(1);
                list[4].add(2);
                xpoint.add(300);
                ypoint.add(100);
                xpoint.add(400);
                ypoint.add(100);
                xpoint.add(500);
                ypoint.add(100);
                xpoint.add(350);
                ypoint.add(350);
                xpoint.add(450);
                ypoint.add(350);
                nom.add("C");
                nom.add("E");
                nom.add("E");
                nom.add("R");
                nom.add("M");
                reponse = new ArrayList<>();
                reponse.add("C");
                reponse.add("R");
                reponse.add("E");
                reponse.add("M");
                reponse.add("E");

                indices.add("Je suis blanc et doux,");
                indices.add("On me tartine souvent sur du pain,");
                indices.add("Je suis un ingrédient commun dans la pâtisserie.");
                break;

            case 12:
                list = new List[5];
                for (int i = 0; i < 5; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(2);
                list[0].add(3);
                list[0].add(4);
                list[1].add(0);
                list[1].add(2);
                list[1].add(3);
                list[1].add(4);
                list[2].add(0);
                list[2].add(1);
                list[2].add(3);
                list[2].add(4);
                list[3].add(0);
                list[3].add(1);
                list[3].add(2);
                list[3].add(4);
                list[4].add(0);
                list[4].add(1);
                list[4].add(2);
                list[4].add(3);

                xpoint.add(300);
                ypoint.add(350);
                xpoint.add(300);
                ypoint.add(200);
                xpoint.add(350);
                ypoint.add(100);
                xpoint.add(400);
                ypoint.add(200);
                xpoint.add(400);
                ypoint.add(350);

                nom.add("R");
                nom.add("E");
                nom.add("T");
                nom.add("O");
                nom.add("P");
                reponse = new ArrayList<>();
                reponse.add("P");
                reponse.add("O");
                reponse.add("R");
                reponse.add("T");
                reponse.add("E");

                indices.add("Je suis souvent en bois,");
                indices.add( "en métal ou en verre,");
                indices.add("Je suis un point d'entrée et de sortie,");
                break;

            case 13:
                list = new List[6];
                for (int i = 0; i < 6; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(5);
                list[0].add(3);
                list[1].add(0);
                list[1].add(2);
                list[2].add(1);
                list[2].add(3);
                list[2].add(5);
                list[3].add(0);
                list[3].add(2);
                list[3].add(4);
                list[4].add(5);
                list[4].add(3);
                list[5].add(0);
                list[5].add(4);
                list[5].add(2);

                xpoint.add(300);
                ypoint.add(100);
                xpoint.add(350);
                ypoint.add(100);
                xpoint.add(400);
                ypoint.add(150);
                xpoint.add(350);
                ypoint.add(250);
                xpoint.add(300);
                ypoint.add(250);
                xpoint.add(300);
                ypoint.add(200);

                nom.add("E");
                nom.add("S");
                nom.add("I");
                nom.add("C");
                nom.add("H");
                nom.add("A");
                reponse = new ArrayList<>();
                reponse.add("C");
                reponse.add("H");
                reponse.add("A");
                reponse.add("I");
                reponse.add("S");
                reponse.add("E");

                indices.add("Je suis un meuble que l'on trouve partout,");
                indices.add("On s'assoit sur moi quand on est fatigué,");
                indices.add("Je peux être en bois, en plastique ou en métal.");
                break;

            case 14:
            	list = new List[6];
                for (int i = 0; i < 6; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(5);
                list[0].add(3);
                
                list[1].add(0);
                list[1].add(2);
                list[1].add(4);
                
                list[2].add(1);
                list[2].add(3);
                list[2].add(5);
                
                list[3].add(2);
                list[3].add(0);
                list[3].add(5);
                list[3].add(4);
                
                list[4].add(3);
                list[4].add(1);
                list[4].add(5);
                
                list[5].add(0);
                list[5].add(4);
                list[5].add(3);
                list[5].add(2);

                xpoint.add(456);
                ypoint.add(100);
                
                xpoint.add(350);
                ypoint.add(100);
                
                xpoint.add(400);
                ypoint.add(150);
                
                xpoint.add(450);
                ypoint.add(300);
                
                xpoint.add(300);
                ypoint.add(250);
                
                xpoint.add(500);
                ypoint.add(250);

                nom.add("A");
                nom.add("E");
                nom.add("I");
                nom.add("C");
                nom.add("R");
                nom.add("H");
                
                reponse = new ArrayList<>();
                reponse.add("C");
                reponse.add("A");
                reponse.add("H");
                reponse.add("I");
                reponse.add("E");
                reponse.add("R");
                
   
                
                indices.add("<html>C'est un outils très utile pour<br>l'apprentissage et l'organisation de ses idées,<br></html>");
                indices.add( "<html><br><br>Je prend souvent des notes et j'ai besoin <br>d'un support pour les écrire,<br><br></html>");
                indices.add("<html><br><br>Il contient des pages blanches sur<br>lesquelles on peut écrire ou dessiner.<br><br></html>");
                
                break;
                
            case 15:
            	list = new List[7];
                for (int i = 0; i < 7; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(5);
                
                list[1].add(0);
                list[1].add(5);
                list[1].add(6);
                list[1].add(2);
                
                list[2].add(1);
                list[2].add(3);
                list[2].add(6);
                
                list[3].add(2);
                list[3].add(4);
                
                list[4].add(3);
                list[4].add(6);
                list[4].add(5);
                
                list[5].add(0);
                list[5].add(1);
                list[5].add(6);
                list[5].add(4);
                
                list[6].add(1);
                list[6].add(5);
                list[6].add(2);
                list[6].add(4);
              

                xpoint.add(300);
                ypoint.add(100);
                
                xpoint.add(350);
                ypoint.add(100);
                
                xpoint.add(400);
                ypoint.add(150);
                
                xpoint.add(350);
                ypoint.add(250);
                
                xpoint.add(300);
                ypoint.add(300);
                
                xpoint.add(200);
                ypoint.add(200);
                
                xpoint.add(450);
                ypoint.add(200);


                nom.add("L");
                nom.add("L");
                nom.add("E");
                nom.add("E");
                nom.add("C");
                nom.add("E");
                nom.add("H");
                
                reponse = new ArrayList<>();
                reponse.add("E");
                reponse.add("C");
                reponse.add("H");
                reponse.add("E");
                reponse.add("L");
                reponse.add("L");
                reponse.add("E");
                
                indices.add("<html>Il est souvent fabriqué en métal en bois ou en <br>plastique et comporte plusieurs échelons,<br><html>");
                indices.add( "<html><br><br>C'est un objet utilisé pour atteindre des <br>hauteurs inaccessibles à la main,<br<<br><br><html>");
                indices.add("<html><br><br>On l'utilise fréquemment pour les travaux de <br>bricolage, les réparations de la maison.<br><br><br><html>");
                
                break;
                
            case 16:
            	list = new List[7];
                for (int i = 0; i < 7; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(4);
                
                list[1].add(0);
                list[1].add(5);
                list[1].add(2);
                
                list[2].add(1);
                list[2].add(3);
                list[2].add(6);
                
                list[3].add(2);
                
                list[4].add(0);
                list[4].add(5);
                

                list[5].add(1);
                list[5].add(6);
                list[5].add(4);
                

                list[6].add(5);
                list[6].add(2);

                xpoint.add(300);
                ypoint.add(100);
                
                xpoint.add(350);
                ypoint.add(100);
                
                xpoint.add(400);
                ypoint.add(150);
                
                xpoint.add(350);
                ypoint.add(250);
                
                xpoint.add(300);
                ypoint.add(250);
                
                xpoint.add(500);
                ypoint.add(200);
                
                xpoint.add(450);
                ypoint.add(200);

                nom.add("I");
                nom.add("S");
                nom.add("U");
                nom.add("X");
                nom.add("C");
                nom.add("E");
                nom.add("A");
                
                reponse = new ArrayList<>();
                reponse.add("C");
                reponse.add("I");
                reponse.add("S");
                reponse.add("E");
                reponse.add("A");
                reponse.add("U");
                reponse.add("X");
                
                indices.add("<html>Cet outils est souvent utilisé en couture ou <br>pour couper du papier,<br><br><html>");
                indices.add( "<html><br>Cet objet de coupe est composé de deux lames <br>en métal qui se rejoignent au milieu,<br><br><br><html>");
                indices.add("<html><br><br><br>Pour l'utiliser, on tient chaque extrémité dans une<br> main et on presse les poignés pour faire bouger<br> les lames.<br><br><br><br><html>");
                
                break;
                
            case 17:
            list = new List[9];
                for (int i = 0; i < 9; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(3);
                list[1].add(0);
                list[1].add(2);
                list[1].add(4);
                list[2].add(1);
                list[2].add(5);
                list[3].add(0);
                list[3].add(4);
                list[3].add(6);
                list[4].add(1);
                list[4].add(3);
                list[4].add(5);
                list[4].add(7);
                list[5].add(2);
                list[5].add(4);
                list[5].add(8);
                list[6].add(3);
                list[6].add(7);
                list[7].add(4);
                list[7].add(6);
                list[7].add(8);
                list[8].add(7);
                list[8].add(5);

                xpoint.add(300);
                ypoint.add(100);
                xpoint.add(375);
                ypoint.add(100);
                xpoint.add(450);
                ypoint.add(100);

                xpoint.add(300);
                ypoint.add(175);
                xpoint.add(375);
                ypoint.add(175);
                xpoint.add(450);
                ypoint.add(175);

                xpoint.add(300);
                ypoint.add(250);
                xpoint.add(375);
                ypoint.add(250);
                xpoint.add(450);
                ypoint.add(250);


                nom.add("E");
                nom.add("L");
                nom.add("U");
                nom.add("S");
                nom.add("I");
                nom.add("S");
                nom.add("N");
                nom.add("E");
                nom.add("T");

                reponse = new ArrayList<>();
                reponse.add("U");
                reponse.add("S");
                reponse.add("T");
                reponse.add("E");
                reponse.add("N");
                reponse.add("S");
                reponse.add("I");
                reponse.add("L");
                reponse.add("E");

                indices.add("Je suis un objet du quotidien,");
                indices.add("Souvent utilisé en cuisine,");
                indices.add("Je viens dans différentes tailles,");
                break;

            case 18:
            list = new List[9];
                for (int i = 0; i < 9; i++) {
                    list[i] = new ArrayList<>();
                }
                list[0].add(1);
                list[0].add(8);
                list[1].add(0);
                list[1].add(2);
                list[1].add(8);
                list[2].add(1);
                list[2].add(3);
                list[3].add(2);
                list[3].add(4);
                list[3].add(8);
                list[4].add(3);
                list[4].add(5);
                list[5].add(4);
                list[5].add(6);
                list[5].add(7);
                list[6].add(5);
                list[6].add(7);
                list[7].add(5);
                list[7].add(6);
                list[7].add(8);
                list[8].add(0);
                list[8].add(1);
                list[8].add(3);
                list[8].add(7);

                xpoint.add(250);
                ypoint.add(175);

                xpoint.add(325);
                ypoint.add(100);

                xpoint.add(400);
                ypoint.add(100);

                xpoint.add(475);
                ypoint.add(100);

                xpoint.add(440);
                ypoint.add(175);

                xpoint.add(475);
                ypoint.add(200);

                xpoint.add(525);
                ypoint.add(200);

                xpoint.add(500);
                ypoint.add(275);

                xpoint.add(325);
                ypoint.add(275);


                nom.add("O");
                nom.add("N");
                nom.add("E");
                nom.add("P");
                nom.add("E");
                nom.add("L");
                nom.add("T");
                nom.add("E");
                nom.add("H");

                reponse = new ArrayList<>();
                reponse.add("T");
                reponse.add("E");
                reponse.add("L");
                reponse.add("E");
                reponse.add("P");
                reponse.add("H");
                reponse.add("O");
                reponse.add("N");
                reponse.add("E");

                indices.add("Je suis un objet électronique,");
                indices.add("Je vous permet de communiquer à distance,");
                indices.add("En composant des chiffres et des lettres,");
                indices.add("Je suis souvent transporté dans une poche.");
                break;
        }

    }

    public List<Integer>[] getList() {
        return list;
    }

    public ArrayList<String> getReponse() {
        return reponse;
    }

    public ArrayList<String> getNom() {
        return nom;
    }

    public List<Integer> getX() {
        return xpoint;
    }

    public List<Integer> getY() {
        return ypoint;
    }

    public Matrice getMatrice(){
        return matrix;
    }

    public ArrayList<String> getIndices(){
        return indices;
    }

}