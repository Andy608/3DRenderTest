package com.andy.models;

public class RawModel {

	private int vaoID; //Vertex Array Object
	private int vertexCount;
	
	public RawModel(int vao, int vertCount) {
		vaoID = vao;
		vertexCount = vertCount;
	}
	
	public int getVaoID() {
		return vaoID;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
}
