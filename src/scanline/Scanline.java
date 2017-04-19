/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanline;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author afonso
 */
public class Scanline extends JPanel{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new ScreenFrame();
        MouseListener mouse = new MouseAction();
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frame.getContentPane().add(new Scanline());
        frame.addMouseListener(mouse);
        frame.setVisible(true);
    }    
}
