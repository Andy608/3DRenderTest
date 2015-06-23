package com.andy.entity;

import org.lwjgl.util.vector.Vector3f;

import com.andy.models.TexturedModel;

public class Entity {

	private TexturedModel model;
	private Vector3f position;
	private Vector3f rotation;
	private float scale;
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public void changePosition(Vector3f vec) {
		position.x += vec.x;
		position.y += vec.y;
		position.z += vec.z;
	}
	
	public void changePosition(float x, float y, float z) {
		position.x += x;
		position.y += y;
		position.z += z;
	}
	
	public void changeRotation(float x, float y, float z) {
		rotation.x += x;
		rotation.y += y;
		rotation.z += z;
	}
	
	public void changeRotation(Vector3f vec) {
		rotation.x += vec.x;
		rotation.y += vec.y;
		rotation.z += vec.z;
	}
	
	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
}
