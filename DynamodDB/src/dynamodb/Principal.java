package dynamodb;

import java.util.Scanner;

public class Principal {
	public static Scanner t = new Scanner(System.in);
	public static AulaDAO bd = new AulaDAO();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (bd.getClienteAN() != null && bd.getClienteBN() != null) {
			int opcion;
			do {
				System.out.println("0-Salir");
				System.out.println("1.- Crear tabla aulas");
				System.out.println("2.- Crear  aula");
				System.out.println("Introduce opción:");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearTablaAulas();
					break;
				case 2:
					insertarAula();
					break;
				}

			} while (opcion != 0);
		} else {
			System.out.println("No se puede conectar con DynamoDB");
		}
	}

	private static void insertarAula() {
		// TODO Auto-generated method stub
		Aula a = new Aula();
		System.out.println("Código");
		a.setCodigo(t.nextLine());
		System.out.println("Metros");
		a.setMetros(t.nextInt());t.nextLine();
		//Comprobar si existe el aula
		Aula aux = bd.obtenerAula(a.getCodigo());
		if(aux==null) {
			if(bd.crearAula(a)) {
				System.out.println("Aula creada");
			}
			else {
				System.out.println("Error: No se ha creado el aula");
			}
		}
		else {
			System.out.println("Error: Aula ya existe");
		}
		
	}

	private static void crearTablaAulas() {
		// TODO Auto-generated method stub
		if(bd.crearTablaAulas()) {
			System.out.println("Tabla creada correctamente");
		}
		else {
			System.out.println("Error al crear la tabla");
		}
	}
}
