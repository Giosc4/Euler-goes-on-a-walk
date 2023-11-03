import javax.swing.JFrame;

public class Main extends JFrame{
    	
	public Main() {
		
        //pour l'IG
        View vp = new View();
    	vp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	vp.setVisible(true);
	}
    	
    public static void main(String[] args) {
    	new Main();
    }
}
