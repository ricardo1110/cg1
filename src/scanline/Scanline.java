/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanline;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author afonso
 */
public class Scanline extends JPanel{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetaDataPoligonos polig = new MetaDataPoligonos();
        Graphics g = null;
        
        MouseListener mouse = new MouseAction();
        JFrame frame = new ScreenFrame(800, 400, polig);
        frame.getContentPane().add(new Scanline());
        frame.addMouseListener(mouse);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frame.setTitle("Janela Principal - Desenhe o poligono");
        
        //Muda o Look and Feel do Java antes de tornar algo visivel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Error ex) {} catch (ClassNotFoundException ex) {
            Logger.getLogger(Scanline.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Scanline.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Scanline.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Scanline.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        ButtonWindow buttonScreen = new ButtonWindow(polig, (ScreenFrame) frame);       
        buttonScreen.setVisible(true);
        
        
        frame.setLocation(buttonScreen.getX() + buttonScreen.getWidth(), buttonScreen.getY());
        frame.setVisible(true);
    }    
}
