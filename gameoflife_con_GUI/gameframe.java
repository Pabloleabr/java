import javax.swing.JFrame;

public class gameframe extends JFrame{
    
    /**
     *
     */
    private static final long serialVersionUID = -3559411624181895226L;

    gameframe(){
        
        this.setTitle("Conway's Gamen of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new gamepanel());
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    
    }
}

