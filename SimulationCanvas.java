
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author patrickmurrow
 */
public class SimulationCanvas extends JPanel{
    private int dimH = 700;
    private int dimW = 700;
    private ArrayList<Specimen> strawberries = new ArrayList<Specimen>();
    private ArrayList<Specimen> mushrooms = new ArrayList<Specimen>();
    private ArrayList<Specimen> creatures = new ArrayList<Specimen>();
    private ArrayList<Specimen> monsters = new ArrayList<Specimen>();
    private int moveCount = 0;
    
    public SimulationCanvas(){
        Specimen c1 = new Creature(this);
        c1.setCreat(3);
        c1.setStraw(0);
        c1.setMush(3);
        c1.setMonst(1);
        c1.setEat(true);
        c1.setEatM(false);
        creatures.add(c1);
        
        //create creatures
        for (int i = 15; i >0; i--){
            Specimen c = new Creature(this);
            creatures.add(c);
        }
        //create strawberries
        for (int i = 30; i >0; i--){
            Specimen s1 = new Food(true, this);
            strawberries.add(s1);
        }
        //create mushrooms
        for (int i = 20; i >0; i--){
            Specimen m = new Food(false, this);
            mushrooms.add(m);
        }//create monsters
        for (int i = 7; i >0; i--){
            Specimen m = new Monster(this);
            monsters.add(m);
        }
        for(Specimen c : creatures){
            System.out.println(c.getStraw() + " " + c.getMush() + " " + c.getCreat() + " " + c.getMonst());
        }
    }
    
    
        
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(dimH, dimW);
    }
    
    /* Evolves the population by picking 6 parents of which 3 children are
    made from*/
    public void evolve() {
        ArrayList<Specimen> parents = new ArrayList<Specimen>();
        //tournament selection
        for (int i = 0; i < 6 /*creatures.size()*/; i++) {
            int temp = 0;
            Specimen high = null;
            for (Specimen c : creatures) {
                if (!parents.contains(c)) {
                   if (c.getEnergyValue() > temp){
                       temp = c.getEnergyValue();
                       high = c;
                   }
                }
            }
            parents.add(high);
        }   
        System.out.println("--------");
        int size = ((parents.size() % 2 == 0) ? parents.size() : parents.size() - 1);
        Random randomNum = new Random();
        //crossover
        for (int i = 0; i < size; i += 2) {
            Specimen parent1 = parents.get(i);
            Specimen parent2 = parents.get(i+1);
            Specimen child = new Creature(this);
            child.setEat((randomNum.nextInt(2) == 0) ? parent2.getEat() : parent1.getEat());
            child.setEatM((randomNum.nextInt(2) == 0) ? parent2.getEatM() : parent1.getEatM());
            child.setCreat((randomNum.nextInt(2) == 0) ? parent2.getCreat() : parent1.getCreat());
            child.setMonst((randomNum.nextInt(2) == 0) ? parent2.getMonst() : parent1.getMonst());
            child.setStraw((randomNum.nextInt(2) == 0) ? parent2.getStraw() : parent1.getStraw());
            child.setMush((randomNum.nextInt(2) == 0) ? parent2.getMush() : parent1.getMush());
            child.setDef((randomNum.nextInt(2) == 0) ? parent2.getDef() : parent1.getDef());
            child.setEnergyValue((parent2.getEnergyValue() + parent1.getEnergyValue())/2);
            //mutation
            if ((randomNum.nextInt(10) == 0)) {
                child.setCreat((int) (Math.random() * 4));
            }
            if ((randomNum.nextInt(10) == 0)) {
                child.setStraw((int) (Math.random() * 4));
            }
            if ((randomNum.nextInt(10) == 0)) {
                child.setMonst((int) (Math.random() * 4));
            }
            if ((randomNum.nextInt(10) == 0)) {
                child.setMush((int) (Math.random() * 4));
            }
            creatures.add(child);
            System.out.println(child.getStraw() + " " + child.getMush() + " " + child.getCreat() + " " + child.getMonst() + "---child---");
            }
        
            //kills off old creatures
            for (final Iterator iterator = creatures.iterator(); iterator.hasNext();) {
            Specimen s = (Creature)iterator.next();
            if (s.getAge() > 600 && (randomNum.nextInt(3) == 0)) {
                iterator.remove();
                System.out.println("killed off");

            }
        }   
    }
    
    @Override
    public void paintComponent (final Graphics g) {
        
        setBackground(Color.black);
        int max = dimH - 5;
        int min = 5;
        
        //draws strawberries in strawberries array
        for (Specimen s : strawberries){
            g.setColor(Color.GREEN);
            g.fillOval(s.getxValue(), s.getyValue(), s.getEnergyValue()/5, s.getEnergyValue()/5);
        }
        //draws mushrooms in mushrooms array
        for (Specimen m : mushrooms) {
            g.setColor(Color.RED);
            g.fillOval(m.getxValue(), m.getyValue(), abs(m.getEnergyValue() / 5), abs(m.getEnergyValue()) / 5);
        }
        //draws creatures in creatures array
        double totalFitness = 0;
        for (Specimen c : creatures) {
            totalFitness += c.getEnergyValue();
            g.setColor(Color.PINK);
            g.fillRect(c.getxValue(), c.getyValue(), 7, 7);
            if (moveCount % 40 == 0) {
                c.act();
            }
        }

        //draws monsters in monsters array
        for (Specimen m : monsters){
            g.setColor(Color.blue);
            g.fillRect(m.getxValue(), m.getyValue(), 7, 7);
            if (moveCount % 40 == 0) {
                m.hunt();
            }
        }
        //removes creatures if they have no energy left
        for (final Iterator iterator = creatures.iterator(); iterator.hasNext();) {
            Specimen s = (Creature)iterator.next();
            if (s.getEnergyValue() <= 0) {
                iterator.remove();
            }
        }
        
        moveCount++;
        
        if (moveCount%5000==0){
            evolve();
        }
        repaint();        
    }

    void removeStraw(Specimen straw) {
        strawberries.remove(straw);
        strawberries.add(new Food(true, this));
    }

    void removeCreature(Specimen creature) {
        creatures.remove(creature);
    }

    void removeMush(Specimen mushroom) {
        mushrooms.remove(mushroom);
        mushrooms.add(new Food(true, this));
    }

    public int getMoveCount() {
        return moveCount;
    }
    
    public int getDimH() {
        return dimH;
    }

    public void setDimH(int dimH) {
        this.dimH = dimH;
    }

    public int getDimW() {
        return dimW;
    }

    public void setDimW(int dimW) {
        this.dimW = dimW;
    }

    public ArrayList<Specimen> getStrawberries() {
        return strawberries;
    }

    public void setStrawberries(ArrayList<Specimen> strawberries) {
        this.strawberries = strawberries;
    }

    public ArrayList<Specimen> getMushrooms() {
        return mushrooms;
    }

    public void setMushrooms(ArrayList<Specimen> mushrooms) {
        this.mushrooms = mushrooms;
    }

    public ArrayList<Specimen> getCreatures() {
        return creatures;
    }

    public void setCreatures(ArrayList<Specimen> creatures) {
        this.creatures = creatures;
    }

    public ArrayList<Specimen> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Specimen> monsters) {
        this.monsters = monsters;
    }
  
}
