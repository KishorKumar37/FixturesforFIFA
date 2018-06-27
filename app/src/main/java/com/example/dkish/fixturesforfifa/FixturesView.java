package com.example.dkish.fixturesforfifa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class FixturesView extends AppCompatActivity {
    List<Fixture> FixturesList; //The main List of objects containing the parameters for the custom list view

    ListView List;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures_view);

        List = findViewById(R.id.List);

        FixturesList = new ArrayList<>(); //Initializing the List of objects as an Array List


        myadapter CustomAdapter = new myadapter(this,R.layout.custom_list,FixturesList);

        List.setAdapter(CustomAdapter); //Assigning the custom made List View adapter of the main list to the list view in FixturesView
    }

    public void PassToEditFixture(final int position){              //This function receives the position from a function in the custom adapter class
                                                                    //Passing an object which is equivalent to the object at that position in the list
        Fixture TemporaryFixture1 = FixturesList.get(position);  //Assigning the values of the object at that position to a temporary object

        Intent StartEditFixtures = new Intent(FixturesView.this , EditFixtures.class);
        startActivity(StartEditFixtures); //Starts the EditFixtures Activity

        EditFixtures TemporaryEditFixture = new EditFixtures();//Creating an object of EditFixtures class to use it's function
        TemporaryEditFixture.EditFixture(TemporaryFixture1); //Passing the temporary object to that function which is going to make the changes


    }
    public void ReceiveFromAddFixture(Fixture TemporaryFixture2){

        FixturesList.add(TemporaryFixture2);
    }





}

























