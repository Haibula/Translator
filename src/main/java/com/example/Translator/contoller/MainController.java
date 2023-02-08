package com.example.Translator.contoller;

import com.example.Translator.model.Translate;
import com.example.Translator.repository.TranlatorRepositort;
import com.example.Translator.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private TranlatorRepositort tranlatorRepositort;
    @Autowired
    private TranslateService translateService;

    @GetMapping("/")
    public String translator(Model model) {
        model.addAttribute("post", "-");
        return "translator-Main";
    }

    @PostMapping("/")
    public String translateBotlihWord(@RequestParam String russianWord, @RequestParam String botlihWord, Model model) {
        try {
            String result = translateService.findWord(russianWord, botlihWord);
            model.addAttribute("post", result);
        } catch (Exception e) {
            return "redirect:/";
        }
        return "translator-Main";
    }

    @GetMapping("/addWord")
    public String addNewWord(Model model) {
        return "addWord";
    }

    @PostMapping("/addWord")
    public String translatorAddData(@RequestParam String russianWord, @RequestParam String botlihWord) {
        botlihWord = botlihWord.toLowerCase().trim();
        russianWord = russianWord.toLowerCase().trim();

        if (russianWord.isEmpty() || botlihWord.isEmpty()) {
            return "redirect:/addWord";
        }
        if (tranlatorRepositort.existsByRussianWord(russianWord) || tranlatorRepositort.existsByBotlihWord(botlihWord)) {
            return "redirect:/addWord";
        }

        Translate translate = new Translate(russianWord, botlihWord);
        tranlatorRepositort.save(translate);

        return "addWord";
    }
}
