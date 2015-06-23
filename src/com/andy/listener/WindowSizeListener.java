package com.andy.listener;

import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL11;

import com.andy.display.Main;
import com.andy.settings.WindowSettings;
import com.andy.util.MathUtil;

public class WindowSizeListener extends GLFWWindowSizeCallback {

	@Override
	public void invoke(long window, int width, int height) {
		System.out.println("Window width: " + width + " | height: " + height);
		WindowSettings.setWindowSize(width, height);
		
		GL11.glViewport(0, 0, width, height);
		MathUtil.initProjectionMatrix(Main.getHandle().getShader(), (float)width / (float)height);
	}
}
