package com.example.demo.modelos;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tareas")
public class TareaModelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private String descricion;
	@Column(nullable = false)
	private LocalDate fecha_creacion=LocalDate.now();
	@Column(nullable = false)
	private String prioridad="media";
	@Column(nullable = false)
	private String estado="pendiente";
	public TareaModelo() {
		
	}
	public TareaModelo(Long id, String titulo, String descricion, LocalDate fecha_creacion, String prioridad,
			String estado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricion = descricion;
		this.fecha_creacion = fecha_creacion;
		this.prioridad = prioridad;
		this.estado = estado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricion() {
		return descricion;
	}
	public void setDescricion(String descricion) {
		this.descricion = descricion;
	}
	public LocalDate getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(LocalDate fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
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
	
	

}
