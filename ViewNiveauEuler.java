import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ViewNiveauEuler extends JFrame {

    static boolean[] etoile = { false, false, false, false, false, false, false};
    static String [] tempString = { "", "", "", "", "", "", ""};

    // JPanel
    JPanel panneauNiveau = new JPanel();

    // JLabel
    static String nom = JOptionPane.showInputDialog( "veuillez entrer votre nom :"); // permet a chaque entré de jeu 
    JLabel text = new JLabel(" Niveaux Eulérien");
    JLabel indication = new JLabel("<html><body>Le fichier a exporter est sous forme de matrix_i.txt ou i est un nombre.<br /> Veuillez entrer l'entier i pour importer le graphe<body><html>");

    // JButton
    JButton niveau1 = new JButton("Niveau 1");
    JButton niveau2 = new JButton("Niveau 2");
    JButton niveau3 = new JButton("Niveau 3");
    JButton niveau4 = new JButton("Niveau 4");
    JButton niveau5 = new JButton("Niveau 5");
    JButton niveau6 = new JButton("Niveau 6");
    JButton retour = new JButton("Retour");

    JButton getMatrixFromFile = new JButton("Importer un graphe");
    JTextField fieldFileName = new JTextField(20);

    private ImageIcon iconEtoile;
    Timer temps;

    public ViewNiveauEuler(int niveau, boolean gagne, String temps) {
        if (niveau != 0 ) {
            if(niveau == 7){
                tempString[niveau -1]=temps;
            }else{
                etoile[niveau - 1] = gagne;
                tempString[niveau -1] = temps;
            }
        }
        setTitle("Chemins Eulériens - Niveaux");
        setSize(800, 600);
        panneauNiveau.setBackground(new Color(0, 71, 106));
        this.getContentPane().add(panneauNiveau);

        panneauNiveau.setLayout(null);

        text.setBounds(250, 100, 300, 100);
        text.setFont(new Font("Serif", Font.BOLD, 35));
        text.setForeground(Color.WHITE);
        panneauNiveau.add(text);

        indication.setBounds(475, 350, 300, 60);
        indication.setForeground(Color.WHITE);
        panneauNiveau.add(indication);

        try {
            iconEtoile = new ImageIcon(
                    ImageIO.read(new File("img/etoile.png"))
                            .getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        } catch (IOException e2) {
            System.out.println("Image not found h");
        }

        for (int i = 0; i < 7; i++) {
            if (etoile[i] == true) {
                switch (i) {
                    case 0:
                        niveau1.setIcon(iconEtoile);
                        break;
                    case 1:
                        niveau2.setIcon(iconEtoile);
                        break;
                    case 2:
                        niveau3.setIcon(iconEtoile);
                        break;
                    case 3:
                        niveau4.setIcon(iconEtoile);
                        break;
                    case 4:
                        niveau5.setIcon(iconEtoile);
                        break;
                    case 5:
                        niveau6.setIcon(iconEtoile);
                        break;
                }
            }

            if(tempString[i] != "" || tempString[i] != null){
                switch (i) {
                    case 0:
                        niveau1.setText("<html><body><span style='font-size:11px'>Niveau 1<br />" + tempString[i]+"</body></html>");
                        break;
                    case 1:
                        niveau2.setText("<html><body><span style='font-size:11px'>Niveau 2<br />" + tempString[i]+"</body></html>");
                        break;
                    case 2:
                        niveau3.setText("<html><body><span style='font-size:11px'>Niveau 3<br />" + tempString[i]+"</body></html>");
                        break;
                    case 3:
                        niveau4.setText("<html><body><span style='font-size:11px'>Niveau 4<br />" + tempString[i]+"</body></html>");
                        break;
                    case 4:
                        niveau5.setText("<html><body><span style='font-size:11px'>Niveau 5<br />" + tempString[i]+"</body></html>");
                        break;
                    case 5:
                        niveau6.setText("<html><body><span style='font-size:11px'>Niveau 6<br />" + tempString[i]+"</body></html>");
                        break;
                    case 6:
                        getMatrixFromFile.setText("<html><body><span style='font-size:11px'>Importer un graphe<br />"+ tempString[i]+"</body></html>");
                }
            }
        }

        panneauNiveau.add(niveau1);
        niveau1.setBounds(100, 200, 150, 60);
        niveau1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewGameEuler(new Niveau(1, null), 1).setVisible(true);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });

        panneauNiveau.add(niveau2);
        niveau2.setBounds(300, 200, 150, 60);
        niveau2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewGameEuler(new Niveau(2, null), 2).setVisible(true);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });

        panneauNiveau.add(niveau3);
        niveau3.setBounds(500, 200, 150, 60);
        niveau3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewGameEuler(new Niveau(3, null), 3).setVisible(true);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });

        panneauNiveau.add(niveau4);
        niveau4.setBounds(100, 275, 150, 60);
        niveau4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewGameEuler(new Niveau(4, null), 4).setVisible(true);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });

        panneauNiveau.add(niveau5);
        niveau5.setBounds(300, 275, 150, 60);
        niveau5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewGameEuler(new Niveau(5, null), 5).setVisible(true);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });

        panneauNiveau.add(niveau6);
        niveau6.setBounds(500, 275, 150, 60);
        niveau6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewGameEuler(new Niveau(6, null), 6).setVisible(true);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });
    
        panneauNiveau.add(getMatrixFromFile);
        getMatrixFromFile.setBounds(100, 350, 150, 60);
        getMatrixFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrintRead printRead = new PrintRead();

                String fileName = "matrix_" + fieldFileName.getText() + ".txt";

                if (!printRead.fileExist(fileName)) {
                    System.out.println("File not found");
                    return;
                }

                new ViewGameEuler(new Niveau(7, fileName), 7).setVisible(true);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                } catch (NullPointerException e1) {
                    e1.printStackTrace();
                }
                setVisible(false);
            }
        });

        panneauNiveau.add(fieldFileName);
        fieldFileName.setBounds(300, 350, 150, 60);
        
        panneauNiveau.add(retour);
        retour.setBounds(575, 490, 200, 60);
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewChoix().setVisible(true);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                setVisible(false);
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public String getNom(){
        return nom;
    }
}
