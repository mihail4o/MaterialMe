/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.materialme

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

import java.util.ArrayList

/***
 * The adapter class for the RecyclerView, contains the sports data
 */
internal class SportsAdapter

(private val mContext: Context, //Member variables
 private val mSportsData: ArrayList<Sport>) : RecyclerView.Adapter<SportsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: SportsAdapter.ViewHolder, position: Int) {
        //Get current sport
        val currentSport = mSportsData[position]
        Glide.with(mContext).load(currentSport.imageResource).into(holder.mSportsImage)
        //Populate the textviews with data
        holder.bindTo(currentSport)
    }

    override fun getItemCount(): Int {
        return mSportsData.size
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView
     */
    internal inner class ViewHolder
    /**
     * Constructor for the ViewHolder, used in onCreateViewHolder().
     * @param itemView The rootview of the list_item.xml layout file
     */
    (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {

            val currentSport = mSportsData.get(adapterPosition)
            val detailIntent = Intent(mContext, DetailActivity::class.java)
            detailIntent.putExtra("title", currentSport.title)
            detailIntent.putExtra("image_resource", currentSport.imageResource)

            mContext.startActivity(detailIntent)

        }

        //Member Variables for the TextViews
        private val mTitleText: TextView
        private val mInfoText: TextView
        var mSportsImage: ImageView

        init {

            //Initialize the views
            mTitleText = itemView.findViewById<View>(R.id.title) as TextView
            mInfoText = itemView.findViewById<View>(R.id.subTitle) as TextView
            mSportsImage = itemView.findViewById<View>(R.id.sportsImage) as ImageView
        }

        fun bindTo(currentSport: Sport) {
            //Populate the textviews with data
            mTitleText.text = currentSport.title
            mInfoText.text = currentSport.info

        }
    }
}
