package com.articlesgest;

/**
 * Hello world!
 *
 */
public class Article 
{
    private static int counter = 1;

    private int id;
    private String nom;
    private double prix;

    public Article(String nom, double prix) {
        this.id = counter++;
        this.nom = nom;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
