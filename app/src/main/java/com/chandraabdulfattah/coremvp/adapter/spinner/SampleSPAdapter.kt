package com.chandraabdulfattah.coremvp.adapter.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.chandraabdulfattah.coremvp.R
import kotlinx.android.synthetic.main.item_sp_sample.view.*

/**
 * Created by bezzo on 11/01/18.
 * Change String to model you need convert to spinner
 */
class SampleSPAdapter constructor(var context : Context, var list : ArrayList<String>)
    : BaseAdapter(), SpinnerContract<ArrayList<String>> {

    override fun update(values: ArrayList<String>) {
        this.clear()
        this.list.addAll(values)
        notifyDataSetChanged()
    }

    override fun clear() {
        this.list.clear()
        notifyDataSetChanged()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var convertView = LayoutInflater.from(context).inflate(R.layout.item_sp_sample, null)
        convertView.item_sp.text = list[position]
        return convertView
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}