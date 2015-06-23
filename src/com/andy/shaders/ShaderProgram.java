package com.andy.shaders;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.andy.util.FileUtil;

public abstract class ShaderProgram {

	private int programID;
	private int vertexShaderID;
	private int fragmentShaderID;
	
	private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public ShaderProgram(String vertexFile, String fragmentFile) {
		programID = GL20.glCreateProgram();
		attachVertexShader(vertexFile);
		attachFragmentShader(fragmentFile);
		bindAttributes();
		link();
		GL20.glValidateProgram(programID);
		getAllUniformLocations();
	}
	
	protected abstract void bindAttributes();
	protected abstract void getAllUniformLocations();
	
	private void attachVertexShader(String name) {
		String vertexShaderSource = FileUtil.loadFile(name);
		
		vertexShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		GL20.glShaderSource(vertexShaderID, vertexShaderSource);
		
		GL20.glCompileShader(vertexShaderID);
		if (GL20.glGetShaderi(vertexShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Error creating vertex shader!\n" + GL20.glGetShaderInfoLog(vertexShaderID, GL20.glGetShaderi(vertexShaderID, GL20.GL_INFO_LOG_LENGTH)));
		}
		GL20.glAttachShader(programID, vertexShaderID);
	}
	
	private void attachFragmentShader(String name) {

		String fragmentShaderSource = FileUtil.loadFile(name);

		fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		GL20.glShaderSource(fragmentShaderID, fragmentShaderSource);

		GL20.glCompileShader(fragmentShaderID);

		if (GL20.glGetShaderi(fragmentShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Error creating fragment shader!\n" + GL20.glGetShaderInfoLog(fragmentShaderID, GL20.glGetShaderi(fragmentShaderID, GL20.GL_INFO_LOG_LENGTH)));
		}

		GL20.glAttachShader(programID, fragmentShaderID);
	}
	
	private void link() {
		
		//Link the program
		GL20.glLinkProgram(programID);
		
		//Check for link errors
		if (GL20.glGetProgrami(programID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Unable to link shader: " + GL20.glGetProgramInfoLog(programID, GL20.glGetProgrami(programID, GL20.GL_INFO_LOG_LENGTH)));
		}
	}
	
	protected void bindAttribute(int attribute, String attribName) {
		GL20.glBindAttribLocation(programID, attribute, attribName);
	}
	
	protected void loadFloat(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	
	protected void loadVector(int location, Vector3f vector) {
		GL20.glUniform3f(location, vector.x, vector.y, vector.z);
	}
	
	protected void loadBoolean(int location, boolean b) {
		float value = 0f;
		if (b) value = 1.0f;
		loadFloat(location, value);
	}
	
	protected void loadMatrix4f(int location, Matrix4f matrix) {
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4fv(location, false, matrixBuffer);
	}
	
	protected int getUniformLocation(String uniformName) {
		return GL20.glGetUniformLocation(programID, uniformName);
	}
	
	public void cleanUp() {
		unbind();
		
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		
		GL20.glDeleteProgram(programID);
	}
	
	public void bind() {
		GL20.glUseProgram(programID);
	}
	
	public void unbind() {
		GL20.glUseProgram(0);
	}
	
	public int getProgramID() {
		return programID;
	}
}
