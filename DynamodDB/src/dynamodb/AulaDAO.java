package dynamodb;

import java.nio.file.Paths;
import java.util.ArrayList;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.profiles.ProfileFile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.BillingMode;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

public class AulaDAO {
	// Declaraciones de clientes
	// Bajo nivel => para crear la tabla
	private DynamoDbClient clienteBN;

	// Alto nivel => para CRUD en tabla
	private DynamoDbEnhancedClient clienteAN;
	private DynamoDbTable<Aula> tAula;

	public AulaDAO() {
		try {
			// Crear los clientes

			// 1º Obtener credenciales de acceso a AWS
			// .env bajo la identificación DynamoDB
			ProfileCredentialsProvider credenciales = ProfileCredentialsProvider.builder()
					.profileFile(
							ProfileFile.builder().type(ProfileFile.Type.CREDENTIALS).content(Paths.get(".env")).build())
					.profileName("DynamoDB").build();
			// Crear el cliente de bajo nivel
			clienteBN = DynamoDbClient.builder().region(Region.US_EAST_1).credentialsProvider(credenciales).build();
			// Crear el cliente de alto nivel
			clienteAN = DynamoDbEnhancedClient
					.builder()
					.dynamoDbClient(clienteBN)
					.build();
			//Mapear tabla Aulas de DynamoDB
			tAula=clienteAN.table("Aulas", 
					TableSchema.fromBean(Aula.class));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public DynamoDbClient getClienteBN() {
		return clienteBN;
	}

	public void setClienteBN(DynamoDbClient clienteBN) {
		this.clienteBN = clienteBN;
	}

	public DynamoDbEnhancedClient getClienteAN() {
		return clienteAN;
	}

	public void setClienteAN(DynamoDbEnhancedClient clienteAN) {
		this.clienteAN = clienteAN;
	}

	public boolean crearTablaAulas() {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			CreateTableRequest peticion = CreateTableRequest.builder()
					.tableName("Aulas")
					.keySchema(KeySchemaElement.builder()
								.attributeName("codigo")
								.keyType(KeyType.HASH)
								.build()
								)
					.attributeDefinitions(AttributeDefinition.builder()
								.attributeName("codigo")
								.attributeType(ScalarAttributeType.S)
								.build()
								)
					.billingMode(BillingMode.PAY_PER_REQUEST)
					.build();
			//Ejecutar petición de crear tabla
			clienteBN.createTable(peticion);
			resultado=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Aula obtenerAula(String codigo) {
		// TODO Auto-generated method stub
		Aula resultado = null;
		try {
			return tAula.getItem(Key.builder()
									.partitionValue(codigo)
									.build());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearAula(Aula a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			tAula.putItem(a);
			resultado=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Aula> obtenerAulas() {
		// TODO Auto-generated method stub
		ArrayList<Aula> resultado =new ArrayList<Aula>();
		try {
			PageIterable<Aula> datos = tAula.scan();
			for(Aula a:datos.items()) {
				resultado.add(a);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

}
