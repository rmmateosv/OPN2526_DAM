package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.modelos.TareaModelo;


@Repository
public interface TareaRepositorio 
	extends JpaRepository<TareaModelo, Long>{

    //Podemos usar métodos para hacer CRUD
    //Sin tener que programar nada
    
    //Definimos métodos particulares
    //Obtener tareas por prioridad
  
    List<TareaModelo> findByPrioridad(String prioridad);
	
	//Obtener tareas por estado
    List<TareaModelo> findByEstado(String estado);
	
	
}
