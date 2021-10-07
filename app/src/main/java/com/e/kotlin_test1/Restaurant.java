package com.e.kotlin_test1;

public class Restaurant {
    private String name;
    private String address;
    private String menu;

    public Restaurant(String name, String address, String menu) {
        this.name = name;
        this.address = address;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return name + address + menu;
    }
}
