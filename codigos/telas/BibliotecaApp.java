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

    private static String lerTexto(String mensagem) {
        while(true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Esse campo não pode ficar vazio. Por favor, insira algo.");
        }
    }

    private static int lerInteiro(String mensagem) {
        while(true) {
            System.out.print(mensagem);
            String linha = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número inteiro.");
            }
        }
    }

    private static void cadastrarLivro() {
        System.out.println();
        System.out.println("===== Cadastro de Livro =====");
        String titulo = lerTexto("Título: ");
        String autor = lerTexto("Autor: ");
        String isbn = lerTexto("ISBN: ");
        int exemplares = lerInteiro("Quantidade de exemplares: ");

        if(exemplares <= 0) {
            System.out.println("A quantidade de exemplares deve ser maior que zero.");
            return;
        }

        Livro livro = new Livro(proximoIdLivro, titulo, autor, isbn, exemplares);
        livros.add(livro);
        proximoIdLivro++;
        System.out.println("Livro cadastrado com sucesso!");
        System.out.println("ID do livro: " + livro.getId());
    }

    private static void listarLivros() {
        if(livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        
        System.out.println("\n===== Livros Cadastrados =====");
        for (Livro livro: livros) {
            exibirLivro(livro);
        }
    }

    private static void exibirLivro(Livro livro) {
        System.out.println("ID: " + livro.getId() + " | Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor() + 
        " | ISBN: " + livro.getIsbn() + " | Exemplares: " + livro.getExemplaresDisponiveis() + " | Total de Exemplares: " + livro.getTotalExemplares());
    }

    private static void buscarLivro() {
        System.out.println();
        System.out.println("===== Buscar Livro =====");
        System.out.println("Título (Enter para pular): ");
        String titulo = scanner.nextLine().trim();
        System.out.println("Autor (Enter para pular): ");
        String autor = scanner.nextLine().trim();

        ArrayList<Livro> encontrados;

        if (!titulo.isEmpty() && !autor.isEmpty()) {
            encontrados = buscarLivros(titulo, autor);
        } else if (!titulo.isEmpty()) {
            encontrados = buscarLivros(titulo);
        } else if (!autor.isEmpty()) {
            encontrados = buscarPorAutor(autor);
        } else {
            System.out.println("Digite pelo menos um título ou autor.");
            return;
        }

        if(encontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            for (Livro livro: encontrados) {
                exibirLivro(livro);
            }
        }
    }

    private static ArrayList<Livro> buscarLivros(String titulo) {
        ArrayList<Livro> encontrados = new ArrayList<>();
        for (Livro livro: livros) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                encontrados.add(livro);
            }
        }
        return encontrados;
    }

    private static ArrayList<Livro> buscarLivros(String titulo, String autor) {
        ArrayList<Livro> encontrados = new ArrayList<>();
        for (Livro livro: livros) {
            boolean achouTitulo = livro.getTitulo().toLowerCase().contains(titulo.toLowerCase());
            boolean achouAutor = livro.getAutor().toLowerCase().contains(autor.toLowerCase());
            if (achouTitulo && achouAutor) {
                encontrados.add(livro);
            }
        }
        return encontrados;
    }

    private static ArrayList<Livro> buscarPorAutor(String autor) {
        ArrayList<Livro> encontrados = new ArrayList<>();
        for (Livro livro: livros) {
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                encontrados.add(livro);
            }
        }
        return encontrados;
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