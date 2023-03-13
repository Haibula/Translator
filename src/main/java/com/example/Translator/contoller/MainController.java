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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private TranlatorRepositort tranlatorRepositort;
    @Autowired
    private TranslateService translateService;

    @GetMapping("/")
    public String translator(Model model) {
        model.addAttribute("post", "-");
        return "refactor";
    }

    @PostMapping("/")
    public String translateBotlihWord(@RequestParam String russianWord, @RequestParam String botlihWord, Model model) {
        try {
            String result = translateService.findWord(russianWord, botlihWord);
            model.addAttribute("post", result);
        } catch (Exception e) {
            return "redirect:/";
        }
        return "refactor";
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

    @GetMapping("/allWorks")
    String getAllWorks(Model model) {
        String result = null;
        String result1 = null;
        Iterable<Translate> authorizations = tranlatorRepositort.findAll();
        Iterator iterator = authorizations.iterator();
        ArrayList<Translate> arr = new ArrayList<>();
        while (iterator.hasNext()) {
            arr.add((Translate) iterator.next());
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            map.put(arr.get(i).getRussianWord(), arr.get(i).getBotlihWord());
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            result += " Рус-->" + entry.getKey() + " Ботл-->" + entry.getValue();
        }
        System.out.println(result);
        model.addAttribute("post", result + " " + result1);
        return "allWorks";
    }
}
