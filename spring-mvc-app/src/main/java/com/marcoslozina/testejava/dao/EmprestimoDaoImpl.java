package com.marcoslozina.testejava.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.marcoslozina.testejava.model.Emprestimo;
import com.marcoslozina.testejava.model.Pessoa;

@Repository("emprestimoDao")
public class EmprestimoDaoImpl extends AbstractDao<Integer, Emprestimo> implements EmprestimoDao {

    public void save(Emprestimo emprestimo) {
	persist(emprestimo);
    }

    public List<Emprestimo> findAllEmprestimos() {
	Criteria criteria = createEntityCriteria().addOrder(Order.asc("dataemprestimo"));
	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// Apaga os
								     // duplicados
	@SuppressWarnings("unchecked")
	List<Emprestimo> emprestimos = (List<Emprestimo>) criteria.list();

	return emprestimos;
    }

    public List<Emprestimo> findAllEmprestimosPessoa(Pessoa pessoa) {
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("pessoa", pessoa));

	@SuppressWarnings("unchecked")
	List<Emprestimo> emprestimos = (List<Emprestimo>) crit.list();
	return emprestimos;
    }

    public Emprestimo findById(long id) {
	System.out.println("Emprestimo ID: " + id);
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("idemprestimo", id));
	Emprestimo emprestimo = (Emprestimo) crit.uniqueResult();

	if (emprestimo != null) {
	    Hibernate.initialize(emprestimo.getPessoa());
	    Hibernate.initialize(emprestimo.getLivro());
	}
	return emprestimo;
    }

    public void deleteById(long id) {
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("idemprestimo", id));
	Emprestimo emprestimo = (Emprestimo) crit.uniqueResult();
	delete(emprestimo);

    }

}
