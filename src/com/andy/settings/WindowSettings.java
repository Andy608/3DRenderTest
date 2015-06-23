package com.andy.settings;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.util.vector.Vector2f;

public class WindowSettings {

	private static ByteBuffer videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
	
	public static final Vector2f PREFERRED_SIZE = new Vector2f(GLFWvidmode.width(videoMode) / 2, GLFWvidmode.height(videoMode) / 2);
	public static Vector2f windowSize = new Vector2f(PREFERRED_SIZE);
	
	public static void setWindowSize(float width, float height) {
		windowSize.set(width, height);
	}
	
	public static Vector2f getWindowSize() {
		return windowSize;
	}
	
	public static ByteBuffer getVideoMode() {
		return videoMode;
	}
}
