/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanline;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author afonso
 */
public class MouseAction implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {
        ScreenFrame s = (ScreenFrame) e.getSource();
        s.IndexPoint(e.getPoint());
        System.out.println(e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
}
