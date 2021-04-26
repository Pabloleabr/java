public class pokemontest {

    static final String F = "Fire";
    static final String W = "Water";
    static final String G = "Grass";
    static final String N = "Normal";
    static final String ATK = "ATK";
    static final String BDEF = "BDEF";
    static final String BATK = "BATK";
    static final String BSPD = "BSPD";
    public static void main(String[] args) {
        
        type fire = new type(F, W);
        type water = new type(W, G);
        type grass = new type(G, F);
        type normal = new type(N, "nothing");
        move pound = new move("pound", 40.0, normal, ATK);
        move grassNot = new move("Grass not", 40.0, grass, ATK);
        move ember = new move("Ember", 40.0, fire, ATK);
        move waterDrop = new move("Water Drop", 40.0, water, ATK);
        move enrage = new move("Enrage", 1.3, normal, BATK);
        move maxDef = new move("Max Defencee", 5.0, normal, BDEF);
        move blank = new move("", 0 , normal, ATK);
        IVs IVbase = new IVs(101.0, 23, 20, 21);
        pokemon charmander = new pokemon("charmander", IVbase, fire, ember, pound, enrage, blank);
        pokemon squirtel = new pokemon("squirtel", IVbase, water, waterDrop, pound, maxDef, blank);
        pokemon bulbasur = new pokemon("bulbasur", IVbase, grass, grassNot, pound, enrage, maxDef);

        System.out.println(charmander.getStats()[0]);
        squirtel.attack(charmander, squirtel.getMoves()[0]);
        System.out.println(charmander.getStats()[0]);
        squirtel.attack(charmander, squirtel.getMoves()[2]);
        System.out.println(squirtel.getStats()[2]);
        bulbasur.attack(charmander, bulbasur.getMoves()[2]);
        System.out.println(bulbasur.getStats()[1]);
    }
}
