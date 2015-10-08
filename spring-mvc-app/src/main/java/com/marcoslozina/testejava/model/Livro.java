package com.marcoslozina.testejava.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "livro", schema = "testedsl")
public class Livro implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private long idlivro;
    private String nome;
    private String escritor;
    private Short anoedicao;
    private Short classificacao;
    private Set<Emprestimo> emprestimos = new HashSet<Emprestimo>(0);

    public Livro() {
	// this.emprestimos = new HashSet<Emprestimo>();
    }

    public Livro(long idlivro) {
	this.idlivro = idlivro;
    }

    public Livro(long idlivro, String nome, String escritor, Short anoedicao, Short classificacao, Set<Emprestimo> emprestimos) {
	this.idlivro = idlivro;
	this.nome = nome;
	this.escritor = escritor;
	this.anoedicao = anoedicao;
	this.classificacao = classificacao;

	if (emprestimos == null) {
	    this.emprestimos = new HashSet<Emprestimo>();
	}

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlivro", unique = true, nullable = false)
    public long getIdlivro() {
	return this.idlivro;
    }

    public void setIdlivro(long idlivro) {
	this.idlivro = idlivro;
    }

    @Column(name = "nome", length = 100)
    public String getNome() {
	return this.nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    @Column(name = "escritor", length = 100)
    public String getEscritor() {
	return this.escritor;
    }

    public void setEscritor(String escritor) {
	this.escritor = escritor;
    }

    @Column(name = "anoedicao")
    public Short getAnoedicao() {
	return this.anoedicao;
    }

    public void setAnoedicao(Short anoedicao) {
	this.anoedicao = anoedicao;
    }

    @Column(name = "classificacao")
    public Short getClassificacao() {
	return this.classificacao;
    }

    public void setClassificacao(Short classificacao) {
	this.classificacao = classificacao;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "livro")
    public Set<Emprestimo> getEmprestimos() {
	return this.emprestimos;
    }

    public void setEmprestimos(Set<Emprestimo> emprestimos) {
	this.emprestimos = emprestimos;
    }

    @Override
    public String toString() {
	return "Livro [idlivro=" + idlivro + ", nome=" + nome + ", escritor=" + escritor + ", anoedicao=" + anoedicao + ", classificacao=" + classificacao + ", emprestimos=" + emprestimos + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Livro))
	    return false;
	Livro other = (Livro) obj;
	if (nome == null) {
	    if (other.getNome() != null)
		return false;
	} else if (!nome.equals(other.getNome()))
	    return false;
	return true;
    }

}
