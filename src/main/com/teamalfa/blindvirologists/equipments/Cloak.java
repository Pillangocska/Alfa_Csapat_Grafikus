package main.com.teamalfa.blindvirologists.equipments;

import main.com.teamalfa.blindvirologists.random.MyRandom;

import java.util.Random;

public class Cloak extends Equipment{
    MyRandom random;

    private final int protectionRate;

    public Cloak(){
        protectionRate = 823;
    }

    /**
     * Tells if the cloak protected the virologist from an infection.
     * @return true if it did, false if it did not.
     */
    public boolean protect(){
        if(MyRandom.getInstance().isYesOrNoDeterministic()) {
            return MyRandom.getInstance().getYesOrNo();
        }else {
            return new Random().nextInt(1001) < protectionRate;
        }
    }

    @Override
    public String getType() {
        return "Cloak";
    }
}
