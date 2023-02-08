package com.example.Translator.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.lang.NonNull;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString

public class Translate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")

    private Long id;

    private String russianWord;

    private String botlihWord;

    public Translate() {

    }

    public Translate(String russianWord, String botlihWord) {
        this.russianWord = russianWord;
        this.botlihWord = botlihWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Translate translate = (Translate) o;
        return id != null && Objects.equals(id, translate.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
