package stockControl.view;

import stockControl.control.TelaControl;
import stockControl.model.Categoria;

public class CategoriaPanelView {
	
	private TelaControl screenControl;
	
	
	public CategoriaPanelView(TelaControl screenControl) {
		super();
		this.screenControl = screenControl;
	}
	
	
	public void showAddRemoveCategoria() {
		Boolean controlaValidacaoAddRemove = true;
		try {
			System.out.print("\nPor gentileza, digite o número referente a opção abaixo e, em seguida, 'Enter' para ser redirecionado ao que deseja: "
					+ "\n\t1. Adicionar Categoria ao Estoque;"
					+ "\n\t2. Editar Categoria Estoque;"
					+ "\n\t3. Remover Categoria Estoque;"
					+ "\n\t4. Voltar ao Menu Principal;");
			while(controlaValidacaoAddRemove) {
				System.out.print("\n-> ");
				screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
				if(screenControl.getCategoriaControl().checkInputSubMenu(screenControl.getScreenInput())) {
					controlaValidacaoAddRemove = false;
					if(screenControl.getScreenInput().equals("1")) {
						showAddCategoria();
					} else if(screenControl.getScreenInput().equals("2")) {
						showEditCategoria();
					} else if(screenControl.getScreenInput().equals("3")) {
						showRemoveCategoria();
					} else if(screenControl.getScreenInput().equals("4")) {
						screenControl.setReload(true);
					}
				} else {
					System.out.print("\nPor gentileza, tente novamente seguindo as especificações.");
				}
			}
		} catch(Exception erro) {
			System.err.print("\nNão foi possível efetuar o processo!");
		}
	}
	
	public void showAddCategoria() {
		try {
			System.out.print("\nCaso queira cancelar a inserção da Categoria, digite '@' e clique no botão 'Enter' à qualquer momento.");
			if(screenControl.getCategoriaControl().getCategoriaVerificadaAntesDaPersistaencia() == null) {
				screenControl.getCategoriaControl().setCategoriaVerificadaAntesDaPersistaencia(new Categoria());
			}
			addTituloCategoria();
			
			System.out.print("\nCategoria inserida com sucesso!");
		} catch(Exception erro) {
			System.err.print("\nNão foi possível salvar a Categoria!");
		}
	}
	
	public void addTituloCategoria() {
		Boolean controlaTituloCategoria = true;
		
		System.out.print("\nPor gentileza, informe o TÍTULO da categoria que está sendo ");
		if(screenControl.getCategoriaControl().getCategoriaVerificadaAntesDaPersistaencia().getTitulo() != null) {
			System.out.print("modificada:");
		} else {
			System.out.print("inserida:");
		}
		while(controlaTituloCategoria) {
			System.out.print("\n-> ");
			screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
			if(screenControl.getCategoriaControl().checkCancela(screenControl.getScreenInput())) {
				controlaTituloCategoria = false;
				throw new IllegalArgumentException("");
			} else {
				if(screenControl.getCategoriaControl().checkTitulo(screenControl.getScreenInput())) {
					screenControl.getCategoriaControl().addInformacoesObjetoCategoria("Título", screenControl.getScreenInput());
					screenControl.getCategoriaControl().checkCategoria(screenControl.getScreenInput());
					controlaTituloCategoria = false;
				} else {
					formattedError("Título");
				}
			}
		}
	}
	
	
	public void formattedError(String atributo) {
		System.err.print("\nPor gentileza, já existae uma Categoria com este Título e/ou o valor referente ao campo '" + atributo + "' deverá conter no máximo 250 caracteres, "
				+ "tente novamente seguindo as especificações.");
		System.out.print("\nOu, caso queira cancelar a inserção da categoria, digite '@' e, em seguida, clique no botão 'Enter'.\n");
	}
	

	
	public void verificarOpcoesModificaoCategoriaAntesDaPersistaencia() {
		System.out.print("\nPor gentileza, digite o número referente a opção abaixo para efetuar a modificação e, em seguida, 'Enter':"
				+ "\n\t1. Categoria;"
				+ "\n-> ");
	}
	
	public void showEditCategoria() {
		
		Boolean controlaCategoria = true;

		try {
			while(controlaCategoria) {
			controlaCategoria = false;
			System.out.print("\nCaso queira desistair da edição de registro categoria, digite '@' e clique no botão 'Enter' à qualquer momento.");
			System.out.print("\nPor gentileza, informe o número do ID do registro para efetuar sua edição. Caso queira confirmar este número, digite '!' e, em seguida, clique no botão 'Enter'.");
					System.out.print("\n-> ");
					screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
					if(screenControl.getScreenInput().equals("!")) {
						controlaCategoria = true;
						getScreenControl().getCategoriaListaView().showCategoriaLista("edit");
					}
				}
				if(screenControl.getCategoriaControl().checkCancela(screenControl.getScreenInput())) {
					controlaCategoria = false;
					throw new IllegalArgumentException("");
				} else {
					if(screenControl.getCategoriaControl().checkId(screenControl.getScreenInput())) {
						Long valorFormatado = Long.parseLong(screenControl.getScreenInput());
						screenControl.getCategoriaControl().editCategoria(valorFormatado);
						controlaCategoria = false;
						System.out.print("\nO registro referente ao ID informado foi editado com sucesso!");
					} else {
						System.out.print("\nO registro referente ao ID informado não consta no banco de dados.");
					}
				}
		
		
		} catch(Exception erro) {
			System.err.print("\nNão foi possível editar o Produto na base de dados!\n");
		}
	}
	
	public void showRemoveCategoria() {

		Boolean controlaCategoria = true;

		try {
			while(controlaCategoria) {
			controlaCategoria = false;
			System.out.print("\nCaso queira desistair da remoção da Categoria, digite '@' e clique no botão 'Enter' à qualquer momento.");
			System.out.print("\nPor gentileza, informe o número do ID do registro para efetuar a remoção. A lista de Produtos da devida Categoria deve estar vazia para efetuar esta ação./nCaso queira confirmar este número, digite '!' e, em seguida, clique no botão 'Enter'.");
					System.out.print("\n-> ");
					screenControl.setScreenInput(screenControl.getStarter().read.nextLine());
					if(screenControl.getScreenInput().equals("!")) {
						controlaCategoria = true;
						getScreenControl().getCategoriaListaView().showCategoriaLista("edit");
					}
				}
				if(screenControl.getCategoriaControl().checkCancela(screenControl.getScreenInput())) {
					controlaCategoria = false;
					throw new IllegalArgumentException("");
				} else {
					if(screenControl.getCategoriaControl().checkId(screenControl.getScreenInput())) {
						Long valorFormatado = Long.parseLong(screenControl.getScreenInput());
						screenControl.getCategoriaControl().removeCategoria(valorFormatado);
						controlaCategoria = false;
						System.out.print("\nO registro referente ao ID informado foi removido com sucesso!\n");
					} else {
						System.out.print("\nO registro referente ao ID informado não consta no banco de dados.\n");
					}
				}
		
		
		} catch(Exception erro) {
			System.err.print("\nNão foi possível remover o produto da base de dados!\n");
		}
	}

	
	public TelaControl getScreenControl() {
		return screenControl;
	}

	public void setScreenControl(TelaControl screenControl) {
		this.screenControl = screenControl;
	}
	
}
