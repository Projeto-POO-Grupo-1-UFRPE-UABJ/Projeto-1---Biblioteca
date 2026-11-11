public class RegistroNaoEncontradoException extends Exception {
  
    public RegistroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
  
    public RegistroNaoEncontradoException(String entidade, int id) {
        super("Registro não encontrado: " + entidade + " com id " + id + ".");
    }
}
