package org.example.service;

import org.example.exception.CustomGameException;
import org.example.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PalindromeServiceImpl implements PalindromeService {

    private static final String RGX_SPACE_SYMBOL = "\\s+";

    private Map<User, Set<String>> userPalindromes = new HashMap<>();

    @Override
    public int submitPalindrome(User user, String word) throws CustomGameException {
        if(word == null || word.isEmpty())
            throw new CustomGameException("Пустая строка. Попробуйте еще раз");

        if(!isPalindrome(word))
            throw new CustomGameException("Введеный текст совсем не палиндром!");

        var submittedPalindromes = userPalindromes.getOrDefault(user, new HashSet<>());

        var points = 0;
        if(submittedPalindromes.add(word)) {
            points = word.length();
            userPalindromes.put(user, submittedPalindromes);
        }else {
            throw new CustomGameException("Вы уже вводили похожий текст!");
        }

        return points;
    }

    @Override
    public boolean isPalindrome(String word) {
        var str = word.toLowerCase();
        str = str.replace(RGX_SPACE_SYMBOL, "");

        var splitStr = str.split("");

        var isPalindrome = true;
        for(int i = 0; i < splitStr.length / 2; i++) {
            if(!splitStr[i].equals(splitStr[(splitStr.length - 1) - i])) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }
}
