import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.*;

public class panelControl extends JPanel implements ActionListener{

    static final int SCREEN_WIDTH = 75;
    static final int SCREEN_HEIGHT = panel.SCREEN_HEIGHT;
    static final int UNIT_SIZE = panel.UNIT_SIZE;
    JButton sand;
    JButton wall;
    JButton air;
    JButton water;
    JButton wood;
    static block currentBlock = panel.tablero.sand;

    panelControl(){
        
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        this.sand = new JButton("sand");
        this.wall = new JButton("wall");
        this.air = new JButton("erase");
        this.water = new JButton("water");
        this.wood = new JButton("wood");

        addButton(sand, Color.YELLOW);
        addButton(wall, Color.GRAY);
        addButton(air, Color.BLACK);
        addButton(water, Color.CYAN);
        addButton(wood, (Color)panel.tablero.wood.color);
        
    }

    private void addButton(JButton but, Color col){
        but.addActionListener(this);
        but.setFocusable(false);
        but.setBackground(col);
        but.setForeground(Color.BLACK);
        but.setPreferredSize(new DimensionUIResource(SCREEN_WIDTH, 25));
        this.add(but);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==sand){
            currentBlock = panel.tablero.sand;
        }
        
        if(e.getSource()==wall){
            currentBlock = panel.tablero.wall;
        }

        if(e.getSource()==air){
            currentBlock = panel.tablero.air;
        }

        if(e.getSource()==water){
            currentBlock = panel.tablero.water;
        }

        if(e.getSource()==wood){
            currentBlock = panel.tablero.wood;
        }
    }

    
    
}
