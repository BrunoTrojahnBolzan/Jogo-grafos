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

	
	
	public void controlaRobo(LinkedList<Coord> caminho1) {

		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		if (caminho1.size() > 0)
		{
			Game.robo.setX(caminho1.getFirst().x * World.TILE_SIZE);
			Game.robo.setY(caminho1.getFirst().y * World.TILE_SIZE);			
			caminho1.removeFirst();
		}
		else
		{
			caminho1.clear();
			JOptionPane.showMessageDialog(Game.frame, "Distância percorrida: " + World.grafo.menor);
			System.exit(0);
			Game.jogo.stop();
		}
	}
	
	
	
	public void render(Graphics g) {
		g.drawImage(robo, this.getX() - Camera.x, this.getY() - Camera.y, null);	
	}
	
	
	
}
