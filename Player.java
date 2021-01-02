package com.gLstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gLstudios.main.Game;
import com.gLstudios.world.Camera;
import com.gLstudios.world.World;

public class Player extends Entity{

	public char right_dir = 'R', left_dir = 'L', dir = right_dir;
	
	public final int nSprites = 4;
	
	public boolean right, up, left, down;
	public double speed = 16;
	
//	private int frames = 0,maxFrames = 6,index = 0,maxIndex = nSprites-1;
//	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[nSprites];
		leftPlayer = new BufferedImage[nSprites];
		
		for(int i=0;i<nSprites;i++)
		{
			rightPlayer[i] = Game.spritesheet.getSprite(16*2, 16*i, this.getWidth(), this.getHeight());
			leftPlayer[i] = Game.spritesheet.getSprite(16*3, 16*i, this.getWidth(), this.getHeight());
		}
	}
	
	public void tick() {

			
		
		Camera.x = Camera.clamp(getX() - (Game.WIDTH/2), 0, (World.WIDTH*16) - Game.WIDTH)  ;
		Camera.y = Camera.clamp(getY() - (Game.HEIGHT/2), 0, (World.HEIGHT*16) - Game.HEIGHT)  ;
	} 

	public void moveRight() {
		if (World.isFree((int)(x+speed),this.getY()))
		{
			dir = right_dir;
			x+=speed;
		}
	}
	public void moveLeft() {
		if (World.isFree((int)(x-speed),this.getY()))
		{
			dir = right_dir;
			x-=speed;
		}
	}	
	
	public void moveUp() {
		if (World.isFree(this.getX(),(int)(y-speed)))
		{
			dir = right_dir;
			y-=speed;
		}
	}	
	
	public void moveDown() {
		if (World.isFree(this.getX(),(int)(y+speed)))
		{
			dir = right_dir;
			y+=speed;
		}
	}
	
	public void render(Graphics g) {
		if(dir == right_dir)
		{
			g.drawImage(rightPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);	
		}	
		else if(dir == left_dir)
		{
			g.drawImage(leftPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);	
		}
	}

}
