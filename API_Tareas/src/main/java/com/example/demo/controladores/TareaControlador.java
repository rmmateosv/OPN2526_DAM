package com.example.demo.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelos.TareaModelo;
import com.example.demo.servicios.TareaServicio;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*")
public class TareaControlador {

	private final TareaServicio ts;

	public TareaControlador(TareaServicio ts) {
		this.ts = ts;
	}
	
	@GetMapping("/mostrar")
	public List<TareaModelo> obtenerTodas(){
		return ts.obtenerTareas();
	}
	
	@PostMapping("crear")
	public TareaModelo 
		crearTarea(@RequestBody TareaModelo t) 
				throws Exception {
		return ts.insertarTarea(t);
	}
	
	@PutMapping("modificar")
	public TareaModelo modificarTarea(@RequestBody TareaModelo t) 
			throws Exception {
		return ts.modificarTarea(t);
	}
	
	
}
