
import java.awt.EventQueue;
import javax.swing.JFrame;


/**
 *
 * @author patrickmurrow
 */
public class EvolveApp {
    
    public static void main(String[] args) {
        System.out.println("Starting...");
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                System.out.println("Creating GUI...");
                JFrame frame = new SimulationFrame("Evolution");
                frame.setVisible(true);
                
            }
        });
    }
    
}
