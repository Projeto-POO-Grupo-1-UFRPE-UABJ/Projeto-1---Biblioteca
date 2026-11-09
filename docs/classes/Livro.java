public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private int totalExemplares;
    private int exemplaresDisponiveis;

    public Livro(int id, String titulo, String autor, String isbn, int totalExemplares) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.totalExemplares = totalExemplares;
        this.exemplaresDisponiveis = totalExemplares;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getTotalExemplares() {
        return totalExemplares;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor (String autor) {
        this.autor = autor;
    }

    public void setIsbn (String isbn) {
        this.isbn = isbn;
    }
    
    public boolean temExemplar() {
        if (exemplaresDisponiveis > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void emprestarExemplar() throws LivroIndisponivelException {
        if (!temExemplar()) {
            throw new LivroIndisponivelException("Nao tem um exemplar disponivel desse livro");
        }
        exemplaresDisponiveis = exemplaresDisponiveis - 1;
    }

    public void devolverExemplar() {
        if (exemplaresDisponiveis < totalExemplares) {
            exemplaresDisponiveis = exemplaresDisponiveis + 1;
        }
    }

    public void aumentarTotalExemplares (int qtd) {
        if (qtd <= 0) {
            throw new IllegalArgumentException("Quantidade invalida");
        }
        totalExemplares = totalExemplares + qtd;
        exemplaresDisponiveis = exemplaresDisponiveis + qtd;
    }

    public void diminuirTotalExemplares(int qtd) {
        if (qtd <= 0) {
            throw new IllegalArgumentException("Quantidade invalida");
        }
        if (qtd > exemplaresDisponiveis) {
            throw new IllegalArgumentException("A quantidade retirada eh maior que a quantidade de exemplares disponiveis");
        }
        totalExemplares = totalExemplares - qtd;
        exemplaresDisponiveis = exemplaresDisponiveis - qtd;
    }
}



