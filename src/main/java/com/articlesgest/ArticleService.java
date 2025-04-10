package com.articlesgest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ArticleService {
    private List<Article> articles;
    private final File fichier = new File("data/articles.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public ArticleService() {
        File dossier = new File("data");
        if (!dossier.exists()) {
            dossier.mkdirs();
        }
        articles = loadDataFile();
    }

    public Article addArticle(String nom, double prix) {
        Article a = new Article(nom, prix);
        articles.add(a);
        saveInFile();
        return a;
    }

    public List<Article> addArticles(String[] noms, double[] prix) {
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < noms.length; i++) {
            articles.add(addArticle(noms[i], prix[i]));
        }
        saveInFile();
        return articles;
    }

    public void editArticle(int id, String nom, double prix) {
        Article article = articles.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
        if (article != null) {
            article.setNom(nom);
            article.setPrix(prix);
            saveInFile();
        }
    }

    public boolean deleteArticleById(int id) {
        boolean removed = articles.removeIf(a -> a.getId() == id);
        if (removed) saveInFile();
        return removed;
    }

    public List<Article> fetchArticles() {
        return new ArrayList<>(articles);
    }

    private List<Article> loadDataFile() {
        if (!fichier.exists()) return new ArrayList<>();
        try {
            return mapper.readValue(fichier, new TypeReference<List<Article>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveInFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(fichier, articles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
