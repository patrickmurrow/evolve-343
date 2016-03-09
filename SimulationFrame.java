
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author patrickmurrow
 */
public class SimulationFrame extends JFrame {
    SimulationCanvas canvas;
   
    public SimulationFrame(String titleIn){
        setTitle(titleIn);
        setLayout(new GridLayout());
        
        canvas = new SimulationCanvas();
        add(canvas, BorderLayout.CENTER);        
        pack();
    }
    
    
}
