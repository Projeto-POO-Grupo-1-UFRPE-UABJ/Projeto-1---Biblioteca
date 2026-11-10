package modelos;

public class Professor extends Usuario {
	 
    public Professor(int id, String nome, String matricula) {
        super(id, nome, matricula);
    }
 
    @Override
    public int getPrazo() {
        return 15;      
    }
 
    @Override
    public double getTaxa() {
        return 0.50;   
    }
 
    @Override
    public int getLimiteMaximo() {
        return 5;      
    }
 
    @Override
    public String getTipo() {
        return "Professor";
    }
}
