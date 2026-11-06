classDiagram
    direction TB

    class Usuario {
        <<Abstract>>
        - id: int
        - nome: String
        - matricula: String
        - limite: int
        + temLimite() boolean
        + aumentarLimite() void
        + diminuirLimite() void
        + getPrazo() int*
        + getTaxa() double*
        + getLimiteMax() int*
        + getTipo() String*
    }

    class Aluno {
        + getPrazo() int
        + getTaxa() double
        + getLimiteMax() int
        + getTipo() String
    }

    class Professor {
        + getPrazo() int
        + getTaxa() double
        + getLimiteMax() int
        + getTipo() String
    }

    class Emprestimo {
        - id: int
        - dataEmprestimo: LocalDate
        - dataPrevista: LocalDate
        - dataDevolucao: LocalDate
        + estaAberto() boolean
        + estaAtrasado() boolean
        + calcAtraso() long
        + calcMulta() double
        + devolver() double
        + devolver(LocalDate data) double
    }

    class Livro {
        - id: int
        - titulo: String
        - autor: String
        - isbn: String
        - totalExemplares: int
        - exemplaresDisponiveis: int
        + temExemplar() boolean
        + emprestarExemplar() void
        + devolverExemplar() void
        + aumentarTotalExemplares(int qtd) void
        + diminuirTotalExemplares(int qtd) void
    }

    Aluno --|> Usuario
    Professor --|> Usuario
    Usuario "1" --o "0..*" Emprestimo
    Emprestimo "0..*" o-- "1" Livro