package com.gLstudios.IA;

// importando classes de Lista encadeada do Java
import java.util.LinkedList;
import com.gLstudios.world.World;

public class Grafo {					

	Nodo g[][] = new Nodo[World.WIDTH][World.HEIGHT];		// Matriz que representa o grafo	

	public int xInicio, yInicio;							// Coordenadas de início 
	public int xFim, yFim;									// Coordenadas de fim 
	public int menor = 9999999;								// Menor distância percorrida
	
	public LinkedList<Coord> caminhoMenor = new LinkedList<Coord>();		// Lista encadeada que salva caminho menor
	LinkedList<Coord> caminhoAux = new LinkedList<Coord>();				// Lista encadeada que salva caminho atual
	
	
	
	public Grafo() {				// Função construtora do Grafo
		for(int i = 0; i < World.WIDTH; i++) {
			for (int j=0; j<World.HEIGHT; j++) {
				g[i][j] = new Nodo(0);			// aloca espaço para cada nodo
			}

		}
	}
	
	class Nodo {					// classe que determina estrutura do Nodo
		int peso;				
		boolean visit;			
		public Nodo(int peso){	// função construtora do Nodo
		 	this.peso = peso;
		 	this.visit = false;
		}
	} 
	
	class Coord {					// classe que representa coordenada
		
		int x, y;
		
		Coord(int x, int y){	// Função construtora da Coord
			
			this.x = x;
			this.y = y;
			
		}
		
	}
	
	public void definePeso(int x, int y, int w) {			// função que atribui pesos
		g[x][y].peso = w;
	}
	
	
	/*
	 Objetivo: Encontrar menor caminho em um grafo recursivamente
	 Entrada: Grafo, coordenadas do ínicio, distância  
	 Saída: Menor caminho possível
	 */	
	public void procuraMenorCaminho(int x, int y, int distancia) {	
		
		caminhoAux.add(new Coord(x, y));			// adiciona posição atual a Lista de coordenadas auxiliar
		
		if(!(x == xFim && y == yFim)) {			// verifica se está no fim do caminho
			
			g[x][y].visit = true;				// marca posição como visitada
			
			if(g[x][y - 1].peso != -1 && g[x][y - 1].visit == false) {		// se há caminho acima
				procuraMenorCaminho(x, y - 1, distancia + g[x][y - 1].peso);		// chama recursivamente a função
			}
			if(g[x + 1][y].peso != -1 && g[x + 1][y].visit == false) {		// se há caminho à direita
				procuraMenorCaminho(x + 1, y, distancia + g[x + 1][y].peso);		// chama recursivamente a função
			}
			if(g[x][y + 1].peso != -1 && g[x][y + 1].visit == false) {		// se há caminho abaixo
				procuraMenorCaminho(x, y + 1, distancia + g[x][y + 1].peso);		// chama recursivamente a função
			}
		
		}else {							// se está no fim

			if(distancia < menor) {					// se o caminho encontrado é mais curto
				menor = distancia;					// sua distância é salva como menor
				caminhoMenor = (LinkedList<Coord>) caminhoAux.clone();	// ele é salvo como menor caminho
		}
			
		}
		
		// Limpa o caminho auxiliar ao desempilhar
		if(caminhoAux.size() > 0)
			caminhoAux.removeLast() ;
		else
			caminhoAux.clear();
		
			
		g[x][y].visit = false;			// redefine as posições como não visitadas ao desempilhar
		
	}

	

	
	public void printgrafo() {				// função de teste que imprime grafo 		
		for (int i=0; i<World.WIDTH; i++)
		{
			for (int j=0; j<World.HEIGHT; j++)
				System.out.print(g[i][j]+" ");
			System.out.println();
		}		
	}
}
