package excecoes;

public class RegistroNaoEncontradoException extends Exception {
  
    public RegistroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
  
    public RegistroNaoEncontradoException(String usuario, int id) {
        super("Registro não encontrado: " + usuario + " com id " + id + ".");
    }
}
