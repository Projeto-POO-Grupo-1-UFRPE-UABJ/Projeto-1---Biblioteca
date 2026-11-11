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
		if(!usuario.temLimite()) {
			throw new LimiteEmprestimosException("O usuário já alcançou o limite de " + usuario.getLimiteMaximo() + " empréstimos simultâneos.");
		}
		
		if(!livro.temExemplar()) {
			throw new LivroIndisponivelException("O livro não possui mais exemplares disponíveis.");
		}
		
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
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public boolean estaAberto() {
		return dataDevolucao == null;
	}
	
	public boolean estaAtrasado() {
		return calcAtraso() > 0;
	}
	
	public long calcAtraso() {
		return Math.max(0, dataPrevista.until(estaAberto() ? LocalDate.now() : dataDevolucao, ChronoUnit.DAYS));
	}
	
	public double calcMulta() {
		return calcAtraso() * usuario.getTaxa();
	}
	
	public double devolver() throws EmprestimoEncerradoException {
		return devolver(LocalDate.now());
	}
	
	public double devolver(LocalDate dataDevolucao) throws EmprestimoEncerradoException {
		if(this.dataDevolucao != null) {
			throw new EmprestimoEncerradoException("Não é possível encerrar este empréstimo pois ele já foi encerrado anteriormente na data: " + this.dataDevolucao);
		}
		
		this.dataDevolucao = dataDevolucao;
		usuario.subtrairLimite();
		livro.devolverExemplar();
		
		return calcMulta();
	}
}