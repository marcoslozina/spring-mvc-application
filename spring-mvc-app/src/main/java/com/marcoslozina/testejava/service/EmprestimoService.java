package com.marcoslozina.testejava.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.marcoslozina.testejava.model.Emprestimo;
import com.marcoslozina.testejava.model.Livro;
import com.marcoslozina.testejava.model.Pessoa;

public interface EmprestimoService {

    Emprestimo findById(long id);

    String save(Emprestimo emprestimo);

    String update(Emprestimo emprestimo);

    List<Emprestimo> findAllEmprestimos();

    void deleteById(long id);

    // Regra de negocio
    boolean isLivroEmprestado(Livro livro);

    // Regra de negocio
    boolean isPessoaComIdadeMinimaParaEmprestimoDeLivro(Pessoa pessoa, Livro livro);

    // Regra de negocio
    boolean isPessoaComDoisLivroEmprestados(Pessoa pessoa);

    boolean isDataEmprestimoMaiorAhoje(Date data) throws ParseException;

    boolean isDataDevolucaoMaiorAhoje(Date data) throws ParseException;
}
