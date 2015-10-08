package com.marcoslozina.testejava.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "emprestimo", schema = "testedsl")
public class Emprestimo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private long idemprestimo;
	private Livro livro;
	private Pessoa pessoa;
	private Date dataemprestimo;
	private Date datahoradevolucao;

	public Emprestimo() {
	}

	public Emprestimo(long idemprestimo, Livro livro, Pessoa pessoa,
			Date dataemprestimo) {
		this.idemprestimo = idemprestimo;
		this.livro = livro;
		this.pessoa = pessoa;
		this.dataemprestimo = dataemprestimo;
	}

	public Emprestimo(long idemprestimo, Livro livro, Pessoa pessoa,
			Date dataemprestimo, Date datahoradevolucao) {
		this.idemprestimo = idemprestimo;
		this.livro = livro;
		this.pessoa = pessoa;
		this.dataemprestimo = dataemprestimo;
		this.datahoradevolucao = datahoradevolucao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idemprestimo", unique = true, nullable = false)
	public long getIdemprestimo() {
		return this.idemprestimo;
	}

	public void setIdemprestimo(long idemprestimo) {
		this.idemprestimo = idemprestimo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idlivro", nullable = false)
	public Livro getLivro() {
		return this.livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idpessoa", nullable = false)
	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dataemprestimo", nullable = false, length = 13)
	public Date getDataemprestimo() {
		return this.dataemprestimo;
	}

	public void setDataemprestimo(Date dataemprestimo) {
		this.dataemprestimo = dataemprestimo;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "datahoradevolucao", length = 15)
	public Date getDatahoradevolucao() {
		return this.datahoradevolucao;
	}

	public void setDatahoradevolucao(Date datahoradevolucao) {
		this.datahoradevolucao = datahoradevolucao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataemprestimo == null) ? 0 : dataemprestimo.hashCode());
		result = prime
				* result
				+ ((datahoradevolucao == null) ? 0 : datahoradevolucao
						.hashCode());
		result = prime * result + (int) (idemprestimo ^ (idemprestimo >>> 32));
		result = prime * result + ((livro == null) ? 0 : livro.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimo other = (Emprestimo) obj;
		if (dataemprestimo == null) {
			if (other.dataemprestimo != null)
				return false;
		} else if (!dataemprestimo.equals(other.dataemprestimo))
			return false;
		if (datahoradevolucao == null) {
			if (other.datahoradevolucao != null)
				return false;
		} else if (!datahoradevolucao.equals(other.datahoradevolucao))
			return false;
		if (idemprestimo != other.idemprestimo)
			return false;
		if (livro == null) {
			if (other.livro != null)
				return false;
		} else if (!livro.equals(other.livro))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}

}
