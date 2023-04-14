package stockControl.utilities;

import java.util.ArrayList;

import stockControl.model.Categoria;
import stockControl.model.Operacao;
import stockControl.model.Produto;
import stockControl.model.Tipo;

public class Repositorio {
	
	private ArrayList<String> repositoryCategoriaPrimeiraLinha;
	private ArrayList<String> repositoryOperacaoPrimeiraLinha;
	private ArrayList<String> repositoryOperacaoLogPrimeiraLinha;
	private ArrayList<String> repositoryProdutoPrimeiraLinha;
	private ArrayList<Categoria> repositoryCategoria;
	private ArrayList<Operacao> repositoryOperacao;
	private ArrayList<Produto> repositoryProduto;

	
	private Starter starter;
	

	public Repositorio(Starter starter) {
		super();
		this.setStarter(starter);
	}
	
	public void starRepoitories() {
		getRepositoryOperacaoPrimeiraLinha();
		getRepositoryCategoria();
		getRepositoryOperacao();
		getRepositoryProduto();
	}

	
	public void starFillRepoitoryTest() {
		try {
			while(getRepositoryCategoria().size() < 6) {
				Integer value = getRepositoryCategoria().size() + 1;
				Categoria novasCategoriasEmCascata = new Categoria();
				novasCategoriasEmCascata.setId((long) value);
				novasCategoriasEmCascata.setTitulo("Teste " + value);
				novasCategoriasEmCascata.setListaProduto(new ArrayList<Produto>());
				getStarter().getScreenControl().getCategoriaControl().addCategoria(novasCategoriasEmCascata);
			}
			
			ArrayList<Categoria> listaDeCategoriasPersistaidas = starter.getRepository().getRepositoryCategoria();
			while(getRepositoryProduto().size() < 8) {
				Integer value;
				Produto produtoEmCascata = new Produto();
				Categoria categoriaDosProdutosEmCascata = new Categoria();	
				
				if(getRepositoryProduto().size() < 6) {
					value = getRepositoryProduto().size() + 1;
				} else {
					value = getRepositoryProduto().size() - 1;
				}
				
				produtoEmCascata.setId((long) value);
				produtoEmCascata.setNome("Testador " + value.toString());
				produtoEmCascata.setQuantidade(1.0 + value);
				produtoEmCascata.setValorTotal(7.8 + (value * 4));
				produtoEmCascata.setCodigo("Testeiro "+ value.toString());
				for(Categoria categoria : listaDeCategoriasPersistaidas) {
					if(categoria.getTitulo().equals("Teste " + value.toString())) {
						categoriaDosProdutosEmCascata = categoria;
						produtoEmCascata.setCategoria(categoriaDosProdutosEmCascata);
						if(categoria.getListaProduto() == null) {
							categoria.setListaProduto(new ArrayList<Produto>());
						}
						categoria.getListaProduto().add(produtoEmCascata);
					}
				}
				
				
				getStarter().getScreenControl().getProdutoControl().setProdutoVerificadoAntesDaPersistaencia(produtoEmCascata);
				getStarter().getScreenControl().getProdutoControl().addProduto(produtoEmCascata);
				Produto produtoRecentlyAdded = getStarter().getRepository().getRepositoryProduto().get(getStarter().getRepository().getRepositoryProduto().size() - 1);
			}
			getStarter().getScreenControl().getProdutoControl().setProdutoVerificadoAntesDaPersistaencia(new Produto());
			System.out.print("\nPreenchimento de dados efetuado com sucesso!\n");
		} catch (Exception e) {
			System.out.print("\nNão foi possível efetuar o preenchimento dos dados para teste.");
		}
	}
	
	
	public ArrayList<String> getRepositoryCategoriaPrimeiraLinha() {
		if(repositoryCategoriaPrimeiraLinha == null) {
			repositoryCategoriaPrimeiraLinha = new ArrayList<String>();
			repositoryCategoriaPrimeiraLinha.add("Id");
			repositoryCategoriaPrimeiraLinha.add("Titulo");
		}
		return repositoryCategoriaPrimeiraLinha;
	}

	public void setRepositoryCategoriaPrimeiraLinha(ArrayList<String> repositoryCategoriaPrimeiraLinha) {
		this.repositoryCategoriaPrimeiraLinha = repositoryCategoriaPrimeiraLinha;
	}

	public ArrayList<String> getRepositoryOperacaoPrimeiraLinha() {
		if(repositoryOperacaoPrimeiraLinha == null) {
			repositoryOperacaoPrimeiraLinha = new ArrayList<String>();
			repositoryOperacaoPrimeiraLinha.add("Operação");
			repositoryOperacaoPrimeiraLinha.add("Quantidade");
			repositoryOperacaoPrimeiraLinha.add("Produto");
			repositoryOperacaoPrimeiraLinha.add("Val. Uni.");
			repositoryOperacaoPrimeiraLinha.add("Val. Tot.");
			repositoryOperacaoPrimeiraLinha.add("Categoria");
			repositoryOperacaoPrimeiraLinha.add("Data");
			repositoryOperacaoPrimeiraLinha.add("Horário");
		}
		return repositoryOperacaoPrimeiraLinha;
	}

	public void setRepositoryOperacaoPrimeiraLinha(ArrayList<String> repositoryOperacaoPrimeiraLinha) {
		this.repositoryOperacaoPrimeiraLinha = repositoryOperacaoPrimeiraLinha;
	}
	

	public ArrayList<String> getRepositoryOperacaoLogPrimeiraLinha() {
		if(repositoryOperacaoLogPrimeiraLinha == null) {
			repositoryOperacaoLogPrimeiraLinha = new ArrayList<String>();
			repositoryOperacaoLogPrimeiraLinha.add("Tipo");
			repositoryOperacaoLogPrimeiraLinha.add("Classificação");
			repositoryOperacaoLogPrimeiraLinha.add("Campo(s) Alterado(s)");
			repositoryOperacaoLogPrimeiraLinha.add("Antigo(s) Valor(es)");
			repositoryOperacaoLogPrimeiraLinha.add("Novo(s) Valor(es)");
			repositoryOperacaoLogPrimeiraLinha.add("Data");
			repositoryOperacaoLogPrimeiraLinha.add("Horário");
		}
		return repositoryOperacaoLogPrimeiraLinha;
	}

	public void setRepositoryOperacaoLogPrimeiraLinha(ArrayList<String> repositoryOperacaoLogPrimeiraLinha) {
		this.repositoryOperacaoLogPrimeiraLinha = repositoryOperacaoLogPrimeiraLinha;
	}

	public ArrayList<String> getRepositoryProdutoPrimeiraLinha() {
		if(repositoryProdutoPrimeiraLinha == null) {
			repositoryProdutoPrimeiraLinha = new ArrayList<String>();
			repositoryProdutoPrimeiraLinha.add("Nome");
			repositoryProdutoPrimeiraLinha.add("Quantidade");
			repositoryProdutoPrimeiraLinha.add("Val. Uni.");
			repositoryProdutoPrimeiraLinha.add("Val. Tot.");
			repositoryProdutoPrimeiraLinha.add("Código");
			repositoryProdutoPrimeiraLinha.add("Categoria");
			
		}
		return repositoryProdutoPrimeiraLinha;
	}

	public void setRepositoryProdutoPrimeiraLinha(ArrayList<String> repositoryProdutoPrimeiraLinha) {
		this.repositoryProdutoPrimeiraLinha = repositoryProdutoPrimeiraLinha;
	}

	public ArrayList<Categoria> getRepositoryCategoria() {
		if(repositoryCategoria == null) {
			repositoryCategoria= new ArrayList<Categoria>();
		}
		return repositoryCategoria;
	}

	public void setRepositoryCategoria(ArrayList<Categoria> repositoryCategoria) {
		this.repositoryCategoria = repositoryCategoria;
	}

	public ArrayList<Operacao> getRepositoryOperacao() {
		if(repositoryOperacao == null) {
			repositoryOperacao = new ArrayList<Operacao>();
		}
		return repositoryOperacao;
	}

	public void setRepositoryOperacao(ArrayList<Operacao> repositoryOperacao) {
		this.repositoryOperacao = repositoryOperacao;
	}

	public ArrayList<Produto> getRepositoryProduto() {
		if(repositoryProduto == null) {
			repositoryProduto = new ArrayList<Produto>();
		}
		return repositoryProduto;
	}

	public void setRepositoryProduto(ArrayList<Produto> repositoryProduto) {
		this.repositoryProduto = repositoryProduto;
	}

	public Starter getStarter() {
		return starter;
	}

	public void setStarter(Starter starter) {
		this.starter = starter;
	}
	
}
