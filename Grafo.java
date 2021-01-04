package com.gLstudios.IA;

import com.gLstudios.world.World;

import java.util.LinkedList;

import java.io.IOException;

public class Grafo {

	Nodo g[][] = new Nodo[World.WIDTH][World.HEIGHT];
	public int xInicio, yInicio;
	public int xFim, yFim;
	int menor = 99999999;
	
	LinkedList<Coord> caminho1 = new LinkedList<Coord>();
	LinkedList<Coord> caminho2 = new LinkedList<Coord>();
	
	class Nodo {
		int visit, peso;
		
		public Nodo(int peso){
		 	this.peso = peso;
		 	this.visit = 0;
		}
	} 
	
	class Coord {
		
		int x, y;
		
		Coord(int x, int y){
			
			this.x = x;
			this.y = y;
			
		}
		
	}
	
	public void setWeight(int x, int y, int w) {
		g[x][y].peso = w;
		g[x][y].visit = 0;
	}
	
	public void dijkstra(int x, int y, int distancia) {
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!(x == xFim && y == yFim)) {
			
			g[x][y].visit = 1;
			
			caminho2.add(new Coord(x, y));
			
			System.out.println("Visitado: " + x + "," + y);
			
			if(g[x][y - 1].peso != -1 && g[x][y - 1].visit == 0) {
				dijkstra(x, y - 1, distancia + g[x][y - 1].peso);
			}
			if(g[x + 1][y].peso != -1 && g[x + 1][y].visit == 0) {
				dijkstra(x + 1, y, distancia + g[x + 1][y].peso);
			}
			if(g[x][y + 1].peso != -1 && g[x][y + 1].visit == 0) {
				dijkstra(x, y + 1, distancia + g[x][y + 1].peso);
			}
		
		}else {
			System.out.println("Distancia: " + distancia);
			
			if(distancia < menor) {
				menor = distancia;
				caminho1 = (LinkedList<Coord>) caminho2.clone();
			}
			
		}
		
		caminho2.pop();
		g[x][y].visit = 0;
		
	}
	
	
	public Grafo() {
		for(int i = 0; i < World.WIDTH; i++) {
			for (int j=0; j<World.HEIGHT; j++) {
				g[i][j] = new Nodo(0);
			}
		}
	}
	
	public void printgrafo() {
		
		for (int i=0; i<World.WIDTH; i++)
		{
			for (int j=0; j<World.HEIGHT; j++)
				System.out.print(g[i][j]+" ");
			System.out.println();
		}
			
	}
}
