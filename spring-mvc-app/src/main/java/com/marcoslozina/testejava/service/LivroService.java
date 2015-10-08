package com.marcoslozina.testejava.service;

import java.util.List;

import com.marcoslozina.testejava.model.Livro;

public interface LivroService {

    Livro findById(long id);

    Livro findByNome(String nome);

    void save(Livro livro);

    void update(Livro livro);

    void deleteById(long id);

    List<Livro> findAllLivros();

}
