/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanline;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.JButton;
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
        MetaDataPoligonos polig = new MetaDataPoligonos();
        Graphics g = null;
        
        MouseListener mouse = new MouseAction();
        JFrame frame = new ScreenFrame(800, 400, polig);
        frame.getContentPane().add(new Scanline());
        frame.addMouseListener(mouse);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frame.setVisible(true);
        
        ButtonWindow buttonScreen = new ButtonWindow(polig, (ScreenFrame) frame);
        buttonScreen.setVisible(true);
        JButton fecharPolig = new JButton("Fechar Poligono"); //facilitar fechamento, no momento: usa aprox. ao ponto inicial (10px)
        JButton mudaCor = new JButton("Mudar Cor");            //escolher outras cores
    }    
}
