package scanline;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Preenchimento {
    private final ArrayList<Point> polygon; 
    private final MetaDataPoligonos polig;
    
    public Preenchimento(MetaDataPoligonos polig) {
        this.polig = polig;
        this.polygon = polig.coordenadas;
    }
    
    public ArrayList<Aresta> createEdges() {
        ArrayList<Aresta> sortedEdges = new ArrayList<Aresta>();
        for (int i = 0; i < polygon.size() - 1; i++) {
            if (polygon.get(i).y < polygon.get(i+1).y) {
                sortedEdges.add(new Aresta(polygon.get(i), polygon.get(i+1)));
            } else {
                sortedEdges.add(new Aresta(polygon.get(i+1), polygon.get(i)));
            }
        }
        return sortedEdges;
    }

     
    public void fill() {
        ArrayList<Aresta> sortedEdges = this.createEdges();
         
        //Ordena todos as arestas da menor para a maior coordenada y (Usando BubbleSort mesmo por enquanto :/)
        Aresta tmp;
        for (int i = 0; i < sortedEdges.size() - 1; i++) {
            for (int j = 0; j < sortedEdges.size() - 1; j++) {
                if (sortedEdges.get(j).p1.y > sortedEdges.get(j+1).p1.y) {
                    tmp = new Aresta(sortedEdges.get(j).p1, sortedEdges.get(j).p2);
                    sortedEdges.set(j, sortedEdges.get(j+1));
                    sortedEdges.set(j+1, tmp);
                }  
            }
        }
        
        // Lista de todos os pontos em que ocorre um corte
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        // Percorre as linhas, da menor para a maior
        for (int scanline = sortedEdges.get(0).p1.y; scanline <= sortedEdges.get(sortedEdges.size()-1).p2.y; scanline++) {           
            list.clear();
            
            // Percorre todas as arestas para verificar qual e cortada pela linha
            for (int i = 0; i < sortedEdges.size(); i++) {   
                if (scanline == sortedEdges.get(i).p1.y) {
                    if (scanline == sortedEdges.get(i).p2.y) {
                        sortedEdges.get(i).desmarcar();
                        list.add((int) sortedEdges.get(i).curX);
                    } else {
                        sortedEdges.get(i).marcar();
                    }
                }
                
                if (scanline == sortedEdges.get(i).p2.y) {
                    sortedEdges.get(i).desmarcar();
                    list.add((int) sortedEdges.get(i).curX);
                }
                
                // Intersecçao da scanline com uma aresta
                if (scanline > sortedEdges.get(i).p1.y && scanline < sortedEdges.get(i).p2.y) {
                    sortedEdges.get(i).atualizar();
                    list.add((int) sortedEdges.get(i).curX);
                }
                
            }
            
            // Ordenando as coordenadas X da menor para a maior
            int swaptmp;
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size() - 1; j++) {
                    if (list.get(j) > list.get(j+1)) {
                        swaptmp = list.get(j);
                        list.set(j, list.get(j+1));
                        list.set(j+1, swaptmp);
                    }
                }
            }
            
            this.polig.g.setColor(this.polig.cor);

            if (list.size() < 2 || list.size() % 2 != 0) {
                System.out.println("This should never happen!");
                continue;
            }
             
            // Preenche o poligono desenhando as linhas horizontais
            for (int i = 0; i < list.size(); i+=2) {
                this.polig.g.drawLine(list.get(i), scanline, list.get(i+1), scanline);
            }
        }
    }
}