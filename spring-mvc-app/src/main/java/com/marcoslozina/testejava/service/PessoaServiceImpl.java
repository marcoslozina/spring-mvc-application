package com.marcoslozina.testejava.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcoslozina.testejava.dao.PessoaDao;
import com.marcoslozina.testejava.model.Pessoa;

@Service("pessoaService")
@Transactional
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    PessoaDao dao;

    public Pessoa findById(long id) {
	return dao.findById(id);
    }

    public Pessoa findByCPF(String cpf) {
	return dao.findByCPF(cpf);
    }

    public String save(Pessoa pessoa) {
	dao.save(pessoa);
	return "Pessoa " + pessoa.getNome() + " " + " CPF: " + pessoa.getCpf() + " foi cadastrada com sucesso!";

    }

    public void deleteByCPF(String cpf) {
	dao.deleteByCPF(cpf);
	

    }

    public List<Pessoa> findAllPessoas() {
	return dao.findAllPessoas();
    }

    public String update(Pessoa pessoa) {
	Pessoa entity = dao.findByCPF(pessoa.getCpf());
	if (entity != null) {
	    entity.setCpf(pessoa.getCpf());
	    entity.setDatanascimento(pessoa.getDatanascimento());
	    entity.setEmprestimos(pessoa.getEmprestimos());
	    entity.setIdpessoa(pessoa.getIdpessoa());
	    entity.setNome(pessoa.getNome());

	}
	return "Pessoa: " + pessoa.getNome() + " "	+ " CPF: " + pessoa.getCpf() + " foi atualizado com sucesso!";
    }

    public boolean isPessoaCPFunique(String cpf) {
	Pessoa pessoa = findByCPF(cpf);
	return (pessoa == null || ((cpf != null) && (pessoa.getCpf() == cpf)));
    }

}
