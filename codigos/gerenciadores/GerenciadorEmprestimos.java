package gerenciadores;

import java.util.ArrayList;
import java.lang.StringBuilder;
import java.time.LocalDate;
import modelos.*;
import excecoes.*;

public class GerenciadorEmprestimos {
	private ArrayList<Emprestimo> emprestimos;
    
    public enum FiltroEmprestimos {
        TODOS,
        ATIVOS,
        ENCERRADOS,
        ATRASADOS
    }
    
    public GerenciadorEmprestimos() {
        this.emprestimos = new ArrayList<>();
    }
    
    public GerenciadorEmprestimos(ArrayList<Emprestimo> emprestimos) {
        this.emprestimos = new ArrayList<Emprestimo>(emprestimos);
    }
    
    public ArrayList<Emprestimo> getEmprestimos() {
        return new ArrayList<Emprestimo>(emprestimos);
    }
    
    public Emprestimo realizarEmprestimo(Usuario usuario, Livro livro) throws LivroIndisponivelException, LimiteEmprestimosException {
        Emprestimo emprestimo = new Emprestimo(emprestimos.size() + 1, usuario, livro);
        emprestimos.add(emprestimo);
        return emprestimo;
    }
    
    public double encerrarEmprestimo(int id) throws RegistroNaoEncontradoException, EmprestimoEncerradoException {
        return encerrarEmprestimo(id, LocalDate.now());
    }
    
    public double encerrarEmprestimo(int id, LocalDate dataDevolucao) throws RegistroNaoEncontradoException, EmprestimoEncerradoException {
        if(emprestimos.isEmpty() || emprestimos.size() < id || id <= 0) {
            throw new RegistroNaoEncontradoException("Não há nenhum empréstimo com o Id " + id + ".\nInsira um Id válido.");
        }
        return emprestimos.get(id - 1).devolver(dataDevolucao);
    }
    
    public String listarEmprestimos() {
    	return listarEmprestimos(FiltroEmprestimos.TODOS);
    }
    
    public String listarEmprestimos(FiltroEmprestimos filtro) {
        if (emprestimos.isEmpty()) {
            return "Ainda não há nenhum empréstimo cadastrado.\n\n";
        } else {
            StringBuilder sb = new StringBuilder();
            int encontrados = 0;

            switch(filtro) {
                case TODOS:
                	sb.append("Lista de todos os empréstimos:\n\n");
                	for(Emprestimo emprestimo : emprestimos) {
                		sb.append(formatarEmprestimo(emprestimo));
            		}
                	
                	break;
                
                case ATIVOS:
                	sb.append("Lista de todos os empréstimos ativos:\n\n");
                	for(Emprestimo emprestimo : emprestimos) {
                	    if(emprestimo.estaAberto()) {
                	    	sb.append(formatarEmprestimo(emprestimo));
                            encontrados++;
                	    }
            		}

                	if(encontrados == 0) {
                        sb.append("- Não há nenhum empréstimo ativo.\n\n");
                    }
                	break;
                
                case ENCERRADOS:
                	sb.append("Lista de todos os empréstimos encerrados:\n\n");
                	for(Emprestimo emprestimo : emprestimos) {
                		if(!emprestimo.estaAberto()) {
                	    	sb.append(formatarEmprestimo(emprestimo));
                            encontrados++;
                	    }
            		}
                	if(encontrados == 0) {
                        sb.append("- Não há nenhum empréstimo encerrado.\n\n");
                    }
                	break;

                case ATRASADOS:
                	sb.append("Lista de todos os empréstimos atrasados:\n\n");
                	for(Emprestimo emprestimo : emprestimos) {
                		if(emprestimo.estaAtrasado()) {
                	    	sb.append(formatarEmprestimo(emprestimo));
                            encontrados++;
                	    }
            		}
                	if(encontrados == 0) {
                        sb.append("- Não há nenhum empréstimo atrasado.\n\n");
                    }
                	break;

                default:
                    return "Insira um tipo de filtro válido. (TODOS, ATIVOS, ENCERRADOS, ATRASADOS)\n\n";
  
            }

            sb.append("\n");
            return sb.toString();
        }
    }
    
    public String formatarEmprestimo(Emprestimo emprestimo) {
        StringBuilder sb = new StringBuilder();

        sb.append("Id: ").append(emprestimo.getId()).append("\n");
        sb.append("- Usuário: ").append(emprestimo.getUsuario().getNome()).append(" (").append(emprestimo.getUsuario().getTipo()).append(")\n");
        sb.append("- Id Usuário: ").append(emprestimo.getUsuario().getId()).append("\n");
        sb.append("- Matrícula Usuário: ").append(emprestimo.getUsuario().getMatricula()).append("\n");
        sb.append("- Livro: ").append(emprestimo.getLivro().getTitulo()).append("\n");
        sb.append("- Autor livro: ").append(emprestimo.getLivro().getAutor()).append("\n");
        sb.append("- ISBN livro: ").append(emprestimo.getLivro().getIsbn()).append("\n");
        sb.append("- Id Livro: ").append(emprestimo.getLivro().getId()).append("\n"); 
        
        if(emprestimo.estaAtrasado()) {
            sb.append(emprestimo.estaAberto() ? "- Este empréstimo está ATRASADO.\n" : "- Este empréstimo foi devolvido com ATRASO.\n");
            sb.append("- Multa ").append(emprestimo.estaAberto() ? "acumulada (até o momento): R$ " : "total: R$ ").append(String.format("%.2f", emprestimo.calcMulta())).append("\n");
        } else {
            sb.append("- Este empréstimo NÃO está atrasado.\n");
        }
        sb.append("- Status: ").append((emprestimo.estaAberto() ? "ATIVO\n" : "ENCERRADO\n"));
        sb.append("\n");

        return sb.toString();
    }
}
