package stockControl.utilities;

import java.util.Scanner;

import stockControl.control.TelaControl;


public class Starter {
	
	Repositorio repository = new Repositorio(this);
	
	TelaControl screenControl = new TelaControl(this);
	
	public Scanner read = new Scanner(System.in);
	
	
	public void start() {
		
	}
	
	
	public void startRepository() {
		getRepository().starRepoitories();
	}
	
	public void startRepositoryTest() {
		getRepository().starFillRepoitoryTest();
	}
	
	
	public TelaControl getScreenControl() {
		return screenControl;
	}

	public Repositorio getRepository() {
		return repository;
	}

	public void setRepository(Repositorio repository) {
		this.repository = repository;
	}


	public void setScreenControl(TelaControl screenControl) {
		this.screenControl = screenControl;
	}

}
