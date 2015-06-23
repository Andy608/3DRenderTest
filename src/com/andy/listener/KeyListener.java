package com.andy.listener;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

public class KeyListener extends GLFWKeyCallback {

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		KeyManager.updateKeys(key, action);
		
		if (KeyManager.quit) {
			GLFW.glfwSetWindowShouldClose(window, GL11.GL_TRUE);
		}
	}
}
