package stockControl.control;

import stockControl.utilities.Starter;
import stockControl.view.CategoriaPanelView;
import stockControl.view.CategoriaDataTableView;
import stockControl.view.MenuPanelView;
import stockControl.view.ProdutoDataTableView;
import stockControl.view.OperacaoDataTableView;
import stockControl.view.ProdutoPanelView;
import stockControl.view.PaginaInicio;

public class TelaControl {
	
	private String screenInput;
	
	private Starter starter;
	
	private PaginaInicio starterPage;
	
	private CategoriaControl categoriaControl;
	private ProdutoControl produtoControl;
	private OperacaoControl operacaoControl;
	
	private ProdutoDataTableView produtoAddEditRemoveView;
	private CategoriaPanelView categoriaAddEditRemoveView;
	
	private CategoriaDataTableView categoriaListaView;
	private ProdutoPanelView produtoListaView;
	private OperacaoDataTableView operacaoListaView;
	
	private MenuPanelView menu;
	
	private Boolean reload;
	
	private String corteVerticalDasTelas;
	
	final static String ESC = "\033[";
	
	
	
	public TelaControl(Starter starter) {
		super();
		this.starter = starter;
	}
	
	
	public void isBeingShown() {
		Integer controlaMenu = 0;
		Boolean controlaBemVindo = false;
		starter.startRepository();
		getStarterPage().startPage(starter.getScreenControl().getCorteVerticalDasTelas());
		screenInput = starter.read.nextLine();		
		if(screenInput.equals("1")) {
			starter.startRepositoryTest();
		}
		reload = true;
		while(reload) {
			screenInput = null;
			starter.getScreenControl().getMenu().mainPage(controlaBemVindo);
			controlaBemVindo = true;
			try {
				screenInput = starter.read.nextLine();
				while(!checkInput(screenInput)) {
					System.err.print("\nO valor informado é inválido!");
					System.out.print("\nPor gentileza, tente novamente digitar um valor de acordo com a lista de opções:");
					System.out.print("\n-> ");
					screenInput = starter.read.nextLine();
				}
			} catch(Exception erro){
					System.err.println("\nPor favor, execute o programa novamente e digite um número de acordo com a lista de opções.");
				}
			controlaMenu = Integer.parseInt(screenInput);
			switch(controlaMenu) {
			case 1:
				starter.getScreenControl().getProdutoAddEditRemoveView().showAddRemoveProduto();
				break;
			case 2:
				starter.getScreenControl().getCategoriaAddEditRemoveView().showAddRemoveCategoria();
				break;
			case 3:
				starter.getScreenControl().getProdutoListaView().showProdutoLista();
				break;
			case 4:
				starter.getScreenControl().getCategoriaListaView().showCategoriaLista("menu");
				break;
			case 5:
				starter.getScreenControl().getOperacaoListaView().showOperacaoLista();
				break;
			case 6:
				starter.getScreenControl().getOperacaoListaView().showLogLista();
				break;
			case 7:
				System.out.println("\nObrigado por utilizar esta aplicação!");
				reload = false;
				break;
			}
		}
	}
	
	public Boolean checkInput(String input) {
		try {
			Integer inputFormatted;
			inputFormatted = Integer.parseInt(input);
			if(inputFormatted <= 0) {
				throw new IllegalArgumentException("");
			}
		} catch(Exception erro) {
			return false;
		}
		return true;
	}
	
	public String formatarMoeda(String valor) {
		valor.replace(".", ",");
		if(valor.length() < 4) {
			valor = valor + "0";
		}
		return valor;
	}
	
	public String formatarCelula(String valor) {
		if(valor.length() < 25) {
			int tamanhoEspaco = 23 - valor.length();
			if(valor.length() % 2 != 0) {
				tamanhoEspaco = tamanhoEspaco - 1;
			}
			int tamanhoEspacoCadaLado = tamanhoEspaco/2;
			String espacos = "";
			
			for(int i = 0; i < tamanhoEspacoCadaLado; i++) {
				espacos += " ";
	        }
			
			String valorModificado = "";
			if(valor.length() % 2 != 0) {
				valorModificado = "*" + espacos + valor + espacos + " *";
			} else {
				valorModificado = "*" + espacos + valor + espacos + "*";
			}
			valor = valorModificado;
		}
		return valor;
	}
	
	public String formatarTamanhoLinhaTabela(Integer quantidadeDeCampos) {
		String tamanhoLinhaTabela = "";
		for(Integer i = 0; i < quantidadeDeCampos; i++) {
			for(Integer j = 0; j < 24; j++) {
				tamanhoLinhaTabela += "*";
			}
		}
		return tamanhoLinhaTabela;
	}
	
	public String formatarTamanhoLinhaMesclada(Integer quantidadeDeCampos, String tituloDaTabela, Boolean verificaSeEDoComeco) {
		Boolean verificaSeEDoFim = !verificaSeEDoComeco;
		Integer tamanhoLinhaTabela = quantidadeDeCampos * 24;
		Integer metadeTamanhoLinhaTabela = tamanhoLinhaTabela % 2 != 0 ? (tamanhoLinhaTabela + 1) / 2 : tamanhoLinhaTabela / 2;
		Integer metadeTamanhoDoTituloDaTabela = tituloDaTabela.length() % 2 != 0 ? (tituloDaTabela.length() + 1) / 2 : tituloDaTabela.length() / 2;
		Integer tamanhoLinhaMesclada = metadeTamanhoLinhaTabela - metadeTamanhoDoTituloDaTabela;
		String linhaMesclada = "";
		
		for(Integer i = 0; i < tamanhoLinhaMesclada; i++) {
			if(verificaSeEDoComeco) {
				linhaMesclada += "*";
				verificaSeEDoComeco = false;
			} else {
				if(i == tamanhoLinhaMesclada - 1 && verificaSeEDoFim == true) {
					linhaMesclada = tituloDaTabela.length() % 2 != 0 ? linhaMesclada + " *": linhaMesclada + "*";
				} else {
					linhaMesclada += " ";
				}
			}
		}
		return linhaMesclada;
	}
	
	
	public Starter getStarter() {
		return starter;
	}

	public void setStarter(Starter starter) {
		this.starter = starter;
	}

	public String getScreenInput() {
		return screenInput;
	}


	public void setScreenInput(String screenInput) {
		this.screenInput = screenInput;
	}

	public PaginaInicio getStarterPage() {
		if(starterPage == null) {
			starterPage = new PaginaInicio();
		}
		return starterPage;
	}

	public void setStarterPage(PaginaInicio starterPage) {
		this.starterPage = starterPage;
	}

	public CategoriaControl getCategoriaControl() {
		if(categoriaControl == null) {
			categoriaControl = new CategoriaControl(this);
		}
		return categoriaControl;
	}
	
	public void setCategoriaControl(CategoriaControl categoriaControl) {
		this.categoriaControl = categoriaControl;
	}
	
	public ProdutoControl getProdutoControl() {
		if(produtoControl == null) {
			produtoControl = new ProdutoControl(this);
		}
		return produtoControl;
	}
	
	public void setProdutoControl(ProdutoControl produtoControl) {
		this.produtoControl = produtoControl;
	}
	
	public OperacaoControl getOperacaoControl() {
		if(operacaoControl == null) {
			operacaoControl = new OperacaoControl(this);
		}
		return operacaoControl;
	}
	
	public void setOperacaoControl(OperacaoControl operacaoControl) {
		this.operacaoControl = operacaoControl;
	}

	public ProdutoDataTableView getProdutoAddEditRemoveView() {
		if(produtoAddEditRemoveView == null) {
			produtoAddEditRemoveView = new ProdutoDataTableView(this);
		}
		return produtoAddEditRemoveView;
	}
	
	public void setProdutoAddEditRemoveView(ProdutoDataTableView produtoAddEditRemoveView) {
		this.produtoAddEditRemoveView = produtoAddEditRemoveView;
	}
	
	public CategoriaPanelView getCategoriaAddEditRemoveView() {
		if(categoriaAddEditRemoveView == null) {
			categoriaAddEditRemoveView = new CategoriaPanelView(this);
		}
		return categoriaAddEditRemoveView;
	}

	public void setCategoriaAddEditRemoveView(CategoriaPanelView categoriaAddEditRemoveView) {
		this.categoriaAddEditRemoveView = categoriaAddEditRemoveView;
	}

	public CategoriaDataTableView getCategoriaListaView() {
		if(categoriaListaView == null) {
			categoriaListaView = new CategoriaDataTableView(this);
		}
		return categoriaListaView;
	}

	public void setCategoriaListaView(CategoriaDataTableView categoriaListaView) {
		this.categoriaListaView = categoriaListaView;
	}

	public ProdutoPanelView getProdutoListaView() {
		if(produtoListaView == null) {
			produtoListaView = new ProdutoPanelView(this);
		}
		return produtoListaView;
	}

	public void setProdutoListaView(ProdutoPanelView produtoListaView) {
		this.produtoListaView = produtoListaView;
	}

	public OperacaoDataTableView getOperacaoListaView() {
		if(operacaoListaView == null) {
			operacaoListaView = new OperacaoDataTableView(this);
		}
		return operacaoListaView;
	}

	public void setOperacaoListaView(OperacaoDataTableView operacaoListaView) {
		this.operacaoListaView = operacaoListaView;
	}

	public MenuPanelView getMenu() {
		if(menu == null) {
			menu = new MenuPanelView(this);
		}
		return menu;
	}

	public void setMenu(MenuPanelView menu) {
		this.menu = menu;
	}

	public Boolean getReload() {
		return reload;
	}
	
	public void setReload(Boolean reload) {
		this.reload = reload;
	}

	public String getCorteVerticalDasTelas() {
		if(corteVerticalDasTelas == null) {
			corteVerticalDasTelas = "";
			for(Integer i = 0; i < 11; i++) {
				if(i == 0) {
					corteVerticalDasTelas += "\n";
				}
				corteVerticalDasTelas += "----------------//";
				if(i == 10) {
					corteVerticalDasTelas += "\n";
				}
			}
		}
		return corteVerticalDasTelas;
	}

	public void setCorteVerticalDasTelas(String corteVerticalDasTelas) {
		this.corteVerticalDasTelas = corteVerticalDasTelas;
	}
	
}
