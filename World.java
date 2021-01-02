package com.gLstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gLstudios.entities.*;
import com.gLstudios.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[]pixels = new int[map.getWidth() * map.getHeight()];
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[WIDTH * HEIGHT];
			
			
			
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for (int xx=0; xx < WIDTH; xx++)
			{
				for (int yy = 0; yy < HEIGHT; yy++)
				{
					int pixelAtual = pixels[xx + (yy * WIDTH)];
					
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);	// seta floor por padrão
					
					if (pixelAtual == 0xFFFFFFFF)					// cor branca (PAREDE)
					{
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
					}
					
					else if (pixelAtual == 0xFFDA5913)				// cor Laranja (LAVA)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16,yy *16, Tile.TILE_LAVA);
					}
					
					else if (pixelAtual == 0xFFAF7A5B)				// cor marrom (LAMA)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16,yy *16, Tile.TILE_MUD);
					}
					
					else if (pixelAtual == 0xFF3239FF)				// cor azul (PLAYER)
					{
						Game.player1.setX(xx*16);
						Game.player1.setY(yy*16);
					}
					else if (pixelAtual == 0xFFFF2339)				// cor vermelha (INIMIGO)
					{
						
						Game.entities.add(new Enemy(xx * 16,yy *16, 16, 16, Entity.ENEMY_EN));
					}
					else if (pixelAtual == 0xFFFFFB32)				// cor amarela (MUNIÇÃO)
					{
						Game.entities.add(new Ammo(xx * 16,yy *16, 16, 16, Entity.AMMO_EN));
					}
					else if (pixelAtual == 0xFFA2FF70)				// cor verde (VIDA)
					{
						Game.entities.add(new LifePack(xx * 16,yy *16, 16, 16, Entity.LIFEPACK_EN));
					}
					else if (pixelAtual == 0xFFFF6F0F)				// cor laranja (ARMA)
					{
						Game.entities.add(new Weapon(xx * 16,yy *16, 16, 16, Entity.WEAPON_EN));
					}

					
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static boolean isFree(int xNext, int yNext) {
		int x1 = xNext /TILE_SIZE;
		int y1 = yNext /TILE_SIZE; 
				
				
		return !(tiles[x1 + (y1* World.WIDTH )] instanceof WallTile);
	}
	
	
	public void render(Graphics g) {
		int	xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for (int xx = 0; xx <= xfinal; xx++)
		{
			for (int yy = ystart; yy <= yfinal; yy++)
			{
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}
	

