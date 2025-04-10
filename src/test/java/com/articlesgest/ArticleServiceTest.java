package com.articlesgest;

import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArticleServiceTest {

    private ArticleService service;
    private final String testFilePath = "data/articles.json";

    @BeforeEach
    void setup() throws Exception {
        Files.deleteIfExists(new File(testFilePath).toPath());
        service = new ArticleService();
    }

    @Test
    void testAddArticle() {
        service.addArticle("Livre", 15.0);
        List<Article> articles = service.fetchArticles();
        assertEquals(1, articles.size());
        assertEquals("Livre", articles.get(0).getNom());
    }

    @Test
    void testAddArticles() {
        service.addArticles(new String[]{"Traceuse", "Gomme"}, new double[]{5.0, 2.9});
        List<Article> articles = service.fetchArticles();
        assertEquals(2, articles.size());
        assertEquals("Traceuse", articles.get(0).getNom());
        assertEquals("Gomme", articles.get(1).getNom());
    }

    @Test
    void testEditArticle() {
        Article article = service.addArticle("Livre", 15.0);
        service.editArticle(article.getId(), "Stylo", 2.5);
        List<Article> articles = service.fetchArticles();
        assertEquals(1, articles.size());
        assertEquals("Stylo", articles.get(0).getNom());
        assertEquals(2.5, articles.get(0).getPrix());
    }

    @Test
    void testDeleteArticle() {
        Article article = service.addArticle("Stylo", 2.5);
        boolean supprimé = service.deleteArticleById(article.getId());
        assertTrue(supprimé);
        assertEquals(0, service.fetchArticles().size());
    }

    @Test
    void testFetchArticles() {
        service.addArticle("Stylo", 2.5);
        service.addArticle("Cahier", 3.5);
        List<Article> articles = service.fetchArticles();
        assertEquals(2, articles.size());
    }
}
