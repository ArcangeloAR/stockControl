package stockControl.view;

import stockControl.control.TelaControl;

public class MenuPanelView {
	
	private TelaControl screenControl;
	
	
	public MenuPanelView(TelaControl screenControl) {
		super();
		this.screenControl = screenControl;
	}
	

	public void mainPage(Boolean controlaBemVindo) {
		System.out.print(getScreenControl().getCorteVerticalDasTelas()
				+ "\nBem-vindo ");
		if(controlaBemVindo) {
			System.out.print("novamente ");
		}
		System.out.print("ao menu, por gentileza, digite o número referente a opção abaixo e, em seguida, 'Enter' para ser redirecionado ao que deseja: "
				+ "\n\t1. Adicionar/Retirar/Editar Produtos do Estoque;"
				+ "\n\t2. Adicionar/Editar/Remover Categoria de Produtos;"
				+ "\n\t3. Visualizar Estoque de Produtos;"
				+ "\n\t4. Visualizar Categoria de Produtos Existentes;"
				+ "\n\t5. Visualizar Operações Realizadas;"
				+ "\n\t6. Visualizar Log das Operações Realizadas;"
				+ "\n\t7. Sair;"
				+ "\n-> ");
	}


	public TelaControl getScreenControl() {
		return screenControl;
	}

	public void setScreenControl(TelaControl screenControl) {
		this.screenControl = screenControl;
	}
	
}
