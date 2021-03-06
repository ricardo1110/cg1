package scanline;

import java.util.ArrayList;

/**
 *
 * @author ricardo
 */
public class Preenchimento {
    private final MetaDataPoligonos polig;
    
    public Preenchimento(MetaDataPoligonos polig) {
        this.polig = polig;
    }
    
    //Cria as arestas com as retas partindo da coordenada como menor valorde y
    private ArrayList<Aresta> criarArestas() {
        ArrayList<Aresta> arestasOrdenadas = new ArrayList<Aresta>();
        for(int i = 0; i < this.polig.coordenadas.size() - 1; i++) {
            if(this.polig.coordenadas.get(i).y < this.polig.coordenadas.get(i+1).y) {
                arestasOrdenadas.add(new Aresta(this.polig.coordenadas.get(i), this.polig.coordenadas.get(i+1)));
            } else {
                arestasOrdenadas.add(new Aresta(this.polig.coordenadas.get(i+1), this.polig.coordenadas.get(i)));
            }
        }
        return arestasOrdenadas;
    }

     
    public void preencher() {
        ArrayList<Aresta> arestasOrdenadas = this.criarArestas();
         
        //Ordena todos as arestas da menor para a maior coordenada y (Usando BubbleSort)
        Aresta buff;
        for(int i = 0; i < arestasOrdenadas.size() - 1; i++) {
            for(int j = 0; j < arestasOrdenadas.size() - 1; j++) {
                if(arestasOrdenadas.get(j).p1.y > arestasOrdenadas.get(j+1).p1.y) {
                    buff = new Aresta(arestasOrdenadas.get(j).p1, arestasOrdenadas.get(j).p2);
                    arestasOrdenadas.set(j, arestasOrdenadas.get(j+1));
                    arestasOrdenadas.set(j+1, buff);
                }  
            }
        }
        
        // Lista de todos os pontos em que ocorre um corte
        ArrayList<Integer> lista = new ArrayList<Integer>();      
        // Percorre as arestas do poligono
        for(int scanline = arestasOrdenadas.get(0).p1.y; scanline <= arestasOrdenadas.get(arestasOrdenadas.size()-1).p2.y; scanline++) {           
            lista.clear();
            
            // Percorre as arestas para verificar em quais coordenadas X as linhas de preenchimento serao desenhadas
            for(int i = 0; i < arestasOrdenadas.size(); i++) {   
                if(scanline == arestasOrdenadas.get(i).p1.y && scanline != arestasOrdenadas.get(i).p2.y) { //Inicio da linha
                    arestasOrdenadas.get(i).setXIni();
                    continue;
                }
                
                if(scanline == arestasOrdenadas.get(i).p2.y) { //Fim da linha
                    arestasOrdenadas.get(i).setXFim();
                    lista.add((int) arestasOrdenadas.get(i).curX);
                    continue;
                }
                
                if(scanline > arestasOrdenadas.get(i).p1.y && scanline < arestasOrdenadas.get(i).p2.y) { //Meio da linha
                    arestasOrdenadas.get(i).atualizar();
                    lista.add((int) arestasOrdenadas.get(i).curX);
                }
                
            }
            
            // Ordenando as coordenadas X da menor para a maior
            int buffInt;
            for (int i = 0; i < lista.size(); i ++) {
                for(int j = 0; j < lista.size() - 1; j++) {
                    if(lista.get(j) > lista.get(j+1)) {
                        buffInt = lista.get(j);
                        lista.set(j, lista.get(j+1));
                        lista.set(j+1, buffInt);
                    }
                }
            }
            
            this.polig.g.setColor(this.polig.cor);
             
            // Preenche o poligono desenhando as linhas horizontais de x[i] a x[i+2]
            for(int i = 0; i < lista.size()-1; i+=2) {
                this.polig.g.drawLine(lista.get(i), scanline, lista.get(i+1), scanline);
            }
        }
    }
}