package modelos;

public abstract class Usuario {
	
	private int id;
	private String nome;
	private String matricula;
	
	private int limite;
	
	public Usuario(int id, String nome, String matricula) {
		this.nome = nome;
		this.id = id;
		this.matricula = matricula;
		this.limite = 0;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public int getLimite() {
		return limite;
	}
	
	public boolean temLimite() {
		return limite < getLimiteMaximo();
	}
	public void somarLimite() {
		limite++;
	}
	public void subtrairLimite() {
		if(limite > 0) {
			limite--;
		}
	}
	
	public abstract int getPrazo();       
	 
    public abstract double getTaxa();     
 
    public abstract int getLimiteMaximo();
 
    public abstract String getTipo(); 
    
    @Override
    public String toString() {
        return getTipo() + " " + id + " - " + nome + " (matrícula " + matricula + ")";
    }
}
