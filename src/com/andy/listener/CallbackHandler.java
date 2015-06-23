package com.andy.listener;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

public final class CallbackHandler {

	private GLFWErrorCallback errorCallback;
	private GLFWKeyCallback keyCallback;
//	private GLFWScrollCallback scrollCallback;
	private GLFWCursorPosCallback cursorPosCallback;
//	private GLFWMouseButtonCallback mouseButtonCallback;
	
	private GLFWWindowSizeCallback windowSizeCallback;
	
	public CallbackHandler(long windowID) {
		GLFW.glfwSetErrorCallback(errorCallback = Callbacks.errorCallbackPrint(System.err));
		GLFW.glfwSetKeyCallback(windowID, keyCallback = new KeyListener());
//		GLFW.glfwSetScrollCallback(windowID, scrollCallback = new MouseScrollListener());
		GLFW.glfwSetCursorPosCallback(windowID, cursorPosCallback = new CursorPosListener());
//		GLFW.glfwSetMouseButtonCallback(windowID, mouseButtonCallback = new MouseButtonListener());
		
		GLFW.glfwSetWindowSizeCallback(windowID, windowSizeCallback = new WindowSizeListener());
	}
	
	public void freeCallbacks() {
		errorCallback.release();
		keyCallback.release();
//		scrollCallback.release();
		cursorPosCallback.release();
//		mouseButtonCallback.release();
		windowSizeCallback.release();
	}
}
