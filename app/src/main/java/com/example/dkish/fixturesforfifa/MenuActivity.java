package com.example.dkish.fixturesforfifa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void ViewFixtures(View view){
        Intent openFixtures = new Intent(MenuActivity.this,FixturesView.class);
        startActivity(openFixtures);                            //Opens FixturesView Activity
    }
    public void AddFixtures(View view){
        Intent addFixtures = new Intent(MenuActivity.this , AddFixtures.class);
        startActivity(addFixtures);                             //Opens AddFixtures Activity
    }


}
