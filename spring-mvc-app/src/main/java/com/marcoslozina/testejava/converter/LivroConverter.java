package com.marcoslozina.testejava.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.marcoslozina.testejava.model.Livro;
import com.marcoslozina.testejava.service.LivroService;

@Component
public class LivroConverter implements Converter<Object, Livro> {

    @Autowired
    LivroService livroService;

    public Livro convert(Object element) {

	Integer id = Integer.parseInt((String) element);
	Livro livro = livroService.findById(id);
	System.out.println("Livro : " + livro);
	return livro;
    }

}
