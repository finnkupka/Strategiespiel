package openglObjects;

import java.util.ArrayList;
import java.util.List;

public class Vao {
	
	private int id;
	private int vertexCount;
	
	private List<Vbo> vbos;
	
	public Vao(int id, int vertexCount) {
		this.id = id;
		this.vertexCount = vertexCount;
		this.vbos = new ArrayList<Vbo>();
	}
	
	public void addVbo(Vbo vbo) {
		this.vbos.add(vbo);
	}
	
	public Vbo getVbo(int index) {
		return this.vbos.get(index);
	}
	
	public int getID() {
		return this.id;
	}
	
	public int getVertexCount() {
		return this.vertexCount;
	}
	
	public void changeVertexCount(int change) {
		this.vertexCount = Math.max(this.vertexCount + change, 0);
	}

}
