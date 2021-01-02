package com.gLstudios.IA;

import java.util.*;

public class Grafo {

	List<Nodo> g[];
	
	class Nodo {
		int vertice,weight;
		
		public Nodo(int v,int w){
		 	this.vertice=v;
		 	this.weight = w;
		}
		public void teste() {
			System.out.println("Teste");
		}
	} 
	
	public void addNodo(int u, int v, int w) {
		g[u].add(u,new Nodo(v,w));
	}
	public void teste2() {
		if(g[0].get(0).weight == 1)
		{
			System.out.println("okok!");
		}
		else
			System.out.println("NãOnÂO");
	}
	
	public Grafo(int n) {
		g = new LinkedList[n];
		for (int i=0; i<g.length; i++)
		{
			g[i] = new LinkedList<Nodo>();
		} 
		new Grafo.Nodo(10,10).teste();
	}
	
	public void constroiGrafo() {
		// apontamentos do 0
		this.addNodo(0, 1, 0);
		
		this.addNodo(1, 2, 0);
		this.addNodo(2, 3, 0);
		this.addNodo(3, 4, 0);
		this.addNodo(4, 5, 0);
	}
	
	
}

