package com.marcoslozina.testejava.dao;

import java.util.List;

import com.marcoslozina.testejava.model.Emprestimo;
import com.marcoslozina.testejava.model.Pessoa;

public interface EmprestimoDao {

    void save(Emprestimo emprestimo);

    void deleteById(long id);

    Emprestimo findById(long id);

    List<Emprestimo> findAllEmprestimos();

    List<Emprestimo> findAllEmprestimosPessoa(Pessoa pessoa);

}
