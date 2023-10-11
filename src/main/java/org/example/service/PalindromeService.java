package org.example.service;

import org.example.exception.CustomGameException;
import org.example.model.User;

public interface PalindromeService {

    int submitPalindrome(User user, String word) throws CustomGameException;

    boolean isPalindrome(String word);
}
