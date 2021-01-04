package com.gLstudios.IA;

import com.gLstudios.world.World;

public class Grafo {

	int g[][] = new int[World.WIDTH][World.HEIGHT];
	public int xInicio, yInicio;
	
	
	class Nodo {
		int x, y, weight;
		
		public Nodo(int x, int y, int w){
		 	this.x=x;
		 	this.y=y;
		 	this.weight = w;
		}
	} 
	
	public void setWeight(int x, int y, int w) {
		
		g[x][y] = w;
	}
	
	public void djikstra(int grafo[][], int xIni, int yIni) {
		
	}
	
	
//	public Grafo() {
//	}
	
	public void printgrafo() {
		
		for (int i=0; i<World.WIDTH; i++)
		{
			for (int j=0; j<World.HEIGHT; j++)
				System.out.print(g[i][j]+" ");
			System.out.println();
		}
			
	}
}

