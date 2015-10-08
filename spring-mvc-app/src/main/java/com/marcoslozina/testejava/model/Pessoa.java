package com.marcoslozina.testejava.model;

import java.util.Date;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pessoa", schema = "testedsl")
public class Pessoa implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private long idpessoa;
    private String nome;
    private Date datanascimento;
    private String cpf;
    private Set<Emprestimo> emprestimos = new HashSet<Emprestimo>(0);

    public Pessoa() {
	// this.emprestimos = new HashSet<Emprestimo>();
    }

    public Pessoa(long idpessoa, String nome) {
	this.idpessoa = idpessoa;
	this.nome = nome;
    }

    public Pessoa(long idpessoa, String nome, Date datanascimento, String cpf, Set<Emprestimo> emprestimos) {
	this.idpessoa = idpessoa;
	this.nome = nome;
	this.datanascimento = datanascimento;
	this.cpf = cpf;
	if (emprestimos == null) {
	    this.emprestimos = new HashSet<Emprestimo>();
	}
	this.emprestimos = emprestimos;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpessoa", unique = true, nullable = false)
    public long getIdpessoa() {
	return this.idpessoa;
    }

    public void setIdpessoa(long idpessoa) {
	this.idpessoa = idpessoa;
    }

    @Column(name = "nome", nullable = false, length = 100)
    public String getNome() {
	return this.nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "datanascimento", length = 13)
    public Date getDatanascimento() {
	return this.datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
	this.datanascimento = datanascimento;
    }

    @Column(name = "cpf", length = 14)
    public String getCpf() {
	return this.cpf;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pessoa")
    public Set<Emprestimo> getEmprestimos() {
	return this.emprestimos;
    }

    public void setEmprestimos(Set<Emprestimo> emprestimos) {
	this.emprestimos = emprestimos;
    }

    @Override
    public String toString() {
	return "Pessoa [idpessoa=" + idpessoa + ", nome=" + nome + ", datanascimento=" + datanascimento + ", cpf=" + cpf + ", emprestimos=" + emprestimos + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (!(obj instanceof Pessoa))
	    return false;
	Pessoa other = (Pessoa) obj;
	if (cpf == null) {
	    if (other.getCpf() != null)
		return false;
	} else if (!cpf.equals(other.getCpf()))
	    return false;
	return true;
    }

}
