import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class View extends JFrame{

    //JPanel
    JPanel panneauColore = new JPanel();
    JPanel panneauPhoto= new JPanel();

    //JLabel
    JLabel image = new JLabel();

    //JButton
    JButton graphParametre = new JButton("Editeur");
    JButton graphAleatoire = new JButton("Jouer");
    JButton niveau = new JButton("Niveau");
    JButton rules = new JButton("Règles du jeu");

    //ImageIcon
    ImageIcon icon;
    ImageIcon iconPhoto;

    public View(){
        setTitle("Home - Les Chemins");
        setSize(800,600);
        panneauColore.setLayout(new BorderLayout());
        getContentPane().add(panneauColore);
        panneauColore.setLayout(null);


        /*
         * Departager le panneau en deux
         */

         setLayout(new BorderLayout());
         panneauColore.setPreferredSize(new Dimension(800, 350));
         panneauColore.setBackground(new Color(0,71,106));
         add(panneauColore, BorderLayout.SOUTH);
        
         panneauPhoto.setPreferredSize(new Dimension(800, 250));
         panneauPhoto.setBackground(new Color(0,71,106));
         add(panneauPhoto, BorderLayout.NORTH);  
 
        /*
         * Image de fond
         */
        try {
            icon = new ImageIcon(
                    ImageIO.read(new File("img/view.jpg"))
                            .getScaledInstance(280, 200, Image.SCALE_SMOOTH));     
        } catch (IOException e2) {
            System.out.println("Image not found");
        }
        image.setIcon(icon);
        panneauPhoto.add(image);
        image.setBounds(300,0,400,400);
        

        /*
         * Button "Paramétrer son graphe"
         */
        panneauColore.add(graphParametre);
        graphParametre.setBounds(20,150,220,60);
        graphParametre.setFont(new Font("Arial", Font.PLAIN, 15));
        graphParametre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new ViewPara().setVisible(true);
                /*
                 * try catch avec un thread.sleep pour pas que la fenetre
                 * se ferme brusquement
                 */
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });

        /*
         * Button Jouer
         */
        panneauColore.add(graphAleatoire);
        graphAleatoire.setBounds(270,0,240,75);
        graphAleatoire.setFont(new Font("Arial", Font.BOLD, 25));
        graphAleatoire.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new ViewChoixGame().setVisible(true);
                /*
                 * try catch avec un thread.sleep pour pas que la fenetre
                 * se ferme brusquement
                 */
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });

        /*
         * Button Niveau
         */
        panneauColore.add(niveau);
        niveau.setBounds(280,150,220,60);
        niveau.setFont(new Font("Arial", Font.PLAIN, 15));
        niveau.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new ViewChoix().setVisible(true);
                /*
                 * try catch avec un thread.sleep pour pas que la fenetre
                 * se ferme brusquement
                 */
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });

        /*
         * Button Rules
         */
        panneauColore.add(rules);
        rules.setBounds(530,150,220,60);
        rules.setFont(new Font("Arial", Font.PLAIN, 15));
        rules.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFrame regle = new JFrame();
                regle.setTitle("Règles du jeu");
                JLabel firstRule = new JLabel("<html><body>Editeur :<br />- Permet de créer son graphe qui doit etre eulerien. On peut également importer son graphe pour jouer dans niveau<br /><br /><br /><br /></body></html>");
                JLabel secondRule = new JLabel("<html><body>Jeux :<br />Vous aurez le choix entre Hamiltonien ou Eulérien<br /> Un graphe s'affichera faudra parcourir le graphe:<br />. Passer une seule et unique fois par tous les sommet pour Hamiltonien<br />. Passer une seule et unique fois par chaque arretes pour Eulérien<br /><br /> Un score s'affichera a chaque fois qu'on valide le graphe</body></html>");
                firstRule.setBounds(0,0,300,200);
                secondRule.setBounds(0,500,300,200);
                regle.setSize(600,400);
                regle.add(firstRule);
                regle.add(secondRule);
                regle.setVisible(true);
            }
        });
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}