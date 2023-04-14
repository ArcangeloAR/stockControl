package stockControl.model;

import java.util.Date;

public class Operacao {
	
	private Long id;
	
	private Date data;
	
	private Tipo tipo;
	
	private Produto produto;
	
	private Categoria categoria;
	
	private Produto oldProduto;
	
	private Categoria oldCategoria;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Produto getOldProduto() {
		return oldProduto;
	}

	public void setOldProduto(Produto oldProduto) {
		this.oldProduto = oldProduto;
	}

	public Categoria getOldCategoria() {
		return oldCategoria;
	}

	public void setOldCategoria(Categoria oldCategoria) {
		this.oldCategoria = oldCategoria;
	}
	
}
