package com.example.admin.joketeller;



import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


import com.example.admin.joketeller.Contants.Contants;
import com.example.admin.joketeller.Contants.ShowAds;

import com.example.admin.joketeller.Sync.AdClosed;
import com.example.admin.joketeller.Sync.EndPointsAsyncTask;
import com.example.admin.joketeller.Sync.RetrieveResults;


public class MainActivity extends AppCompatActivity implements AdClosed {
    Toolbar toolBar;
    Button btn;
    @Override
    public void afterAdClosed() {
// runs when user closes the add
        getJokesFromAsyncTask().execute();
    }

    ShowAds showAds;
    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBar=(Toolbar)findViewById(R.id.toolBar) ;
        toolBar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolBar);
        btn=(Button)findViewById(R.id.send);


        spinner = (ProgressBar)findViewById(R.id.progressBar);
        if(Contants.type== Contants.Type.FREE) {
            showAds=new ShowAds();
            showAds.addCode(this,this);




        }

    }
    public EndPointsAsyncTask getJokesFromAsyncTask() {
        return new EndPointsAsyncTask(this,new RetrieveResults() {
            @Override
            public void OnResultRetrieved(String Result) {
                btn.setEnabled(true);
                if (Result != null && Result.length() > 0) {

                    Intent i = new Intent(MainActivity.this, com.example.jokelibrary.GetJoke.class);
                    i.putExtra(getString(R.string.joke), Result);
                    startActivity(i);
                    spinner.setVisibility(View.GONE);
                }
            }
            @Override
            public void OnPreRetrieving() {
                if(spinner!=null)
                {
                    btn.setEnabled(false);
                spinner.setVisibility(View.VISIBLE);}
            }
        });
    }
    public void sendJoke(View v)
    {
        if(Contants.type== Contants.Type.FREE) {
            showAds.displayInterstitial();



        }
        else {
            getJokesFromAsyncTask().execute();
        }

    }


}
