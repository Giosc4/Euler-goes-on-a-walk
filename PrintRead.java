import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class PrintRead {
    private int nFile=0;

    public PrintRead() {
            File dir = new File("graphs");
            if (!dir.exists()) {
                dir.mkdir();
            } else {
                this.nFile = dir.listFiles().length;
            }
    }

    public boolean fileExist(String fileName) {
        String path = "graphs/" + fileName;
        File dir = new File("graphs/", fileName);

        if(dir.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean printOnFile(Matrice adjacency) {
        //adjacency.printMatrice();
        try {
            String fileName = "matrix_" + nFile + ".txt";
            nFile++;

            Path filePath = Path.of("graphs", fileName);
            FileWriter writer = new FileWriter(filePath.toString());

            writer.write(adjacency.getSommet() + "\n");
            for (int i = 0; i < adjacency.getSommet(); i++) {
                for (int j = 0; j < adjacency.getSommet(); j++) {
                    writer.write(adjacency.getMatrice()[i][j] + " ");
                }
                writer.write("\n");
            }
            for (Vertex v : adjacency.getVertices()) {
                writer.write(v.getX() + " " + v.getY() + " " + v.getRadius() + "\n");
            }
            writer.write("---");
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void erreur(){
        JFrame erreur = new JFrame();
        erreur.setTitle("Erreur");
        JLabel rule= new JLabel("<html><body>Le fichier n'existe pas!</body></html>");
        rule.setBounds(50,50,300,200);
        erreur.setSize(600,400);
        JOptionPane.showMessageDialog(erreur, rule);
        JButton ok = new JButton("OK");
        ok.setBounds(250,300,100,50);
        erreur.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                erreur.setVisible(false);
            }
        });
    }

    public Matrice readFromFile(String fileName){
        Matrice matrice = null;
        try {
            //Je verifie si le fichier existe
            Path filePath = Path.of("graphs",fileName);
            if (!filePath.toFile().exists()) {
                erreur();
                return null;
            }
            // Je lis le fichier ligne par ligne
            BufferedReader reader = new BufferedReader(new FileReader(filePath+""));
            String line = reader.readLine();
            //Je recupere le nombre de sommet
            int nbSommet = Integer.parseInt(line);
            ArrayList<String> lineMatrice = new ArrayList<>();
            int i=nbSommet;
            // Je recupere les lignes de la matrice
            while ( i > 0){
                line = reader.readLine();
                lineMatrice.add(line);
                i--;
            }
            int j=nbSommet;
            ArrayList<Vertex> vertices = new ArrayList<>();
            // Je recupere les coordonnées des sommets et le rayon
            while(j>0){
                line = reader.readLine();
                String[] split = line.split(" ");
                vertices.add(new Vertex(Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2])));
                j--;
            }
            // Je cree la matrice
            matrice = new Matrice(vertices, lineMatrice);
            // Je ferme le reader
            reader.close();
;        }catch (IOException e ) {
                e.printStackTrace();
        } catch (NullPointerException e2) {
                    e2.printStackTrace();
        }
        return matrice;
    }

    public int getNbFile(){
        if(nFile == 0){
            return 0;
        }else{
            return nFile-1;
        }
    }
}
