package com.gLstudios.IA;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import com.gLstudios.IA.Grafo.Coord;
import com.gLstudios.entities.Entity;
import com.gLstudios.main.Game;
import com.gLstudios.world.Camera;
import com.gLstudios.world.World;

public class IA extends Entity{

	private BufferedImage robo = Game.spritesheet.getSprite(16*4, 16, this.getWidth(), this.getHeight());
	
	public IA(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	public void tick() {
		Camera.x = Camera.clamp(getX() - (Game.WIDTH/2), 0, (World.WIDTH*16) - Game.WIDTH)  ;
		Camera.y = Camera.clamp(getY() - (Game.HEIGHT/2), 0, (World.HEIGHT*16) - Game.HEIGHT);
		
		controlaRobo(World.grafo.caminhoMenor);
	}

	
	
	/*
	 Objetivo: Fazer o robo seguir o menor caminho
	 Entrada: Menor caminho
	 Saída: sprite do robo movimentado pelas coordenadas
	*/
	public void controlaRobo(LinkedList<Coord> caminho) {		 

		try {
			TimeUnit.MILLISECONDS.sleep(500);			// função que pausa execução por determinado tempo
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		if (caminho.size() > 0)						// verifica se ainda restam coordenadas no caminho
		{
			// define a posição do robo para a coordenada atual da lista
			Game.robo.setX(caminho.getFirst().x * World.TILE_SIZE);		
			Game.robo.setY(caminho.getFirst().y * World.TILE_SIZE);			
			caminho.removeFirst();					// remove coordenada recém utilizada
		}
		else										// caso tenha chegado no fim
		{
			caminho.clear();						// limpa a Lista caminho
			if(World.grafo.menor != 9999999)
				JOptionPane.showMessageDialog(Game.frame, "Distância percorrida: " + World.grafo.menor);			// exibe mensagem
			else
				JOptionPane.showMessageDialog(Game.frame, "Impossível de alcançar o destino");			// exibe mensagem				
			System.exit(0);							// encerra execução do programa
		}
	}
	
	
	
	public void render(Graphics g) {
		g.drawImage(robo, this.getX() - Camera.x, this.getY() - Camera.y, null);	
	}
	
	
	
}
