package modelos;

import java.time.LocalDate;
import java.lang.Math;
import java.time.temporal.ChronoUnit;
import excecoes.*;

public class Emprestimo {
	private int id; 
	private LocalDate dataEmprestimo;
	private LocalDate dataPrevista;
	private LocalDate dataDevolucao;
	private Usuario usuario;
	private Livro livro;
	
	public Emprestimo(int id, Usuario usuario, Livro livro) throws LivroIndisponivelException, LimiteEmprestimosException {
		usuario.somarLimite();
		livro.emprestarExemplar();
		
		this.usuario = usuario;
		this.livro = livro;
		this.id = id;
		this.dataEmprestimo = LocalDate.now();
		this.dataPrevista = dataEmprestimo.plusDays(usuario.getPrazo());
		this.dataDevolucao = null;
	}
	
	public int getId() {
		return id;
	}
	
	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}
	
	public LocalDate getDataPrevista() {
		return dataPrevista;
	}
	
	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}
	
	public Usuario getUsario() {
		return usuario;
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public boolean estaAberto() {
		return dataDevolucao == null ? true : false;
	}
	
	public boolean estaAtrasado() {
		return dataDevolucao == null ? LocalDate.now().isAfter(dataPrevista) : dataDevolucao.isAfter(dataPrevista);
	}
	
	public long calcAtraso() {
		return Math.max(0, dataPrevista.until(dataDevolucao, ChronoUnit.DAYS));
	}
	
	public double calcMulta() {
		return calcAtraso() > 0 ? calcAtraso() * usuario.getTaxa() : 0.00;
	}
	
	public double devolver() {
		dataDevolucao = LocalDate.now();
		usuario.subtrairLimite();
		livro.devolverExemplar();
		return calcMulta();
	}
	
	public double devolver(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
		usuario.subtrairLimite();
		livro.devolverExemplar();
		return calcMulta();
	}
}