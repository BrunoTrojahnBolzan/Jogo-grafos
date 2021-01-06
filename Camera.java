package com.gLstudios.world;

public class Camera {

	public static int x = 0;
	public static int y = 35;
	
	public static int clamp(int axisAtual, int axisMin, int axisMax) {
		if(axisAtual<axisMin)
			axisAtual = axisMin;
	
		else if(axisAtual>axisMax)
			axisAtual = axisMax;
		
		return axisAtual;
	}
}
