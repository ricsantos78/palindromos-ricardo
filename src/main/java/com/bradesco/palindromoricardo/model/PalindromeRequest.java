package com.bradesco.palindromoricardo.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PalindromeRequest {

    @NotEmpty(message = "Matrix cannot be empty")
    @Size(max = 10, message = "Matrix must have between 1 and 10 rows")
    private char[][] matrix;
}
