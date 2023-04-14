package stockControl.control;

import java.util.ArrayList;

import stockControl.model.Categoria;

public class CategoriaControl {
	
	private TelaControl screenControl;
	
	private Categoria categoriaVerificadaAntesDaPersistaencia;
	
	
	public CategoriaControl(TelaControl screenControl) {
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
	
	public Boolean checkTitulo(String titulo) {
		try {
			ArrayList<Categoria> listaDeCategorias = screenControl.getStarter().getRepository().getRepositoryCategoria();
			
			if(titulo.length() <= 0 || titulo.length() > 250) {
				throw new IllegalArgumentException("");
			}
			for(Categoria categoriaAtualDaLista : listaDeCategorias) {
			if(categoriaAtualDaLista.getTitulo().equals(titulo)) {
				throw new IllegalArgumentException("");
				}
			}
		} catch(Exception erro){
				return false;
			}
		return true;
	}
	
	public Boolean checkCategoria(String categoria) {
		try {
			boolean verificaSeAdicionaCategoria = true;

			if(categoria == null || categoria.length() <= 0 || categoria.length() > 250) {
				throw new IllegalArgumentException("");
			}
			
			for(Categoria categoriaInRepository : getScreenControl().getStarter().getRepository().getRepositoryCategoria()) {
				if(categoria != categoriaInRepository.getTitulo()) {
					verificaSeAdicionaCategoria = true;
				} else {
					verificaSeAdicionaCategoria = false;
					break;
				}
			}
			
			if(verificaSeAdicionaCategoria) {
				Categoria novaCategoria = new Categoria();
				novaCategoria.setTitulo(categoria);
				getScreenControl().getCategoriaControl().addCategoria(novaCategoria);
				verificaSeAdicionaCategoria = false;
			}
			
		} catch(Exception erro){
				return false;
			}
		return true;
	}
	
	public void addInformacoesObjetoCategoria(String tipoDeValor, String titulo) {
		if(categoriaVerificadaAntesDaPersistaencia == null) {
			categoriaVerificadaAntesDaPersistaencia = new Categoria();
		}
		if(tipoDeValor.equals("Título")) {
			categoriaVerificadaAntesDaPersistaencia.setTitulo(titulo);
		}
	}
	
	public void addCategoria(Categoria novaCategoria) {
		if(checkIdCategoria(novaCategoria.getId())) {
			Integer sizeRepositoryCategoria = (getScreenControl().getStarter().getRepository().getRepositoryCategoria().size());
			Long idNewCategoria = (long) (sizeRepositoryCategoria + 1);
			novaCategoria.setId(idNewCategoria);
		}
			getScreenControl().getStarter().getRepository().getRepositoryCategoria().add(novaCategoria);
	}
	
	@SuppressWarnings("unused")
	public Boolean checkIdCategoria(Long id) {
		if(id != null) {
			for(Categoria categoriasExistentesNoRepositorio : getScreenControl().getStarter().getRepository().getRepositoryCategoria()) {
				boolean checkId = true;
				if(id == categoriasExistentesNoRepositorio.getId()) {
					checkId = false;
					categoriasExistentesNoRepositorio.setId(categoriasExistentesNoRepositorio.getId());
					getCategoriaVerificadaAntesDaPersistaencia().setId(categoriasExistentesNoRepositorio.getId());
					break;
				}
			}
		}
		return true;
	}
	
	public void editCategoria(Long id) {
		try {
			Categoria categoriaParaEditar = new Categoria();
			ArrayList<Categoria> categoriaLista = screenControl.getStarter().getRepository().getRepositoryCategoria();
			for(Categoria categoria : categoriaLista) {
				if(id == categoria.getId()) {
					categoriaParaEditar = categoria;
					break;
				}
			}
			setCategoriaVerificadaAntesDaPersistaencia(categoriaParaEditar);
			screenControl.getCategoriaAddEditRemoveView().addTituloCategoria();
		} catch(Exception erro) {
			System.err.print("\nNão foi possível efetuar o processo de edição!");
		}
	}
	
	public void removeCategoria(Long id) {
		Categoria categoriaParaERemover = new Categoria();
		ArrayList<Categoria> categoriaLista = screenControl.getStarter().getRepository().getRepositoryCategoria();
		for(Categoria categoria : categoriaLista) {
			if(id == categoria.getId()) {
				categoriaParaERemover = categoria;
				break;
			}
		}
		if(categoriaParaERemover.getListaProduto().size() == 0) {
			categoriaLista.remove(categoriaParaERemover);
		} else {
			System.err.print("\nNão foi possível remover a Categoria!\n");
			System.out.print("\nPor gentileza, verifique se a lista de Produtos está vazia.");
		}
		
	}
	
	
	public TelaControl getScreenControl() {
		return screenControl;
	}

	public void setScreenControl(TelaControl screenControl) {
		this.screenControl = screenControl;
	}

	public Categoria getCategoriaVerificadaAntesDaPersistaencia() {
		if(categoriaVerificadaAntesDaPersistaencia == null) {
			categoriaVerificadaAntesDaPersistaencia = new Categoria();
		}
		return categoriaVerificadaAntesDaPersistaencia;
	}

	public void setCategoriaVerificadaAntesDaPersistaencia(Categoria categoriaVerificadaAntesDaPersistaencia) {
		this.categoriaVerificadaAntesDaPersistaencia = categoriaVerificadaAntesDaPersistaencia;
	}
	
	
	
}
