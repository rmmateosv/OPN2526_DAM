package app;

import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
			opcion = t.nextInt();t.nextLine();
			switch (opcion) {
			case 1: 	
					break;
			}
		}while(opcion!=0);
	}
}
