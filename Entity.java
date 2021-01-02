package com.gLstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gLstudios.main.Game;
import com.gLstudios.world.Camera;

public class Entity {

	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(16*5, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(16*6, 0, 16, 16);
	public static BufferedImage AMMO_EN = Game.spritesheet.getSprite(16*7, 0, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(16*8, 0, 16, 16);
	
	
	protected double x, y;
	protected int width, height;
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}

	// métodos setters
	public void setX(int newX) {
		this.x = newX;
	}
	public void setY(int newY) {
		this.y = newY;
	}
	
	// métodos getters
	public int getX() {
		return (int)this.x;
	}
	public int getY() {
		return (int)this.y;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,this.getX() - Camera.x,this.getY() - Camera.y, null);
	}
	
}
