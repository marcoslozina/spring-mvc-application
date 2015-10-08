package com.marcoslozina.testejava.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcoslozina.testejava.dao.LivroDao;
import com.marcoslozina.testejava.model.Livro;

@Service("livroService")
@Transactional
public class LivroServiceImpl implements LivroService {

    @Autowired
    LivroDao dao;

    public Livro findById(long id) {
	return dao.findById(id);
    }

    public Livro findByNome(String nome) {
	return dao.findByNome(nome);
    }

    public void save(Livro livro) {
	dao.save(livro);
    }

    public void deleteById(long id) {
	dao.deleteById(id);
    }

    public List<Livro> findAllLivros() {
	return dao.findAllLivros();
    }

    public void update(Livro livro) {

	Livro entity = dao.findById(livro.getIdlivro());
	if (entity != null) {
	    entity.setAnoedicao(livro.getAnoedicao());
	    entity.setClassificacao(livro.getClassificacao());
	    entity.setEmprestimos(livro.getEmprestimos());
	    entity.setEscritor(livro.getEscritor());
	    entity.setIdlivro(livro.getIdlivro());
	    entity.setNome(livro.getNome());
	}

    }
}
