package com.example.jokelibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by admin on 2/7/2017.
 */

public class GetJoke extends AppCompatActivity{
    TextView display_joke;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jokes_layout);
        Intent intent=getIntent();
        display_joke=(TextView)findViewById(R.id.displayJoke);
        String joke=intent.getStringExtra(getString(R.string.joke));
        display_joke.setText(joke);

    }
}
