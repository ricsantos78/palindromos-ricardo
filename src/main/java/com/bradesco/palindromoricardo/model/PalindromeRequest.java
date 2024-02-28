package com.bradesco.palindromoricardo.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PalindromeRequest {

    @NotEmpty(message = "Matrix cannot be empty")
    @Size(max = 10, message = "Matrix must have between 1 and 10 rows")
    private char[][] matrix;

    @AssertTrue(message = "Matrix must have between 1 and 10 columns or rows and be a square matrix")
    public boolean isMatrixValid() {
        if (matrix == null || matrix.length == 0 || matrix.length > 10) {
            return false;
        }
        int numCols = matrix[0].length;
        for (char[] row : matrix) {
            if (row == null || row.length != numCols) {
                return false;
            }
            if (row.length > 10) {
                return false;
            }
        }
        return true;
    }

    public boolean isSquareMatrix() {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int numRows = matrix.length;
        for (char[] row : matrix) {
            if (row == null || row.length != numRows) {
                return false;
            }
        }
        return true;
    }
}
