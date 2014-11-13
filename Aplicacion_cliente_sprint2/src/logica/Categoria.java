package logica;

import java.util.ArrayList;

public class Categoria {
	private int id;
	private String nombre;
	private ArrayList<Subcategoria> subcategorias;

	public Categoria(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
		subcategorias = new ArrayList<Subcategoria>();
	}

	public void addSubcategoria(Subcategoria p) {
		subcategorias.add(p);
	}

	public ArrayList<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setProductos(ArrayList<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "--->id: " + id + "----nombre: " + nombre +"\n"+subcategorias.toString();
	}

}
