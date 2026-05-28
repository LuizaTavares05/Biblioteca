#  Grupo 3

> **Serratec Residência de Software · Sala 34 · API Restfull**

---

## Bem-vindos, Grupo 3!

Criamos este repositório com o intuito de centralizar e versionar os algoritmos desenvolvidos pelo **Grupo 3** como parte do Trabalho Avaliativo da disciplina de **API Restfull** do programa **Serratec Residência de Software**.

Aqui nós iremos submeter as soluções dos desafios propostos, praticando conceitos fundamentais de API Restfull.

---

## Integrantes
- Luiza
- Emily Neves
- Luiz Antônio
- Roberta
- Gabriel Mendonça
---

##  Estrutura do Repositório

```
BIBLIOTECA/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/br/com/escola/biblioteca/
│   │   │   ├── config/                     # Configurações gerais da aplicação
│   │   │   │   ├── MailConfig.java
│   │   │   │   ├── OpenAPIConfig.java     # Configuração Swagger/OpenAPI
│   │   │   │   ├── PasswordEncoderConfig.java
│   │   │   │   └── SecurityConfig.java    # Configuração Spring Security + JWT
│   │   │   │
│   │   │   ├── controller/                 # Endpoints REST da API
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── AutorController.java
│   │   │   │   ├── EditoraController.java
│   │   │   │   ├── GeneroController.java
│   │   │   │   └── LivroController.java
│   │   │   │
│   │   │   ├── dto/                        # Objetos de transferência de dados
│   │   │   │   ├── AutorRequestDto.java
│   │   │   │   ├── AutorResponseDto.java
│   │   │   │   ├── EditoraRequestDTO.java
│   │   │   │   ├── EditoraResponseDTO.java
│   │   │   │   ├── GeneroRequestDTO.java
│   │   │   │   ├── GeneroResponseDTO.java
│   │   │   │   ├── LivroRequestDto.java
│   │   │   │   ├── LivroResponseDto.java
│   │   │   │   ├── LoginDTO.java
│   │   │   │   └── UsuarioDTO.java
│   │   │   │
│   │   │   ├── entity/                     # Entidades JPA
│   │   │   │   ├── Autor.java
│   │   │   │   ├── Editora.java
│   │   │   │   ├── ErroResposta.java
│   │   │   │   ├── Genero.java
│   │   │   │   ├── Livro.java
│   │   │   │   └── Usuario.java
│   │   │   │
│   │   │   ├── exception/                  # Tratamento de exceções
│   │   │   │   ├── BusinessException.java
│   │   │   │   ├── ControllerExceptionHandler.java
│   │   │   │   └── NotFoundException.java
│   │   │   │
│   │   │   ├── repository/                 # Interfaces JPA Repository
│   │   │   │   ├── AutorRepository.java
│   │   │   │   ├── EditoraRepository.java
│   │   │   │   ├── GeneroRepository.java
│   │   │   │   ├── LivroRepository.java
│   │   │   │   └── UsuarioRepository.java
│   │   │   │
│   │   │   ├── security/                   # Segurança JWT
│   │   │   │   ├── JwtAuthorizationFilter.java
│   │   │   │   └── JwtService.java
│   │   │   │
│   │   │   ├── service/                    # Regras de negócio
│   │   │   │   ├── AutorService.java
│   │   │   │   ├── EditoraService.java
│   │   │   │   ├── GeneroService.java
│   │   │   │   ├── LivroService.java
│   │   │   │   └── UsuarioService.java
│   │   │   │
│   │   │   └── BibliotecaApplication.java # Classe principal Spring Boot
│   │   │
│   │   └── resources/
│   │       └── application.properties      # Configurações da aplicação
│   │
│   └── test/
│
├── target/
├── .gitattributes
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml                                  # Dependências Maven
└── README.md
```
---

## Como Contribuir

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/LuizaTavares05/Biblioteca.git
   ```

2. **Crie uma branch com seu nome ou número da questão:**
   ```bash
   git checkout -b nome-do-integrante
   ```

3. **Adicione seu código na pasta correspondente.**

4. **Faça o commit com uma mensagem descritiva:**
   ```bash
   git add .
   git commit -m "xxx"
   ```

5. **Envie para o repositório remoto:**
   ```bash
   git push origin nome-do-integrante
   ```

---

<p align="center">
 <strong>Grupo 3 — Serratec Residência · Sala 34</strong>
</p>