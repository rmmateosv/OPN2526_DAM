package dynamodb;

import java.util.ArrayList;
import java.util.List;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
@DynamoDbBean
public class Aula {
	private String codigo;
	private int metros;
	private List<Recurso> recursos= new ArrayList();
	
	public Aula() {
		
	}
	@DynamoDbPartitionKey
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@DynamoDbAttribute("metros")
	public int getMetros() {
		return metros;
	}
	public void setMetros(int metros) {
		this.metros = metros;
	}
	@DynamoDbAttribute("recursos")
	public List<Recurso> getRecursos() {
		return recursos;
	}
	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
	@Override
	public String toString() {
		String resultado;
		
		resultado= "Aula [codigo=" + codigo + ", "
				+ "metros=" + metros+ "recursos=[";
		
		for(Recurso r:recursos) {
			resultado+= "[" + r + "]";
		}
		
		resultado+= "]";
		
		return resultado;
	}
	
	
}
