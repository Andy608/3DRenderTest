package com.andy.listener;

import org.lwjgl.glfw.GLFW;

public class KeyManager {

	public static boolean moveForward;
	public static boolean moveBackward;
	public static boolean moveLeft;
	public static boolean moveRight;
	
	public static boolean moveUp;
	public static boolean moveDown;
	
	public static boolean quit;
	
	public static void updateKeys(int key, int action) {
		
		switch (key) {
		case GLFW.GLFW_KEY_W: case GLFW.GLFW_KEY_UP: {
			System.out.println("Forward!");
			moveForward = (action != GLFW.GLFW_RELEASE);
			break;
		}
		case GLFW.GLFW_KEY_S: case GLFW.GLFW_KEY_DOWN: {
			System.out.println("Backward!");
			moveBackward = (action != GLFW.GLFW_RELEASE);
			break;
		}
		case GLFW.GLFW_KEY_A: case GLFW.GLFW_KEY_LEFT: {
			System.out.println("Left!");
			moveLeft = (action != GLFW.GLFW_RELEASE);
			break;
		}
		case GLFW.GLFW_KEY_D: case GLFW.GLFW_KEY_RIGHT: {
			System.out.println("Right!");
			moveRight = (action != GLFW.GLFW_RELEASE);
			break;
		}
		case GLFW.GLFW_KEY_SPACE: {
			System.out.println("Up!");
			moveUp = (action != GLFW.GLFW_RELEASE);
			break;
		}
		case GLFW.GLFW_KEY_LEFT_SHIFT: case GLFW.GLFW_KEY_RIGHT_SHIFT: {
			System.out.println("Down!");
			moveDown = (action != GLFW.GLFW_RELEASE);
			break;
		}
		case GLFW.GLFW_KEY_ESCAPE: {
			System.out.println("Bye!");
			quit = (action != GLFW.GLFW_RELEASE);
		}
		}
	}
}
