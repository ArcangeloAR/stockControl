package stockControl.view;

import java.util.ArrayList;

import stockControl.control.TelaControl;
import stockControl.model.Categoria;
import stockControl.model.Produto;

public class CategoriaDataTableView {
	
	private TelaControl screenControl;
	
	
	public CategoriaDataTableView(TelaControl screenControl) {
		super();
		this.screenControl = screenControl;
	}
	
	
	public void showCategoriaLista(String tipo) {

		ArrayList<Categoria> categoriaLista = screenControl.getStarter().getRepository().getRepositoryCategoria();
		String tituloDaTabela = "Categorias dos Produtos";
		String linhaIds = "";
		String linhaTitulos = "";
		Integer numeroDeCampos = categoriaLista.size();
		Integer numeroDeLinhas = 0;
		Integer linhaAtual = 0;
		String tamanhoLinhaTabela = "";
		String tamanhoLinhaMescladaEsquerda = "";
		String tamanhoLinhaMescladaDireita = "";
		
		System.out.print(getScreenControl().getCorteVerticalDasTelas());
		
		for(Categoria legendasParaPrimeiraLinha : categoriaLista) {
			if(tipo == "edit") {
				linhaIds = linhaIds + screenControl.formatarCelula("Id - " +legendasParaPrimeiraLinha.getId().toString());
			}
			linhaTitulos = linhaTitulos + screenControl.formatarCelula(legendasParaPrimeiraLinha.getTitulo());
			legendasParaPrimeiraLinha.setListaProduto(legendasParaPrimeiraLinha.getListaProduto() == null ? new ArrayList<Produto>() : legendasParaPrimeiraLinha.getListaProduto());
			numeroDeLinhas = numeroDeLinhas <= legendasParaPrimeiraLinha.getListaProduto().toArray().length ? legendasParaPrimeiraLinha.getListaProduto().toArray().length : numeroDeLinhas;
		}
		
		 tamanhoLinhaTabela = getScreenControl().formatarTamanhoLinhaTabela(numeroDeCampos);
		 tamanhoLinhaMescladaEsquerda = getScreenControl().formatarTamanhoLinhaMesclada(numeroDeCampos, tituloDaTabela, true);
		 tamanhoLinhaMescladaDireita = getScreenControl().formatarTamanhoLinhaMesclada(numeroDeCampos, tituloDaTabela, false);
		
		System.out.print("\n" + tamanhoLinhaTabela + "\n" + tamanhoLinhaMescladaEsquerda + tituloDaTabela + tamanhoLinhaMescladaDireita);
		System.out.print("\n" + tamanhoLinhaTabela + "\n" + linhaTitulos + "\n" + tamanhoLinhaTabela + "\n");
		
		
		
		while(numeroDeLinhas - 1 > linhaAtual) {
			String linha = "";
			for(Categoria listaDoNomeDoProdutoDeCadaLinha : categoriaLista) {
				listaDoNomeDoProdutoDeCadaLinha.setListaProduto(listaDoNomeDoProdutoDeCadaLinha.getListaProduto() == null ? new ArrayList<Produto>() : listaDoNomeDoProdutoDeCadaLinha.getListaProduto());
				if(listaDoNomeDoProdutoDeCadaLinha.getListaProduto().isEmpty()
						||listaDoNomeDoProdutoDeCadaLinha.getListaProduto().get(linhaAtual) == null 
						|| listaDoNomeDoProdutoDeCadaLinha.getListaProduto().get(linhaAtual).getNome().isEmpty()
						|| listaDoNomeDoProdutoDeCadaLinha.getListaProduto().get(linhaAtual).getNome() == "") {
					linha += screenControl.formatarCelula(" - ");
				} else {
					linha += screenControl.formatarCelula(listaDoNomeDoProdutoDeCadaLinha.getListaProduto().get(linhaAtual).getNome());
				}
				
			}
			System.out.print(linha);
			linhaAtual += 1;
		}
		
		System.out.print("\n" + tamanhoLinhaTabela + "\n");
	}


	public TelaControl getScreenControl() {
		return screenControl;
	}

	public void setScreenControl(TelaControl screenControl) {
		this.screenControl = screenControl;
	}
	
	
}
