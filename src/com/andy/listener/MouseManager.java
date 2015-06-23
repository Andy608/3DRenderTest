package com.andy.listener;

import com.andy.settings.WindowSettings;

public class MouseManager {

	private static double xOffset;
	private static double yOffset;
	
	
//	private static boolean isLeftClick;
//	private static boolean isRightClick;
	
	public static void updateMousePos(long windowID, double x, double y) {
		
		xOffset = x - (WindowSettings.getWindowSize().x / 2);
		yOffset = y - WindowSettings.getWindowSize().y / 2;
		
		System.out.println("XOffset: " + xOffset + " | " + "YOffset: " + yOffset);
		System.out.println("XPos:    " + x + " | " + "YPos:    " + y);
	}
	
	public static double getXOffset() {
		return xOffset;
	}
	
	public static double getYOffset() {
		return yOffset;
	}
	
//	public static void updateMouseButtons()
	
}
