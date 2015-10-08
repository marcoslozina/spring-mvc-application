package com.marcoslozina.testejava.dao;

import java.util.List;

import com.marcoslozina.testejava.model.Livro;

public interface LivroDao {

    Livro findById(long id);

    Livro findByNome(String nome);

    void save(Livro livro);

    void deleteById(long id);

    List<Livro> findAllLivros();

}
