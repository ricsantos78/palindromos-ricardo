package com.bradesco.palindromoricardo.repository;

import com.bradesco.palindromoricardo.model.entity.Palindrome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalindromeRepository extends JpaRepository<Palindrome, Long> {
}
