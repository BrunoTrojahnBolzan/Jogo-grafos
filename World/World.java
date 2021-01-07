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
			
			grafo = new Grafo();			// Inicia o grafo
			
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());		
			for (int xx=0; xx < WIDTH; xx++)			//loop que passa por cada pixel do mapa
			{
				for (int yy = 0; yy < HEIGHT; yy++)
				{
					int pixelAtual = pixels[xx + (yy * WIDTH)];
					
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);	// seta caminho
					
					if (pixelAtual == 0xFFFFFFFF)					// cor branca (PAREDE)
					{
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
						grafo.definePeso(xx, yy, -1); 	// define o peso da parede como inválido						
					}
					
					else if (pixelAtual == 0xFFDA5913)				// cor Laranja (LAVA)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16,yy *16, Tile.TILE_LAVA);
						grafo.definePeso(xx, yy, -1);	// define o peso da lava como 5
					}
					
					else if (pixelAtual == 0xFFAF7A5B)				// cor marrom (LAMA)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16,yy *16, Tile.TILE_MUD);
						grafo.definePeso(xx, yy, 2);	// define o peso da LAMA como 2
					}
					
					else if (pixelAtual == 0xFFD5E2E8)				// cor marrom (NEVE)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16,yy *16, Tile.TILE_SNOW);
						grafo.definePeso(xx, yy, 3);	// define o peso da NEVE como 3
					}
					
					else if (pixelAtual == 0xFF3239FF)				// cor azul (PLAYER)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);	// seta caminho
						// posiciona o robo no início
						Game.robo.setX(xx*16);			 
						Game.robo.setY(yy*16);
						
						grafo.definePeso(xx, yy, 1);	// define peso do início do caminho
						grafo.xInicio = xx;				// define coordenadas iniciais
						grafo.yInicio = yy;
					}
					
					else if (pixelAtual == 0xFF000000)				// cor preta (caminho)
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);	// seta caminho
						grafo.definePeso(xx, yy, 1);
					}
			
					else if (pixelAtual == 0xFFFE6E0E)				// Final do caminho
					{
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Entity.BANDEIRA_EN);	// seta caminho
						grafo.definePeso(xx, yy, 0);				// define peso do final do caminho
						// guarda coordenadas do fim do caminho
						grafo.xFim = xx;			
						grafo.yFim = yy;
					}

					
				}
			}

		grafo.procuraMenorCaminho(grafo.xInicio, grafo.yInicio, 0);	// chamada da função que procura menor caminho 
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
