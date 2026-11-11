package telas;

import java.util.ArrayList;
import gerenciadores.GerenciadorEmprestimos;
import modelos.Livro;
import modelos.Usuario;

public class BibliotecaApp {
    
    private static ArrayList<Livro> livros = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    private static GerenciadorEmprestimos gerenciador = new GerenciadorEmprestimos();

    private static int proximoIdLivro = 1;
    private static int proximoIdUsuario = 1;

    public static void main(String[] args) {

    }
}