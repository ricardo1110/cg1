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
import javax.swing.JFrame;

/**
 *
 * @author afonso
 */
public class ScreenFrame extends JFrame{
    ArrayList<Point> arestas;
    Point currentPoint;
    Graphics g;
    public ScreenFrame(){
        arestas = new ArrayList();
        currentPoint = new Point(0,0);
    }
    public void IndexPoint(Point p){
        arestas.add(p);
        this.currentPoint = p;
        System.out.println("Arestas Captadas" + arestas.toString());
        g = this.getGraphics();
        this.paint(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.BLACK);
        for(int i = 1; i < arestas.size(); i++){
            Point p1 = arestas.get(i-1),p2 = arestas.get(i);
            
            System.out.println("Gerando aresta entre: "+p1+" e "+p2);
            //ajustando coordenadas p/ algoritmo
            if(p2.x < p1.x && p2.y < p1.y){ //reta orentada para cima e esquerda
                Point pc = p1;
                p1 = p2;
                p2 = pc;
                System.out.println("reta orentada para cima e esquerda");
            }else if(p2.x > p1.x && p2.y < p1.y){ //reta orentada para cima e direita
                
                p2.setLocation(p2.y, p2.x);
                p1.setLocation(p1.y, p1.x);
                
                System.out.println("reta orentada para cima e direita");
            }else if(p2.x < p1.x && p2.y > p1.y){ //reta orentada para baixo e esquerda
                p2.setLocation(p2.y, p2.x);
                p1.setLocation(p1.y, p1.x);
                
                System.out.println("reta orentada para baixo e esquerda");
            }else{
                System.out.println("reta orentada para baixo e direita");
            }
            
            gerarAresta(p1,p2,0);  //sem sensibilidade a cores no momento
            
            
        }
              
    }
    
    //Adaptação do código da apostila para desenhar linhas
    //teste 2do octante
    private void gerarAresta(Point p1, Point p2, int color){
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;
        int dx, dy, incE, incNE, d, x, y;
        
        dx = x2 - x1;
        dy = y2 - y1;
        
        if(y2 > x2){
        
            d = 2 * dy - dx; /* Valor inicial de d */
            incE = 2 * dx; /* Incremento de E */
            incNE = 2 * (dx - dy); /* Incremento de NE */
            x = x1;
            y = y1;

            g.drawLine(x,y,x,y);   //write_pixel(x, y, color);   //Não existe uma forma elegante para colorir um pixel diretamente
            System.out.println("\t plotting: ("+x+","+y+")");
            while (y < y2){
                if (d <= 0){
                    /* Escolhe E */
                    d = d + incE;
                    y = y + 1;
                }else{
                    /* Escolhe NE */
                    d = d + incNE;
                    x = x + 1;
                    y = y + 1;
                    }/* end if */
                g.drawLine(x,y,x,y);                //write_pixel(x, y, color);
                //System.out.println("\t plotting: ("+x+","+y+")");
            }/* end while */
        }else{
            d = 2 * dy - dx; /* Valor inicial de d */
            incE = 2 * dy; /* Incremento de E */
            incNE = 2 * (dy - dx); /* Incremento de NE */
            x = x1;
            y = y1;

            g.drawLine(x,y,x,y);   //write_pixel(x, y, color);   //Não existe uma forma elegante para colorir um pixel diretamente
            System.out.println("\t plotting: ("+x+","+y+")");
            while (x < x2){
                if (d <= 0){
                /* Escolhe E */
                d = d + incE;
                x = x + 1;
                }else{
                /* Escolhe NE */
                d = d + incNE;
                x = x + 1;
                y = y + 1;
                }/* end if */
                g.drawLine(x,y,x,y);                //write_pixel(x, y, color);
                //System.out.println("\t plotting: ("+x+","+y+")");
            }/* end while */
        }
    }/* end inc_line */
    
    
    //Parcela p/ 1ro octante
    /*/Adaptação do código da apostila para desenhar linhas
    private void gerarAresta(Point p1, Point p2, int color){
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;
        int dx, dy, incE, incNE, d, x, y;
        
        dx = x2 - x1;
        dy = y2 - y1;
        d = 2 * dy - dx; /* Valor inicial de d 
        incE = 2 * dy; /* Incremento de E 
        incNE = 2 * (dy - dx); /* Incremento de NE 
        x = x1;
        y = y1;
        
        g.drawLine(x,y,x,y);   //write_pixel(x, y, color);   //Não existe uma forma elegante para colorir um pixel diretamente
        System.out.println("\t plotting: ("+x+","+y+")");
        while (x < x2){
            if (d <= 0){
            /* Escolhe E 
            d = d + incE;
            x = x + 1;
            }else{
            /* Escolhe NE 
            d = d + incNE;
            x = x + 1;
            y = y + 1;
            }/* end if 
            g.drawLine(x,y,x,y);                //write_pixel(x, y, color);
            //System.out.println("\t plotting: ("+x+","+y+")");
        }/* end while 
    }/* end inc_line */
}
