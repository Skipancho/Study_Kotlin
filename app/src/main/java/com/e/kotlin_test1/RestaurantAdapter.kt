package com.e.kotlin_test1

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.restaurant.view.*

class RestaurantAdapter : BaseAdapter {
    private var list : List<Restaurant2>
    private var context : Context

    constructor(list: List<Restaurant2>, context: Context){
        this.list = list
        this.context = context
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(index: Int): Any {
        return list.get(index)
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup?): View {
        val v = View.inflate(context,R.layout.restaurant,null)

        v.name_tv.text = list.get(index).getName()
        v.address_tv.text = list.get(index).getAddress()
        v.menu_btn.setOnClickListener { show_dialog(index) }

        return v
    }

    private fun show_dialog(i : Int){
        val str : String = list.get(i).getMenu()
        val dialog : AlertDialog
        val builder : AlertDialog.Builder = AlertDialog.Builder(context)
        dialog = builder.setMessage(str)
            .setPositiveButton("확인",null)
            .create();
        dialog.show();
    }

}