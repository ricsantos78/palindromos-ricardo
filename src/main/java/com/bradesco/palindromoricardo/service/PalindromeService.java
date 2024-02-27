package com.bradesco.palindromoricardo.service;

import java.util.List;

public interface PalindromeService {
    List<String> findAndSavePalindromesInMatrix(char[][] matrix);

    List<String> findAllPalindromes();
}
