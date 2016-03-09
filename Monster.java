
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
/**
 *
 * @author patrickmurrow
 */
public class Monster implements Specimen{

    private int xValue;
    private int yValue;
    private SimulationCanvas canvas;
    private int count;

    public Monster() {
    }

    public Monster(SimulationCanvas canvas) {
        int max = canvas.getDimH() - 15;
        int min = 15;
        this.xValue = (int) ((Math.random()*max) + min);
        this.yValue = (int) ((Math.random()*max) + min);
        this.canvas = canvas;
    }
    /* Updates x and y values to be closer to the closest creature*/ 
    @Override
    public void hunt() {
        count++;
        double shortDist = canvas.getDimH() + 10;
        Specimen closeCreature = null;
        for (Specimen c : canvas.getCreatures()) {
            double tempDist = sqrt((abs(xValue - c.getxValue()) ^ 2) + (abs(yValue - c.getyValue()) ^ 2));
            if (tempDist < shortDist) {
                shortDist = tempDist;
                closeCreature = c;
            }
        }
        if (count % 2 == 0) {

            if (xValue < closeCreature.getxValue()) {
                xValue++;
            } else if (xValue > closeCreature.getxValue()) {
                xValue--;
            }
            if (yValue < closeCreature.getyValue()) {
                yValue++;
            } else if (yValue > closeCreature.getyValue()) {
                yValue--;
            }
        }
        if (sqrt((abs(xValue - closeCreature.getxValue()) ^ 2) + (abs(yValue - closeCreature.getyValue()) ^ 2))<3) {
            canvas.removeCreature(closeCreature);
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
    public void act() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int getEnergyValue() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setStraw(int i) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setMush(int i) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setCreat(int i) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setMonst(int i) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int getCreat() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int getMonst() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getStraw() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int getMush() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setEnergyValue(int i) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setDef(int i) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int getDef() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setEat(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean getEat() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setEatM(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean getEatM() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int getAge() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
