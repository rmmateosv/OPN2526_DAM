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

	public List<TareaModelo> obtenerTareaPorPrioridad(String p) {
		return tr.findByPrioridad(p);
	}
	public List<TareaModelo> obtenerTareaPorEstado(String e) {
		return tr.findByEstado(e);
	}
	public TareaModelo insertarTarea(TareaModelo t) throws Exception {
		try {
			//Comprobar que se rellenan todos los atributos
			//de la tarea
			if(t.getTitulo()==null || 
			   t.getDescripcion()==null ||
			   t.getEstado()==null || 
			   t.getPrioridad()== null ||
			   t.getFecha_creacion() == null) {
				throw new Exception("Faltan datos de la tarea ");
			}else {
				//comprobar prioridad
				if(!t.getPrioridad().equalsIgnoreCase("baja") && 
						!t.getPrioridad().equalsIgnoreCase("media") && 
						!t.getPrioridad().equalsIgnoreCase("alta")) {
					throw new Exception("Priodad incorrecta (baja|media|alta)");
				}
				//comprobar estado
				if(!t.getEstado().equalsIgnoreCase("pendiente") && 
						!t.getEstado().equalsIgnoreCase("iniciado") && 
						!t.getEstado().equalsIgnoreCase("finalizado")) {
					throw new Exception("Estado incorrecto (pendiente|iniciado|finalizado)");
				}	
				return tr.save(t);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Error al crear la tarea:"+e.getMessage());
		}
	}
	public TareaModelo modificarTarea(TareaModelo t) throws Exception {
		//Comprobar que se pasa el id de la tarea a modificar
		if(t.getId()==null) {
			throw new Exception("id es obligatorio");
		}
		//Comprobar que la tarea existe
		TareaModelo tBD = tr.findById(t.getId()).orElse(null);
		if(tBD==null) {
			throw new Exception("No existe la tareas");
		}
		
		//Modificar tarea
		//Modificar los campos que vienen en JSON
		if(t.getTitulo()!=null) {
			tBD.setTitulo(t.getTitulo());
		}
		if(t.getEstado()!=null) {
			tBD.setEstado(t.getEstado());
		}
		if(t.getPrioridad()!=null) {
			tBD.setPrioridad(t.getPrioridad());
		}
		if(t.getDescripcion()!=null) {
			tBD.setDescripcion(t.getDescripcion());
		}
		//Guardar en BD
		return tr.save(tBD);
		
	}
	public boolean borrarTarea(Long id) throws Exception {
		boolean resultado = false;
		
		//Comprobar que la tarea existe
		TareaModelo t = tr.findById(id).orElse(null);
		if(t==null) {
			throw new Exception("No existe la tarea");
		}
		//Borrar la tarea
		tr.delete(t);
		resultado=true;
		return resultado;
	}
	
}
