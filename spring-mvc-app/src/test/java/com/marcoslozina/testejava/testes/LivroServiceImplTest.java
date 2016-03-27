package com.marcoslozina.testejava.testes;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.marcoslozina.testejava.dao.LivroDao;
import com.marcoslozina.testejava.model.Livro;
import com.marcoslozina.testejava.service.LivroServiceImpl;

import junit.framework.Assert;

public class LivroServiceImplTest {
    @Mock
    LivroDao dao;
    @InjectMocks
    LivroServiceImpl livroService;
    @Spy
    List<Livro> livros = new ArrayList<Livro>();

    @BeforeClass
    public void setUp() throws ParseException {
	MockitoAnnotations.initMocks(this);
	livros = getLivros();
    }

    @Test
    public void findById() {
	Livro livro = livros.get(0);
	when(dao.findById(anyLong())).thenReturn(livro);
	Assert.assertEquals(livroService.findById(livro.getIdlivro()), livro);
    }

    @Test
    public void findByNome() {
	Livro livro = livros.get(0);
	when(dao.findByNome(anyString())).thenReturn(livro);
	Assert.assertEquals(livroService.findByNome(livro.getNome()), livro);
    }

    @Test
    public String saveLivro() {
	doNothing().when(dao).save(any(Livro.class));
	livroService.save(any(Livro.class));
	verify(dao, atLeastOnce()).save(any(Livro.class));
	return anyString();
    }

    @Test
    public void updateLivro() {
	Livro livro = livros.get(0);
	when(dao.findByNome(anyString())).thenReturn(livro);
	livroService.update(livro);
	verify(dao, atLeastOnce()).findByNome(anyString());
    }

    @Test
    public void deleteLivroById() {
	doNothing().when(dao).deleteById(anyLong());
	livroService.deleteById(anyLong());
	verify(dao, atLeastOnce()).deleteById(anyLong());
    }

    @Test
    public void findAllLivros() {
	when(dao.findAllLivros()).thenReturn(livros);
	Assert.assertEquals(livroService.findAllLivros(), livros);
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

}
