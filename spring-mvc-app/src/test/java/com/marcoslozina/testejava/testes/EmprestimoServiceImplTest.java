package com.marcoslozina.testejava.testes;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.marcoslozina.testejava.dao.EmprestimoDao;
import com.marcoslozina.testejava.dao.LivroDao;
import com.marcoslozina.testejava.dao.PessoaDao;
import com.marcoslozina.testejava.model.Emprestimo;
import com.marcoslozina.testejava.model.Livro;
import com.marcoslozina.testejava.model.Pessoa;
import com.marcoslozina.testejava.service.EmprestimoServiceImpl;

import junit.framework.Assert;
public class EmprestimoServiceImplTest {
    @Mock
    EmprestimoDao daoEmprestimo;
    @Mock
    PessoaDao daoPessoa;
    @Mock
    LivroDao daoLivro;
    @InjectMocks
    EmprestimoServiceImpl emprestimoService;
    @Spy
    List<Livro> livros = new ArrayList<Livro>();
    @Spy
    List<Pessoa> pessoas = new ArrayList<Pessoa>();
    @Spy
    List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

    @BeforeClass
    public void setUp() throws ParseException {
	MockitoAnnotations.initMocks(this);
	emprestimos = getEmprestimos();
	livros = getLivros();
	pessoas = getPessoas();
    }

    @Test
    public void findById() {
	Emprestimo emprestimo = emprestimos.get(0);
	when(daoEmprestimo.findById(anyLong())).thenReturn(emprestimo);
	Assert.assertEquals(
		emprestimoService.findById(emprestimo.getIdemprestimo()),
		emprestimo);
    }

    @Test
    public String saveEmprestimo() {
	doNothing().when(daoEmprestimo).save(any(Emprestimo.class));
	emprestimoService.save(any(Emprestimo.class));
	verify(daoEmprestimo, atLeastOnce()).save(any(Emprestimo.class));
	return anyString();

    }

    @Test
    public void isLivroEmprestado() {
	Livro livroEmprestado = emprestimos.get(0).getLivro();
	Livro livroNaoEmprestado = livros.get(2);
	Assert.assertEquals(
		emprestimoService.isLivroEmprestado(livroEmprestado), true);
	Assert.assertEquals(
		emprestimoService.isLivroEmprestado(livroNaoEmprestado), false);
    }

    @Test
    public void isPessoaComIdadeMinimaParaEmprestimoDeLivro() {
	Pessoa pessoaComIdadeMinima = pessoas.get(0); // Data de nacimento
						      // 05-12-2003 tem 12 anos
	Pessoa pessoaSemIdadeMinima = pessoas.get(2); // Data de nacimento
						      // 15-09-2004 tem 11 anos
	Pessoa pessoaMaior = pessoas.get(1); // Data de nacimento 27-01-1988 tem
					     // 27 anos
	Livro livro = livros.get(2); // Livro:O Guaraní idade minima: 12 anos
	Assert.assertEquals(emprestimoService
		.isPessoaComIdadeMinimaParaEmprestimoDeLivro(
			pessoaComIdadeMinima, livro), true);
	Assert.assertEquals(emprestimoService
		.isPessoaComIdadeMinimaParaEmprestimoDeLivro(
			pessoaSemIdadeMinima, livro), false);
	Assert.assertEquals(
		emprestimoService.isPessoaComIdadeMinimaParaEmprestimoDeLivro(
			pessoaMaior, livro), true);
    }

    @Test
    public void isPessoaComDoisLivroEmprestados() {
	Pessoa pessoaComDoisLivrosEmprestados = pessoas.get(0);
	Pessoa pessoaSemDoisLivrosEmprestados = pessoas.get(1);
	Assert.assertEquals(
		emprestimoService
			.isPessoaComDoisLivroEmprestados(pessoaComDoisLivrosEmprestados),
		true);
	Assert.assertEquals(
		emprestimoService
			.isPessoaComDoisLivroEmprestados(pessoaSemDoisLivrosEmprestados),
		false);
    }

    @Test
    public void isDataEmprestimoMaiorAhoje() {

	DateTime tomorrowDate_1 = new DateTime();
	tomorrowDate_1 = tomorrowDate_1.plusDays(1);
	Date tomorrow_1 = tomorrowDate_1.toDate();

	Assert.assertEquals(
		emprestimoService.isDataEmprestimoMaiorAhoje(tomorrow_1), true);

    }

    @Test
    public void isDataDevolucaoMaiorAhoje() {

	Date today_2 = new Date();
	DateTime tomorrowDate_2 = new DateTime();
	tomorrowDate_2 = tomorrowDate_2.plusDays(1);
	Date tomorrow_2 = tomorrowDate_2.toDate();

	Assert.assertEquals(
		emprestimoService.isDataDevolucaoMaiorAhoje(today_2), false);
	Assert.assertEquals(
		emprestimoService.isDataDevolucaoMaiorAhoje(tomorrow_2), true);

    }

    @Test
    public void updateEmprestimo() {
	Emprestimo emprestimo = emprestimos.get(0);
	when(daoEmprestimo.findById(anyLong())).thenReturn(emprestimo);
	emprestimoService.update(emprestimo);
	verify(daoEmprestimo, atLeastOnce()).findById(anyLong());
    }

    @Test
    public void deleteEmprestimoById() {
	doNothing().when(daoEmprestimo).deleteById(anyLong());
	emprestimoService.deleteById(anyLong());
	verify(daoEmprestimo, atLeastOnce()).deleteById(anyLong());
    }

    @Test
    public void findAllEmprestimos() {
	when(daoEmprestimo.findAllEmprestimos()).thenReturn(emprestimos);
	Assert.assertEquals(emprestimoService.findAllEmprestimos(), emprestimos);
    }

    public List<Emprestimo> getEmprestimos() throws ParseException {
	Emprestimo e1 = new Emprestimo();

	e1.setIdemprestimo(1);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date datEmprestimo = sdf.parse("05/10/2015");
	e1.setDataemprestimo(datEmprestimo);
	e1.setDatahoradevolucao(null);
	Pessoa p1 = new Pessoa();
	p1.setIdpessoa(1);
	p1.setNome("Marcos Raimundo Lozina");
	p1.setCpf("870.829.000-82");

	Date dataNacimento1 = sdf.parse("27/01/1986"); // 29 anos
	p1.setDatanascimento(dataNacimento1);

	Livro l1 = new Livro();
	l1.setIdlivro(1);
	l1.setNome("Capitães da areia");
	l1.setEscritor("Jorge Amado");
	l1.setAnoedicao((short) 2008);
	l1.setAnoedicao((short) 12);

	Livro l2 = new Livro();
	l2.setIdlivro(2);
	l2.setNome(" Memórias póstumas de Brás Cubas");
	l2.setEscritor("Machado de Assis");
	l2.setAnoedicao((short) 2009);
	l2.setClassificacao((short) 10);

	Emprestimo e2 = new Emprestimo();

	e2.setIdemprestimo(1);

	datEmprestimo = sdf.parse("06/10/2015");
	e2.setDataemprestimo(datEmprestimo);
	e2.setDatahoradevolucao(null);
	e2.setPessoa(p1);
	e2.setLivro(l2);

	e1.setPessoa(p1);
	e1.setLivro(l1);
	emprestimos.add(e1);
	emprestimos.add(e2);

	return emprestimos;
    }

    public List<Livro> getLivros() {
	Livro l1 = new Livro();
	l1.setIdlivro(1);
	l1.setNome("Capitães da areia");
	l1.setEscritor("Jorge Amado");
	l1.setAnoedicao((short) 2008);
	l1.setClassificacao((short) 12);

	Livro l2 = new Livro();
	l2.setIdlivro(2);
	l2.setNome(" Memórias póstumas de Brás Cubas");
	l2.setEscritor("Machado de Assis");
	l2.setAnoedicao((short) 2009);
	l2.setClassificacao((short) 10);

	Livro l3 = new Livro();
	l3.setIdlivro(3);
	l3.setNome(" O Guaraní");
	l3.setEscritor("José de Alencar");
	l3.setAnoedicao((short) 2010);
	l3.setClassificacao((short) 12);

	livros.add(l1);
	livros.add(l2);
	livros.add(l3);
	return livros;
    }

    public List<Pessoa> getPessoas() throws ParseException {
	Pessoa p1 = new Pessoa();
	p1.setIdpessoa(1);
	p1.setNome("Marcos Raimundo Lozina");
	p1.setCpf("870.829.000-82");
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date dataNacimento1 = sdf.parse("05/10/2003"); // 12 anos
	p1.setDatanascimento(dataNacimento1);

	Pessoa p2 = new Pessoa();
	p2.setIdpessoa(2);
	p2.setNome("João pedro");
	p2.setCpf("915.256.452-91");
	Date dataNacimento2 = sdf.parse("15/09/2004"); // 11 anos
	p2.setDatanascimento(dataNacimento2);

	Pessoa p3 = new Pessoa();
	p3.setIdpessoa(3);
	p3.setNome("Aline Pereira");
	p2.setCpf("159.852.456-52");
	Date dataNacimento3 = sdf.parse("27/01/1988"); // 12 anos
	p2.setDatanascimento(dataNacimento3);

	pessoas.add(p1);
	pessoas.add(p2);
	pessoas.add(p3);

	return pessoas;
    }
}
