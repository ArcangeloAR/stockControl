package stockControl.control;

import java.util.Date;

import stockControl.model.Categoria;
import stockControl.model.Operacao;
import stockControl.model.Produto;
import stockControl.model.Tipo;

public class OperacaoControl {
	
	private TelaControl screenControl;
	
	
	public OperacaoControl(TelaControl screenControl) {
		super();
		this.screenControl = screenControl;
	}
	
	public void addOperacao(Tipo tipo, Produto produto, Categoria categoria, Produto oldProduto, Categoria oldCategoria) {
		try {
			oldProduto = oldProduto == null ? new Produto() : oldProduto;
			oldCategoria = oldCategoria == null ? new Categoria() : oldCategoria;
			Operacao newOperacao = new Operacao();
			Date dataHoraAtual = new Date();
			Integer sizeRepositoryOperacao = (getScreenControl().getStarter().getRepository().getRepositoryOperacao().size());
			Long idNewOperacao = (long) (sizeRepositoryOperacao + 1);
			newOperacao.setId(idNewOperacao);
			newOperacao.setData(dataHoraAtual);
			newOperacao.setTipo(Tipo.Entrada);
			newOperacao.setProduto(produto);
			newOperacao.setCategoria(categoria);
			newOperacao.setOldProduto(oldProduto);
			newOperacao.setOldCategoria(oldCategoria);
			
			getScreenControl().getStarter().getRepository().getRepositoryOperacao().add(newOperacao);
		} catch(Exception erro){
			System.err.println("\nNão foi possível efetuar o registro da Operação.");
		}
	}
	
	
	public TelaControl getScreenControl() {
		return screenControl;
	}

	public void setScreenControl(TelaControl screenControl) {
		this.screenControl = screenControl;
	}

}
