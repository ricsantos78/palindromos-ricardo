package com.bradesco.palindromoricardo.endpoint;

import com.bradesco.palindromoricardo.model.entity.Palindrome;
import com.bradesco.palindromoricardo.repository.PalindromeRepository;
import com.bradesco.palindromoricardo.service.impl.PalindromeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class PalindromeServiceImplTest {
    @Mock
    private PalindromeRepository palindromeRepository;

    @InjectMocks
    private PalindromeServiceImpl palindromeService;

    @Test
    void testFindAndSavePalindromesInMatrix() {
        // Dados de entrada para o teste
        char[][] matrix = {
                {'A', 'B', 'A'},
                {'B', 'O', 'B'},
                {'A', 'B', 'A'}
        };

        // Chama o método a ser testado
        List<String> result = palindromeService.findAndSavePalindromesInMatrix(matrix);

        // Verifica se o método save foi chamado com as palavras corretas
        verify(palindromeRepository, times(3)).save(any(Palindrome.class));

        // Verifica se o resultado contém as palavras corretas
        assertThat(result).containsExactlyInAnyOrder("ABA", "BOB", "AOA");
    }

    @Test
    void testFindAllPalindromes() {
        // Dado que existem palíndromos salvos no banco de dados
        List<Palindrome> palindromes = Arrays.asList(
                new Palindrome("ABA"),
                new Palindrome("BOB"),
                new Palindrome("AOA")
        );
        // Mock do repositório para retornar a lista de palíndromos
        when(palindromeRepository.findAll()).thenReturn(palindromes);

        // Quando o método findAllPalindromes é chamado
        List<String> result = palindromeService.findAllPalindromes();

        // Então a lista de palíndromos deve ser retornada corretamente
        assertThat(result)
                .isNotNull()
                .hasSize(3)
                .containsExactly("ABA", "BOB", "AOA");
    }

}
