/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanline;

import java.awt.Color;
import java.awt.Graphics;
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
    public Color cor;
    public Graphics g;
    
    public MetaDataPoligonos() {
        this.coordenadas = new ArrayList<Point>();
        this.numAresta = 0;
        this.numVertice = 0;
        this.cor = Color.BLACK;
    }
    
    public void reset() {
        this.coordenadas = new ArrayList<Point>();
        this.numAresta = 0;
        this.numVertice = 0;
        this.cor = Color.BLACK;
    }
}
