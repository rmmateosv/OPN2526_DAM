package app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Principal {
	public static Scanner te = new Scanner(System.in);
	public static DAO bd = new DAO();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			int opcion;
			do {
				System.out.println("0-Salir");
				System.out.println("1.- Crear Tarea");
				System.out.println("2.- Listar Tareas");
				System.out.println("3.- Mostrar por ID");
				System.out.println("4.- Mostrar por estado");
				System.out.println("5.- Modificar Tarea");
				System.out.println("6.- Eliminar Tarea");
				System.out.println("7.- Mostrar por Fecha");
				System.out.println("Introduce opción:");
				opcion = te.nextInt();te.nextLine();
				switch (opcion) {
				case 1: 
					crearTarea();
					break;
				case 2: 
					listarTareas();
					break;
				case 3: 
					listarTareasPorId();
					break;
				case 4: 
					listarTareasPorEstado();
					break;
				case 5: 
					modificarTarea();
					break;
				}
			}while(opcion!=0);
		}
		else {
			System.err.println("No hay conexión con la BD");
		}
	}

	private static void modificarTarea() {
		// TODO Auto-generated method stub
		listarTareas();
		System.out.println("Introduce ID:");
		int id = te.nextInt(); te.nextLine();
		//Comprobar si la tarea existe
		Tarea t = bd.obtenerTareas(id);
		if(t==null) {
			System.out.println("No existe esa tarea");
		}
		else {
			
		}
	}

	private static void listarTareasPorEstado() {
		// TODO Auto-generated method stub
		System.out.println("Estado(Pendiente|En proceso|Finalizada):");
		String estado = te.nextLine();
		ArrayList<Tarea> tareas= bd.obtenerTareas(estado);
		for(Tarea t : tareas) {
			System.out.println(t);
		}
	}

	private static void listarTareasPorId() {
		// TODO Auto-generated method stub
		System.out.println("Introduce ID");
		int id = te.nextInt();te.nextLine();
		Tarea tarea= bd.obtenerTareas(id);
		if(tarea!=null) {
			System.out.println(tarea);
		}
		else {
			System.out.println("No ha encontrado la tarea");
		}
	}

	private static void listarTareas() {
		// TODO Auto-generated method stub
		ArrayList<Tarea> tareas= bd.obtenerTareas();
		for(Tarea t : tareas) {
			System.out.println(t);
		}	
	}

	private static void crearTarea() {
		// TODO Auto-generated method stub
		Tarea t = new Tarea();
		System.out.println("Título");
		t.setTitulo(te.nextLine());
		System.out.println("Descripción");
		t.setDescripcion(te.nextLine());
		System.out.println("Prioridad (baja/media/alta)");
		t.setPrioridad(te.nextLine());
		if(bd.crearTarea(t)) {
			System.out.println("Tarea creada. ID:"+t.getId());
		}
		else {
			System.err.println("No se ha creado la tarea");
		}
		
	}
	
}
