package com.gLstudios.IA;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
		
		World.grafo.controlaRobo(World.grafo.caminho1);
	}

	
	public void render(Graphics g) {
		g.drawImage(robo, this.getX() - Camera.x, this.getY() - Camera.y, null);	
	}
	
	
	
}
