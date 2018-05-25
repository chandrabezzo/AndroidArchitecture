package com.chandraabdulfattah.coremvp.adapter.recyclerView

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chandraabdulfattah.coremvp.R
import com.chandraabdulfattah.coremvp.data.model.Jabatan
import com.chandraabdulfattah.coremvp.ui.base.BaseHolder
import io.realm.RealmResults
import kotlinx.android.synthetic.main.item_rv_default.view.*

class JabatanRVAdapter(val list : RealmResults<Jabatan>,
                       val context : Context)
    : RecyclerView.Adapter<JabatanRVAdapter.ItemJabatan>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemJabatan {
        return ItemJabatan(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_default, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemJabatan, position: Int) {
        holder.model = list[position]
    }

    inner class ItemJabatan(itemView : View) : BaseHolder<Jabatan>(itemView) {
        override fun setContent(model: Jabatan) {
            itemView.tv_item_rv.text = model.nama
        }
    }
}