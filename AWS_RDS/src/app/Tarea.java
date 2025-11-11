package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarea {
	private int id;
	private String titulo;
	private String descripcion;
	private LocalDateTime fechaC;
	private String prioridad;
	private String estado;
	
	public Tarea() {
		
	}

	public Tarea(int id, String titulo, String descripcion, LocalDateTime fechaC, String prioridad, String estado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaC = fechaC;
		this.prioridad = prioridad;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaC() {
		return fechaC;
	}

	public void setFechaC(LocalDateTime fechaC) {
		this.fechaC = fechaC;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		//Formatear fechas
		DateTimeFormatter formato = 
				DateTimeFormatter.ofPattern("d/M/yyyy H:m:s");
		return "Tarea [id=" + id + ", "
				+ "titulo=" + titulo +
				", descripcion=" + descripcion + ", "
				+ "fechaC=" + fechaC.format(formato)
				+ ", prioridad=" + prioridad + ", estado=" + estado + "]";
	}
	
	
	
}
