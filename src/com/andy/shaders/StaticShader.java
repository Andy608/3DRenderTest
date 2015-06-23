package com.andy.shaders;

import org.lwjgl.util.vector.Matrix4f;

import com.andy.entity.Camera;
import com.andy.util.MathUtil;
import com.andy.util.PathStorage;

public class StaticShader extends ShaderProgram {

	private int transformationMatrixLocation;
	private int projectionMatrixLocation;
	private int viewMatrixLocation;
	
	public StaticShader() {
		super(PathStorage.VERTEX_SHADER_PATH, PathStorage.FRAGMENT_SHADER_PATH);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	@Override
	protected void getAllUniformLocations() {
		transformationMatrixLocation = super.getUniformLocation("transformationMatrix");
		projectionMatrixLocation = super.getUniformLocation("projectionMatrix");
		viewMatrixLocation = super.getUniformLocation("viewMatrix");
	}
	
	public void loadTransformationMatrix(Matrix4f transformation) {
		super.loadMatrix4f(transformationMatrixLocation, transformation);
	}
	
	public void loadProjectionMatrix(Matrix4f projection) {
		super.loadMatrix4f(projectionMatrixLocation, projection);
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = MathUtil.createViewMatrix(camera);
		super.loadMatrix4f(viewMatrixLocation, viewMatrix);
	}
}
