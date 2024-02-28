# Palindromos Ricardo

## Descrição
Este projeto é uma aplicação Java Spring Boot que encontra palíndromos em uma matriz de caracteres e os salva em um banco de dados H2.

## Configuração
Certifique-se de ter o Java e o Maven instalados em sua máquina.

1. Clone o repositório:
via https
```
git clone https://github.com/ricsantos78/palindromos-ricardo.git
```

3. Navegue até o diretório do projeto:
```
cd palindromos-ricardo
```

3. Execute a aplicação usando o Maven:
   
```
 mvn spring-boot:run
```

4. Acesse a documentação da API em http://localhost:8080/swagger-ui/.

# Destaques do Código

Método findAndSavePalindromesInMatrix em PalindromeServiceImpl.java
Destaca a lógica para encontrar e salvar palíndromos na matriz.

```
@Override
public List<String> findAndSavePalindromesInMatrix(char[][] matrix) {
    // Verificar se a matriz é válida
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return Collections.emptyList();
    }
    
    List<String> palindromes = new ArrayList<>();

    // Encontrar palíndromos horizontais, verticais, diagonais e reversos
    palindromes.addAll(findPalindromes(matrix, this::isHorizontalPalindrome));
    palindromes.addAll(findPalindromes(matrix, this::isVerticalPalindrome));
    palindromes.addAll(findPalindromes(matrix, this::isDiagonalPalindrome));
    palindromes.addAll(findPalindromes(matrix, this::isReverseDiagonalPalindrome));

    // Persistir palíndromos no banco de dados
    List<String> savedPalindromes = new ArrayList<>();
    palindromes.stream()
            .filter(palindrome -> palindrome.length() > MIN_PALINDROME_LENGTH && palindrome.equalsIgnoreCase(new StringBuilder(palindrome).reverse().toString()))
            .distinct()
            .forEach(palindrome -> {
                palindromeRepository.save(new Palindrome(palindrome));
                savedPalindromes.add(palindrome);
            });
     return savedPalindromes;
}
```
