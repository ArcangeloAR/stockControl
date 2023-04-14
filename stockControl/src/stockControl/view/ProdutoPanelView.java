package stockControl.view;

import java.text.DecimalFormat;
import java.util.ArrayList;

import stockControl.control.TelaControl;
import stockControl.model.Produto;

public class ProdutoPanelView {

	private TelaControl screenControl;
	
	
	public ProdutoPanelView(TelaControl screenControl) {
		super();
		this.screenControl = screenControl;
	}
	
	
	public void showProdutoLista() {
		String tituloDaTabela = "Lista de Produtos em Estoque";
		ArrayList<Produto> produtoLista = screenControl.getStarter().getRepository().getRepositoryProduto();
		Integer numeroDeCampos = 6;
		String linhaTitulos = "";
		String tamanhoLinhaTabela = "";
		String tamanhoLinhaMescladaEsquerda = "";
		String tamanhoLinhaMescladaDireita = "";
		System.out.print(getScreenControl().getCorteVerticalDasTelas());
		
		for(String legendaPrimeiraLinha : screenControl.getStarter().getRepository().getRepositoryProdutoPrimeiraLinha()) {
			linhaTitulos = linhaTitulos + screenControl.formatarCelula(legendaPrimeiraLinha);
		}
		
		tamanhoLinhaTabela = getScreenControl().formatarTamanhoLinhaTabela(numeroDeCampos);
		tamanhoLinhaMescladaEsquerda = getScreenControl().formatarTamanhoLinhaMesclada(numeroDeCampos, tituloDaTabela, true);
		tamanhoLinhaMescladaDireita = getScreenControl().formatarTamanhoLinhaMesclada(numeroDeCampos, tituloDaTabela, false);
		System.out.print("\n" + tamanhoLinhaTabela + "\n" + tamanhoLinhaMescladaEsquerda + tituloDaTabela + tamanhoLinhaMescladaDireita);
		System.out.print("\n" + tamanhoLinhaTabela + "\n" + linhaTitulos + "\n" + tamanhoLinhaTabela + "\n");
		
		for(Produto produto : produtoLista) {
			String log = "";
			Double valorUnitario = (produto.getValorTotal() / produto.getQuantidade());
			String valorUnitarioFormated = new DecimalFormat("#,##0.00").format(valorUnitario);
			log = screenControl.formatarCelula(produto.getNome())
			+ screenControl.formatarCelula(screenControl.formatarMoeda(produto.getQuantidade().toString()))
			+ screenControl.formatarCelula(screenControl.formatarMoeda(valorUnitarioFormated.toString().equals("∞") ? "0,00" : valorUnitarioFormated))
			+ screenControl.formatarCelula(screenControl.formatarMoeda(produto.getValorTotal().toString()))
			+ screenControl.formatarCelula(produto.getCodigo()) 
			+ screenControl.formatarCelula(produto.getCategoria().getTitulo());
			
			System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
		}
	}
	

	public TelaControl getScreenControl() {
		return screenControl;
	}

	public void setScreenControl(TelaControl screenControl) {
		this.screenControl = screenControl;
	}
	
}
