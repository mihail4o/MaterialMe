package com.example.android.materialme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val sportsTitle = findViewById(R.id.titleDetail) as TextView
        val sportsImage = findViewById(R.id.sportsImageDetail) as ImageView

        sportsTitle.setText(getIntent().getStringExtra("title"))
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(sportsImage)
    }
}
