package com.andy.util;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.andy.entity.Camera;
import com.andy.settings.WindowSettings;
import com.andy.shaders.StaticShader;

public class MathUtil {

	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 100.0f;
	private static Matrix4f projectionMatrix;
	
	public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, float scale) {
		
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float)(Math.toRadians(rotation.x)), new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate((float)(Math.toRadians(rotation.y)), new Vector3f(0, 1, 0), matrix, matrix);
		Matrix4f.rotate((float)(Math.toRadians(rotation.z)), new Vector3f(0, 0, 1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createViewMatrix(Camera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Vector3f cameraPos = camera.getPosition();
		Vector3f oppositeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		Matrix4f.translate(oppositeCameraPos, viewMatrix, viewMatrix);
		return viewMatrix;
	}
	
	public static void createProjectionMatrix(float ar) {
		float aspectRatio = ar;
//		float aspectRatio = (float) GLFWvidmode.width(WindowSettings.getVideoMode()) / GLFWvidmode.height(WindowSettings.getVideoMode());
		float yScale = (float) ((1f / Math.tan(Math.toRadians(FOV) / 2f)) * aspectRatio);
		float xScale = (float) ((yScale / aspectRatio));
		float frustumLength = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = xScale;
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustumLength);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustumLength);
		projectionMatrix.m33 = 0;
	}
	
	public static void initProjectionMatrix(StaticShader shader) {
		MathUtil.createProjectionMatrix(WindowSettings.getWindowSize().x / WindowSettings.getWindowSize().y);
		loadProjectionMatrix(shader);
	}
	
	public static void initProjectionMatrix(StaticShader shader, float aspectRatio) {
		MathUtil.createProjectionMatrix(aspectRatio);
		loadProjectionMatrix(shader);
	}
	
	private static void loadProjectionMatrix(StaticShader shader) {
		shader.bind();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.unbind();
	}
}
