package com.andy.listener;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class CursorPosListener extends GLFWCursorPosCallback {
	
	@Override
	public void invoke(long window, double xpos, double ypos) {
		MouseManager.updateMousePos(window, xpos, ypos);
	}
}
