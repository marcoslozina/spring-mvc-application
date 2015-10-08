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

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.marcoslozina.testejava.controller.AppController;
import com.marcoslozina.testejava.model.Emprestimo;
import com.marcoslozina.testejava.model.Livro;
import com.marcoslozina.testejava.model.Pessoa;
import com.marcoslozina.testejava.service.EmprestimoService;
import com.marcoslozina.testejava.service.LivroService;
import com.marcoslozina.testejava.service.PessoaService;

public class AppControllerTest {

    @Mock
    LivroService livroService;
    @Mock
    PessoaService pessoaService;
    @Mock
    EmprestimoService emprestimoService;
    @Mock
    MessageSource message;
    @InjectMocks
    AppController appController;
    @Spy
    List<Livro> livros = new ArrayList<Livro>();
    @Spy
    List<Pessoa> pessoas = new ArrayList<Pessoa>();
    @Spy
    List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    @Spy
    ModelMap model;
    @Mock
    BindingResult result;

    @BeforeMethod
    @BeforeClass
    public void setUp() throws ParseException {
	MockitoAnnotations.initMocks(this);
	livros = getLivros();
	pessoas = getPessoas();
	emprestimos = getEmprestimos();
    }

    @Test
    public void listPessoas() {
	when(pessoaService.findAllPessoas()).thenReturn(pessoas);
	AssertJUnit.assertEquals(appController.listPessoas(model),
		"pessoaslist");
	AssertJUnit.assertEquals(model.get("pessoas"), pessoas);
	verify(pessoaService, atLeastOnce()).findAllPessoas();
    }

    @Test
    public void listLivros() {
	when(livroService.findAllLivros()).thenReturn(livros);
	AssertJUnit.assertEquals(appController.listLivros(model), "livroslist");
	AssertJUnit.assertEquals(model.get("livros"), livros);
	verify(livroService, atLeastOnce()).findAllLivros();
    }

    @Test
    public void listEmprestimos() {
	when(emprestimoService.findAllEmprestimos()).thenReturn(emprestimos);
	AssertJUnit.assertEquals(appController.listEmprestimos(model),
		"emprestimoslist");
	AssertJUnit.assertEquals(model.get("emprestimos"), emprestimos);
	verify(emprestimoService, atLeastOnce()).findAllEmprestimos();
    }

    @Test
    public void newPessoa() {
	AssertJUnit.assertEquals(appController.newPessoa(model), "pessoaadd");
	AssertJUnit.assertNotNull(model.get("pessoa"));
	Assert.assertFalse((Boolean) model.get("edit"));
    }

    @Test
    public void newLivro() {
	AssertJUnit.assertEquals(appController.newLivro(model), "livroadd");
	AssertJUnit.assertNotNull(model.get("livro"));
	Assert.assertFalse((Boolean) model.get("edit"));
    }

    @Test
    public void newEmprestimo() {
	AssertJUnit.assertEquals(appController.newEmprestimo(model),
		"emprestimoadd");
	AssertJUnit.assertNotNull(model.get("emprestimo"));
	Assert.assertFalse((Boolean) model.get("edit"));
    }

    @Test
    public void savePessoaWithValidationError() {
	when(result.hasErrors()).thenReturn(true);
	doNothing().when(pessoaService).save(any(Pessoa.class));
	AssertJUnit.assertEquals(
		appController.savePessoa(pessoas.get(0), result, model),
		"pessoaadd");
    }

    @Test
    public void savePessoaWithSuccess() {
	when(result.hasErrors()).thenReturn(false);
	when(pessoaService.isPessoaCPFunique(anyString())).thenReturn(true);
	doNothing().when(pessoaService).save(any(Pessoa.class));
	AssertJUnit.assertEquals(
		appController.savePessoa(pessoas.get(0), result, model),
		"pessoaRegistrationsuccess");
	AssertJUnit
		.assertEquals(
			model.get("success"),
			"Pessoa Marcos Raimundo Lozina  CPF: 870.829.000-82 foi cadastrada com sucesso!");
    }

    @Test
    public void saveLivroWithSuccess() {
	when(result.hasErrors()).thenReturn(false);
	doNothing().when(livroService).save(any(Livro.class));
	AssertJUnit.assertEquals(
		appController.saveLivro(livros.get(0), result, model),
		"livroRegistrationsuccess");
	AssertJUnit
		.assertEquals(model.get("success"),
			"Livro: Capitães da areia  escritor: Jorge Amado foi cadastrada com sucesso!");
    }

    @Test
    public void saveLivroWithValidationError() {
	when(result.hasErrors()).thenReturn(true);
	doNothing().when(livroService).save(any(Livro.class));
	AssertJUnit.assertEquals(
		appController.saveLivro(livros.get(0), result, model),
		"livroadd");
    }

    @Test
    public void saveEmprestimoWithValidationError() throws ParseException {

	when(emprestimoService.isDataEmprestimoMaiorAhoje(new Date()))
		.thenReturn(false);
	when(emprestimoService.isDataDevolucaoMaiorAhoje(new Date()))
		.thenReturn(false);
	when(emprestimoService.isLivroEmprestado(any(Livro.class))).thenReturn(
		false);
	when(
		emprestimoService
			.isPessoaComDoisLivroEmprestados(any(Pessoa.class)))
		.thenReturn(false);
	when(
		emprestimoService.isPessoaComIdadeMinimaParaEmprestimoDeLivro(
			any(Pessoa.class), any(Livro.class))).thenReturn(true);
	when(result.hasErrors()).thenReturn(true);
	doNothing().when(emprestimoService).save(any(Emprestimo.class));
	AssertJUnit
		.assertEquals(appController.saveEmprestimo(emprestimos.get(0),
			result, model), "emprestimoadd");
    }

    @Test
    public void saveEmprestimoWithSuccess() throws ParseException {
	when(emprestimoService.isDataEmprestimoMaiorAhoje(new Date()))
		.thenReturn(false);
	when(emprestimoService.isDataDevolucaoMaiorAhoje(new Date()))
		.thenReturn(false);
	when(emprestimoService.isLivroEmprestado(any(Livro.class))).thenReturn(
		false);
	when(
		emprestimoService
			.isPessoaComDoisLivroEmprestados(any(Pessoa.class)))
		.thenReturn(false);
	when(
		emprestimoService.isPessoaComIdadeMinimaParaEmprestimoDeLivro(
			any(Pessoa.class), any(Livro.class))).thenReturn(true);
	when(result.hasErrors()).thenReturn(false);
	doNothing().when(emprestimoService).save(any(Emprestimo.class));
	AssertJUnit
		.assertEquals(appController.saveEmprestimo(emprestimos.get(0),
			result, model), "emprestimoRegistrationsuccess");
	AssertJUnit
		.assertEquals(
			model.get("success"),
			"Emprestimo para: Marcos Raimundo Lozina  livro: Capitães da areia foi cadastrada com sucesso!");
    }

    @Test
    public void editPessoa() {
	Pessoa pessoa = pessoas.get(0);
	when(pessoaService.findByCPF(anyString())).thenReturn(pessoa);
	Assert.assertEquals(appController.editPessoa(anyString(), model),
		"pessoaadd");
	Assert.assertNotNull(model.get("pessoa"));
	Assert.assertTrue((Boolean) model.get("edit"));
	Assert.assertEquals(((Pessoa) model.get("pessoa")).getIdpessoa(), 1);
    }

    @Test
    public void editLivro() {
	Livro livro = livros.get(0);
	when(livroService.findById(anyLong())).thenReturn(livro);
	Assert.assertEquals(appController.editLivro(anyLong(), model),
		"livroadd");
	Assert.assertNotNull(model.get("livro"));
	Assert.assertTrue((Boolean) model.get("edit"));
	Assert.assertEquals(((Livro) model.get("livro")).getIdlivro(), 1);
    }

    @Test
    public void editEmprestimo() {
	Emprestimo emprestimo = emprestimos.get(0);
	when(emprestimoService.findById(anyLong())).thenReturn(emprestimo);
	Assert.assertEquals(appController.editEmprestimo(anyLong(), model),
		"emprestimoadd");
	Assert.assertNotNull(model.get("emprestimo"));
	Assert.assertTrue((Boolean) model.get("edit"));
	Assert.assertEquals(
		((Emprestimo) model.get("emprestimo")).getIdemprestimo(), 1);
    }

    @Test
    public void updatePessoaWithValidationError() {
	when(result.hasErrors()).thenReturn(true);
	doNothing().when(pessoaService).update(any(Pessoa.class));
	Assert.assertEquals(appController.updatePessoa(pessoas.get(0), result,
		model, pessoas.get(0).getCpf()), "pessoaadd");
    }

    @Test
    public void updateLivroWithValidationError() {
	when(result.hasErrors()).thenReturn(true);
	doNothing().when(livroService).update(any(Livro.class));
	Assert.assertEquals(appController.updateLivro(livros.get(0), result,
		model, livros.get(0).getIdlivro()), "livroadd");
    }

    @Test
    public void updateEmprestimoWithValidationError()
	    throws NoSuchMessageException, ParseException {
	when(result.hasErrors()).thenReturn(true);
	doNothing().when(emprestimoService).update(any(Emprestimo.class));
	Assert.assertEquals(appController.updateEmprestimo(emprestimos.get(0),
		result, model, emprestimos.get(0).getIdemprestimo()),
		"emprestimoadd");
    }

    @Test
    public void updatePessoaWithSuccess() {
	when(result.hasErrors()).thenReturn(false);

	doNothing().when(pessoaService).update(any(Pessoa.class));
	Assert.assertEquals(appController.updatePessoa(pessoas.get(0), result,
		model, pessoas.get(0).getCpf()), "pessoaRegistrationsuccess");
	Assert.assertEquals(
		model.get("success"),
		"Pessoa: Marcos Raimundo Lozina  CPF: 870.829.000-82 foi atualizado com sucesso!");
    }

    @Test
    public void updateLivroWithSuccess() {
	when(result.hasErrors()).thenReturn(false);

	doNothing().when(livroService).update(any(Livro.class));
	Assert.assertEquals(appController.updateLivro(livros.get(0), result,
		model, livros.get(0).getIdlivro()), "livroRegistrationsuccess");
	Assert.assertEquals(model.get("success"),
		"Livro: Capitães da areia  escritor: Jorge Amado foi atualizado com sucesso!");
    }

    @Test
    public void updateEmprestimoWithSuccess() throws NoSuchMessageException,
	    ParseException {
	when(result.hasErrors()).thenReturn(false);

	doNothing().when(emprestimoService).update(any(Emprestimo.class));
	Assert.assertEquals(appController.updateEmprestimo(emprestimos.get(0),
		result, model, emprestimos.get(0).getIdemprestimo()),
		"emprestimoRegistrationsuccess");
	Assert.assertEquals(
		model.get("success"),
		"Emprestimo da pessoa: Marcos Raimundo Lozina  livro: Capitães da areia foi atualizado com sucesso!");
    }

    @Test
    public void deletePessoa() {
	doNothing().when(pessoaService).deleteByCPF(anyString());
	Assert.assertEquals(appController.deletePessoa("123"),
		"redirect:/listpessoas");
    }

    @Test
    public void deleteLivro() {
	doNothing().when(livroService).deleteById(anyLong());
	Assert.assertEquals(appController.deleteLivro(123),
		"redirect:/listlivros");
    }

    @Test
    public void deleteEmprestimo() {
	doNothing().when(emprestimoService).deleteById(anyLong());
	Assert.assertEquals(appController.deleteEmprestimo(123),
		"redirect:/listemprestimos");
    }

    public List<Livro> getLivros() {
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
	l2.setAnoedicao((short) 10);

	Livro l3 = new Livro();
	l3.setIdlivro(3);
	l3.setNome(" O Guaraní");
	l3.setEscritor("José de Alencar");
	l3.setAnoedicao((short) 2010);
	l3.setAnoedicao((short) 11);

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
	Date dataNacimento1 = sdf.parse("27/01/1988"); // 27 anos
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
	Date dataNacimento3 = sdf.parse("05/12/2003"); // 12 anos
	p2.setDatanascimento(dataNacimento3);

	pessoas.add(p1);
	pessoas.add(p2);
	pessoas.add(p3);

	return pessoas;
    }

    public List<Emprestimo> getEmprestimos() throws ParseException {
	Emprestimo e1 = new Emprestimo();

	e1.setIdemprestimo(1);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date datEmprestimo = sdf.parse("05/10/2015");
	e1.setDataemprestimo(datEmprestimo);
	e1.setDatahoradevolucao(datEmprestimo);
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

	e1.setPessoa(p1);
	e1.setLivro(l1);
	emprestimos.add(e1);

	return emprestimos;
    }
}
