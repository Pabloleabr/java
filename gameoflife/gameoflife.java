import java.util.Random;

public class gameoflife {

    public static void main(String[] args){
        
        Random random = new Random();
        int HEIGHT = 20;
        int WIDTH = 40;
        int[][] tablero = new int[HEIGHT][WIDTH];

        tablero = ClearTablero(tablero, HEIGHT, WIDTH);

        for(int i = 0;i < HEIGHT;i++){//pone el tablero con numero aleatorios
            for(int j = 0;j < WIDTH;j++){
                tablero[i][j]= random.nextInt(2);
            }
        }

        for(int x = 0;x<100;x++){//num generaiones que se repite
            
            for(int a = 0;a < HEIGHT;a++){
                for(int b = 0;b < WIDTH;b++){
                    System.out.print(tablero[a][b]);
                }
                System.out.println();
            }
            System.out.println("------------");
            try {Thread.sleep(400);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            
            tablero = NewGeneration(tablero, HEIGHT, WIDTH);
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

        for(int i = 0;i < HEIGHT;i++){//copia el array de arrays
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
    

}