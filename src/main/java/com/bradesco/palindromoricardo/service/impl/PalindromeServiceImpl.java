package com.bradesco.palindromoricardo.service.impl;

import com.bradesco.palindromoricardo.model.entity.Palindrome;
import com.bradesco.palindromoricardo.repository.PalindromeRepository;
import com.bradesco.palindromoricardo.service.PalindromeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class PalindromeServiceImpl implements PalindromeService {

    private final PalindromeRepository palindromeRepository;

    private static final int MIN_PALINDROME_LENGTH = 2;


    @Override
    public List<String> findAllPalindromes() {
        return palindromeRepository.findAll().stream()
                .map(Palindrome::getWord)
                .toList();
    }

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


        // Verificar palíndromos válidos e não duplicados
        List<String> validPalindromes = palindromes.stream()
                .filter(this::isPalindromeValid)
                .distinct()
                .toList();

        // Persistir palíndromos no banco de dados
        List<String> savedPalindromes = new ArrayList<>();
        for (String palindrome : validPalindromes) {
            palindromeRepository.save(new Palindrome(palindrome));
            savedPalindromes.add(palindrome);
        }

        return savedPalindromes;
    }

    private boolean isPalindromeValid(String palindrome) {
        return palindrome.length() > MIN_PALINDROME_LENGTH && palindrome.equalsIgnoreCase(new StringBuilder(palindrome).reverse().toString());
    }

    // Método auxiliar para encontrar palíndromos em uma matriz
    private List<String> findPalindromes(char[][] matrix, Predicate<String> palindromeChecker) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        return IntStream.range(0, rows)
                .mapToObj(i -> IntStream.range(0, cols)
                        .mapToObj(j -> getSubString(matrix, i, j, palindromeChecker))
                        .flatMap(List::stream)
                        .toList())
                .flatMap(List::stream)
                .toList();
    }

    // Método auxiliar para obter as subcadeias de uma matriz
    private List<String> getSubString(char[][] matrix, int row, int col, Predicate<String> palindromeChecker) {
        List<String> palindromes = new ArrayList<>();
        checkDirection(matrix, row, col, -1, -1, palindromeChecker, palindromes);
        checkDirection(matrix, row, col, -1, 0, palindromeChecker, palindromes);
        checkDirection(matrix, row, col, -1, 1, palindromeChecker, palindromes);
        checkDirection(matrix, row, col, 0, -1, palindromeChecker, palindromes);
        checkDirection(matrix, row, col, 0, 1, palindromeChecker, palindromes);
        checkDirection(matrix, row, col, 1, -1, palindromeChecker, palindromes);
        checkDirection(matrix, row, col, 1, 0, palindromeChecker, palindromes);
        checkDirection(matrix, row, col, 1, 1, palindromeChecker, palindromes);
        return palindromes;
    }

    // Método auxiliar para verificar as direções
    private void checkDirection(char[][] matrix, int row, int col, int dRow, int dCol, Predicate<String> palindromeChecker, List<String> palindromes) {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (isValidPosition(row + i * dRow, col + i * dCol, matrix)) {
            sb.append(matrix[row + i * dRow][col + i * dCol]);
            i++;
        }
        String candidate = sb.toString();
        if (palindromeChecker.test(candidate)) {
            palindromes.add(candidate);
        }
    }

    // Método auxiliar para verificar se uma posição é válida
    private boolean isValidPosition(int row, int col, char[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    // Método auxiliar para verificar se uma string é um palíndromo
    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    private boolean isHorizontalPalindrome(String str) {
        return isPalindrome(str);
    }

    private boolean isVerticalPalindrome(String str) {
        return isPalindrome(str);
    }

    private boolean isDiagonalPalindrome(String str) {
        return isPalindrome(str);
    }

    private boolean isReverseDiagonalPalindrome(String str) {
        return isPalindrome(str);
    }


}
