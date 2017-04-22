/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanline;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author afonso
 */
public class ScreenFrame extends JFrame{
    public ArrayList<Point> arestas;
//    Graphics g;
    BufferedImage img;
    Point pontoInicial;
    int erro = 5;
    private final MetaDataPoligonos polig;
    private boolean fechado;
    
    public ScreenFrame(int width, int height, MetaDataPoligonos polig){
        this.setSize(width, height);
        this.polig = polig;
        arestas = new ArrayList();
        img = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB );
        gerarImagem(width,height);
    }
    
    public boolean isFechado() {
        return fechado;
    }
    
    public void setFechado() {
        this.fechado = false;
    }
    
    //Verifica se o ponto está próximo o suficiente do original para fechar o polígono
    //erro atual: 5 px de raio
    private boolean aproxFechamento(Point inicialAproximado){
        int dx,dy;
        dx = Math.abs(pontoInicial.x - inicialAproximado.x);
        dy = Math.abs(pontoInicial.y - inicialAproximado.y);
        return dx <= 5 && dy <= 5;
    }
    
    //indexa as novas arestas na lista e desenha cada segmento
    public void IndexPoint(Point p){
        int i;
        
        this.polig.coordenadas.add(p);
        this.polig.numVertice++;
        
        if(arestas.size() >= 1){
            if(aproxFechamento(p)){                         //substitui ponto clicado pelo 1ro, fechando o polígono
                System.out.println("poligono fechado");
                p = pontoInicial;
                this.fechado = true;
            } else {
                this.fechado = false;
            }
            arestas.add(p);
            i = arestas.size() - 1;
            System.out.println("Lado:" +arestas.get(i-1)+" "+arestas.get(i));
            gerarAresta(arestas.get(i-1),arestas.get(i),0);
            paintComponents(this.polig.g);
            
        }else{
            System.out.println("primeira aresta encontrada");
            this.pontoInicial = p;
            arestas.add(p);
            this.polig.g = this.getGraphics();
            this.write_pixel(p.x, p.y, 0);
            this.paintComponents(this.polig.g);
        }
        System.out.println("Aresta Captada:" + p); 
    }

    @Override
    public void paintComponents(Graphics grphcs) {
        super.paintComponents(grphcs);
        this.polig.g.drawImage(img, WIDTH, WIDTH, this);
    }
    
    public void gerarImagem(int width, int height){
        for ( int rc = 0; rc < height; rc++ ) {
          for ( int cc = 0; cc < width; cc++ ) {
            // Set the pixel colour of the image n.b. x = cc, y = rc
            img.setRGB(cc, rc, Color.WHITE.getRGB());
          }//for cols
        }//for rows
    }
    
    private void write_pixel(int x, int y, int color){
        img.setRGB(x, y,Color.BLACK.getRGB() );
    }
    
    private void gerarAresta(Point p1, Point p2, int color){
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;
        
        this.polig.numAresta++;
        
        line(x1,y1,x2,y2,0);

    }
    // (precisa ser reescrito)
    public void line(int x,int y,int x2, int y2, int color) {
        int w = x2 - x ;
        int h = y2 - y ;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
        if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
        if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
        if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
        int longest = Math.abs(w) ;
        int shortest = Math.abs(h) ;
        if (!(longest>shortest)) {
            longest = Math.abs(h) ;
            shortest = Math.abs(w) ;
            if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
            dx2 = 0 ;            
        }
        int numerator = longest >> 1 ;
        for (int i=0;i<=longest;i++) {
            this.write_pixel(x, y, color);//putpixel(x,y,color) ;
            numerator += shortest ;
            if (!(numerator<longest)) {
                numerator -= longest ;
                x += dx1 ;
                y += dy1 ;
            } else {
                x += dx2 ;
                y += dy2 ;
            }
        }
    }
    
    //Adaptação do código da apostila para desenhar linhas
    //teste 2do octante
//    private void gerarAresta(Point p1, Point p2, int color){
//        int x1 = p1.x;
//        int y1 = p1.y;
//        int x2 = p2.x;
//        int y2 = p2.y;
//        int dx, dy, incE, incNE, d, x, y;
//        
//        dx = x2 - x1;
//        dy = y2 - y1;
//        System.out.println(p1 + " e " + p2);
//        if(y2 > x2){
//            System.out.println("2do quadrante");
//            d = 2 * dy - dx; /* Valor inicial de d */
//            incE = 2 * dx; /* Incremento de E */
//            incNE = 2 * (dx - dy); /* Incremento de NE */
//            x = x1;
//            y = y1;
//
//            g.drawLine(x,y,x,y);   //write_pixel(x, y, color);   //Não existe uma forma elegante para colorir um pixel diretamente
//            System.out.println("\t plotting: ("+x+","+y+")");
//            while (y < y2){
//                if (d <= 0){
//                    /* Escolhe E */
//                    d = d + incE;
//                    y = y + 1;
//                }else{
//                    /* Escolhe NE */
//                    d = d + incNE;
//                    x = x + 1;
//                    y = y + 1;
//                    }/* end if */
//                g.drawLine(x,y,x,y);                //write_pixel(x, y, color);
//                //System.out.println("\t plotting: ("+x+","+y+")");
//            }/* end while */
//        }else{
//            System.out.println("1ro quadrante");
//            d = 2 * dy - dx; /* Valor inicial de d */
//            incE = 2 * dy; /* Incremento de E */
//            incNE = 2 * (dy - dx); /* Incremento de NE */
//            x = x1;
//            y = y1;
//
//            write_pixel(x, y, color);   //Não existe uma forma elegante para colorir um pixel diretamente
//            System.out.println("\t plotting: ("+x+","+y+")");
//            while (x < x2){
//                if (d <= 0){
//                /* Escolhe E */
//                d = d + incE;
//                x = x + 1;
//                }else{
//                /* Escolhe NE */
//                d = d + incNE;
//                x = x + 1;
//                y = y + 1;
//                }/* end if */
//                write_pixel(x, y, color);
//                System.out.println("\t plotting: ("+x+","+y+")");
//            }/* end while */
//        }
//    }/* end inc_line */
}
