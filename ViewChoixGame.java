import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;

public class ViewChoixGame extends JFrame {

    //JPanel
    JPanel choix = new JPanel();
    JPanel panneauPhoto = new JPanel();

    //JBoutton
    JButton euler = new JButton("Eulerien");
    JButton hamiltonien = new JButton("Hamiltonien");
    JButton menu = new JButton("Menu");

    //JLabel
    JLabel image = new JLabel();
    JLabel title = new JLabel("Jeu");

    //ImageIcon
    ImageIcon icon;

    private String Nom(){
        String name = JOptionPane.showInputDialog( "veuillez entrer votre nom :"); // permet a chaque entré de jeu 
        while(name == null || name.equals("")){
            name = JOptionPane.showInputDialog("Erreur vous n'avez pas entrer votre nom ! veuillez entrer votre nom :");
        }
        return name;
    }

    public ViewChoixGame(){
        setTitle("Game - Choix");
        setSize(800,600);
        choix.setBackground(new Color(121,6,32));
        this.getContentPane().add(choix);
        choix.setLayout(null);

        /*
         * Departager le panneau en deux
         */
        setLayout(new BorderLayout());
         choix.setPreferredSize(new Dimension(800, 350));
         choix.setBackground(new Color(0,71,106));
         add(choix, BorderLayout.SOUTH);
        
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
         * Titre
         */
        choix.add(title);
        title.setBounds(360,0,250,100);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setForeground(Color.WHITE);

        /*
         * Button menu
         */
        choix.add(menu);
        menu.setBounds(550,270,220,60);
        menu.setFont(new Font("Arial", Font.PLAIN, 15));
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            new View().setVisible(true);
            setVisible(false);
            }
        });
            
        /*
         * Button Game Eulerien
         */
        choix.add(euler);
        euler.setBounds(130,150,220,60);
        euler.setFont(new Font("Arial", Font.PLAIN, 15));
        euler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Matrice m = CheminEuler.MatriceAleaEuler();
                String n=Nom();
                new ViewGame(m, new Graphe(m),n).setVisible(true);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });

        /*
         * Button Game Hamiltonien
         */
        choix.add(hamiltonien);
        hamiltonien.setBounds(400,150,220,60);
        hamiltonien.setFont(new Font("Arial", Font.PLAIN, 15));
        hamiltonien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Matrice m = CheminHamiltonien.MatriceAleaHamiltonien();
                String n=Nom();
                new ViewGameHam(m, new Graphe(m),n).setVisible(true);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);
    } 
}
