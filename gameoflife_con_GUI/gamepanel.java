
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class gamepanel extends JPanel implements ActionListener{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    Random random = new Random();
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 5;
    static final int DELAY = 300;
    static int[][] tablero = new int[SCREEN_HEIGHT][SCREEN_WIDTH];
    Timer timer;
 

    gamepanel(){
        
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.addKeyListener(new MyKeyAdapater());
        tablero = ClearTablero(tablero, SCREEN_HEIGHT/UNIT_SIZE, SCREEN_WIDTH/UNIT_SIZE);
        for(int i = 0;i < SCREEN_HEIGHT/UNIT_SIZE;i++){//pone el tablero con numero aleatorios
            for(int j = 0;j < SCREEN_WIDTH/UNIT_SIZE;j++){
                tablero[i][j]= random.nextInt(2);
            }
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }

    
    public void paint(Graphics g){
        super.paint(g); //pinta el fondo

        Graphics2D g2d = (Graphics2D) g;
        
        
        for(int i = 0; i< SCREEN_HEIGHT/UNIT_SIZE;i++){
            for(int j = 0;j< SCREEN_WIDTH/UNIT_SIZE;j++){
                if (tablero[i][j]==1){
                    g2d.setPaint(Color.white);
                    g2d.fillRect(i*UNIT_SIZE, j*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    g2d.setPaint(Color.gray);
                    g2d.fillRect(i*UNIT_SIZE, j*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                }
            }
        }
        
        
    }
    static int[][] ClearTablero(int[][] tablero, int HEIGHT, int WIDTH){
        for(int i = 0;i < HEIGHT;i++){
            for(int j = 0;j < WIDTH;j++){
                tablero[i][j]= 0;
            }
        }
        return tablero;

    }

    static int[][] NewGeneration(int[][] tablero, int HEIGHT, int WIDTH){
        int[][] newtablero = new int[HEIGHT][WIDTH];

        for(int i = 0;i < HEIGHT;i++){
            for(int j = 0;j < WIDTH;j++){
                newtablero[i][j]= tablero[i][j];
            }
        }


        for(int i = 0;i < HEIGHT;i++){
            for(int j = 0;j < WIDTH;j++){
                int contador = 0;

                if(i>0){//comprueba si las tres de arribas son 1
                    if(tablero[i-1][j] == 1){contador++;}
                    if(j<WIDTH-1){if(tablero[i-1][j+1] == 1){contador++;}}
                    if(j>0){if(tablero[i-1][j-1] == 1){contador++;}}
                }
                if(i<HEIGHT-1){//comprueba si las tres de abajo son 1
                    if(tablero[i+1][j] == 1){contador++;}
                    if(j<WIDTH-1){if(tablero[i+1][j+1] == 1){contador++;}}
                    if(j>0){if(tablero[i+1][j-1] == 1){contador++;}}
                }
                if(j>0){if(tablero[i][j-1] == 1){contador++;}}//izquierda
                if(j<WIDTH-1){if(tablero[i][j+1] == 1){contador++;}}//derecha

                if(tablero[i][j] == 1 && (contador == 2 || contador == 3)){
                    newtablero[i][j] = 1;
                }
                else if(tablero[i][j] == 0 &&  contador == 3){
                    newtablero[i][j] = 1;
                }
                else if(tablero[i][j] == 1){
                    newtablero[i][j] = 0;
                }
                
                
            }
            
            
        }
        return newtablero;
    }
    

    @Override
    public void actionPerformed(ActionEvent e){

        
        tablero = NewGeneration(tablero, SCREEN_HEIGHT/UNIT_SIZE, SCREEN_WIDTH/UNIT_SIZE);
        repaint();
    }

    public class MyKeyAdapater extends KeyAdapter{
        
        @Override
        public void keyPressed(KeyEvent e){


            }
        }
    }

