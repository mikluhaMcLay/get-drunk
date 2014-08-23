package org.startup.entity;

public enum AlkoCategory {
    VODKA(40), BEER(40), WHISKEY(40), RUM(40), WERMUT(40), LIQUOR(40), WINE(40);

    private int degree;

    AlkoCategory(int degree) {
        this.degree = degree;
    }

}
