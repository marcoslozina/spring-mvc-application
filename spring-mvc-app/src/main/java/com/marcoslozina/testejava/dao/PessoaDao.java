package com.marcoslozina.testejava.dao;

import java.util.List;

import com.marcoslozina.testejava.model.Pessoa;

public interface PessoaDao {

    Pessoa findById(long id);

    Pessoa findByCPF(String cpf);

    void save(Pessoa pessoa);

    void deleteByCPF(String cpf);

    List<Pessoa> findAllPessoas();

}
