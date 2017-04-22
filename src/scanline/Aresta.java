package scanline;

import java.awt.Point;
/**
 *
 * @author ricardo
 */

//Esta classe e usada para guardar as arestas a fim de facilitar o preenchimento do poligono
public class Aresta {
    
    public Point p1;        // Ponto de partida
    public Point p2;        // Ponto de Chegada
    float m;                // Inclinaçao
    float curX;             // A coordenada de intersecçao com a linha sendo escaneda
    
    public Aresta(Point inicio, Point fim) {
        p1 = new Point(inicio);
        p2 = new Point(fim);
        
        m = (float)((float)(p1.y - p2.y) / (float)(p1.x - p2.x));
    }
    
    public void marcar() {
        curX = p1.x;
    }
    
    public void desmarcar()
    {
        curX = p2.x;
    }
    
    public void atualizar() {
        curX += (float)((float)1/(float)m);
    }
    
}
