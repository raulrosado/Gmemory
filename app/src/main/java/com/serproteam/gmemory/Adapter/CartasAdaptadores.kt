package com.serproteam.gmemory.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.serproteam.gmemory.R
import com.serproteam.gmemory.domain.Entitys.Cartas

class CartasAdaptadores(private val context: Context,private val personaje:String, private val cartasArray: ArrayList<Cartas>,
                        private val itemClickListener: OnCartaClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnCartaClickListener{
        fun onCartaClickListener(item: Cartas, context: Context, cardFaceFront: FrameLayout, cardFaceBack:FrameLayout, cardFlip:FrameLayout,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.carta, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(cartasArray[position], position)
        }
    }

    override fun getItemCount(): Int {
        return cartasArray.size
    }

    inner class MainViewHolder(val view: View) : BaseViewHolder<Int>(view) {
        override fun bind(item: Cartas, position: Int) {
            val uri = "@drawable/"+ personaje + item.carta.toString()
            Log.v("raul", uri.toString());
            Log.v("raul", item.toString());
            val imageResource: Int = context.getResources().getIdentifier(uri, null, context.getPackageName())
            view.findViewById<ImageView>(R.id.imgCarta).setImageResource(imageResource)
            if(item.activo){
                view.findViewById<FrameLayout>(R.id.card_face_back).visibility = View.VISIBLE
            }else{
                view.findViewById<FrameLayout>(R.id.card_face_back).visibility = View.GONE
            }

            view.findViewById<LinearLayout>(R.id.cardCarta).setOnClickListener {
                itemClickListener.onCartaClickListener(item, context, view.findViewById<FrameLayout>(R.id.card_face_front),
                    view.findViewById<FrameLayout>(R.id.card_face_back), view.findViewById<FrameLayout>(R.id.card_flip),position)
            }
        }
    }
}