package com.marcoslozina.testejava.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcoslozina.testejava.dao.EmprestimoDao;
import com.marcoslozina.testejava.model.Emprestimo;
import com.marcoslozina.testejava.model.Livro;
import com.marcoslozina.testejava.model.Pessoa;

@Service("emprestimoService")
@Transactional
public class EmprestimoServiceImpl implements EmprestimoService {

    @Autowired
    EmprestimoDao dao;

    public void save(Emprestimo emprestimo) {
	dao.save(emprestimo);
    }

    public void update(Emprestimo emprestimo) {
	Emprestimo entity = dao.findById(emprestimo.getIdemprestimo());
	if (entity != null) {
	    entity.setDataemprestimo(emprestimo.getDataemprestimo());
	    entity.setDatahoradevolucao(emprestimo.getDatahoradevolucao());
	    entity.setIdemprestimo(emprestimo.getIdemprestimo());
	    entity.setLivro(emprestimo.getLivro());
	    entity.setPessoa(emprestimo.getPessoa());
	}

    }

    public List<Emprestimo> findAllEmprestimos() {
	return dao.findAllEmprestimos();
    }

    public List<Emprestimo> findAllEmprestimosPessoa(Pessoa pessoa) {
	return dao.findAllEmprestimosPessoa(pessoa);
    }

    public boolean isLivroEmprestado(Livro livro) {
	List<Emprestimo> emprestimos = dao.findAllEmprestimos();

	for (Emprestimo emprestimo : emprestimos) {
	    if (emprestimo.getLivro().getIdlivro() == livro.getIdlivro()
		    && (emprestimo.getDatahoradevolucao() == null)) {
		return true;
	    }
	}
	return false;
    }

    public boolean isPessoaComIdadeMinimaParaEmprestimoDeLivro(Pessoa pessoa,
	    Livro livro) {
	LocalDate birthdate = new LocalDate(pessoa.getDatanascimento());
	LocalDate now = new LocalDate();
	Years anosDaPessoa = Years.yearsBetween(birthdate, now);

	if (livro.getClassificacao() <= anosDaPessoa.getYears()) {
	    return true;
	}
	return false;
    }

    public boolean isPessoaComDoisLivroEmprestados(Pessoa pessoa) {
	List<Emprestimo> emprestimos = dao.findAllEmprestimos();
	int quantidadeLivrosEmprestados = 0;
	for (Emprestimo emprestimo : emprestimos) {
	    if (emprestimo.getPessoa().equals(pessoa)
		    && (emprestimo.getDatahoradevolucao() == null)) {
		quantidadeLivrosEmprestados++;
	    }
	}
	return quantidadeLivrosEmprestados == 2;
    }

    public Emprestimo findById(long id) {
	return dao.findById(id);
    }

    public void deleteById(long id) {
	dao.deleteById(id);

    }

    public boolean isDataEmprestimoMaiorAhoje(Date dataEmprestimo) {

	Date hoje = new Date();

	if (null != dataEmprestimo) {
	    return !hoje.after(dataEmprestimo);

	}
	return true;
    }

    public boolean isDataDevolucaoMaiorAhoje(Date dataDevolucao) {

	Date hoje = new Date();

	if (null != dataDevolucao) {
	    return !hoje.after(dataDevolucao);

	}
	return false; // A data de devolução pode ser nula a diferença da data
		      // de empréstimo
    }

}
