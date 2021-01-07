package com.gLstudios.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Menu {

	
	public void render(Graphics g) {
		Font font = new Font("Serif", Font.BOLD,20);
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("O JOGO", (Game.WIDTH/2) - 40, 25);
		
		
		font = new Font("", Font.BOLD,16);
		g.setFont(font);
		g.drawString("Pressione Enter", (Game.WIDTH/2) - 59, 120);
		g.drawString(" para continuar", (Game.WIDTH/2) - 59, 140);

	}
}
