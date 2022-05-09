package com.serproteam.gmemory.Adapter

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.utils.LottieValueAnimator
import com.serproteam.gmemory.R

class CartasAdaptadores2(private val cartasArray: ArrayList<Int>,
                         private val itemClickListener: OnCartaClickListener) :
    RecyclerView.Adapter<CartasAdaptadores2.MainViewHolder>(){

    interface OnCartaClickListener{
        fun onCartaClickListener(item: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
        return MainViewHolder(layoutInflater.inflate(R.layout.carta,parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.render(cartasArray[position])
    }

    override fun getItemCount(): Int {
        return cartasArray.size
    }

    class MainViewHolder(val view: View):RecyclerView.ViewHolder(view){
        fun render(item: Int){

        }
    }

//    inner class MainViewHolder(val view: View) : BaseViewHolder<Int>(view) {
//        override fun bind(item: Int, position: Int) {
//            Log.v("raul", "item:" + item.toString());
//            view.setOnClickListener {
//                itemClickListener.onCartaClickListener(item)
//            }
//        }
//    }
}