package com.example.mega_app.API;

public class Category {
    private int id_категории;
    private String название;
    private String ссылка;

    // Конструктор
    public Category(int id_категории, String название, String ссылка) {
        this.id_категории = id_категории;
        this.название = название;
        this.ссылка = ссылка;
    }

    // Геттеры и сеттеры
    public int getIdКатегории() {
        return id_категории;
    }

    public void setIdКатегории(int id_категории) {
        this.id_категории = id_категории;
    }

    public String getНазвание() {
        return название;
    }

    public void setНазвание(String название) {
        this.название = название;
    }

    public String getСсылка() {
        return ссылка;
    }

    public void setСсылка(String ссылка) {
        this.ссылка = ссылка;
    }
}
