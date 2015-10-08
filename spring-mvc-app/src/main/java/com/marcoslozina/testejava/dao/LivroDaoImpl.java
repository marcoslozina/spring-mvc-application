package com.marcoslozina.testejava.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.marcoslozina.testejava.model.Livro;

@Repository("livroDao")
public class LivroDaoImpl extends AbstractDao<Integer, Livro> implements LivroDao {

    public Livro findById(int id) {
	System.out.println("Livro ID: " + id);
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("idlivro", id));
	Livro livro = (Livro) crit.uniqueResult();

	if (livro != null) {
	    Hibernate.initialize(livro.getEmprestimos());
	}
	return livro;
    }

    public Livro findByNome(String nome) {
	System.out.println("Livro nome: " + nome);
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("nome", nome));
	Livro livro = (Livro) crit.uniqueResult();

	if (livro != null) {
	    Hibernate.initialize(livro.getEmprestimos());
	}
	return livro;
    }

    public void save(Livro livro) {
	persist(livro);
    }

    public void deleteById(long id) {
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("idlivro", id));
	Livro livro = (Livro) crit.uniqueResult();
	delete(livro);

    }

    public List<Livro> findAllLivros() {
	Criteria criteria = createEntityCriteria().addOrder(Order.asc("nome"));
	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// Apaga os
								     // duplicados
	@SuppressWarnings("unchecked")
	List<Livro> livros = (List<Livro>) criteria.list();
	return livros;
    }

    public Livro findById(long id) {
	System.out.println("Livro ID: " + id);
	Criteria crit = createEntityCriteria();
	crit.add(Restrictions.eq("idlivro", id));
	Livro livro = (Livro) crit.uniqueResult();

	if (livro != null) {
	    Hibernate.initialize(livro.getEmprestimos());
	}
	return livro;
    }

}
