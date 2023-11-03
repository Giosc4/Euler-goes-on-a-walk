import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class ViewGameHam extends JFrame{ 

    //JPanel
    JPanel panneauBoutton = new JPanel();
    Graphe panneauGraphe;
    JLabel panneauTemps;
    
    //JButton
    JButton retour = new JButton("Retour");
    JButton valider = new JButton("Valider");
    JButton solutionComplete = new JButton("Reponse");
    JButton solutionPartielle = new JButton("Indice");
    JButton ressayer = new JButton("Ressayer");

    //Icon
    private ImageIcon iconIndice;
    private ImageIcon iconTrue;
    private ImageIcon iconFalse;

    private JLabel indice;
    
    Matrice matriceorigine;
    int compteur=0;

    int comptTemps;
    Timer temps; 
    int tiempo;

    String nom;

    
    int etoiles = 10;
    int minutes = 0;
    int secondes = 0;
    Graphe GG;

    public ViewGameHam(Matrice matrice, Graphe view, String nom){
        this.nom=nom;

        temps = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                comptTemps += 1;
                incrementationTemps();
                if(comptTemps %60 == 0){
                    minutes += 1;
                }
                secondes = comptTemps % 60;
                if(comptTemps % 20 == 0){
                    etoiles -=1;
                } 
            }
        });
        temps.start();

        panneauGraphe = new Graphe(matrice);
        setTitle("Chemin Hamiltonien - Jeu");
        setSize(800,600);
        add(panneauBoutton);
        panneauBoutton.setBackground(new Color(219,219,219));
        add(panneauGraphe);
        panneauGraphe.setPlayingTrue();
        
        this.indice = new JLabel();

         /*
         * Departager le panneau en deux
         */

         setLayout(new BorderLayout());
         panneauGraphe.setPreferredSize(new Dimension(800, 400));
         panneauGraphe.setBackground(new Color(219,219,219));
         add(panneauGraphe);
        
         panneauBoutton.setPreferredSize(new Dimension(800, 200));
         panneauBoutton.setBackground(new Color(219,219,219));
         add(panneauBoutton, BorderLayout.SOUTH);
        
         panneauTemps = new JLabel("00:00");
         panneauTemps.setPreferredSize(new Dimension(800,25));
         panneauTemps.setBackground(new Color(219,219,219));
         panneauTemps.setAlignmentX(CENTER_ALIGNMENT);
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
         * Action of the button "retour"
         * Return to the retour
         */
        retour.addActionListener((ActionEvent e)->{
            new ViewChoixGame().setVisible(true);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            this.setVisible(false);
        });
 
        /*
         * Action of the button "Valider"
         */
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
        	
        	if(valider.getText().equals("Suivant")) {
                ressayer.setEnabled(true);
                solutionComplete.setEnabled(true);
                solutionPartielle.setEnabled(true);
            	this.remove(panneauGraphe);
                compteur=0;
            	panneauGraphe = new Graphe(CheminHamiltonien.MatriceAleaHamiltonien());
                panneauGraphe.setBackground(new Color(219,219,219));
                matriceorigine=panneauGraphe.getMatrice().copie();
            	add(panneauGraphe);
            	valider.setText("Valider");
                solutionPartielle.setEnabled(true);
            	solutionComplete.setEnabled(true);
                comptTemps = 0;
                etoiles = 10;
                if(!temps.isRunning()){
                    temps.start();
                    
                }
               
            	valider.setText("Valider");
        	}else if(valider.getText().equals("Valider")) {
        		panneauGraphe.setPlayingFalse();
        		matriceorigine=panneauGraphe.getMatrice().copie();
        		//matriceorigine.copie().printMatrice();
        		final ArrayList<Integer> reponse_ordinateur = CheminHamiltonien.cheminHamilton(panneauGraphe.getMatrice().copie());
            	final ArrayList<Integer> reponse_joueur = panneauGraphe.getVertex();
                
                    if(reponse_joueur.containsAll(reponse_ordinateur) && CheminHamiltonien.valider(reponse_joueur)) {
                        JTextArea gagne = new JTextArea(4,30);
                        gagne.setText("Bravo " + nom + ", Vous avez reussi le niveau. \n"
                                        + "Score : " + etoiles + "/10 etoiles\n" 
                                        + "Temps : " + minutes + " min et " + secondes + " secondes\n"
                                        + "rendez vous dans le fichier \"score.txt\" pour voir tous vos resultats. " );
                        gagne.setBackground(Color.LIGHT_GRAY);
                        gagne.setForeground(Color.DARK_GRAY);
                        panneauGraphe.add(gagne);
                        panneauGraphe.setPlayingFalse();
                        valider.setText("Suivant");
                        //Image pour dire que c'est bon
                        icon.drawImage(iconTrue.getImage(), 100, 100, 50, 50, null);
                        try {
                            enregistreScore();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        ressayer.setEnabled(false);
                        solutionComplete.setEnabled(false);
                        solutionPartielle.setEnabled(false);
                    }else{
                        panneauGraphe.setPlayingFalse();
                        valider.setEnabled(false);
                        panneauGraphe.add(new JLabel("Faux ! Veuillez réessayer"));
                        etoiles--;
                        //Image pour dire que c'est faux
                        icon.drawImage(iconFalse.getImage(), 100, 100, 50, 50, null);
                        try {
                            enregistreScore();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
            	revalidate();
            	repaint();
            	
        	}
        });

        /*
         * Action of the button "Reponse"
         */
        solutionComplete.addActionListener((ActionEvent e)->{  
            indice.setText("");
            panneauGraphe.add(new JLabel("la réponse qui était attendu : " + Arrays.toString(CheminHamiltonien.cheminHamilton(panneauGraphe.getMatOri().copie()).toArray()) + " au suivant"));
            valider.setText("Suivant");
            solutionPartielle.setEnabled(false);
            solutionComplete.setEnabled(false);
            panneauGraphe.setPlayingFalse();
            
        }); 

        /*
         * Action of the button "Indice"
         */
        solutionPartielle.addActionListener((ActionEvent e)->{
            
        	panneauGraphe.remove(indice);
        	panneauGraphe.add(indice);
        	
        	etoiles--;
        	ArrayList<Integer> solution=CheminHamiltonien.cheminHamilton(panneauGraphe.getMatOri().copie());        	
        	String s= "";
        	if(compteur<solution.size()) {
        		for(int i = 0; i <= compteur; i++) {
            		s += (solution.get(i)+ ", ");
            	}
            	compteur++;

        	} else {
        		valider.setText("Suivant");
        		solutionPartielle.setEnabled(false);
        		solutionComplete.setEnabled(false);
        	}
        	indice.setText(s);
        	repaint();
        	revalidate();
        });
        
        ressayer.addActionListener((ActionEvent e)->{
            panneauGraphe.setPlayingTrue();
            valider.setEnabled(true);
            solutionComplete.setEnabled(true);
            solutionPartielle.setEnabled(true);
        	matriceorigine = panneauGraphe.getMatOri().copie();
        	this.remove(panneauGraphe);
			panneauGraphe=new Graphe(matriceorigine);
			panneauGraphe.setBackground(new Color(219,219,219));
			panneauGraphe.reset();
			add(panneauGraphe);
        	valider.setText("Valider");
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

    public String getNom(){
        return nom;
    }

    public String afficheCompt(){
        int min = 0;
        if(comptTemps %60 == 0){
            min += 1;
        }
        return (min +  " : " + comptTemps);
    }
    
    public void enregistreScore() throws IOException{
        try (PrintWriter fichier = new PrintWriter(new FileWriter("scores.txt", true));){
            fichier.println("HAMILTONIEN : \n Utilisateur : "+ nom + "\n              "+
            "Temps : " + minutes + ":" + secondes +"\n              "+ 
            "Niveau : " + matriceorigine.getSommet() + " sommets\n              "+ 
            "Score : " + etoiles +"/10\n");
        } catch (FileNotFoundException e) {
            System.out.println("erreur exception FICHIER TEXTE");
        }
    }
    
}