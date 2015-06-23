package com.andy.display;

import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.util.vector.Vector3f;

import com.andy.entity.Camera;
import com.andy.entity.Entity;
import com.andy.listener.CallbackHandler;
import com.andy.models.CubeModel;
import com.andy.models.RawModel;
import com.andy.models.TexturedModel;
import com.andy.render.ModelLoader;
import com.andy.render.ModelRenderer;
import com.andy.settings.WindowSettings;
import com.andy.shaders.StaticShader;
import com.andy.texture.TextureLoader;
import com.andy.util.MathUtil;

public class Main {
	
	private static Main gameHandle;
	
	private CallbackHandler cbh;
	private ModelLoader loader;
	private ModelRenderer modelRenderer;
	private StaticShader shader;
	
	private Entity entity;
	private CubeModel cube;
	private RawModel model;
	private TexturedModel texturedCube;
	
	private Camera camera;
	
	private String windowTitle;
	private long windowID;
	
	private static final int TICKS_PER_SECOND = 60;
	private static final double SECONDS_PER_TICK = 1 / (double)TICKS_PER_SECOND;
	
	private int ticks;
	private int frames;
	
	private double now;
	private double last;
	private double lag;
	private double delta;
	
	private Main(String title) {
		windowTitle = title;
	}
	
	private void init() throws IllegalStateException {
		System.out.println("Welcome to OpenGL " + Sys.getVersion() + "!");
		try {
			if (GLFW.glfwInit() == GL11.GL_FALSE) {
				throw new IllegalStateException("Failed to initialize new GLFW window. :(");
			}
			else {
				createWindow();
				initCallbacks();
				initWindow();
				initGraphics();
				run();
			}
		}
		finally {
			cleanUp();
		}
	}
	
	private void createWindow() throws RuntimeException {
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_TRUE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
		
		windowID = GLFW.glfwCreateWindow((int)WindowSettings.PREFERRED_SIZE.x, (int)WindowSettings.PREFERRED_SIZE.y, Main.getHandle().getTitle(), MemoryUtil.NULL, MemoryUtil.NULL);
		if (windowID == MemoryUtil.NULL) {
			throw new RuntimeException("Failed to create a new GLFW window. :(");
		}
	}
	
	private void initWindow() {
		setWindowPosition();
		GLFW.glfwMakeContextCurrent(windowID);
		GLFW.glfwSwapInterval(0);
		GLFW.glfwShowWindow(windowID);
	}
	
	private void setWindowPosition() {
		GLFW.glfwSetWindowPos(windowID, (int)((GLFWvidmode.width(WindowSettings.getVideoMode()) - WindowSettings.PREFERRED_SIZE.x) / 2), (int)((GLFWvidmode.height(WindowSettings.getVideoMode()) - WindowSettings.PREFERRED_SIZE.y) / 2));
	}
	
	private void initGraphics() {
		GLContext.createFromCurrent();
		GL11.glViewport(0, 0, (int)WindowSettings.PREFERRED_SIZE.x, (int)WindowSettings.PREFERRED_SIZE.y);
		
		shader = new StaticShader();
		loader = new ModelLoader();
		modelRenderer = new ModelRenderer();
		MathUtil.initProjectionMatrix(shader);
		
		cube = new CubeModel();
		model = loader.loadToVao(cube.getVertices(), cube.getIndices(), cube.getTextureCoords());
		texturedCube = new TexturedModel(model, cube.getTexture());
		entity = new Entity(texturedCube, new Vector3f(0, 0, -6f), new Vector3f(0, 0, 0), 1);
		camera = new Camera();
	}
	
	private void run() {
		last = GLFW.glfwGetTime();
		
		while (GLFW.glfwWindowShouldClose(windowID) == GL11.GL_FALSE) {
			now = GLFW.glfwGetTime();
			delta = now - last;
			last = now;
			lag += delta;
			
			if (lag >= 0.15d) {
				lag = 0.15d;
			}
			
			processInput();
			while (lag >= SECONDS_PER_TICK) {
				lag -= SECONDS_PER_TICK;
				update();
			}
			
			render(lag / SECONDS_PER_TICK);
		}
	}
	
	private void processInput() {
		GLFW.glfwPollEvents();
	}
	
	private void update() {
		ticks++;
		
//		entity.changePosition(0f, 0f, -0.01f);
		entity.changeRotation(1, 1, 1);
		camera.update();
		
		if (ticks % TICKS_PER_SECOND == 0) {
			System.out.println("Ticks: " + ticks + " | Frames: " + frames);
			ticks = 0;
			frames = 0;
		}
	}
	
	private void render(double lerp) {
		frames++;
		modelRenderer.prepare();
		shader.bind();
		shader.loadViewMatrix(camera);
		modelRenderer.renderEntity(entity, shader);
		shader.unbind();
		GLFW.glfwSwapBuffers(windowID);
	}
	
	private void cleanUp() {
		
		if (shader != null) shader.cleanUp();
		if (loader != null) loader.cleanUp();
		if (cbh != null) cbh.freeCallbacks();
		TextureLoader.cleanUp();
		
		GLFW.glfwDestroyWindow(windowID);
		GLFW.glfwTerminate();
	}
	
	private void initCallbacks() {
		cbh = new CallbackHandler(windowID);
	}
	
	public String getTitle() {
		return windowTitle;
	}
	
	public long getWindowID() {
		return windowID;
	}
	
	public CallbackHandler getCallbacks() {
		return cbh;
	}
	
	public StaticShader getShader() {
		return shader;
	}
	
	public static Main getHandle() {
		return gameHandle;
	}
	
	public static void main(String[] args) {
		gameHandle = new Main("3D Rendering Test!");
		gameHandle.init();
	}
}
