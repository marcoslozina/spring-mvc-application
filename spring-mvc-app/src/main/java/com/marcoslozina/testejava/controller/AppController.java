package com.marcoslozina.testejava.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.marcoslozina.testejava.model.Emprestimo;
import com.marcoslozina.testejava.model.Livro;
import com.marcoslozina.testejava.model.Pessoa;
import com.marcoslozina.testejava.service.EmprestimoService;
import com.marcoslozina.testejava.service.LivroService;
import com.marcoslozina.testejava.service.PessoaService;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    LivroService livroService;

    @Autowired
    EmprestimoService emprestimoService;

    @Autowired
    MessageSource messageSource;

    // Gestão de pessoas

    @RequestMapping(value = { "/", "/indexGeral" }, method = RequestMethod.GET)
    public String indexGeral(ModelMap model) {
	return "indexGeral";
    }

    @RequestMapping(value = { "/", "/listpessoas" }, method = RequestMethod.GET)
    public String listPessoas(ModelMap model) {

	List<Pessoa> pessoas = pessoaService.findAllPessoas();
	model.addAttribute("pessoas", pessoas);
	return "pessoaslist";
    }

    @RequestMapping(value = { "/newpessoa" }, method = RequestMethod.GET)
    public String newPessoa(ModelMap model) {
	Pessoa pessoa = new Pessoa();
	model.addAttribute("pessoa", pessoa);
	model.addAttribute("edit", false);
	return "pessoaadd";
    }

    @RequestMapping(value = { "/newpessoa" }, method = RequestMethod.POST)
    public String savePessoa(@Valid Pessoa pessoa, BindingResult result,
	    ModelMap model) {

	if (result.hasErrors()) {
	    return "pessoaadd";
	}

	if (!pessoaService.isPessoaCPFunique(pessoa.getCpf())) {
	    FieldError cpfError = new FieldError("pessoa", "cpf",
		    messageSource.getMessage("non.unique.cpf",
			    new String[] { pessoa.getCpf() },
			    Locale.getDefault()));
	    result.addError(cpfError);
	    return "pessoaadd";
	}

	pessoaService.save(pessoa);

	model.addAttribute("success", "Pessoa " + pessoa.getNome() + " "
		+ " CPF: " + pessoa.getCpf() + " foi cadastrada com sucesso!");
	// return "success";
	return "pessoaRegistrationsuccess";
    }

    @RequestMapping(value = { "/edit-pessoa-{cpf}" }, method = RequestMethod.GET)
    public String editPessoa(@PathVariable String cpf, ModelMap model) {
	Pessoa pessoa = pessoaService.findByCPF(cpf);
	model.addAttribute("pessoa", pessoa);
	model.addAttribute("edit", true);
	return "pessoaadd";
    }

    @RequestMapping(value = { "/edit-pessoa-{cpf}" }, method = RequestMethod.POST)
    public String updatePessoa(@Valid Pessoa pessoa, BindingResult result,
	    ModelMap model, @PathVariable String cpf) {

	if (result.hasErrors()) {
	    return "pessoaadd";
	}

	pessoaService.update(pessoa);

	model.addAttribute("success", "Pessoa: " + pessoa.getNome() + " "
		+ " CPF: " + pessoa.getCpf() + " foi atualizado com sucesso!");
	return "pessoaRegistrationsuccess";
    }

    @RequestMapping(value = { "/delete-pessoa-{cpf}" }, method = RequestMethod.GET)
    public String deletePessoa(@PathVariable String cpf) {
	pessoaService.deleteByCPF(cpf);
	return "redirect:/listpessoas";
    }

    // Gestão de livros

    @RequestMapping(value = { "/", "/listlivros" }, method = RequestMethod.GET)
    public String listLivros(ModelMap model) {

	List<Livro> livros = livroService.findAllLivros();
	model.addAttribute("livros", livros);
	return "livroslist";
    }

    @RequestMapping(value = { "/newlivro" }, method = RequestMethod.GET)
    public String newLivro(ModelMap model) {
	Livro livro = new Livro();
	model.addAttribute("livro", livro);
	model.addAttribute("edit", false);
	return "livroadd";
    }

    @RequestMapping(value = { "/newlivro" }, method = RequestMethod.POST)
    public String saveLivro(@Valid Livro livro, BindingResult result,
	    ModelMap model) {

	if (result.hasErrors()) {
	    return "livroadd";
	}

	livroService.save(livro);

	model.addAttribute("success", "Livro: " + livro.getNome() + " "
		+ " escritor: " + livro.getEscritor()
		+ " foi cadastrada com sucesso!");
	// return "success";
	return "livroRegistrationsuccess";
    }

    @RequestMapping(value = { "/edit-livro-{idlivro}" }, method = RequestMethod.GET)
    public String editLivro(@PathVariable long idlivro, ModelMap model) {
	Livro livro = livroService.findById(idlivro);
	model.addAttribute("livro", livro);
	model.addAttribute("edit", true);
	return "livroadd";
    }

    @RequestMapping(value = { "/edit-livro-{idlivro}" }, method = RequestMethod.POST)
    public String updateLivro(@Valid Livro livro, BindingResult result,
	    ModelMap model, @PathVariable long idlivro) {

	if (result.hasErrors()) {
	    return "livroadd";
	}

	livroService.update(livro);

	model.addAttribute("success", "Livro: " + livro.getNome() + " "
		+ " escritor: " + livro.getEscritor()
		+ " foi atualizado com sucesso!");
	return "livroRegistrationsuccess";
    }

    @RequestMapping(value = { "/delete-livro-{idlivro}" }, method = RequestMethod.GET)
    public String deleteLivro(@PathVariable long idlivro) {
	livroService.deleteById(idlivro);
	return "redirect:/listlivros";
    }

    // Gestão de empréstimos

    @RequestMapping(value = { "/", "/listemprestimos" }, method = RequestMethod.GET)
    public String listEmprestimos(ModelMap model) {

	List<Emprestimo> emprestimos = emprestimoService.findAllEmprestimos();

	model.addAttribute("emprestimos", emprestimos);

	return "emprestimoslist";
    }

    @RequestMapping(value = { "/newemprestimo" }, method = RequestMethod.GET)
    public String newEmprestimo(ModelMap model) {
	Emprestimo emprestimo = new Emprestimo();
	List<Pessoa> pessoas = pessoaService.findAllPessoas();
	List<Livro> livros = livroService.findAllLivros();

	model.addAttribute("pessoas", pessoas);
	model.addAttribute("livros", livros);
	model.addAttribute("emprestimo", emprestimo);
	model.addAttribute("edit", false);
	return "emprestimoadd";
    }

    @RequestMapping(value = { "/newemprestimo" }, method = RequestMethod.POST)
    public String saveEmprestimo(@Valid Emprestimo emprestimo,
	    BindingResult result, ModelMap model) throws ParseException {

	if (emprestimoService.isLivroEmprestado(emprestimo.getLivro()) == true) {
	    FieldError livroEmprestadoErro = new FieldError("emprestimo",
		    "livro", messageSource.getMessage("livro.emprestado.erro",
			    new String[] { emprestimo.getLivro().getNome() },
			    Locale.getDefault()));
	    result.addError(livroEmprestadoErro);

	}
	if (emprestimoService.isPessoaComDoisLivroEmprestados(emprestimo
		.getPessoa()) == true) {
	    FieldError livroEmprestadoErro = new FieldError("emprestimo",
		    "pessoa", messageSource.getMessage(
			    "pessoa.com.dos.livros.erro",
			    new String[] { emprestimo.getPessoa().getNome() },
			    Locale.getDefault()));
	    result.addError(livroEmprestadoErro);

	}
	if (emprestimoService.isPessoaComIdadeMinimaParaEmprestimoDeLivro(
		emprestimo.getPessoa(), emprestimo.getLivro()) == false) {
	    FieldError livroEmprestadoErro = new FieldError("emprestimo",
		    "pessoa", messageSource.getMessage(
			    "pessoa.com.idade.minima.erro", new String[] {
				    emprestimo.getPessoa().getNome(),
				    emprestimo.getLivro().getClassificacao()
					    .toString(),
				    emprestimo.getLivro().getNome() },
			    Locale.getDefault()));
	    result.addError(livroEmprestadoErro);
	}
	if (emprestimoService.isDataEmprestimoMaiorAhoje(emprestimo
		.getDataemprestimo())) {
	    FieldError dataEmprestimoErro = new FieldError("emprestimo",
		    "dataemprestimo", messageSource.getMessage(
			    "data.emprestimo.maior.a.hoje.erro",
			    new String[] {}, Locale.getDefault()));
	    result.addError(dataEmprestimoErro);
	}
	if (emprestimoService.isDataDevolucaoMaiorAhoje(emprestimo
		.getDatahoradevolucao())) {
	    FieldError dataDevolucaoErro = new FieldError("emprestimo",
		    "datahoradevolucao", messageSource.getMessage(
			    "data.devolucao.maior.a.hoje.erro",
			    new String[] {}, Locale.getDefault()));
	    result.addError(dataDevolucaoErro);
	}

	if (result.hasErrors()) {
	    List<Pessoa> pessoas = pessoaService.findAllPessoas();
	    List<Livro> livros = livroService.findAllLivros();

	    model.addAttribute("pessoas", pessoas);
	    model.addAttribute("livros", livros);
	    return "emprestimoadd";
	}

	emprestimoService.save(emprestimo);

	model.addAttribute("success", "Emprestimo para: "
		+ emprestimo.getPessoa().getNome() + " " + " livro: "
		+ emprestimo.getLivro().getNome()
		+ " foi cadastrada com sucesso!");
	// return "success";
	return "emprestimoRegistrationsuccess";
    }

    @RequestMapping(value = { "/edit-emprestimo-{idemprestimo}" }, method = RequestMethod.GET)
    public String editEmprestimo(@PathVariable long idemprestimo, ModelMap model) {
	Emprestimo emprestimo = emprestimoService.findById(idemprestimo);
	List<Pessoa> pessoas = pessoaService.findAllPessoas();
	List<Livro> livros = livroService.findAllLivros();

	model.addAttribute("pessoas", pessoas);
	model.addAttribute("livros", livros);
	model.addAttribute("emprestimo", emprestimo);
	model.addAttribute("edit", true);
	return "emprestimoadd";
    }

    @RequestMapping(value = { "/edit-emprestimo-{idemprestimo}" }, method = RequestMethod.POST)
    public String updateEmprestimo(@Valid Emprestimo emprestimo,
	    BindingResult result, ModelMap model,
	    @PathVariable long idemprestimo) throws NoSuchMessageException,
	    ParseException {

	if (emprestimoService.isDataEmprestimoMaiorAhoje(emprestimo
		.getDataemprestimo())) {
	    FieldError dataEmprestimoErro = new FieldError("emprestimo",
		    "dataemprestimo", messageSource.getMessage(
			    "data.emprestimo.maior.a.hoje.erro",
			    new String[] {}, Locale.getDefault()));
	    result.addError(dataEmprestimoErro);
	}
	if (emprestimoService.isDataDevolucaoMaiorAhoje(emprestimo
		.getDatahoradevolucao())) {
	    FieldError dataDevolucaoErro = new FieldError("emprestimo",
		    "datahoradevolucao", messageSource.getMessage(
			    "data.devolucao.maior.a.hoje.erro",
			    new String[] {}, Locale.getDefault()));
	    result.addError(dataDevolucaoErro);
	}

	if (result.hasErrors()) {
	    List<Pessoa> pessoas = pessoaService.findAllPessoas();
	    List<Livro> livros = livroService.findAllLivros();

	    model.addAttribute("pessoas", pessoas);
	    model.addAttribute("livros", livros);
	    return "emprestimoadd";
	}

	emprestimoService.update(emprestimo);

	model.addAttribute("success", "Emprestimo da pessoa: "
		+ emprestimo.getPessoa().getNome() + " " + " livro: "
		+ emprestimo.getLivro().getNome()
		+ " foi atualizado com sucesso!");
	return "emprestimoRegistrationsuccess";
    }

    @RequestMapping(value = { "/delete-emprestimo-{idemprestimo}" }, method = RequestMethod.GET)
    public String deleteEmprestimo(@PathVariable long idemprestimo) {
	emprestimoService.deleteById(idemprestimo);
	return "redirect:/listemprestimos";
    }

}
