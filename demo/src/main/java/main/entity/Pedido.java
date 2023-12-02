package main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private double valor;
	
	private String observacao;
	
	private boolean entrega;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Funcionario funcionario;

}
