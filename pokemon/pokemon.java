

public class pokemon {
    String nombre;
    type type;
    move[] moves;
    IVs ivs;
    double[] tempStats;
    String status;

    pokemon(String name,IVs pIvs, type pType, move move1, move move2, move move3, move move4){
        nombre = name;
        type = pType;
        moves = new move[]{move1, move2, move3, move4};
        ivs = pIvs;
        tempStats = new double[] {ivs.getHp(), ivs.getAtk(), ivs.getDef(), ivs.getSpd()};
        status = "ALIVE";
    }
    
    double getMaxhp(){
        return ivs.getHp();
    }
    
    String getName(){
        return nombre;
    }

    double[] getStats(){
        return tempStats;
    }

    String getStatus(){
        return status;
    }

    type getType(){
        return type;
    }

    move[] getMoves(){
        return moves;
    }
     
    void resetStats(){
        tempStats = new double[] {ivs.getHp(), ivs.getAtk(), ivs.getDef(), ivs.getSpd()};
    }

    void takeDamage(double d){
        tempStats[0] -= d;
    }
    
    void statBAtk(double p){
        tempStats[1] *= p;
    }

    void statBDef(double p){
        tempStats[2] *= p;
    }

    void statBSpd(double p){
        tempStats[3] *= p;
    }

    move selectMove(int index){
        return moves[index];
    }

    void attack(pokemon p2, move m){
        double power = m.getMPower();
        if (m.getAD() == "ATK"){
            double damage = power + this.tempStats[1] - p2.getStats()[2];
            if (damage < 1.0){
                damage = 1.0;
            }
            if(p2.getType().checkWeakness(m.getMtype())){
                damage *= 2;
            }

            p2.takeDamage(damage);

            if (p2.getStats()[0]< 0){
                p2.getStats()[0] = 0;
                p2.status = "DEAD";
            }
        }
        else{
            switch (m.getAD()) {
                case "BDEF":
                    this.statBDef(power);
                    break;
                case "BATK":
                    this.statBAtk(power);
                    break;
                case "BSPD":
                    this.statBSpd(power);
                    break;
            }
        }
    }
}

class IVs{
    double hp;
    double atk;
    double def;
    double spd;
    IVs(double ihp,double iatk,double idef,double ispd){
        hp = ihp;
        atk = iatk;
        def = idef;
        spd = ispd;
    }

    double getHp(){
        return hp;
    }
    double getAtk(){
        return atk;
    }
    double getDef(){
        return def;
    }
    double getSpd(){
        return spd;
    }
}

class type{
    String pType;
    String weak;
    type(String type, String weakness){
        pType = type;
        weak = weakness;
    }

    String getType(){
        return pType;
    }

    String getWeak(){
        return weak;
    }

    boolean checkWeakness(type opType){
        if(this.getWeak() == opType.getType()){
            return true;
        }
        else{
            return false;
        }
        
    }
}

class move{
    String mName;
    double mPower;
    type type;
    String AD;
    move(String name, double power, type mType, String AorD){
        mName = name;
        mPower = power;
        type = mType;
        AD = AorD;
    }
    String getMName(){
        return mName;
    }
    double getMPower(){
        return mPower;
    }
    type getMtype(){
        return type;
    }
    String getAD(){
        return AD;
    }
}