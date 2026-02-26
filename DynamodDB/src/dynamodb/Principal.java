package dynamodb;


import java.util.ArrayList;
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
				System.out.println("3.- Mostrar aulas");
				System.out.println("4.- Mostrar un aula");
				System.out.println("5.- Añadir recurso a aula");
				System.out.println("6.- Borrar aula");
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
				case 3:
					mostrarAulas();
					break;
				case 4:
					mostrarAula();
					break;
				case 5:
					addRecurso();
					break;
				case 6:
					borrarAula();
					break;
				}

			} while (opcion != 0);
		} else {
			System.out.println("No se puede conectar con DynamoDB");
		}
	}

	private static boolean addRecurso() {
		// TODO Auto-generated method stub
		System.out.println("Introduce código del aula");
		String codigo  = t.nextLine();
		Aula a = bd.obtenerAula(codigo);
		if(a==null) {
			System.out.println("Error: No existen el aula");
		}
		else {
			System.out.println("Introduce código de recurso");
			int codigoR = t.nextInt(); t.nextLine();
			//Comprobar que no está entre los recursos del aula
			for (Recurso r:a.getRecursos()) {
				if(r.getCodigo()==codigoR) {
					System.out.println("Error: ya existe el recurso");
					return false;
				}
			}
			Recurso r = new Recurso();
			r.setCodigo(codigoR);
			System.out.println("Nombre de recurso");
			r.setNombre(t.nextLine());
			
			
		}
		return true;
	}

	private static void borrarAula() {
		// TODO Auto-generated method stub
		
	}

	private static void mostrarAula() {
		// TODO Auto-generated method stub
		System.out.println("Introduce código del aula");
		String codigo  = t.nextLine();
		Aula a = bd.obtenerAula(codigo);
		if(a==null) {
			System.out.println("Error: No existen el aula");
		}
		else {
			System.out.println(a);
		}
	}

	private static void mostrarAulas() {
		// TODO Auto-generated method stub
		ArrayList<Aula> aulas = bd.obtenerAulas();
		for(Aula a:aulas) {
			System.out.println(a);
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
