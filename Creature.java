
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import java.util.Random;

/**
 *
 * @author patrickmurrow
 */
public class Creature implements Specimen{
    private int age;
    private int xValue;
    private int yValue;
    private int energy_level;
    private SimulationCanvas canvas;
    private boolean eat;
    private boolean eatM;
    private int straw;
    private int mush;
    private int monst;
    private int creat;
    private int def;
    private boolean isStraw;
    private boolean isMush;
    private boolean isCreature;
    private boolean isMonster;
    private int max;
    private int min;
    private int xDir;
    private int yDir;

    public Creature() {
    }

    //gives initial random genetic makeup - will be changed if is a child
    public Creature(SimulationCanvas canvas) {
        Random randomNum = new Random();
        this.canvas = canvas;
        this.max = canvas.getDimH() - 15;
        this.min = 15;
        this.xValue = (int) ((Math.random()*max) + min);
        this.yValue = (int) ((Math.random()*max) + min);
        this.energy_level = 300 + (int)(Math.random() * 20);
        this.eat = (randomNum.nextInt(2) == 0);
        this.straw = (int) (Math.random() * 4);
        this.mush = (int) (Math.random() * 4);
        this.monst = (int) (Math.random() * 4);
        this.creat = (int) (Math.random() * 4); 
        this.def = (int) (Math.random() * 5); 
        this.xDir = (int) ((Math.random()*max) + min);
        this.yDir = (int) ((Math.random()*max) + min);
    }

    /* Performed by a creature every step*/
    @Override
    public void act() {
        age++;
            Specimen close = findClosest();
            if (close != null) {
                int act = findAction();
                if (act == 0) {
                    towards(close);
                } else if (act == 1) {
                    away(close);
                } else if (act == 2) {
                    random(close);
                }
            } else {
                defaultMove(close);
   //         }
        }
    }

    /* Finds the closest object to the current creature*/
    public Specimen findClosest() {
        double shortDist = canvas.getDimH() + 10;
        Specimen shortest = null;
        int maxDist = 12;
        if (straw != 3) {
            for (Specimen s : canvas.getStrawberries()) {
                double tempDist = sqrt((abs(xValue - s.getxValue()) ^ 2) + (abs(yValue - s.getyValue()) ^ 2));
                if (tempDist < shortDist && tempDist < maxDist) {
                    shortDist = tempDist;
                    shortest = s;
                    isStraw = true;
                    isMush = false;
                    isCreature = false;
                    isMonster = false;
                }
            }
        }
        if (mush != 3) {
            for (Specimen m : canvas.getMushrooms()) {
                double tempDist = sqrt((abs(xValue - m.getxValue()) ^ 2) + (abs(yValue - m.getyValue()) ^ 2));
                if (tempDist < shortDist && tempDist < maxDist) {
                    shortDist = tempDist;
                    shortest = m;
                    isStraw = false;
                    isMush = true;
                    isCreature = false;
                    isMonster = false;
                }
            }
        }
        if (creat != 3) {
            for (Specimen c : canvas.getCreatures()) {
                double tempDist = sqrt((abs(xValue - c.getxValue()) ^ 2) + (abs(yValue - c.getyValue()) ^ 2));
                if (tempDist < shortDist && c != this && tempDist < maxDist) {
                    shortDist = tempDist;
                    shortest = c;
                    isStraw = false;
                    isMush = false;
                    isCreature = true;
                    isMonster = false;
                }
            }
        }
        if (monst != 3) {
            for (Specimen m : canvas.getMonsters()) {
                double tempDist = sqrt((abs(xValue - m.getxValue()) ^ 2) + (abs(yValue - m.getyValue()) ^ 2));
                if (tempDist < shortDist && tempDist < maxDist) {
                    shortDist = tempDist;
                    shortest = (Specimen) m;
                    isStraw = false;
                    isMush = false;
                    isCreature = false;
                    isMonster = true;
                }
            }
        }
        return shortest;
    }
    
    /* Finds what action is to be performed based on which 
    object is closest*/
    public int findAction(){
        if (isMonster){
            return monst;
        }
        if (isCreature){
            return creat;
        }
        if (isMush){
            return mush;
        }
        if (isStraw){
            return straw;
        }
        return 0;
    }
    
    /* Increments the x and y values to be closer to the x and y values
    of the closest object*/
    public void towards(Specimen spec) {
        if (xValue < spec.getxValue()) {
            xValue += 1;
        } else if (xValue > spec.getxValue()) {
            xValue -= 1;           
        }
        if (yValue < spec.getyValue()) {
            yValue += 1;
        } else if (yValue > spec.getyValue()) {
            yValue -= 1;
        }
        if ((isStraw || isMush) && xValue == spec.getxValue() && yValue == spec.getyValue()) {
            energy_level += spec.getEnergyValue();
            if (isStraw) {
                energy_level += spec.getEnergyValue();
                canvas.removeStraw(spec);
            }
            else if (isMush){
                energy_level += spec.getEnergyValue();
                canvas.removeMush(spec);
            }
        }
        energy_level--;
    }
    /* Increments the x and y values to be further away from the x and y values
    of the closest object*/
    public void away(Specimen spec) {
        Random randomNum = new Random();
        if (xValue < spec.getxValue()) {
            xValue -= 1;
        } else if (xValue > spec.getxValue()) {
            xValue += 1;
        }
        if (yValue < spec.getyValue()) {
            yValue -= 1;
        } else if (yValue > spec.getyValue()) {
            yValue += 1;
        }
        if (xValue < 0){
            xValue = canvas.getDimW()-2;
        } else if (xValue > canvas.getDimW()){
            xValue = 2;
        }
        if (yValue < 0){
            yValue = canvas.getDimH()-2;
        } else if (yValue > canvas.getDimW()){
            yValue = 2;
        }
    
        energy_level--;
    }
    
    /* Selects a random position in the canvas and goes towards it*/
    public void random(Specimen spec){
        if (spec != null) {
            if (sqrt((abs(xValue - spec.getxValue()) ^ 2) + (abs(yValue - spec.getyValue()) ^ 2)) < 4 && eat && isStraw) {
                towards(spec);
            } else if (sqrt((abs(xValue - spec.getxValue()) ^ 2) + (abs(yValue - spec.getyValue()) ^ 2)) < 4 && eatM && isMush) {
                towards(spec);
            }
        }
        if (canvas.getMoveCount() % 2000 == 0){
            xDir = (int) ((Math.random()*max) + min);
            yDir = (int) ((Math.random()*max) + min);
        }
        if (xValue < xDir) {
            xValue += 1;
        } else if (xValue > xDir) {
            xValue -= 1;           
        }
        if (yValue < yDir) {
            yValue += 1;
        } else if (yValue > yDir) {
            yValue -= 1;
        }
        energy_level--;
    }
    /* Increments the x/y values based on the genetic default move*/
    public void defaultMove(Specimen spec) {
        if(def == 0){
            xValue++;
        } else if (def == 1){
            xValue--;
        }
        else if (def == 2){
            yValue++;
        }
        else if (def == 3){
            yValue--;
        }
        else {
            random(spec);
        }
        if (xValue < 0){
            xValue = canvas.getDimW()-2;
        } else if (xValue > canvas.getDimW()){
            xValue = 2;
        }
        if (yValue < 0){
            yValue = canvas.getDimH()-2;
        } else if (yValue > canvas.getDimW()){
            yValue = 2;
        }
    }

    @Override
    public int getxValue() {
        return xValue;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    @Override
    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }

    @Override
    public int getEnergyValue() {
        return energy_level;
    }

    @Override
    public void setEnergyValue(int energy_level) {
        this.energy_level = energy_level;
    }

    @Override
    public int getStraw() {
        return straw;
    }

    @Override
    public void setStraw(int straw) {
        this.straw = straw;
    }

    @Override
    public int getMush() {
        return mush;
    }

    @Override
    public void setMush(int mush) {
        this.mush = mush;
    }

    @Override
    public int getMonst() {
        return monst;
    }

    @Override
    public void setMonst(int monst) {
        this.monst = monst;
    }

    @Override
    public int getCreat() {
        return creat;
    }

    @Override
    public void setCreat(int creat) {
        this.creat = creat;
    }

    @Override
    public int getDef() {
        return def;
    }

    @Override
    public void setDef(int def) {
        this.def = def;
    }

    @Override
    public boolean getEat() {
        return eat;
    }

    @Override
    public void setEat(boolean eat) {
        this.eat = eat;
    }

    @Override
    public boolean getEatM() {
        return eatM;
    }

    @Override
    public void setEatM(boolean eatM) {
        this.eatM = eatM;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void hunt() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
  
}
