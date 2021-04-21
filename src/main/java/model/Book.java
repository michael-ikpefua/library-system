package model;

import java.util.Locale;

public class Book {

    private String name;
    private int quantity;

    public Book(String name, int quantity)
    {
        this.name = name;

        this.quantity = quantity;
    }


    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void updateQuantity(int quantityNo) {
        quantity = quantityNo;
    }

}
