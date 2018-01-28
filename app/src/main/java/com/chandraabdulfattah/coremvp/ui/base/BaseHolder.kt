package com.chandraabdulfattah.coremvp.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife

/**
 * Created by bezzo on 21/12/17.
 * Uncomment code below Butter Knife if you use ButterKnife
 */

abstract class BaseHolder<M>(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    // Butter Knife
//    init {
//        ButterKnife.bind(this, itemView)
//    }

    var model: M? = null
        set(model) {
            field = model
            if (model != null) {
                setContent(model)
            }
        }

    abstract fun setContent(model: M)
}