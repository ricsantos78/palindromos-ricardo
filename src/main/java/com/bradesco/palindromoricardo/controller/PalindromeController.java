package com.bradesco.palindromoricardo.controller;

import com.bradesco.palindromoricardo.model.PalindromeRequest;
import com.bradesco.palindromoricardo.model.PalindromeResponse;
import com.bradesco.palindromoricardo.service.PalindromeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/palindromes")
@RequiredArgsConstructor
@Validated
public class PalindromeController {

    private final PalindromeService palindromeService;

    @PostMapping
    @Operation(summary = "Encontra e salva palíndromos na matriz")
    public ResponseEntity<PalindromeResponse> findAndSavePalindromes(@RequestBody @Valid PalindromeRequest request) {

        List<String> palindromes = palindromeService.findAndSavePalindromesInMatrix(request.getMatrix());
        PalindromeResponse response = new PalindromeResponse(palindromes);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/all")
    @Operation(summary = "Retorna a lista de palíndromos salvos")
    public ResponseEntity<List<String>> findAllPalindromes() {
        List<String> palindromes = palindromeService.findAllPalindromes();
        return ResponseEntity.ok(palindromes);
    }
}
