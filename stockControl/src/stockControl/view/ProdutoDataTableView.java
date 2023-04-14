package stockControl.view;

import java.util.ArrayList;

import stockControl.control.TelaControl;
import stockControl.model.Produto;

public class ProdutoDataTableView {
	
	private TelaControl screenControl;
	
	
	public ProdutoDataTableView(TelaControl screenControl) {
		super();
		this.screenControl = screenControl;
	}
	
	
	public void showAddRemoveProduto() {
		Boolean controlaValidacaoAddRemove = true;
		try {
			System.out.print("\nPor gentileza, digite o número referente a opção abaixo e, em seguida, 'Enter' para ser redirecionado ao que deseja: "
					+ "\n\t1. Adicionar Produtos ao Estoque;"
					+ "\n\t2. Retirar Produtos do Estoque;"
					+ "\n\t3. Editar Produtos do Estoque;"
					+ "\n\t4. Voltar ao Menu Principal;");
			while(controlaValidacaoAddRemove) {
				System.out.print("\n-> ");
				screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
				if(screenControl.getProdutoControl().checkInputSubMenu(screenControl.getScreenInput())) {
					controlaValidacaoAddRemove = false;
					if(screenControl.getScreenInput().equals("1")) {
						showAddProduto();
					} else if(screenControl.getScreenInput().equals("2")) {
						showRemoveProduto();
					} else if(screenControl.getScreenInput().equals("3")) {
						showEditProduto();
					} else if(screenControl.getScreenInput().equals("4")) {
						screenControl.setReload(true);
					}
				} else {
					System.out.print("\nPor gentileza, tente novamente seguindo as especificações.\n");
				}
			}
		} catch(Exception erro) {
			System.err.print("\nNão foi possível efetuar o processo!\n");
			System.out.print(getScreenControl().getCorteVerticalDasTelas());
		}
	}
	
	public void showAddProduto() {
		try {
			System.out.print("\nCaso queira cancelar a inserção do produto, digite '@' e clique no botão 'Enter' à qualquer momento.");
			if(screenControl.getProdutoControl().getProdutoVerificadoAntesDaPersistaencia() == null) {
				screenControl.getProdutoControl().setProdutoVerificadoAntesDaPersistaencia(new Produto());
			}
			addNomeOuCodigoProduto("nome");
			addQuantidadeOuValorTotalProduto("quantidade");
			addQuantidadeOuValorTotalProduto("valor total");
			addNomeOuCodigoProduto("codigo");
			addCategoriaProduto();
			screenControl.getProdutoControl().verificarProdutoAntesDaPersistaencia(screenControl.getProdutoControl().getProdutoVerificadoAntesDaPersistaencia(), "add");
			
			System.out.print("\nProduto inserido com sucesso!");
		} catch(Exception erro) {
			System.err.print("\nNão foi possível salvar o produto!\n");
		}
	}
	
	public void addNomeOuCodigoProduto(String nomeOuCodigo) {
		Boolean controlaNomeOuCodigoProduto = true;
		
		System.out.print("\nPor gentileza, informe o " + nomeOuCodigo.toUpperCase() + " do produto que está sendo ");
		if(screenControl.getProdutoControl().getProdutoVerificadoAntesDaPersistaencia().getNome() != null) {
			System.out.print("modificado:");
		} else {
			System.out.print("inserido:");
		}
		while(controlaNomeOuCodigoProduto) {
			System.out.print("\n-> ");
			screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
			if(screenControl.getProdutoControl().checkCancela(screenControl.getScreenInput())) {
				controlaNomeOuCodigoProduto = false;
				throw new IllegalArgumentException("");
			} else {
				if(screenControl.getProdutoControl().checkNomeOuCodigo(screenControl.getScreenInput(), nomeOuCodigo)) {
					screenControl.getProdutoControl().addInformacoesObjetoProduto(nomeOuCodigo, screenControl.getScreenInput(),  null, null);
					controlaNomeOuCodigoProduto = false;
				} else {
					formattedError(nomeOuCodigo);
					System.out.print(getScreenControl().getCorteVerticalDasTelas());
				}
			}
		}
	}
	
	public void addQuantidadeOuValorTotalProduto(String quantidadeOuValorTotal) {
		Boolean controlaValidacaoQuantidade = true;
		String controlaTerminologia =  "";
		System.out.print("\nPor gentileza, informe ");
		if(quantidadeOuValorTotal.equals("quantidade")) {
			controlaTerminologia = "a";
		} else if(quantidadeOuValorTotal.equals("valor total")) {
			controlaTerminologia = "o";
		}
		System.out.print(controlaTerminologia + " " + quantidadeOuValorTotal.toUpperCase() + " do produto que está sendo ");
				if(screenControl.getProdutoControl().getProdutoVerificadoAntesDaPersistaencia().getQuantidade() != null) {
					System.out.print("modificad" + controlaTerminologia);
				} else {
					System.out.print("inserid" + controlaTerminologia);
				}
				System.out.print(". Esta informação é obrigatória e deverá ser maior que 0:\n");
		while(controlaValidacaoQuantidade) {
			System.out.print("-> ");
			screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
			screenControl.setScreenInput(screenControl.getScreenInput().replace(",", "."));
			if(screenControl.getProdutoControl().checkCancela(screenControl.getScreenInput())) {
				controlaValidacaoQuantidade = false;
				throw new IllegalArgumentException("");
			} else {
				if(screenControl.getProdutoControl().checkQuantidadeOuValorTotal(screenControl.getScreenInput())) {
					Double valorFormatado = Double.parseDouble(screenControl.getScreenInput());
					screenControl.getProdutoControl().addInformacoesObjetoProduto(quantidadeOuValorTotal, null, valorFormatado, null);
					controlaValidacaoQuantidade = false;
				} else {
					formattedError(quantidadeOuValorTotal);
					System.out.print(getScreenControl().getCorteVerticalDasTelas());
				}
			}
		}
	}
	
	public void addCategoriaProduto() {
		Boolean controlaCategoriaProduto = true;
		Boolean controlaViewListaCategoriaProduto = true;
		
		System.out.print("\nPor gentileza, será necessário o título da CATEGORIA referente ao produto que está sendo inserido, esta informação é obrigatória "
				+ "(caso não haja a categoria informada, ela será inserida automaticamente)."
				+ "\nCaso queira ver a lista de categorias existentes antes de informar seu título digite '!' e, em seguida, clique no botão 'Enter'.");

		while(controlaCategoriaProduto) {
			while(controlaViewListaCategoriaProduto) {
				controlaViewListaCategoriaProduto = false;
				
				System.out.print("\n-> ");
				screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
				if(screenControl.getScreenInput().equals("!")) {
					getScreenControl().getCategoriaListaView().showCategoriaLista("insert");
					controlaViewListaCategoriaProduto = true;
					
				System.out.print("\nCaso queira ver a lista de categorias existentes novamente, digite '!' e, em seguida, clique no botão 'Enter'."
						+ "\nCaso queira prosseguir prosseguir com a inclusão do produto, por gentileza informe o título da CATEGORIA");
				}
			}
			if(screenControl.getProdutoControl().checkCancela(screenControl.getScreenInput())) {
				controlaCategoriaProduto = false;
				throw new IllegalArgumentException("");
			} else {
				screenControl.getProdutoControl().addInformacoesObjetoProduto("categoria", null,  null, screenControl.getScreenInput());
				if(screenControl.getCategoriaControl().checkCategoria(screenControl.getScreenInput())) {
					controlaCategoriaProduto = false;
				} else {
					formattedError("categoria");
					controlaViewListaCategoriaProduto = true;
				}
			}
		}
	}
	
	public void formattedError(String atributo) {
		System.err.print("\nPor gentileza, o valor referente ao campo '" + atributo);
		if(atributo.equals("nome") || atributo.equals("codigo") || atributo.equals("categoria")) {
			System.err.print("' deverá conter no máximo 250 caracteres, ");
		}
		if(atributo.equals("quantidade") || atributo.equals("valor total")) {
			System.err.print("' deverá ser maior que zero, ");
		}
		System.err.print("tente novamente seguindo as especificações.");
		System.out.print("\nOu, caso queira cancelar a inserção do produto, digite '@' e, em seguida, clique no botão 'Enter'.\n");
	}
	
	public void verificarOpcoesModificaoProdutoAntesDaPersistaencia() {
		System.out.print("\nPor gentileza, digite o número referente a opção abaixo para efetuar a modificação e, em seguida, 'Enter':"
				+ "\n\t1. Nome;"
				+ "\n\t2. Quantidade;"
				+ "\n\t3. Valor Total"
				+ "\n\t4. Codigo;"
				+ "\n\t5. Nome da Categoria;"
				+ "\n\t6. Voltar;"
				+ "\n-> ");
	}
	
	public void showEditProduto() {
		ArrayList<Produto> listaDeProdutos = screenControl.getStarter().getRepository().getRepositoryProduto();
		
		Boolean controlaProduto = true;
		Boolean controlaQuantidadeRetiradaProduto = true;
		
		try {
			while(controlaProduto) {
				controlaProduto = false;
				System.out.print("\nCaso queira desistair da edição de registro produto, digite '@' e clique no botão 'Enter' à qualquer momento.");
				System.out.print("\nPor gentileza, informe o nome do Produto para efetuar sua edição. Caso queira confirmar os produtos em estoque, digite '!' e, em seguida, clique no botão 'Enter'.");
				System.out.print("\n-> ");
				screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
				if(screenControl.getScreenInput().equals("!")) {
					controlaProduto = true;
					getScreenControl().getProdutoListaView().showProdutoLista();
				}
			}
			if(screenControl.getProdutoControl().checkCancela(screenControl.getScreenInput())) {
				controlaProduto = false;
				throw new IllegalArgumentException("");
			} else {
				if(screenControl.getProdutoControl().checkNomeParaRetiradaOuEdicao(screenControl.getScreenInput())) {
					Produto produtoParaAlteracao = new Produto();
					for(Produto produtoNaLista : listaDeProdutos) {
						if(produtoNaLista.getNome().equals(screenControl.getScreenInput())) {
							produtoParaAlteracao = produtoNaLista;
							break;
						}
					}
					if(produtoParaAlteracao.getId() != null || produtoParaAlteracao.getId() <= 0) {
						screenControl.getProdutoControl().verificarProdutoAntesDaPersistaencia(produtoParaAlteracao, "edit");
						System.out.print("\nProduto editado com sucesso!\n");
					} else {
						System.err.print("\nNão foi possível efetuar o processo de edição do Produto!");
						System.out.print("\nPor gentileza, verifique e tente novamente!");
						System.out.print("\n-> ");
					}
				} else {
					System.out.print("\nO registro referente ao nome informado não consta no banco de dados.\n");
					System.out.print(getScreenControl().getCorteVerticalDasTelas());
				}
			}
		} catch(Exception erro) {
			System.err.print("\nNão foi possível remover o produto da base de dados!\n");
			System.out.print(getScreenControl().getCorteVerticalDasTelas());
		}
	}
	
	public void showRemoveProduto() {

		Boolean controlaProduto = true;
		Boolean controlaQuantidadeRetiradaProduto = true;
		
		try {
			while(controlaProduto) {
				controlaProduto = false;
				System.out.print("\nCaso queira desistair da retirada do Produto, digite '@' e clique no botão 'Enter' à qualquer momento.");
				System.out.print("\nPor gentileza, informe o nome do Produto para efetuar a retirada. Caso queira confirmar os produtos em estoque, digite '!' e, em seguida, clique no botão 'Enter'.");
				System.out.print("\n-> ");
				screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
				if(screenControl.getScreenInput().equals("!")) {
					controlaProduto = true;
					getScreenControl().getProdutoListaView().showProdutoLista();
				}
			}
			if(screenControl.getProdutoControl().checkCancela(screenControl.getScreenInput())) {
				controlaProduto = false;
				throw new IllegalArgumentException("");
			} else {
				if(screenControl.getProdutoControl().checkNomeParaRetiradaOuEdicao(screenControl.getScreenInput())) {
					String nomeProduto = screenControl.getScreenInput();
					System.out.print("\nPor gentileza, informa a quantidade de Produtos que está sendo retirada:");
					System.out.print("\n-> ");
						while(controlaQuantidadeRetiradaProduto) {
							screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
							if(screenControl.getProdutoControl().checkCancela(screenControl.getScreenInput())) {
								controlaProduto = false;
								throw new IllegalArgumentException("");
							} else {
								if(screenControl.getProdutoControl().checkQuantidadeOuValorTotal(screenControl.getScreenInput())) {
									controlaQuantidadeRetiradaProduto = false;
									Double valorRetirado = Double.valueOf(screenControl.getScreenInput());
									screenControl.getProdutoControl().retiraProduto(nomeProduto, valorRetirado);
									controlaProduto = false;
									System.out.print("\nQuantidade de Produto retirada com sucesso\n!");
									System.out.print(getScreenControl().getCorteVerticalDasTelas());
								} else {
									System.err.print("\nNão foi possível efetuar o processo de retirada do produto. A quantidade a ser retirada é maior do que a que está em estoque.");
									System.out.print("\nPor gentileza, verifique e tente novamente!");
									System.out.print("\n-> ");
								}
							}
						}
				} else {
					System.out.print("\nO registro referente ao nome informado não consta no banco de dados.\n");
					System.out.print(getScreenControl().getCorteVerticalDasTelas());
				}
			}
		} catch(Exception erro) {
			System.err.print("\nNão foi possível remover o produto da base de dados!\n");
			System.out.print(getScreenControl().getCorteVerticalDasTelas());
		}
	}

	
	public TelaControl getScreenControl() {
		return screenControl;
	}

	public void setScreenControl(TelaControl screenControl) {
		this.screenControl = screenControl;
	}
	
}
