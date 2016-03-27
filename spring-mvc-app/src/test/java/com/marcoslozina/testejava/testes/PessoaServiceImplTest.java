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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.marcoslozina.testejava.dao.PessoaDao;
import com.marcoslozina.testejava.model.Pessoa;
import com.marcoslozina.testejava.service.PessoaServiceImpl;

import junit.framework.Assert;

public class PessoaServiceImplTest {
    @Mock
    PessoaDao dao;
    @InjectMocks
    PessoaServiceImpl pessoaService;
    @Spy
    List<Pessoa> pessoas = new ArrayList<Pessoa>();

    @BeforeClass
    public void setUp() throws ParseException {
	MockitoAnnotations.initMocks(this);
	pessoas = getPessoas();
    }

    @Test
    public void findById() {
	Pessoa pessoa = pessoas.get(0);
	when(dao.findById(anyLong())).thenReturn(pessoa);
	Assert.assertEquals(pessoaService.findById(pessoa.getIdpessoa()),
		pessoa);
    }

    @Test
    public void findByCPF() {
	Pessoa pessoa = pessoas.get(0);
	when(dao.findByCPF(anyString())).thenReturn(pessoa);
	Assert.assertEquals(pessoaService.findByCPF(pessoa.getCpf()), pessoa);
    }

    @Test
    public void isPessoaCPFunique() {
	Pessoa pessoa = pessoas.get(0);
	when(dao.findByCPF(anyString())).thenReturn(pessoa);
	Assert.assertEquals(pessoaService.isPessoaCPFunique(pessoa.getCpf()),
		true);
    }

    @Test
    public String savePessoa() {
	doNothing().when(dao).save(any(Pessoa.class));
	pessoaService.save(any(Pessoa.class));
	verify(dao, atLeastOnce()).save(any(Pessoa.class));
	return anyString();
    }

    @Test
    public void updatePessoa() {
	Pessoa pessoa = pessoas.get(0);
	when(dao.findByCPF(anyString())).thenReturn(pessoa);
	pessoaService.update(pessoa);
	verify(dao, atLeastOnce()).findByCPF(anyString());
    }

    @Test
    public void deletePessoaByCPF() {
	doNothing().when(dao).deleteByCPF(anyString());
	pessoaService.deleteByCPF(anyString());
	verify(dao, atLeastOnce()).deleteByCPF(anyString());
    }

    @Test
    public void findAllPessoas() {
	when(dao.findAllPessoas()).thenReturn(pessoas);
	Assert.assertEquals(pessoaService.findAllPessoas(), pessoas);
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

}
