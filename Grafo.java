package com.gLstudios.IA;

import java.awt.Component;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.gLstudios.main.Game;
import com.gLstudios.world.World;

public class Grafo {

	Nodo g[][] = new Nodo[World.WIDTH][World.HEIGHT];

	public int xInicio, yInicio;
	public int xFim, yFim;
	int menor = 99999999;
	
	public LinkedList<Coord> caminho1 = new LinkedList<Coord>();
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
		
		caminho2.add(new Coord(x, y));

		if(!(x == xFim && y == yFim)) {
			
			g[x][y].visit = 1;
						
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
			System.out.println(x + "  " + y);

			
			if(distancia < menor) {
				menor = distancia;
				caminho1 = (LinkedList<Coord>) caminho2.clone();
//				for(int i=0;i<caminho2.size();i++)
//					System.out.println(caminho2.get(i).x + "|" + caminho2.get(i).y);
			}
			
		}
		
		// correções para desempilhar
		if(caminho2.size() > 1)
			caminho2.removeLast() ;
		else
		{
			caminho2.clear();
			
		}
			
		g[x][y].visit = 0;
		
	}
	
	
	public Grafo() {
		for(int i = 0; i < World.WIDTH; i++) {
			for (int j=0; j<World.HEIGHT; j++) {
				g[i][j] = new Nodo(0);
			}

		}
	}
	
	public void controlaRobo(LinkedList<Coord> caminho) {
		try {
			TimeUnit.MILLISECONDS.sleep(300);;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println(caminho1.get(0));
//		
	
		if (caminho1.size() > 0)
		{
			System.out.println("X: " + this.caminho1.getFirst().x + "Y: "+ this.caminho1.getFirst().y);
			Game.robo.setX(this.caminho1.getFirst().x * World.TILE_SIZE);
			Game.robo.setY(this.caminho1.getFirst().y * World.TILE_SIZE);			
			caminho1.removeFirst() ;

		}
		else
		{
			caminho1.clear();
			JOptionPane.showMessageDialog(Game.frame, "Distância percorrida: " + menor);
			System.exit(0);

			Game.jogo.stop();
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
