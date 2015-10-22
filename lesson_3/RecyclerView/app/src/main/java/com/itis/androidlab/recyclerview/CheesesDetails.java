package com.itis.androidlab.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class CheesesDetails extends AppCompatActivity {

    public static final String CHEESE = "cheese";
    public static final String IMAGE = "image";
    public static final String TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheese_details);

        ((ImageView) findViewById(R.id.cheese_img))
                .setImageResource(getIntent().getIntExtra(IMAGE, 0));

        ((TextView) findViewById(R.id.cheese_title))
                .setText(getIntent().getStringExtra(TITLE));




//        Cheese cheese = getIntent().getParcelableExtra(CHEESE);
//        ((ImageView) findViewById(R.id.cheese_img))
//                .setImageResource(cheese.getImage());
//
//        ((TextView) findViewById(R.id.cheese_title))
//                .setText(cheese.getTitle());
    }
}
