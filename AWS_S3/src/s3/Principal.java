package s3;

import java.util.Scanner;

public class Principal {
	
	public static Scanner t = new Scanner(System.in);
	public static AccesoS3 s3 = new AccesoS3();
	
	public static void main(String[] args) {
		int opcion;
		do {
			System.out.println("0-Salir");
			System.out.println("1.- Crear Bucket");
			System.out.println("2.- Listar Bucket");
			System.out.println("3.- Subir fichero Bucket");
			System.out.println("4.- Crear fichero en Bucket");
			System.out.println("5.- Mostrar contenido de objeto");
			System.out.println("6.- Eliminar objeto");
			System.out.println("Introduce opción:");
			opcion = t.nextInt();t.nextLine();
			
			switch (opcion) {
			case 1: {
					crearBucket();
					break;
				}
			
			}
		}while(opcion!=0);
		
	}

	private static void crearBucket() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el nombre del Bucket");
		String nombreB=t.nextLine();
		
		if(s3.crearBucket(nombreB)) {
			System.out.println("Bucket creado");
		}
		else {
			System.err.println("Error al crear el bucket");
		}
		
	}

}
