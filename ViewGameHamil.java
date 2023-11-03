import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ViewGameHamil extends JFrame {

    //JPanel
    JPanel panneauBoutton = new JPanel();
    Graphe panneauGraphe;

    //JLabel
    JLabel text;
    JLabel panneauTemps;
    JLabel indice = new JLabel("");
    JLabel solution = new JLabel("");

    //JButton
    JButton retour = new JButton("Retour");
    JButton valider = new JButton("Valider");
    JButton solutionComplete = new JButton("Reponse");
    JButton solutionPartielle = new JButton("Indice");
    JButton ressayer = new JButton("Ressayer");

    private ImageIcon iconTrue;
    private ImageIcon iconFalse;
    private ImageIcon iconIndice;

    private JTextArea LigneBEST;
    String ligne1 = "";
    String ligne2 = "";
    String ligne3 = "";

    Timer temps;
    int comptTemps;
    ArrayList<String> res = new ArrayList<>();
    static int[] res2 = new int[3];
    int compteur=0;
    int decaler=0;
    Matrice matrice;
    int tiempo;
    int minutes = 0;
    int secondes = 0;
   
    public ViewGameHamil(Niveau n, int i){

        LigneBEST = new JTextArea();
        res2[0] = 10000;
        res2[1] = 10000;
        res2[2] = 10000;

        temps = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                comptTemps += 1;
                incrementationTemps();
                if(comptTemps %60 == 0){
                    minutes += 1;
                }
                secondes = comptTemps % 60;
            }
        });
        temps.start();

        matrice = new Matrice(n.getList());
        Graphe g = new Graphe(matrice);
        g.setNom(n.getNom());
        g.setX(n.getX());
        g.setY(n.getY());
        panneauGraphe = g;
        setTitle("Chemins Hamiltonien - Niveau");
        setSize(800,600);

        panneauGraphe.setLayout(null);
        text = new JLabel("Niveau "+i);
        text.setBounds(350,0,300,100);
        text.setFont(new Font("Serif", Font.BOLD, 25));
        panneauGraphe.add(text);

         /*
         * Departager le panneau en deux
         */

        setLayout(new BorderLayout());
        panneauGraphe.setPreferredSize(new Dimension(800, 375));
        panneauGraphe.setBackground(new Color(219,219,219));
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
        retour.addActionListener((ActionEvent e)->{
            this.setVisible(false);
            new ViewNiveauHamil(0,false,"").setVisible(true);
        });

        valider.addActionListener(e ->{
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
        		//matrice.copie().printMatrice();
            	final ArrayList<Integer> reponse_joueur = panneauGraphe.getVertex();

                if(n.getNom().size() == reponse_joueur.size()) {
                    temps.stop();
                    tiempo = comptTemps;
                    Score(tiempo);
                    //afficheRes();

                    icon.drawImage(iconTrue.getImage(), 100, 100, 50, 50, null);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    icon.setColor(panneauGraphe.getBackground());
                    icon.fillRect(100, 100, 50, 50);
                    this.setVisible(false);
                    new ViewNiveauHamil(i,true,"temps: "+minutes+":"+secondes).setVisible(true);

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
                }            
        });

        /*
         * Action of the button "Reponse"
         */  
        solutionComplete.addActionListener((ActionEvent e)->{
            ((Graphe) panneauGraphe).setPlayingFalse();
            solution = new JLabel(" "+Arrays.toString(n.getReponse().toArray())+" ");
            solution.setBounds(600,20,300,50);
            solution.setFont(new Font("Serif", Font.PLAIN, 15));
            solution.setForeground(Color.RED);
            panneauBoutton.add(solution);
            solutionPartielle.setEnabled(false);
            solutionComplete.setEnabled(false);
            ressayer.setEnabled(false);
            valider.setEnabled(false);
            valider.setOpaque(false);
            repaint();
        }); 

        /*
         * Action of the button "Indice"
         */
        solutionPartielle.addActionListener((ActionEvent e)->{
            ArrayList<String> solution= n.getIndices();    
            String reponse="";    	
        	if(compteur<solution.size()) {
        		for(int j=0; j<= compteur; j++) {
                    reponse=solution.get(j)+ " ";
            	}
            	compteur++;

        	}else {
                indice = new JLabel("La solution a déja etait affiché en entier");
                indice.setBounds(500, 0, 300, 50);
                indice.setFont(new Font("Serif", Font.PLAIN, 14));
                indice.setForeground(Color.BLACK);
                panneauBoutton.add(indice);
        		solutionPartielle.setEnabled(false);
                repaint();
        	}
            JLabel solu = new JLabel(reponse);
            solu.setBounds(475, 250+decaler, 900, 70);
            solu.setFont(new Font("Serif", Font.PLAIN, 13));
            solu.setForeground(Color.RED);
            panneauGraphe.add(solu);
            decaler+=33;
            repaint();
        });

        ressayer.addActionListener((ActionEvent e)->{
            compteur = 0;
            decaler = 0;

            indice.setText("");
            valider.setEnabled(true);
        	matrice=new Matrice(n.getList());
			this.remove(panneauGraphe);
            Graphe graphe = new Graphe(matrice);
            graphe.setNom(n.getNom());
            graphe.setX(n.getX());
            graphe.setY(n.getY());
			panneauGraphe= graphe;
			((Graphe) panneauGraphe).reset();
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

    public void incrementationTemps(){
        int mintutes = (comptTemps % 3600) / 60;
        int secondes = comptTemps % 60;
        panneauTemps.setText(mintutes+":"+secondes);
    }

    public int getTemps(){
        return tiempo;
    }

    
    public void Score(int t){ // lire les 3 lignes du fichier et les modifiers selon le score effectué
        try(BufferedReader lire = new BufferedReader(new FileReader("bestHamil.txt"))){
            int l = 1; // ligne courante
            int l2 = 2; // 2eme ligne 
            int l3 = 3; // 3eme ligne
            ligne1 = lire.readLine(); // stock la 1ere, 2eme et 3eme lignes  
            ligne2 = lire.readLine(); 
            ligne3 = lire.readLine(); 
        } catch (IOException e) { 
            System.out.println("erreur exception FICHIER BEST lecture ");
        }

        try(BufferedWriter ecrire = new BufferedWriter(new FileWriter("bestHamil.txt"))){
            if(res2[0] > t){    // si le premier est depassé le remplace 
                res2[0] = t; // remplace dans la liste au bon index pour stocket le resultat
                res.add(0,ViewNiveauHamil.nom); // remplace dans la liste de nom
                ecrire.write(ViewNiveauHamil.nom + "\n"); // remplace dans le fichier texte
                ecrire.write(ligne2 + "\n");
                ecrire.write(ligne3 + "\n");
            }
            else if(res2[0] < t && res2[1] > t){ // meme chose pour le deuxieme
                res2[1] = t;
                res.add(1,ViewNiveauHamil.nom); 
                ecrire.write(ligne1 + "\n");
                ecrire.write(ViewNiveauHamil.nom + "\n");
                ecrire.write(ligne3 + "\n");
            } 

            else if(res2[1] < t && res2[2] > t){ // et pour le troisieme
                res2[2] = t;
                res.add(2,ViewNiveauHamil.nom); 
                ecrire.write(ligne1 + "\n");
                ecrire.write(ligne2 + "\n");
                ecrire.write(ViewNiveauHamil.nom + "\n");
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
        try(BufferedReader lire = new BufferedReader(new FileReader("bestHamil.txt"))){
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
