package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.example.demo.modelos.TareaModelo;

import jakarta.persistence.EntityManager;

public interface TareaRepositorio 
	extends JpaRepository<TareaModelo, Long>{

    //Podemos usar métodos para hacer CRUD
    //Sin tener que programar nada
    
    //Definimos métodos particulares
    //Obtener tareas por prioridad
    @Query("SELECT t FROM TareaModelo t WHERE t.prioridad = :prioridad")
    List<TareaModelo> findByPrioridad(String prioridad);
	
	//Obtener tareas por estado
	
	
}
