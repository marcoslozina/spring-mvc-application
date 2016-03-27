package com.marcoslozina.testejava.service;

import java.util.List;

import com.marcoslozina.testejava.model.Pessoa;

public interface PessoaService {

    Pessoa findById(long id);

    Pessoa findByCPF(String cpf);

    boolean isPessoaCPFunique(String cpf);

    String save(Pessoa pessoa);

    String update(Pessoa pessoa);

    void deleteByCPF(String cpf);

    List<Pessoa> findAllPessoas();

}
