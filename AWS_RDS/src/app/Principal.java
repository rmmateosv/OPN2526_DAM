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
				}
			}while(opcion!=0);
		}
		else {
			System.err.println("No hay conexión con la BD");
		}
	}

	private static void listarTareas() {
		// TODO Auto-generated method stub
		ArrayList<Tarea> tareas= bd.obtenerTareas();
		
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
