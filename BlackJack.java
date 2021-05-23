import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class BlackJack {
    public static void main(String[] args) {
        baraja estandar = new baraja();
        boolean jugar = true;
        estandar.barajar();
        List<carta> manoOp = new ArrayList<>();
        List<carta> manoTuya = new ArrayList<>();
        Scanner ask = new Scanner(System.in);

        System.out.print("El juego Blackjack consiste en acercarte todo lo posible a 21 sin pasarse, tener en cuenta que todo lo que sea mas grande de 10 cuenta como ");
        System.out.println("10, el 1 cuenta como 11 y 1, si vas a contar cartas que se baraja cuando queden menos de 10 cartas en el mazo de 48");
        System.out.println(" ");
        while (jugar){
            manoOp.clear();
            manoTuya.clear();
            manoOp.add(estandar.robarCarta());
            manoOp.add(estandar.robarCarta());
            System.out.println("La banca tiene: una carta oculta y " +"|"+ manoOp.get(1).toString() +"|") ;
            manoTuya.add(estandar.robarCarta());
            manoTuya.add(estandar.robarCarta());
            System.out.println("Tu tienes: " + mostrarMano(manoTuya));
            String answer = "";
            while (!answer.equals("stand")){
                System.out.println("introduce que quieres hacer: hit, stand o calcular probabilidad(CP)");                
                answer = ask.nextLine();
                switch (answer) {
                    case "hit":
                        manoTuya.add(estandar.robarCarta());
                        System.out.println("La banca tiene: una carta oculta y " +"|"+ manoOp.get(1).toString() +"|") ;
                        System.out.println(" ");
                        System.out.println("Tu tienes: " + mostrarMano(manoTuya));
                        break;
                
                    case "CP":
                        int cont = 0;
                        for(carta i : estandar.getMazo()){
                            if((21-calcular(manoTuya))<(i.getNum()>9 ? 10 : i.getNum())){
                                ++cont;
                            }
                        }
                        double Prob =  Math.round((double)cont/estandar.getMazo().size()*100);
                        System.out.println("La probabilidad de sacar una carta que te haga perder es: " + Prob +"%");
                        break;
                    case "MM":
                        System.out.println(estandar.getMazoVisual());
                        break;
                }
                if(perdido(calcular(manoTuya))){
                    answer="stand";
                    System.out.println("PERDISTE!!!");
                    System.out.println("la banca tenia: " + mostrarMano(manoOp));
                }
            }
            if(!perdido(calcular(manoTuya))){
                while(calcular(manoOp)<17){
                    manoOp.add(estandar.robarCarta());
                }
                System.out.println("la banca tiene: " + mostrarMano(manoOp));
                System.out.println(" ");
                System.out.println("tu mano: " + mostrarMano(manoTuya));
                if(perdido(calcular(manoOp)) || calcular(manoTuya) > calcular(manoOp)){
                    System.out.println("Ganaste!!!");
                }
                else{
                    System.out.println("PERDISTE!!!");
                }

            }
            System.out.println("quieres seguir jugando?(si o no)");                
            answer = ask.nextLine();
            jugar = answer.equals("si") ? true : false;

            if(estandar.getMazo().size()<10){
                estandar.nuevoMazo();
                estandar.barajar();
            }
        }
        ask.close();       
    }

    public static String mostrarMano(List<carta> mano){
        String cadena = "";
        for(carta i : mano){
            cadena +=  "|" + i.toString() + "| ";
        }
        return cadena;
    }

    public static int calcularAux(List<carta> mano){
        int x = 0;
        for(carta i : mano){
            if( i.getNum() > 9){
                x+=10;
            } else if(i.getNum()==1){
                x+=11;
            }else{
                x+=i.getNum();
            }
        }
        return x;
    }

    public static int calcular(List<carta> mano){
        int x = calcularAux(mano);
        
        for(carta i : mano){
            if(x>21){
                if(i.getNum()==1){
                    x-=10;
                }
        }
        }
        return x;
    }

    public static boolean perdido(int i){
        //devuelve true si has perdido y false si no has perdido
        return i > 21 ? true : false;
    }
}

class baraja{
    /**tenemos una baraja de cartas que esta compuesta por objetos carta 
    * una baraja tiene del 1 al 12 de 4 palos distintos
    */
    private List<carta> mazo;

    baraja(){
        nuevoMazo();
    }

    public void barajar(){
        Random rnum = new Random();
        for(int i=0; i<10000;i++){
            int num = rnum.nextInt(47);
            carta cartaElegida = mazo.get(num);
            mazo.remove(num);
            mazo.add(cartaElegida);
        }
    }

    public void nuevoMazo(){
        mazo = new ArrayList<>();
        anydirPalo('D');
        anydirPalo('B');
        anydirPalo('P');
        anydirPalo('T');
    }

    public carta robarCarta(){
        carta cartaRobada = mazo.get(0);
        mazo.remove(0);
        return cartaRobada;
    }

    private void anydirPalo(char palo){
        for(int i = 1; i < 13; i++){
            mazo.add(new carta(i, palo));
        }
    }
    
    public List<carta> getMazo(){return mazo;}

    public List<String> getMazoVisual(){
        List<String> visualDeck = new ArrayList<>();
        for(carta i : mazo){
            visualDeck.add(i.toString());
        }
        return visualDeck;
    }
}

class carta{
    /**cada carta tiene un palo y un numero */
    private int num;
    private char palo;

    carta(int n, char s){
        num = n;
        palo = s;
    }
    public int getNum(){return num;}
    public int getPalo(){return palo;}

    @Override
    public String toString(){
        return String.valueOf(num) + palo;
    }
}
