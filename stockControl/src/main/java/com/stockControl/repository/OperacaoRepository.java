package com.stockControl.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockControl.model.Operacao;
import com.stockControl.model.Tipo;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long> {
	
	public List<Operacao> findAllByData(Date data);
	
	public List<Operacao> findAllByTipo(Tipo tipo);
	
}
	