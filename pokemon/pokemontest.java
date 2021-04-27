import java.util.Random;
import java.util.Scanner;


public class pokemontest {

    static final String F = "Fire";
    static final String W = "Water";
    static final String G = "Grass";
    static final String N = "Normal";
    static final String ATK = "ATK";
    static final String BDEF = "BDEF";
    static final String BATK = "BATK";
    static final String BSPD = "BSPD";

    static String select;
    static int move;
    static type fire = new type(F, W ,G);
    static type water = new type(W, G , F);
    static type grass = new type(G, F, W);
    static type normal = new type(N, "nothing" , "nothing");
    static move pound = new move("pound", 40.0, normal, ATK);
    static move grassNot = new move("Grass not", 40.0, grass, ATK);
    static move ember = new move("Ember", 40.0, fire, ATK);
    static move waterDrop = new move("Water Drop", 40.0, water, ATK);
    static move enrage = new move("Enrage", 1.3, normal, BATK);
    static move maxDef = new move("Max Defencee", 2.0, normal, BDEF);
    static move blank = new move("Attaque inofensivo", -999 , normal, ATK);
    static IVs IVbase = new IVs(101.0, 23, 20, 21);
    static IVs bossIv = new IVs(666.0,50,50,50);
    static pokemon charmander = new pokemon("charmander", IVbase, fire, ember, pound, enrage, blank);
    static pokemon squirtel = new pokemon("squirtel", IVbase, water, waterDrop, pound, maxDef, blank);
    static pokemon bulbasur = new pokemon("bulbasur", IVbase, grass, grassNot, pound, enrage, maxDef);
    static pokemon boss = new pokemon("boss", bossIv, fire, ember, pound, enrage, blank);
    static pokemon currentP = squirtel;      
    static Random bossMove = new Random();  

    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        

        
        
        boolean running = true;
        while(running){
            System.out.println(menu());
            System.out.println("Escribe pelear, cambiar o salir");
            select = sc.nextLine();

            if(currentP.getStatus()=="DEAD"){
                System.out.println("Tiene que cambiar de pokemon!!");
                System.out.println("se te ha muerto...");
                cambio();
            }
            else if(select.equals("pelear")){
                System.out.println("Para seleccionar un movimiento introduce el numero al que corresponde");
                System.out.println(movimientos());
                move = sc.nextInt();
                currentP.attack(boss, currentP.getMoves()[move]);
                boss.attack(currentP, boss.getMoves()[bossMove.nextInt(4)]);
            } 
            else if(select.equals("cambiar")) {
                cambio();
            }
            else if(select.equals("salir")){
                running = false;
            }
            
            
            
        }
    }
    static String movimientos() {
        
        String m = "0 = " + currentP.getMoves()[0].getMName() + "   1 = " + currentP.getMoves()[1].getMName() + "\n" +
                "2 = " + currentP.getMoves()[2].getMName() + "   3 = " + currentP.getMoves()[3].getMName();
        return m;
        
    }
    static String menu(){
        return "----------------------------------\n"+
        boss.getName() + " HP " + (int)boss.getStats()[0] + "/" + (int)boss.getMaxhp() + "\n"
        + boss.getType().getType() + "\n" + "\n"
        + currentP.getName() + " HP " + (int)currentP.getStats()[0] + "/" + (int)currentP.getMaxhp() + "\n"
        +"----------------------------------"
        ;
    }

    static void cambio(){
                System.out.println("selecciona un pokemon");
                System.out.println("charmander | bulbasur | squirtel");
                select = sc.nextLine();
                switch (select) {
                    case "charmander":
                        currentP = charmander;
                        break;
                    case "squirtel":
                        currentP = squirtel;
                        break;
                    case "bulbasur":
                        currentP = bulbasur;
                        break;
                }
    }
}
