package com.example.Translator.service;

import com.example.Translator.model.Translate;
import com.example.Translator.repository.TranlatorRepositort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TranslateService {
    @Autowired
    private TranlatorRepositort tranlatorRepositort;
    private String russianWord;
    private String botlihWord;

    private boolean existsRussianWord;
    private boolean existsBotlihWord;

    public String findWord(String rusWord, String bothWord) {
        String result = "";
        russianWord = rusWord.toLowerCase().trim();
        botlihWord = bothWord.toLowerCase().trim();
        existsRussianWord = tranlatorRepositort.existsByRussianWord(russianWord);
        existsBotlihWord = tranlatorRepositort.existsByBotlihWord(botlihWord);

        if (existsRussianWord || existsBotlihWord) {
            result = singleWord();
        } else {
            result = groupWord();
        }

        return result;
    }

    public String groupWord() {

        String result = "";

        String[] split = smashArray(russianWord, botlihWord);

        for (String word : split) {
            if (!russianWord.isEmpty()) {
                Optional<Translate> post = tranlatorRepositort.findAllByRussianWord(word);
                result += " " + returnWordWithFirstWooerCaseLetter(post.get().getBotlihWord());
            } else {
                Optional<Translate> post = tranlatorRepositort.findAllByBotlihWord(word);
                result += " " + returnWordWithFirstWooerCaseLetter(post.get().getRussianWord());
            }

        }
        return result.trim();
    }

    public String singleWord() {
        String result = "";
        if (existsRussianWord) {
            Optional<Translate> post = tranlatorRepositort.findAllByRussianWord(russianWord);
            result = returnWordWithFirstWooerCaseLetter(post.get().getBotlihWord());
        } else {
            Optional<Translate> post = tranlatorRepositort.findAllByBotlihWord(botlihWord);
            result = returnWordWithFirstWooerCaseLetter(post.get().getRussianWord());
        }
        return result;
    }

    public String[] smashArray(String ferstWord, String secondWord) {
        String[] result;
        if (ferstWord.isEmpty()) {
            result = secondWord.split(" ");
        } else {
            result = ferstWord.split(" ");
        }
        return result;
    }

    public String returnWordWithFirstWooerCaseLetter(String word) {

        return word.substring(0, 1)
                .toUpperCase() + word
                .substring(1).trim();
    }

}
