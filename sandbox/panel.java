
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;


public class panel extends JPanel implements ActionListener, MouseInputListener{

    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 2;
    static board tablero;
    Timer timer;

    panel(){
        tablero = new board(SCREEN_WIDTH/UNIT_SIZE,SCREEN_HEIGHT/UNIT_SIZE);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.addMouseListener(this);
        timer = new Timer(100, this);
        timer.start();
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static int getScreenWidth() {
        return SCREEN_WIDTH;
    }

    public static int getUnitSize() {
        return UNIT_SIZE;
    }

    public void paint(Graphics g){
        super.paint(g); //pinta el fondo

        Graphics2D g2d = (Graphics2D) g;

        for(int i = 0; i< SCREEN_HEIGHT/UNIT_SIZE;i++){
            for(int j = 0;j< SCREEN_WIDTH/UNIT_SIZE;j++){
                g2d.setPaint(tablero.grid[i][j].color);
                g2d.fillRect(i*UNIT_SIZE, j*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                
                
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        tablero.nextGen();
        repaint();
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    

    //codigo copiado para que funcione alk mantener pulsado y no solo al pulsar,algun dia aprenderas threads
    volatile private boolean mouseDown = false;
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = true;
            initThread();
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseDown = false;
        }
    }
    
    volatile private boolean isRunning = false;
    private synchronized boolean checkAndMark() {
        if (isRunning) return false;
        isRunning = true;
        return true;
    }
    private void initThread() {
        if (checkAndMark()) {
            new Thread() {
                public void run() {
                    do {
                        tablero.grid[getMousePosition().x/UNIT_SIZE][getMousePosition().y/UNIT_SIZE] = panelControl.currentBlock;
                        //do something
                    } while (mouseDown);
                    isRunning = false;
                }
            }.start();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        mouseDown=false;
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    
}




