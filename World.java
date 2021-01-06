package com.gLstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gLstudios.IA.Grafo;
import com.gLstudios.entities.Entity;
import com.gLstudios.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	public static Grafo grafo;

	
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[]pixels = new int[map.getWidth() * map.getHeight()];
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[WIDTH * HEIGHT];
			grafo = new Grafo();
			
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for (int xx=0; xx < WIDTH; xx++)
			{
				for (int yy = 0; yy < HEIGHT; yy++)
				{
					int pixelAtual = pixels[xx + (yy * WIDTH)];
					
					
					if (pixelAtual == 0xFFFFFFFF)					// cor branca (PAREDE)
					{
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
						grafo.setWeight(xx, yy, -1); 				
					}
					
					else if (pixelAtual == 0xFFDA5913)				// cor Laranja (LAVA)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16,yy *16, Tile.TILE_LAVA);
						grafo.setWeight(xx, yy, 5);
					}
					
					else if (pixelAtual == 0xFFAF7A5B)				// cor marrom (LAMA)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16,yy *16, Tile.TILE_MUD);
						grafo.setWeight(xx, yy, 2);
					}
					
					else if (pixelAtual == 0xFF3239FF)				// cor azul (PLAYER)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);	// seta caminho
						Game.player1.setX(xx*16);
						Game.player1.setY(yy*16);
						Game.robo.setX(xx*16);
						Game.robo.setY(yy*16);
						
						grafo.setWeight(xx, yy, 1);
						grafo.xInicio = xx;
						grafo.yInicio = yy;
					}
					
					else if (pixelAtual == 0xFF000000)				// cor preta (caminho)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);	// seta caminho
						grafo.setWeight(xx, yy, 1);
					}
					else if (pixelAtual == 0xFFFE6E0E)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Entity.WEAPON_EN);	// seta caminho
						grafo.setWeight(xx, yy, 0);
						grafo.xFim = xx;
						grafo.yFim = yy;
					}

					
				}
			}
		grafo.dijkstra(grafo.xInicio, grafo.yInicio, 0);
//		grafo.controlaRobo(grafo.caminho1);
		//grafo.printgrafo();
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
