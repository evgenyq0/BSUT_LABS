package org.example;

import java.math.BigDecimal;
import java.text.DecimalFormat;

class Book {
    private String id;
    private String title;
    private String author;
    private int year;
    private String genre;
    private BigDecimal price;

    public Book(String id, String title, String author, int year, String genre, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.price = price;
    }

    // Геттеры
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public String getGenre() { return genre; }
    public BigDecimal getPrice() { return price; }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        return String.format("ID: %s | %s, %s (%d) [%s] - %s руб.",
                id, title, author, year, genre, df.format(price));
    }
}