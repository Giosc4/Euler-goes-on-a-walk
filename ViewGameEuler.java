import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ViewGameEuler extends JFrame {
    ArrayList<String> res = new ArrayList<>();
    static int[] res2 = new int[3];

    //JPanel
    JPanel panneauBoutton = new JPanel();
    Graphe panneauGraphe;

    //JLabel
    JLabel text;
    JLabel panneauTemps;
    JLabel solution;
    JLabel indice;
    JLabel marqueurTemps;

    //JButton
    JButton retour = new JButton("Retour");
    JButton valider = new JButton("Valider");
    JButton solutionComplete = new JButton("Reponse");
    JButton solutionPartielle = new JButton("Indice");
    JButton ressayer = new JButton("Ressayer");
    
    Matrice matriceorigine;
    int compteur=0;
    PrintRead printRead = new PrintRead();

    // ImageIcon
    private ImageIcon iconTrue;
    private ImageIcon iconFalse;
    private ImageIcon iconIndice;

    private JTextArea LigneBEST;
    String ligne1 = "";
    String ligne2 = "";
    String ligne3 = "";

    Timer temps;
    int comptTemps;
    int minutes = 0;
    int secondes = 0;
    Matrice matrice;
    int tiempo;

    public ViewGameEuler(Niveau n, int i) {
        
       LigneBEST = new JTextArea();
        res2[0] = 10000; // les 3 valeurs qui stockent les 3 meilleurs scores
        res2[1] = 10000;
        res2[2] = 10000;

        temps = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                comptTemps += 1;
                incrementationTemps();
                if(comptTemps %60 == 0){    // pouvoir avoir les minutes et les secondes separement
                    minutes += 1;
                }
                secondes = comptTemps % 60;
            }
        });
        temps.start();

        Graphe g;
        if(i == 7){
            g = new Graphe(n.getMatrice());
            g.setX(n.getX());
            g.setY(n.getY());
            matrice = n.getMatrice();
        }else{
            matrice = new Matrice(n.getList());
            g = new Graphe(matrice);
        }
        panneauGraphe = g;
        setTitle("Chemins Eulériens - Niveau");
        setSize(800,600);
        add(panneauBoutton);
        panneauBoutton.setBackground(new Color(219, 219, 219));
        panneauGraphe.setLayout(null);
        text = new JLabel("Niveau "+i);
        text.setBounds(350, 0, 300, 100);
        text.setFont(new Font("Serif", Font.BOLD, 25));
        panneauGraphe.add(text);

        /*
         * Departager le panneau en deux
         */

        setLayout(new BorderLayout());
        panneauGraphe.setPreferredSize(new Dimension(800, 375));
        panneauGraphe.setBackground(new Color(219, 219, 219));
        add(panneauGraphe);

        panneauBoutton.setPreferredSize(new Dimension(800, 170));
        panneauBoutton.setBackground(new Color(219,219,219));
        add(panneauBoutton, BorderLayout.SOUTH);

        panneauTemps = new JLabel("00:00");
        panneauTemps.setPreferredSize(new Dimension(800,25));
        panneauTemps.setBackground(new Color(219,219,219));
        add(panneauTemps, BorderLayout.NORTH);

        /*
         * Button pour le panneau Graphe
         */

        panneauBoutton.setLayout(null);
        panneauBoutton.add(retour);
        retour.setBounds(30, 60, 160, 60);

        panneauBoutton.add(valider);
        valider.setBounds(220, 60, 160, 60);

        panneauBoutton.add(ressayer);
        ressayer.setBounds(410, 60, 160, 60);

        panneauBoutton.add(solutionComplete);
        solutionComplete.setBounds(600, 110, 170, 50);
        panneauBoutton.add(solutionPartielle);
        solutionPartielle.setBounds(600, 60, 170, 50);

        /*
         * Icon of the button "Indice"
         */
        try {
            iconIndice = new ImageIcon(
                    ImageIO.read(new File("img/indice.png"))
                            .getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        } catch (IOException e2) {
            System.out.println("Image not found");
        }
        solutionPartielle.setIcon(iconIndice);

        /*
         * Action of the button "Menu"
         * Return to the menu
         */
        retour.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
            new ViewNiveauEuler(0, false,"").setVisible(true);
        });

        /*
         * Action of the button "Valider"
         * Qui permet de verifier si la solution est juste ou non
         */
        valider.addActionListener(e -> {
            try {
                iconTrue = new ImageIcon(
                        ImageIO.read(new File("img/greenIcon.png"))
                                .getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                iconFalse = new ImageIcon(ImageIO.read(new File("img/X.png"))
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            } catch (IOException e2) {
                System.out.println("Image not found");
            }
            Graphics icon = panneauGraphe.getGraphics();

            panneauGraphe.setPlayingFalse();
            matriceorigine = panneauGraphe.getMatrice().copie();
            //matriceorigine.copie().printMatrice();
            
            final ArrayList<Integer> reponse_ordinateur = CheminEuler
                    .cheminEuler(panneauGraphe.getMatrice().copie());
            final ArrayList<Integer> reponse_joueur = panneauGraphe.getVertex();

            if (reponse_joueur.containsAll(reponse_ordinateur) && matriceorigine.Si1() == false) {
                temps.stop();   // si la reponse correspond au chemin, on arrete le timer 
                tiempo = comptTemps;    // stockage de la valeur de temps pour vouvoir l'utiliser apres sur la methode scrore 

                Score(tiempo);
                //afficheRes();   // methode affiche dans le terminal des 3 meilleurs

                icon.drawImage(iconTrue.getImage(), 100, 100, 50, 50, null);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                icon.setColor(panneauGraphe.getBackground());
                icon.fillRect(100, 100, 50, 50);
                this.setVisible(false);
                new ViewNiveauEuler(i, true, "temps: "+minutes+":"+secondes).setVisible(true);
            }else {
                valider.setEnabled(false);

                icon.drawImage(iconFalse.getImage(), 100, 100, 50, 50, null);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                icon.setColor(panneauGraphe.getBackground());
                icon.fillRect(100, 100, 50, 50);
                panneauGraphe.setPlayingFalse();
            }
        });

        /*
         * Action of the button "Reponse"
         * Permet d'afficher la réponse
         */
        solutionComplete.addActionListener((ActionEvent e)->{
            panneauGraphe.setPlayingFalse();
            final ArrayList<Integer> reponse_ordinateur = CheminEuler.cheminEuler(panneauGraphe.getMatOri().copie());
            JLabel solut = new JLabel(" " + Arrays.toString(reponse_ordinateur.toArray()) + " ");
            solut.setBounds(650, 350, 300, 50);
            solut.setFont(new Font("Serif", Font.PLAIN, 15));
            solut.setForeground(Color.RED);
            panneauGraphe.add(solut);
            solutionPartielle.setEnabled(false);
            solutionComplete.setEnabled(false);
            ressayer.setEnabled(false);
            valider.setEnabled(false);
            panneauGraphe.setPlayingFalse();
            repaint();
        });

        solutionPartielle.addActionListener((ActionEvent e)->{
            ArrayList<Integer> solution=CheminEuler.cheminEuler(panneauGraphe.getMatOri().copie());    
            String reponse="";    	
        	if(compteur<solution.size()) {
        		for(int j=0; j<= compteur; j++) {
                    reponse+=solution.get(j)+ " ";
            	}
            	compteur++;

        	}else {
                panneauGraphe.setPlayingFalse();
                indice = new JLabel("La solution a déja etait affiché en entier");
                indice.setBounds(400, 0, 600, 50);
                indice.setFont(new Font("Serif", Font.PLAIN, 15));
                indice.setForeground(Color.BLACK);
                panneauBoutton.add(indice);
                solutionPartielle.setEnabled(false);
                solutionComplete.setEnabled(false);
                panneauGraphe.setPlayingFalse();
                repaint();
        	}
            JLabel solu = new JLabel(reponse);
            solu.setBounds(650, 330, 300, 50);
            solu.setFont(new Font("Serif", Font.PLAIN, 15));
            solu.setForeground(Color.RED);
            panneauGraphe.add(solu);
            repaint();
        });
        
        ressayer.addActionListener((ActionEvent e)->{
            panneauGraphe.setPlayingTrue();
            minutes = 0;
            secondes = 0;
            compteur = 0;
            if(indice != null){
                indice.setText("");
            }else{
                indice = new JLabel("");
            }
            valider.setEnabled(true);
            matriceorigine=panneauGraphe.getMatOri().copie();
            this.remove(panneauGraphe);
			panneauGraphe = new Graphe(matriceorigine);
			panneauGraphe.reset();
			add(panneauGraphe);

            panneauGraphe.setLayout(null);
            text = new JLabel("Niveau "+i);
            text.setBounds(350,0,300,100);
            text.setFont(new Font("Serif", Font.BOLD, 25));
            panneauGraphe.add(text);
            solutionPartielle.setEnabled(true);
            solutionComplete.setEnabled(true);

            setLayout(new BorderLayout());
            panneauGraphe.setPreferredSize(new Dimension(800, 375));
            panneauGraphe.setBackground(new Color(219,219,219));
            add(panneauGraphe);
            
            panneauBoutton.setPreferredSize(new Dimension(800, 175));
            panneauBoutton.setBackground(new Color(219,219,219));
            add(panneauBoutton, BorderLayout.SOUTH);

           
        	revalidate();
        	repaint();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void incrementationTemps(){  //methode qui met a jour le panneau graphe pour qu'on puisse voir le temps en minutes et secondes
        int mintutes = (comptTemps % 3600) / 60;
        int secondes = comptTemps % 60;
        panneauTemps.setText(mintutes+":"+secondes);
    }

    public int getTemps(){  // renvoie le temps 
        return tiempo;
    }

    // WARNING : nous avons commencé cette fonction qui devait sauvegarder les 3 meilleurs temps et donc les stocker dans le fichier "best.txt" mais nous n'avons pas reusi a finaliser ceci
    public void Score(int t){ // lire les 3 lignes du fichier et les modifiers selon le score effectué
        try(BufferedReader lire = new BufferedReader(new FileReader("best.txt"))){
            int l = 1; // ligne courante
            int l2 = 2; // 2eme ligne 
            int l3 = 3; // 3eme ligne
            ligne1 = lire.readLine(); // stock la 1ere, 2eme et 3eme lignes  
            ligne2 = lire.readLine(); 
            ligne3 = lire.readLine(); 
        } catch (IOException e) { 
            System.out.println("erreur exception FICHIER BEST lecture ");
        }

        try(BufferedWriter ecrire = new BufferedWriter(new FileWriter("best.txt"))){
            if(res2[0] > t){    // si le premier est depassé le remplace 
                res2[0] = t; // remplace dans la liste au bon index pour stocket le resultat
                res.add(0,ViewNiveauEuler.nom); // remplace dans la liste de nom
                ecrire.write(ViewNiveauEuler.nom + "\n"); // remplace dans le fichier texte
                ecrire.write(ligne2 + "\n");
                ecrire.write(ligne3 + "\n");
            }
            else if(res2[0] < t && res2[1] > t){ // meme chose pour le deuxieme
                res2[1] = t;
                res.add(1,ViewNiveauEuler.nom); 
                ecrire.write(ligne1 + "\n");
                ecrire.write(ViewNiveauEuler.nom + "\n");
                ecrire.write(ligne3 + "\n");
            } 

            else if(res2[1] < t && res2[2] > t){ // et pour le troisieme
                res2[2] = t;
                res.add(2,ViewNiveauEuler.nom); 
                ecrire.write(ligne1 + "\n");
                ecrire.write(ligne2 + "\n");
                ecrire.write(ViewNiveauEuler.nom + "\n");
            }

        } catch (IOException e) { 
            System.out.println("erreur exception FICHIER BEST ecriture ");
        }
        
    }

    public void afficheRes(){
        for(int i=0; i<res.size(); i++){
            System.out.print(" "+res.get(i));
        }
        System.out.println();
    }

    public void AffichetopScore() throws FileNotFoundException{ // permet de stocker dans un JText les 3 premieres lignes du fichier best qui stock les 3 meilleurs joueurs
        try(BufferedReader lire = new BufferedReader(new FileReader("best.txt"))){
            String ligne;
            int i=0;
            while((ligne = lire.readLine()) !=null && i < 3 ){
                LigneBEST.append(ligne+ "\n");
                i++;
                System.out.println(LigneBEST);

            }
        } catch (IOException e) { 
            System.out.println("erreur exception FICHIER BEST");
        }
        System.out.println(LigneBEST);
    }

}
