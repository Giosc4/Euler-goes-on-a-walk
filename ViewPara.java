import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.Random;

/*
LAVORI DA COMPLETARE:
    - SISTEMARE IL PULSANTE RANDOM GENERATOR
    - SE IL GRAFO è EUELERIANO:
        FAR COMPARIRE UNA FINESTRA CON SCRITTO "IL GRAFO è EULERIANO" E POI SCOMPARE DOPO 2 SECONDO 
    - AGGIUNGERE IL CODICE DEI LIVELLI 
    - quando aggiungo un vertice devo inserire anche il numero del vertice
 */

public class ViewPara extends JFrame {
    // JPanel
    JPanel panneauSaisie = new JPanel();
    JPanel panneauDessin = new JPanel();

    // JLabel
    JLabel text = new JLabel(" Dessinez votre graphe :");

    // JButton
    JButton menu = new JButton("Menu");
    JButton euler = new JButton("Eulérien");
    JButton randomGenerator = new JButton("Random Generator");
    JButton vertex = new JButton("Sommet");
    JButton arete = new JButton("Arete");
    JButton deleteVertex = new JButton("Supprime Sommet");
    JButton deleteArete = new JButton("Supprime Arete");
    JButton saveOnFile = new JButton("Enregistrer dans le fichier");
    JButton editVetex = new JButton("Deplacer Sommet");
    JButton cleanAll = new JButton("Tout effacer");

    private boolean drawVertex_btn = false;
    private boolean drawEdge_btn = false;
    private boolean deleteVertex_btn = false;
    private boolean deleteEdges_btn = false;
    private boolean editVertex_btn = false;

    private int firstVertexIndexEdge;
    private int firstVertexIndexRemoveEdge;
    private int firstVertexIndexEditVertex = -1;
    private Matrice matrix;

    PrintRead printRead = new PrintRead();

    // Principal class of the game
    public ViewPara() {
        setTitle("Chemins Eulériens - Editeur");
        setSize(800, 600);
        panneauSaisie.setLayout(null);

        panneauDessin.setLayout(null);
        panneauDessin.add(text);
        text.setBounds(10, 20, 300, 50);
        text.setFont(new Font("Serif", Font.BOLD, 20));

        /*
         * Departager le panneau en deux
         */

        setLayout(new BorderLayout());
        panneauDessin = createPanneauDessin();
        panneauDessin.setPreferredSize(new Dimension(800, 400));
        add(panneauDessin, BorderLayout.NORTH);

        panneauSaisie.setPreferredSize(new Dimension(800, 200));
        panneauSaisie.setBackground(new Color(0, 71, 106));
        add(panneauSaisie, BorderLayout.SOUTH);

        /*
         * Button pour le panneau Saisie
         */
        int buttonWidth = 120;
        int buttonHeight = 50;

        // Button Sommet
        vertex.setBounds(20, 50, buttonWidth, buttonHeight);
        panneauSaisie.add(vertex);

        // Button Arete
        arete.setBounds(20, 110 , buttonWidth, buttonHeight);
        panneauSaisie.add(arete);

        // Button Supprimer Sommet
        deleteVertex.setBounds(160, 50 , buttonWidth, buttonHeight);
        deleteVertex.setFont(new Font("Arial", Font.PLAIN, 11));
        panneauSaisie.add(deleteVertex);

        // Button Supprimer Arete
        deleteArete.setBounds(160, 110, buttonWidth, buttonHeight);
        panneauSaisie.add(deleteArete);

        // Button Editeur Sommet
        editVetex.setBounds(300, 50, buttonWidth, buttonHeight);
        panneauSaisie.add(editVetex);

        // Button Générateur aléatoire
        randomGenerator.setBounds(300,110, buttonWidth, buttonHeight);
        randomGenerator.setFont(new Font("Arial", Font.PLAIN, 11));
        panneauSaisie.add(randomGenerator);

        // Button Eulérien
        euler.setBounds(440, 50, buttonWidth, buttonHeight);
        panneauSaisie.add(euler);

        // Button Tout Effacer
        cleanAll.setBounds(440, 110, buttonWidth, buttonHeight);
        panneauSaisie.add(cleanAll);

        // Button Sauvegarder
        saveOnFile.setBounds(580, 50, 175, buttonHeight);
        panneauSaisie.add(saveOnFile);
        
        // Button Menu
        menu.setBounds(580, 110, 175, buttonHeight);
        panneauSaisie.add(menu);

        matrix = new Matrice(0);
        firstVertexIndexEdge = -1;
        firstVertexIndexRemoveEdge = -1;

        /*
         * Action of the button "Menu"
         * Return to the menu
         */
        menu.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
            View v = new View();
            v.setVisible(true);
        });

        /*
         * Action of the button "Vertex"
         */
        vertex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawVertex_btn = true;

                drawEdge_btn = false;
                deleteVertex_btn = false;
                deleteEdges_btn = false;
                editVertex_btn = false;

            }
        });

        /*
         * Action of the button "Arete"
         */
        arete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawEdge_btn = true;

                drawVertex_btn = false;
                deleteVertex_btn = false;
                deleteEdges_btn = false;
                editVertex_btn = false;

            }
        });

        /*
         * Action of the button "Delete Vertex"
         */
        deleteVertex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteVertex_btn = true;

                drawEdge_btn = false;
                drawVertex_btn = false;
                deleteEdges_btn = false;
                editVertex_btn = false;

            }
        });

        /*
         * Action of the button "Delete Arete"
         */
        deleteArete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEdges_btn = true;

                deleteVertex_btn = false;
                drawEdge_btn = false;
                drawVertex_btn = false;
                editVertex_btn = false;

            }
        });

        /*
         * Action of the button "Edit Vertex"
         */
        editVetex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editVertex_btn = true;

                deleteEdges_btn = false;
                deleteVertex_btn = false;
                drawEdge_btn = false;
                drawVertex_btn = false;
            }
        });

        /*
         * Action of the button "euler"
         * 
         */
        euler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawVertex_btn = false;
                drawEdge_btn = false;
                deleteVertex_btn = false;
                deleteEdges_btn = false;
                editVertex_btn = false;
                Matrice origine = matrix.copie();

                try {
                    Image greenIcon = ImageIO.read(new File("img/greenIcon.png"))
                            .getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    Image xIcon = ImageIO.read(new File("img/X.png"))
                            .getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    Graphics2D g2d = (Graphics2D) panneauDessin.getGraphics();

                    if(matrix.getSommet() != 0){
                        if (CheminEuler.cheminEuler(origine) != null) {
                            g2d.drawImage(greenIcon, 100, 100, 50, 50, null);
                        } else {
                            g2d.drawImage(xIcon, 100, 100, 50, 50, null);
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        g2d.setColor(getBackground());
                        g2d.fillRect(100, 100, 50, 50);
                    } else {
                        System.out.println("No graph");
                    }
                    
                } catch (IOException e3) {
                    System.out.println("Error ");
                    e3.printStackTrace();
                }
            }
        });

        /*
         * Action of the button "Clean All"
         */
        cleanAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawVertex_btn = false;
                drawEdge_btn = false;
                deleteVertex_btn = false;
                deleteEdges_btn = false;
                editVertex_btn = false;

                matrix = new Matrice(0);
                Graphics2D g2d = (Graphics2D) panneauDessin.getGraphics();
                g2d.clearRect(0, 0, panneauDessin.getWidth(), panneauDessin.getHeight());
                panneauDessin = createPanneauDessin();
                redrawGraph();
                //matrix.printMatrice();
            }
        });

        /*
         * Action of the button "Random Generator Matrix"
         */
        randomGenerator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawVertex_btn = false;
                drawEdge_btn = false;
                deleteVertex_btn = false;
                deleteEdges_btn = false;
                editVertex_btn = false;

                Random rand = new Random();
                matrix = new Matrice(rand.nextInt(5) + 1);
                Graphics2D g2d = (Graphics2D) panneauDessin.getGraphics();
                g2d.clearRect(0, 0, panneauDessin.getWidth(), panneauDessin.getHeight());
                panneauDessin = createPanneauDessin();
                matrix = matrix.getRandomMatrix((int) (Math.random() * 5 + 1));
                redrawGraph();
                //matrix.printMatrice();

            }
        });

        /*
         * Action of the button "saveOnFile"
         */
        saveOnFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawVertex_btn = false;
                drawEdge_btn = false;
                deleteVertex_btn = false;
                deleteEdges_btn = false;
                editVertex_btn = false;
                if(CheminEuler.euler(matrix) && CheminEuler.connexe(matrix)){
                    if (printRead.printOnFile(matrix)) {
                        JFrame regle = new JFrame();
                        regle.setTitle("Règles de jeu Hamiltonien");
                        JLabel rule = new JLabel(
                                "Bien enregistré dans le fichier matrix_" + printRead.getNbFile() + ".txt");
                        rule.setBounds(50, 50, 300, 200);
                        regle.setSize(600, 400);
                        JOptionPane.showMessageDialog(regle, rule);
                        JButton ok = new JButton("OK");
                        ok.setBounds(250, 300, 100, 50);
                        regle.add(ok);
                        ok.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                regle.setVisible(false);
                            }
                        });
    
                
                    }
                }
               
                // Verifier s'il est eulerien
                 else {
                    JFrame regle = new JFrame();
                    regle.setTitle("Information");
                    JLabel rule= new JLabel("Le fichier matrix_"+(printRead.getNbFile()+1)+".txt n'a pas pu être enregistrer");
                    rule.setBounds(50,50,300,200);
                    regle.setSize(600,400);
                    JOptionPane.showMessageDialog(regle, rule);
                    JButton ok = new JButton("OK");
                    ok.setBounds(250,300,100,50);
                    regle.add(ok);
                    ok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e){
                            regle.setVisible(false);
                        }
                    });
                }
            }
        });

        /*
         * Panel for actions in the graph
         */
        panneauDessin.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (drawVertex_btn) {
                    int x = e.getX();
                    int y = e.getY();
                    matrix.addVertex(x, y, 10);
                    drawCircle(panneauDessin.getGraphics(), x, y, 10);
                    //matrix.printMatrice();
                    drawVertex_btn = false;
                    drawEdge_btn = false;
                }

                if (drawEdge_btn) {
                    int x = e.getX();
                    int y = e.getY();
                    if (matrix.getVertexIndexAt(x, y) != -1) {
                        int vertexIndex = matrix.getVertexIndexAt(x, y);
                        Graphics2D g2d = (Graphics2D) panneauDessin.getGraphics();
                        Vertex vertex = matrix.getVertices().get(vertexIndex);

                        if (firstVertexIndexEdge == -1) {
                            firstVertexIndexEdge = vertexIndex;
                            g2d.setColor(Color.RED); // set color of first selected vertex to red
                            fillCircle(g2d, vertex.getX(), vertex.getY(), 10);

                        } else {
                            if (firstVertexIndexEdge != vertexIndex && matrix.getVertexIndexAt(x, y) != -1) {
                                matrix.ajoutArete(firstVertexIndexEdge, vertexIndex);
                                drawLine(g2d, matrix.getVertices().get(firstVertexIndexEdge).getX(),
                                        matrix.getVertices().get(firstVertexIndexEdge).getY(),
                                        matrix.getVertices().get(vertexIndex).getX(),
                                        matrix.getVertices().get(vertexIndex).getY());
                                //matrix.printMatrice();
                            }
                            Vertex firstSelectedVertex = matrix.getVertices().get(firstVertexIndexEdge);
                            g2d.setColor((Color) panneauDessin.getBackground());
                            fillCircle(g2d, firstSelectedVertex.getX(), firstSelectedVertex.getY(), 10);
                            g2d.setColor(Color.BLACK); // set color of first selected vertex back to black
                            drawCircle(g2d, firstSelectedVertex.getX(), firstSelectedVertex.getY(), 10);
                            firstVertexIndexEdge = -1;
                            drawEdge_btn = false;
                        }
                    }
                }

                if (deleteVertex_btn) {
                    int x = e.getX();
                    int y = e.getY();
                    int vertexIndex = matrix.getVertexIndexAt(x, y);

                    if (vertexIndex != -1) {
                        Graphics2D g2d = (Graphics2D) panneauDessin.getGraphics();

                        matrix.deleteVertex(vertexIndex);
                        g2d.clearRect(0, 0, panneauDessin.getWidth(), panneauDessin.getHeight());
                        panneauDessin = createPanneauDessin();
                        redrawGraph();
                       //matrix.printMatrice();
                    }
                    deleteVertex_btn = false;
                }

                if (deleteEdges_btn) {
                    int x = e.getX();
                    int y = e.getY();
                    int selectedVertexIndex = matrix.getVertexIndexAt(x, y);
                    Graphics2D g2d = (Graphics2D) panneauDessin.getGraphics();

                    if (selectedVertexIndex != -1) {
                        if (firstVertexIndexRemoveEdge == -1) {
                            firstVertexIndexRemoveEdge = selectedVertexIndex;
                            Vertex firstVertex = matrix.getVertices().get(firstVertexIndexRemoveEdge);
                            g2d.setColor(new Color(121, 6, 32));
                            g2d.fillOval(firstVertex.getX() - 20 / 2, firstVertex.getY() - 20 / 2, 20, 20);

                        } else if (firstVertexIndexRemoveEdge != selectedVertexIndex) {
                            matrix.supprimerArete(firstVertexIndexRemoveEdge, selectedVertexIndex);
                            g2d.clearRect(0, 0, panneauDessin.getWidth(), panneauDessin.getHeight());
                            panneauDessin = createPanneauDessin();
                            //matrix.printMatrice();

                            firstVertexIndexRemoveEdge = -1;
                            deleteEdges_btn = false;
                            redrawGraph();
                        }
                    }
                }

                if (editVertex_btn) {
                    int x = e.getX();
                    int y = e.getY();
                    int vertexIndex = matrix.getVertexIndexAt(x, y);
                    Graphics2D g2d = (Graphics2D) panneauDessin.getGraphics();

                    if (firstVertexIndexEditVertex == -1 && vertexIndex != -1) {
                        Vertex selectedVertex = matrix.getVertices().get(vertexIndex);
                        g2d.setColor(Color.BLUE);
                        g2d.fillOval(selectedVertex.getX() - 10, selectedVertex.getY() - 10, 10 * 2, 10 * 2);
                        firstVertexIndexEditVertex = vertexIndex;

                    } else {
                        if (vertexIndex == -1 && firstVertexIndexEditVertex != -1) {
                            Vertex selectedVertex = matrix.getVertices().get(firstVertexIndexEditVertex);
                            selectedVertex.setXY(x, y);
                            g2d.clearRect(0, 0, panneauDessin.getWidth(), panneauDessin.getHeight());

                            panneauDessin = createPanneauDessin();

                            firstVertexIndexEditVertex = -1;
                            redrawGraph();
                            editVertex_btn = false;
                        }
                    }
                }
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void redrawGraph() {
        Graphics2D g2d = (Graphics2D) panneauDessin.getGraphics();

        for (Vertex v : matrix.getVertices()) {
            drawCircle(g2d, v.getX(), v.getY(), 10);
        }
        for (int i = 0; i < matrix.getVertices().size(); i++) {
            Vertex v = matrix.getVertices().get(i);
            for (int j = 0; j < matrix.getVertices().size(); j++) {
                if (matrix.getMatrice()[i][j] == 1) {
                    Vertex v2 = matrix.getVertices().get(j);
                    drawLine(g2d, v.getX(), v.getY(), v2.getX(), v2.getY());
                }
            }
        }
    }

    public JPanel createPanneauDessin() {

        setTitle("Chemins Eulériens - Paramétrer");
        setSize(800, 600);
        panneauSaisie.setLayout(null);

        panneauDessin.setLayout(null);
        panneauDessin.add(text);
        text.setBounds(10, 20, 300, 50);
        text.setFont(new Font("Serif", Font.BOLD, 20));
        panneauDessin.setPreferredSize(new Dimension(800, 400));
        return panneauDessin;
    }

    public void drawCircle(Graphics g, int x, int y, int radius) {
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
        g.setColor(Color.BLACK);
        g.drawString(matrix.getVertexIndexAt(x, y) + "", x + 15, y);
    }

    public void fillCircle(Graphics g, int x, int y, int radius) {
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x1, y1, x2, y2);
    }

}
