/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patrickmurrow
 */
public interface Specimen {
    int getxValue();
    int getyValue();
    void act();
    void hunt();
    int getEnergyValue();
    void setEnergyValue(int i);
    void setStraw(int i);
    void setMush(int i);
    void setCreat(int i);
    void setMonst(int i);
    void setDef(int i);
    void setEat(boolean b);
    void setEatM(boolean b);

    public boolean getEatM();

    public boolean getEat();
    
    public int getCreat();

    public int getMonst();

    public int getStraw();

    public int getMush();

    public int getDef();

    public int getAge();
}
