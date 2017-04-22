/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanline;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author ricardo
 */
public class MetaDataPoligonos {
    public ArrayList<Point> coordenadas;
    public int numAresta;
    public int numVertice;
    
    public MetaDataPoligonos() {
        this.coordenadas = new ArrayList<Point>();
        this.numAresta = 0;
        this.numVertice = 0;
    }
}
