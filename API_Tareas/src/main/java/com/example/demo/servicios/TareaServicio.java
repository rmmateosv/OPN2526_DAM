package com.example.demo.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.modelos.TareaModelo;
import com.example.demo.repositorios.TareaRepositorio;

@Service
public class TareaServicio {
	public final TareaRepositorio tr;

	public TareaServicio(TareaRepositorio tr) {
		this.tr = tr;
	}

	public List<TareaModelo> obtenerTareas() {
		//Usa el repositorio para hacer un select * de tareas
		return tr.findAll();
	}

	public TareaModelo obtenerTareaPorId(long id) {
		return null;
	}

	public TareaModelo obtenerTareaPorPrioridad(String p) {
		return null;
	}
	public TareaModelo obtenerTareaPorEstado(String e) {
		return null;
	}
	public TareaModelo insertarTarea(TareaModelo t) throws Exception {
		try {
			//Comprobar que se rellenan todos los atributos
			//de la tarea
			if(t.getTitulo()==null || 
			   t.getDescricion()==null ||
			   t.getEstado()==null || 
			   t.getPrioridad()== null ||
			   t.getFecha_creacion() == null) {
				throw new Exception("Faltan datos de la tarea ");
			}else {
				return tr.save(t);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Error al crear la tarea:"+e.getMessage());
		}
	}
	public TareaModelo modificarTarea(TareaModelo t) {
		return null;
	}
	public boolean borrarTarea(TareaModelo t) {
		return false;
	}
	
}
