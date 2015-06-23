package com.andy.entity;

import org.lwjgl.util.vector.Vector3f;

import com.andy.listener.KeyManager;
import com.andy.listener.MouseManager;

public class Camera {

	private float walkSpeed = 0.1f;
	
	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;
	
	public void update() {
		if (KeyManager.moveForward) {
			position.x += walkSpeed * (float)Math.sin(Math.toRadians(yaw));
			position.z -= walkSpeed * (float)Math.cos(Math.toRadians(yaw));
		}
		
		if (KeyManager.moveBackward) {
			position.x -= walkSpeed * (float)Math.sin(Math.toRadians(yaw));
			position.z += walkSpeed * (float)Math.cos(Math.toRadians(yaw));
		}
		
		if (KeyManager.moveLeft) {
			position.x += walkSpeed * (float)Math.sin(Math.toRadians(yaw - 90));
			position.z -= walkSpeed * (float)Math.cos(Math.toRadians(yaw - 90));
		}
		
		if (KeyManager.moveRight) {
			position.x -= walkSpeed * (float)Math.sin(Math.toRadians(yaw + 90));
			position.z += walkSpeed * (float)Math.cos(Math.toRadians(yaw + 90));
		}
		
		if (KeyManager.moveUp) {
			position.y += walkSpeed;
		}
		
		if (KeyManager.moveDown) {
			position.y -= walkSpeed;
		}
		
		updateRotation();
	}
	
	private void updateRotation() {
		pitch += (float)(MouseManager.getYOffset() * 0.01f);
		yaw += (float)(MouseManager.getXOffset() * 0.01f);
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public float getYaw() {
		return yaw;
	}
	
	public float getRoll() {
		return roll;
	}
}
