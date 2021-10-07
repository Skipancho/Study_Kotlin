package com.e.kotlin_test1

class Restaurant2 {
    private var name : String
    private var address : String
    private var menu : String

    constructor(name: String, address: String, menu: String) {
        this.name = name
        this.address = address
        this.menu = menu
    }

    fun getName(): String {
        return name
    }
    fun getAddress(): String {
        return address
    }
    fun getMenu(): String {
        return menu
    }

    override fun toString(): String {
        return name + address + menu
    }
}