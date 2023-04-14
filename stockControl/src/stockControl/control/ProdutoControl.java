package stockControl.control;

import java.text.DecimalFormat;
import java.util.ArrayList;

import stockControl.model.Categoria;
import stockControl.model.Produto;
import stockControl.model.Tipo;

public class ProdutoControl {
	
	private TelaControl screenControl;
	
	private Produto produtoVerificadoAntesDaPersistaencia;
	
	private Integer valorReferenteOpcaoModificaoDados;
	
	public ProdutoControl(TelaControl screenControl) {
		super();
		this.screenControl = screenControl;
	}
	
	public Boolean checkInputSubMenu(String input) {
		if(input.equals("0") || input.equals("1") || input.equals("2") 
				|| input.equals("3") || input.equals("4")) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean checkCancela(String cancelar) {
		if(cancelar.equals("@")) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean checkId(String id) {
		try {
			Long idFormatted;
			idFormatted = Long.parseLong(id);
			if(idFormatted <= 0) {
				throw new IllegalArgumentException("");
			}
		} catch(Exception erro){
				return false;
			}
		return true;
	}
	
	public Boolean checkNomeParaRetiradaOuEdicao(String nome) {
		Boolean validaNome = false;
		try {
			ArrayList<Produto> listaDeProdutos = screenControl.getStarter().getRepository().getRepositoryProduto();
			
			for(Produto produtoNaLista : listaDeProdutos) {
				if(produtoNaLista.getNome().equals(nome)) {
					validaNome = true;
				}
			}
		} catch(Exception erro) {
			validaNome = false;
		}
		return validaNome;
	}
	
	public Boolean checkNomeOuCodigo(String nomeOuCodigo, String tipo) {
		try {
			Integer limiteBaixo = 0;
			Integer limiteAlto = 250;
			if(tipo.equals("nome")) {
				limiteBaixo = 2;
			}
			if(nomeOuCodigo.length() < limiteBaixo || nomeOuCodigo.length() >= limiteAlto) {
				throw new IllegalArgumentException("");
			}
		} catch(Exception erro){
				return false;
			}
		return true;
	}
	
	public Boolean checkQuantidadeOuValorTotal(String quantidadeOuValorTotal) {
		try {
			Double quantidadeOuValorTotalFormatted;
			quantidadeOuValorTotalFormatted = Double.parseDouble(quantidadeOuValorTotal);
			if(quantidadeOuValorTotalFormatted <= 0) {
				throw new IllegalArgumentException("");
			}
		} catch(Exception erro){
				return false;
			}
		return true;
	}
	
	public void addInformacoesObjetoProduto(String tipoDeValor, String valorNomeOuCodigo, Double valorQuantidadeOuValorTotal, String categoria) {
		if(produtoVerificadoAntesDaPersistaencia == null) {
			produtoVerificadoAntesDaPersistaencia = new Produto();
		}
		if(tipoDeValor.equals("nome")) {
			produtoVerificadoAntesDaPersistaencia.setNome(valorNomeOuCodigo);
		}
		if(tipoDeValor.equals("quantidade")) {
			produtoVerificadoAntesDaPersistaencia.setQuantidade(valorQuantidadeOuValorTotal);
		}
		if(tipoDeValor.equals("valor total")) {
			produtoVerificadoAntesDaPersistaencia.setValorTotal(valorQuantidadeOuValorTotal);
		}
		if(tipoDeValor.equals("codigo")) {
			produtoVerificadoAntesDaPersistaencia.setCodigo(valorNomeOuCodigo);
		}
		if(tipoDeValor.equals("categoria")) {
			if(produtoVerificadoAntesDaPersistaencia.getCategoria() == null) {
				produtoVerificadoAntesDaPersistaencia.setCategoria(new Categoria());
			}
			produtoVerificadoAntesDaPersistaencia.getCategoria().setTitulo(categoria);
		}
	}
	
	public void addProduto(Produto novoProduto) {
		if(checkIdProduto(novoProduto.getId())) {
			Integer sizeRepositoryProduto = (getScreenControl().getStarter().getRepository().getRepositoryProduto().size());
			Long idNewProduto = (long) (sizeRepositoryProduto + 1);
			novoProduto.setId(idNewProduto);
			produtoVerificadoAntesDaPersistaencia.setId(idNewProduto);
		}
			getScreenControl().getStarter().getRepository().getRepositoryProduto().add(novoProduto);
			
			getScreenControl().getOperacaoControl().addOperacao(Tipo.Entrada, novoProduto, novoProduto.getCategoria(), null, null);
	}
		
	
	public Boolean checkIdProduto(Long id) {
		if(id != null) {
			for(Produto produtoInRepository : getScreenControl().getStarter().getRepository().getRepositoryProduto()) {
				boolean checkId = true;
					if(id == produtoInRepository.getId()) {
						checkId = false;
						produtoVerificadoAntesDaPersistaencia.setId(produtoInRepository.getId());
						break;
					}
				}
		}
		return true;
	}
	
	public void verificarProdutoAntesDaPersistaencia(Produto produto, String origem) {
		Produto produtoAntigo = produto;
		Boolean controlaValidacaoDados = true;
		Boolean controlaAlteracaoSeguida = origem == "add" ? true : false;
		
		while(controlaValidacaoDados) {
			constroiRegistaroNaTela(produto);
			if(controlaAlteracaoSeguida) {
				System.out.print("\nCaso deseje alterar algum dado antes do registro ser salvo, por gentileza digite '#' e, em seguida, clique no botão 'Enter'."
						+ "\nCaso deseje prosseguir, clique apenas no botão 'Enter'.");
				System.out.print("\n-> ");
				screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
				screenControl.setScreenInput(screenControl.getScreenInput() == null || screenControl.getScreenInput().equals("") ? " " : screenControl.getScreenInput());
				if(checkCancela(screenControl.getScreenInput())) {
					controlaValidacaoDados = false;
					throw new IllegalArgumentException("");
				}
			} else {
				screenControl.setScreenInput("#");
				controlaAlteracaoSeguida = true;
			}
			if(screenControl.getScreenInput().equals("#")) {
				verificaOpcoesDeModificaoDoProdutoAntesDaPersistaencia(produto);
				produto.setId(produtoVerificadoAntesDaPersistaencia.getId() != null ? produtoVerificadoAntesDaPersistaencia.getId() : produto.getId());
				produto.setNome(produtoVerificadoAntesDaPersistaencia.getNome() != null ? produtoVerificadoAntesDaPersistaencia.getNome() : produto.getNome());
				produto.setCategoria(produtoVerificadoAntesDaPersistaencia.getCategoria() != null ? produtoVerificadoAntesDaPersistaencia.getCategoria() : produto.getCategoria());
				produto.setCodigo(produtoVerificadoAntesDaPersistaencia.getCodigo() != null ? produtoVerificadoAntesDaPersistaencia.getCodigo() : produto.getCodigo());
				produto.setQuantidade(produtoVerificadoAntesDaPersistaencia.getQuantidade() != null ? produtoVerificadoAntesDaPersistaencia.getQuantidade() : produto.getQuantidade());
				produto.setValorTotal(produtoVerificadoAntesDaPersistaencia.getValorTotal() != null ? produtoVerificadoAntesDaPersistaencia.getValorTotal() : produto.getValorTotal());
				if(valorReferenteOpcaoModificaoDados != 6) {
					controlaValidacaoDados = true;
				}
			} else {
					if(origem.equals("edit")) {
						ArrayList<Produto> produtoLista = screenControl.getStarter().getRepository().getRepositoryProduto();
						for(Produto produtoEmEstoque : produtoLista) {
							if(produtoEmEstoque.getNome().equals(produto.getNome())) {
								produtoEmEstoque.setQuantidade(produtoEmEstoque.getQuantidade() + produtoAntigo.getQuantidade());
								produtoEmEstoque.setValorTotal(produtoEmEstoque.getValorTotal() + produtoAntigo.getValorTotal());
//								screenControl.getOperacaoControl().addOperacao(Tipo.Entrada, produto, produto.getCategoria(), produtoAntigo, produtoAntigo.getCategoria());
								break;
							}
						}
					} else {
						screenControl.getProdutoControl().addProduto(produto);
//						screenControl.getOperacaoControl().addOperacao(Tipo.Entrada, produto, produto.getCategoria(), null, null);	
					}
					controlaValidacaoDados = false;
				}
			
		}
	}
	
	public void constroiRegistaroNaTela(Produto produto) {
		String tituloDaTabela = "Verificação Do Produto Antes De Seu Registro";
		Integer numeroDeCampos = 6;
		String linhaTitulos = "";
		String tamanhoLinhaTabela = "";
		String primeiraLinha = "";
		String log = "";
		Double valorUnitario = (produto.getValorTotal() / produto.getQuantidade());
		String valorUnitarioFormated = new DecimalFormat("#,##0.00").format(valorUnitario);
		String tamanhoLinhaMescladaEsquerda = "";
		String tamanhoLinhaMescladaDireita = "";
		
		for(String legendaPrimeiraLinha : screenControl.getStarter().getRepository().getRepositoryProdutoPrimeiraLinha()) {
			linhaTitulos = linhaTitulos + screenControl.formatarCelula(legendaPrimeiraLinha);
		}
		
		tamanhoLinhaTabela = getScreenControl().formatarTamanhoLinhaTabela(numeroDeCampos);
		tamanhoLinhaMescladaEsquerda = getScreenControl().formatarTamanhoLinhaMesclada(numeroDeCampos, tituloDaTabela, true);
		tamanhoLinhaMescladaDireita = getScreenControl().formatarTamanhoLinhaMesclada(numeroDeCampos, tituloDaTabela, false);
		System.out.print("\n" + tamanhoLinhaTabela + "\n" + tamanhoLinhaMescladaEsquerda + tituloDaTabela + tamanhoLinhaMescladaDireita);
		System.out.print("\n" + tamanhoLinhaTabela + "\n" + linhaTitulos + "\n" + tamanhoLinhaTabela + "\n");
		
		log = screenControl.formatarCelula(produto.getNome())
		+ screenControl.formatarCelula(screenControl.formatarMoeda(produto.getQuantidade().toString()))
		+ screenControl.formatarCelula(screenControl.formatarMoeda(valorUnitarioFormated.equals("∞") ? "0,00" : valorUnitarioFormated))
		+ screenControl.formatarCelula(screenControl.formatarMoeda(produto.getValorTotal().toString()))
		+ screenControl.formatarCelula(produto.getCodigo()) 
		+ screenControl.formatarCelula(produto.getCategoria().getTitulo());
		
		System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
		
	}
	
	public void verificaOpcoesDeModificaoDoProdutoAntesDaPersistaencia(Produto produto) {
		screenControl.getProdutoAddEditRemoveView().verificarOpcoesModificaoProdutoAntesDaPersistaencia();
		screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
		valorReferenteOpcaoModificaoDados = Integer.parseInt(screenControl.getScreenInput());
		switch(valorReferenteOpcaoModificaoDados) {
		case 1:
			screenControl.getProdutoAddEditRemoveView().addNomeOuCodigoProduto("nome");
			break;
		case 2:
			screenControl.getProdutoAddEditRemoveView().addQuantidadeOuValorTotalProduto("quantidade");
			break;
		case 3:
			screenControl.getProdutoAddEditRemoveView().addQuantidadeOuValorTotalProduto("valor total");
			break;
		case 4:
			screenControl.getProdutoAddEditRemoveView().addNomeOuCodigoProduto("codigo");
			break;
		case 5:
			screenControl.getProdutoAddEditRemoveView().addCategoriaProduto();
			break;
		case 6:

			break;
		}
	}
	
	public void editProduto(String nome, Double quantidade) {
		try {
			ArrayList<Produto> listaDeProdutos = screenControl.getStarter().getRepository().getRepositoryProduto();
			for(Produto produtoNaLista : listaDeProdutos) {
				if(produtoNaLista.getNome().equals(nome)) {
					if(produtoNaLista.getQuantidade() < quantidade) {
						throw new IllegalArgumentException("");
					} else {
						Produto produtoAntigo = produtoNaLista;
						produtoNaLista.setQuantidade(produtoNaLista.getQuantidade() - quantidade);
						screenControl.getOperacaoControl().addOperacao(Tipo.Saída, produtoAntigo, produtoAntigo.getCategoria(), produtoNaLista, produtoNaLista.getCategoria());
					}
					break;
				}
			}
		} catch(Exception erro) {
			System.err.print("\nNão foi possível efetuar o processo de retirada do produto. A quantidade a ser retirada é maior do que a que está em estoque. Por gentileza, verifique e tente novamente!");
		}
	}
	
	public void retiraProduto(String nome, Double quantidade) {
		try {
			ArrayList<Produto> listaDeProdutos = screenControl.getStarter().getRepository().getRepositoryProduto();
			for(Produto produtoNaLista : listaDeProdutos) {
				if(produtoNaLista.getNome().equals(nome)) {
					if(produtoNaLista.getQuantidade() < quantidade) {
						throw new IllegalArgumentException("");
					} else {
						Produto produtoAntigo = produtoNaLista;
						produtoNaLista.setQuantidade(produtoNaLista.getQuantidade() - quantidade);
						screenControl.getOperacaoControl().addOperacao(Tipo.Saída, produtoAntigo, produtoAntigo.getCategoria(), produtoNaLista, produtoNaLista.getCategoria());
					}
					break;
				}
			}
		} catch(Exception erro) {
			System.err.print("\nNão foi possível efetuar o processo de retirada do produto. A quantidade a ser retirada é maior do que a que está em estoque. Por gentileza, verifique e tente novamente!");
		}
	}
	
	public TelaControl getScreenControl() {
		return screenControl;
	}

	public void setScreenControl(TelaControl screenControl) {
		this.screenControl = screenControl;
	}

	public Produto getProdutoVerificadoAntesDaPersistaencia() {
		if(produtoVerificadoAntesDaPersistaencia == null) {
			produtoVerificadoAntesDaPersistaencia = new Produto();
		}
		return produtoVerificadoAntesDaPersistaencia;
	}

	public void setProdutoVerificadoAntesDaPersistaencia(Produto produtoVerificadoAntesDaPersistaencia) {
		this.produtoVerificadoAntesDaPersistaencia = produtoVerificadoAntesDaPersistaencia;
	}
	
}
