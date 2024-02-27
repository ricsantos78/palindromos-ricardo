package com.bradesco.palindromoricardo.model;

import lombok.Data;

import java.util.List;

@Data
public class PalindromeResponse {

    private List<String> palindromes;

    public PalindromeResponse(List<String> palindromes) {
        this.palindromes = palindromes;
    }

}
