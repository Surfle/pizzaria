package main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	@OneToOne
	private Funcionario funcionario;
	@OneToOne
	private Cliente cliente;
	@OneToOne
	private Sabor sabor;
	@OneToOne
	private Produto produto;

}
