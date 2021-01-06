package com.gLstudios.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.gLstudios.IA.IA;
import com.gLstudios.entities.Entity;
import com.gLstudios.entities.Player;
import com.gLstudios.graphics.Spritesheet;
import com.gLstudios.world.World;

public class Game extends Canvas implements Runnable, KeyListener{


	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	private final int SCALE = 4;
	public final static int WIDTH = 240;
	public final static int HEIGHT = 160;
	
	private BufferedImage image;
	
	public static List<Entity> entities;
	public static Spritesheet spritesheet;
	
	public static World world;
	public static Game jogo;
	

	public static Player player1;
	public static IA robo;

	
	public Game(){
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT* SCALE));
		initFrame();

		
		//Inicializando objetos
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheet.png");
			
		// Inicializando Jogador;
		player1 = new Player(0,0,16,16,spritesheet.getSprite(16*2,0, 16, 16));
		entities.add(player1);
		
		//Inicializando Inteligencia artificial
		robo = new IA(0,0,16,16,spritesheet.getSprite(16*4,16, 16, 16));
		
		// Inicializando Mundo
		world = new World("/gameMap.png");
		
	}
	
	public static void main (String args[]) {
		jogo = new Game();
		jogo.start();
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

	public void tick() {
		
//		for (int i = 0; i<entities.size(); i++)
//		{
//			Entity e = entities.get(i);
//			e.tick();
//		}
		robo.tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);			// fundo preto da tela
		

//				RENDERIZAÇÃO		

		world.render(g);
		robo.render(g);
		
		for (int i = 0; i<entities.size(); i++)
		{
			Entity e = entities.get(i);
			e.render(g);;
		}		
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH* SCALE, HEIGHT* SCALE, null); 
		bs.show();
	}
	
	public void initFrame() {
		frame = new JFrame("Jogo Grafos");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
//		int frames = 0;											// int to check the fps
		double timer = System.currentTimeMillis();			
		
		while(isRunning)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1)
			{
				tick();
				render();
//				frames ++;
				delta --;
			}
			if (System.currentTimeMillis() - timer >= 1000)
			{
//				System.out.println("FPS:" + frames);
//				frames = 0;
				timer +=1000;
			}
		}
		stop();
	}

	
	@Override
	public void keyTyped(KeyEvent e) {
				
		if (e.getKeyChar() == 'd')							// direita pressionado
		{
			player1.moveRight();
		}
		else if (e.getKeyChar() == 'a')						// esquerda pressionado
		{
			player1.moveLeft();
		}
		else if (e.getKeyChar() == 'w')						// cima pressionado
		{
			player1.moveUp();
		}
		else if (e.getKeyChar() == 's')						// baixo pressionado
		{
			player1.moveDown();
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
	
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}


}
