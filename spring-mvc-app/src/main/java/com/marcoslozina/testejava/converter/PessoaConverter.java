package com.marcoslozina.testejava.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.marcoslozina.testejava.model.Pessoa;
import com.marcoslozina.testejava.service.PessoaService;

@Component
public class PessoaConverter implements Converter<Object, Pessoa> {

    @Autowired
    PessoaService pessoaService;

    public Pessoa convert(Object element) {

	Integer id = Integer.parseInt((String) element);
	Pessoa pessoa = pessoaService.findById(id);
	System.out.println("Pessoa : " + pessoa);
	return pessoa;
    }

}
