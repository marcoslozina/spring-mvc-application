package com.marcoslozina.testejava.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.marcoslozina.testejava.model.Pessoa;

@Repository("pessoaDao")
public class PessoaDaoImpl extends AbstractDao<Integer, Pessoa> implements PessoaDao {

    public Pessoa findById(long id) {
	System.out.println("Peesoa ID: " + id);
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("idpessoa", id));
	Pessoa pessoa = (Pessoa) crit.uniqueResult();

	if (pessoa != null) {
	    Hibernate.initialize(pessoa.getEmprestimos());
	}
	return pessoa;

    }

    public Pessoa findById(int id) {
	System.out.println("Pessoa ID: " + id);
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("idpessoa", id));
	Pessoa pessoa = (Pessoa) crit.uniqueResult();

	if (pessoa != null) {
	    Hibernate.initialize(pessoa.getEmprestimos());
	}
	return pessoa;
    }

    public Pessoa findByCPF(String cpf) {
	System.out.println("Pessoa CPF: " + cpf);
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("cpf", cpf));
	Pessoa pessoa = (Pessoa) crit.uniqueResult();

	if (pessoa != null) {
	    Hibernate.initialize(pessoa.getEmprestimos());
	}

	return pessoa;
    }

    public void save(Pessoa pessoa) {
	persist(pessoa);
    }

    public void deleteByCPF(String cpf) {
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("cpf", cpf));
	Pessoa pessoa = (Pessoa) crit.uniqueResult();
	delete(pessoa);
    }

    public List<Pessoa> findAllPessoas() {
	Criteria criteria = createEntityCriteria().addOrder(Order.asc("nome"));
	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// Apaga os
								     // duplicados
	@SuppressWarnings("unchecked")
	List<Pessoa> pessoas = (List<Pessoa>) criteria.list();
	return pessoas;
    }

}
