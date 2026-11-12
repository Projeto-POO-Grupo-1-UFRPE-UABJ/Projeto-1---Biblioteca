package telas;

import java.util.ArrayList;
import java.util.Scanner;
import gerenciadores.GerenciadorEmprestimos;
import modelos.Livro;
import modelos.Usuario;

public class BibliotecaApp {
    
    private static ArrayList<Livro> livros = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    private static GerenciadorEmprestimos gerenciador = new GerenciadorEmprestimos();

    private static int proximoIdLivro = 1;
    private static int proximoIdUsuario = 1;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;

        do {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                
                case 2:
                    listarLivros();
                    break;

                case 3:
                    buscarLivro();
                    break;

                case 4:
                    cadastrarUsuario();
                    break;

                case 5:
                    listarUsuarios();
                    break;

                case 6:
                    emprestarLivro();
                    break;

                case 7:
                    devolverLivro();
                    break;

                case 8:
                    listarEmprestimos();
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== Biblioteca =====");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Listar Livros");
        System.out.println("3 - Buscar Livro");
        System.out.println("4 - Cadastrar Usuário");
        System.out.println("5 - Listar Usuários");
        System.out.println("6 - Emprestar Livro");
        System.out.println("7 - Devolver Livro");
        System.out.println("8 - Listar Empréstimos");
        System.out.println("0 - Sair");
        System.out.println("Escolha uma opção: ");
    }

    private static int lerOpcao() {
        String linha = scanner.nextLine();
        try {
            return Integer.parseInt(linha.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void cadastrarLivro() {
    
    }

    private static void listarLivros() {
    
    }

    private static void buscarLivro() {
    
    }

    private static void cadastrarUsuario() {
    
    }

    private static void listarUsuarios() {
    
    }

    private static void emprestarLivro() {
    
    }

    private static void devolverLivro() {
    
    }

    private static void listarEmprestimos() {
    
    }
}