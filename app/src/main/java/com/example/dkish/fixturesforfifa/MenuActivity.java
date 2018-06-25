package com.example.dkish.fixturesforfifa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Here, MenuActivity.this is the current activity
        if (ContextCompat.checkSelfPermission(MenuActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // request the permission
                ActivityCompat.requestPermissions(MenuActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        j);


            }

        if (ContextCompat.checkSelfPermission(MenuActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // request the permission
            ActivityCompat.requestPermissions(MenuActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    j);


        }
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
