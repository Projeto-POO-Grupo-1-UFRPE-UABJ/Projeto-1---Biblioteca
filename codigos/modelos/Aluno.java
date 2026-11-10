package modelos;

public class Aluno extends Usuario {

	    public Aluno(int id, String nome, String matricula) {
	        super(id, nome, matricula);
	    }
	 
	    @Override
	    public int getPrazo() {
	        return 7;       
	    }
	 
	    @Override
	    public double getTaxa() {
	        return 1.00;  
	    }
	 
	    @Override
	    public int getLimiteMaximo() {
	        return 3;  
	    }
	 
	    @Override
	    public String getTipo() {
	        return "Aluno";
	    }
	}
