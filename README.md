# Projeto-1---Biblioteca
Sistema de biblioteca em Java com telas Swing, banco SQLite e relatório CSV. Controla livros, alunos e professores, empréstimos, devoluções e multas por atraso. Projeto de MPOO.

**-Relações:**

Usuário-Aluno e Usuário-Professor (herança): as classes Aluno e Professor estabelecem uma relação de herança direta com a superclasse abstrata Usuario. Desse modo, atributos comuns e essenciais ao domínio (como identificador, nome e matrícula) são centralizados na classe genérica, enquanto as subclasses especializadas implementam os comportamentos polimórficos particulares de sua regra de negócio.


Empréstimo-Usuário e Empréstimo-Livro (agregação): o emprestimo funciona como agregador que referencia as duas classes. Se um empréstimo for excluido, o usuário e o livro não devem ser apagados. O emprestimo é o “todo” e o usuário/livro são as partes. Adotou-se conceitualmente a relação de agregação para evidenciar a independência de ciclo de vida entre as entidades: caso um registro de empréstimo seja encerrado ou excluído do sistema, as instâncias de Usuario e Livro continuam existindo de maneira autônoma no banco de dados, sem sofrer remoção em cascata.


