package stockControl.view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import stockControl.control.TelaControl;
import stockControl.model.Operacao;
import stockControl.model.Tipo;

public class OperacaoDataTableView {
	
	private TelaControl screenControl;
	
	
	public OperacaoDataTableView(TelaControl screenControl) {
		super();
		this.screenControl = screenControl;
	}
	
	
	public void showOperacaoLista() {
		String tituloDaTabela = "Operações Realizadas";
		ArrayList<Operacao> operacaoLista = screenControl.getStarter().getRepository().getRepositoryOperacao();
		System.out.print(getScreenControl().getCorteVerticalDasTelas());
		String linhaTitulos = "";
		String tamanhoLinhaTabela = "";
		String tamanhoLinhaMesclada = "";
		String tamanhoLinhaMescladaEsquerda = "";
		String tamanhoLinhaMescladaDireita = "";
		Integer numeroDeCampos = 8;
		
		for(String legendaPrimeiraLinha : screenControl.getStarter().getRepository().getRepositoryOperacaoPrimeiraLinha()) {
			linhaTitulos = linhaTitulos + screenControl.formatarCelula(legendaPrimeiraLinha);
		}

		tamanhoLinhaTabela = getScreenControl().formatarTamanhoLinhaTabela(numeroDeCampos);
		tamanhoLinhaMescladaEsquerda = getScreenControl().formatarTamanhoLinhaMesclada(numeroDeCampos, tituloDaTabela, true);
		tamanhoLinhaMescladaDireita = getScreenControl().formatarTamanhoLinhaMesclada(numeroDeCampos, tituloDaTabela, false);
		
		System.out.print("\n" + tamanhoLinhaTabela + "\n" + tamanhoLinhaMescladaEsquerda + tituloDaTabela + tamanhoLinhaMescladaDireita);
		System.out.print("\n" + tamanhoLinhaTabela + "\n" + linhaTitulos + "\n" + tamanhoLinhaTabela + "\n");
		
		for(Operacao operacao : operacaoLista) {
			if(operacao.getTipo().equals(Tipo.Entrada) || operacao.getTipo().equals(Tipo.Saída)) {
				String tipoOperacao = "";
				String log = "";
				String valorTotalFormatado = screenControl.formatarMoeda(operacao.getProduto().getValorTotal().toString());
				Double valorUnitario = (operacao.getProduto().getValorTotal() / operacao.getProduto().getQuantidade());
				String valorUnitarioFormated = new DecimalFormat("#,##0.00").format(valorUnitario);			
				Date dataHoraAtual = new Date();
				String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
				String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
				
				log = screenControl.formatarCelula(operacao.getTipo().toString()) + screenControl.formatarCelula(operacao.getProduto().getQuantidade().toString()) 
				+ screenControl.formatarCelula(operacao.getProduto().getNome()) + screenControl.formatarCelula(screenControl.formatarMoeda(valorUnitarioFormated.equals("∞") ? "0,00" : valorUnitarioFormated))  
				+ screenControl.formatarCelula(valorTotalFormatado)	+ screenControl.formatarCelula(operacao.getCategoria().getTitulo()) 
				+ screenControl.formatarCelula(data) + screenControl.formatarCelula(hora);
				
				System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
			}
		}
	}
	
	public void showLogLista() {
		ArrayList<Operacao> operacaoLista = screenControl.getStarter().getRepository().getRepositoryOperacao();
		System.out.print(getScreenControl().getCorteVerticalDasTelas());
		String tituloDaTabela = "Log das Operações Relizadas";
		String linhaTitulos = "";
		String tamanhoLinhaTabela = "";
		String tamanhoLinhaMescladaEsquerda = "";
		String tamanhoLinhaMescladaDireita = "";
		Integer numeroDeCampos = 7;
		for(String legendaPrimeiraLinha : screenControl.getStarter().getRepository().getRepositoryOperacaoLogPrimeiraLinha()) {
			linhaTitulos = linhaTitulos + screenControl.formatarCelula(legendaPrimeiraLinha);
		}
		 tamanhoLinhaTabela = getScreenControl().formatarTamanhoLinhaTabela(numeroDeCampos);
		 tamanhoLinhaMescladaEsquerda = getScreenControl().formatarTamanhoLinhaMesclada(numeroDeCampos, tituloDaTabela, true);
		 tamanhoLinhaMescladaDireita = getScreenControl().formatarTamanhoLinhaMesclada(numeroDeCampos, tituloDaTabela, false);
		
		System.out.print("\n" + tamanhoLinhaTabela + "\n" + tamanhoLinhaMescladaEsquerda + tituloDaTabela + tamanhoLinhaMescladaDireita);
		System.out.print("\n" + tamanhoLinhaTabela + "\n" + linhaTitulos + "\n" + tamanhoLinhaTabela + "\n");
		
		for(Operacao operacao : operacaoLista) {
			
			if(operacao.getTipo().equals(Tipo.Inclusão) || operacao.getTipo().equals(Tipo.Entrada)) {
				if(operacao.getCategoria() != null && operacao.getCategoria().getId() != null) {
					String log = "";
					String classificacao = "Categoria";
					String tipo = operacao.getTipo().toString();
					String campo = "Id / Título";
					String antigoValor = "-";
					String novoValor = operacao.getCategoria().getId().toString() + " / " + operacao.getCategoria().getTitulo();
					Date dataHoraAtual = new Date();
					String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
					String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
					
					log = screenControl.formatarCelula(tipo) + screenControl.formatarCelula(classificacao) + screenControl.formatarCelula(campo)
					+ screenControl.formatarCelula(antigoValor)+ screenControl.formatarCelula(novoValor) + screenControl.formatarCelula(data) 
					+ screenControl.formatarCelula(hora);
					
					System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
				}
				
				if(operacao.getProduto() != null && operacao.getProduto().getId() != null) {
					String log = "";
					String classificacao = "Produto";
					String tipo = operacao.getTipo().toString();
					String campo = "Todos";
					String antigoValor = "-";
					String novoValor = "Nome: " + operacao.getProduto().getNome();
					Date dataHoraAtual = new Date();
					String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
					String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
					
					log = screenControl.formatarCelula(tipo) + screenControl.formatarCelula(classificacao) + screenControl.formatarCelula(campo)
					+ screenControl.formatarCelula(antigoValor)+ screenControl.formatarCelula(novoValor) + screenControl.formatarCelula(data) 
					+ screenControl.formatarCelula(hora);
					
					System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
				}
				
			}
			
			if(operacao.getTipo().equals(Tipo.Exclusão) || operacao.getTipo().equals(Tipo.Saída)) {
				if(operacao.getOldCategoria() != null && operacao.getOldCategoria().getId() != null) {
					String log = "";
					String classificacao = "Categoria";
					String tipo = operacao.getTipo().toString();
					String campo = "Id / Título";
					String antigoValor = operacao.getOldCategoria().getId().toString() + " / " + operacao.getOldCategoria().getTitulo();
					String novoValor = "-";
					Date dataHoraAtual = new Date();
					String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
					String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
					
					log = screenControl.formatarCelula(tipo) + screenControl.formatarCelula(classificacao) + screenControl.formatarCelula(campo)
					+ screenControl.formatarCelula(antigoValor)+ screenControl.formatarCelula(novoValor) + screenControl.formatarCelula(data) 
					+ screenControl.formatarCelula(hora);
					
					System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
				}
				
				if(operacao.getOldProduto() != null && operacao.getOldProduto().getId() != null) {
					String log = "";
					String classificacao = "Produto";
					String tipo = operacao.getTipo().toString();
					String campo = "Todos";
					String antigoValor = "Nome - " + operacao.getProduto().getNome();
					String novoValor = "-";
					Date dataHoraAtual = new Date();
					String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
					String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
					
					log = screenControl.formatarCelula(tipo) + screenControl.formatarCelula(classificacao) + screenControl.formatarCelula(campo)
					+ screenControl.formatarCelula(antigoValor)+ screenControl.formatarCelula(novoValor) + screenControl.formatarCelula(data) 
					+ screenControl.formatarCelula(hora);
					
					System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
				}
				
			}
			
			if(operacao.getTipo().equals(Tipo.Alteração)) {
				if(operacao.getOldCategoria() != null && operacao.getOldCategoria().getId() != null) {
					String log = "";
					String classificacao = operacao.getTipo().toString();
					String tipo = "Categoria";
					String campo = "";
					String antigoValor = "";
					String novoValor = "";
					Date dataHoraAtual = new Date();
					String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
					String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
					
					if(operacao.getOldCategoria().getTitulo() != operacao.getCategoria().getTitulo()) {
						campo = "Título";
						antigoValor = operacao.getOldCategoria().getTitulo();
						novoValor = operacao.getCategoria().getTitulo();
					}
	
					log = screenControl.formatarCelula(tipo) + screenControl.formatarCelula(classificacao) + screenControl.formatarCelula(campo)
					+ screenControl.formatarCelula(antigoValor)+ screenControl.formatarCelula(novoValor) + screenControl.formatarCelula(data) 
					+ screenControl.formatarCelula(hora);
					
					System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
				}
				
				if(operacao.getOldProduto() != null && operacao.getOldProduto().getId() != null) {
					String log = "";
					String classificacao = operacao.getTipo().toString();
					String tipo = "Produto";
					String campo = "";
					String antigoValor = "";
					String novoValor = "";
					Date dataHoraAtual = new Date();
					String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
					String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
					
					if(operacao.getOldProduto().getNome() != operacao.getProduto().getNome()) {
						campo = "Nome";
						antigoValor = operacao.getOldProduto().getNome();
						novoValor = operacao.getProduto().getNome();
						
						log = screenControl.formatarCelula(tipo) + screenControl.formatarCelula(classificacao) + screenControl.formatarCelula(campo)
						+ screenControl.formatarCelula(antigoValor)+ screenControl.formatarCelula(novoValor) + screenControl.formatarCelula(data) 
						+ screenControl.formatarCelula(hora);
						
						System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
					}
					if(operacao.getOldProduto().getQuantidade() != operacao.getProduto().getQuantidade()) {
						campo = "Quantidade";
						antigoValor = operacao.getOldProduto().getQuantidade().toString();
						novoValor = operacao.getProduto().getQuantidade().toString();
						
						log = screenControl.formatarCelula(tipo) + screenControl.formatarCelula(classificacao) + screenControl.formatarCelula(campo)
						+ screenControl.formatarCelula(antigoValor)+ screenControl.formatarCelula(novoValor) + screenControl.formatarCelula(data) 
						+ screenControl.formatarCelula(hora);
						
						System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
					}
					if(operacao.getOldProduto().getValorTotal() != operacao.getProduto().getValorTotal()) {
						campo = "Valor Total";
						antigoValor = operacao.getOldProduto().getValorTotal().toString();
						novoValor = operacao.getProduto().getValorTotal().toString();
						
						log = screenControl.formatarCelula(tipo) + screenControl.formatarCelula(classificacao) + screenControl.formatarCelula(campo)
						+ screenControl.formatarCelula(antigoValor)+ screenControl.formatarCelula(novoValor) + screenControl.formatarCelula(data) 
						+ screenControl.formatarCelula(hora);
						
						System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
					}
					if(operacao.getOldProduto().getCodigo() != operacao.getProduto().getCodigo()) {
						campo = "Código";
						antigoValor = operacao.getOldProduto().getCodigo();
						novoValor = operacao.getProduto().getCodigo();
						
						log = screenControl.formatarCelula(tipo) + screenControl.formatarCelula(classificacao) + screenControl.formatarCelula(campo)
						+ screenControl.formatarCelula(antigoValor)+ screenControl.formatarCelula(novoValor) + screenControl.formatarCelula(data) 
						+ screenControl.formatarCelula(hora);
						
						System.out.print(log + "\n" + tamanhoLinhaTabela + "\n");
					}
				}
			}
		}
	}


	public TelaControl getScreenControl() {
		return screenControl;
	}

	public void setScreenControl(TelaControl screenControl) {
		this.screenControl = screenControl;
	}
	
}
