import java.awt.BorderLayout;

import javax.swing.JFrame;


public class frame extends JFrame{
    frame(){
        this.setTitle("sand box");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(new panel(),BorderLayout.EAST);
        this.add(new panelControl(),BorderLayout.WEST);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
    }
    
}
