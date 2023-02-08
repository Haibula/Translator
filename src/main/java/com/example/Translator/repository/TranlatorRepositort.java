package com.example.Translator.repository;

import com.example.Translator.model.Translate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.Optional;
@Repository
public interface TranlatorRepositort extends JpaRepository<Translate,Long>{

    Optional<Translate> findAllByRussianWord(String russianWord);
    Optional<Translate> findAllByBotlihWord(String botlihWord);

    Boolean existsByBotlihWord(String botlihWord);
    Boolean existsByRussianWord(String russianWord);
}



