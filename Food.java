/**
 *
 * @author patrickmurrow
 */
public class Food implements Specimen{
    int xValue;
    int yValue;
    int energy_value;
    Boolean strawCheck;

    public Food() {
    }

    public Food(Boolean sCheck, SimulationCanvas can) {
        strawCheck = sCheck;
        int max = can.getDimH() - 20;
        int min = 20;
        xValue = (int) ((Math.random()*((max - min) + 1)));
        yValue = (int) ((Math.random()*((max - min) + 1)));
        if (strawCheck){
            energy_value = (int)(Math.random()*(70 -40) + 40);
        }
        else{
            energy_value = -1*((int)(Math.random()*(70 - 40) +40));
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
        return energy_value;
    }

    @Override
    public void setEnergyValue(int energy_value) {
        this.energy_value = energy_value;
    }

    public Boolean isStrawCheck() {
        return strawCheck;
    }

    public void setStrawCheck(Boolean strawCheck) {
        this.strawCheck = strawCheck;
    }

    @Override
    public void act() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void hunt() {
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
