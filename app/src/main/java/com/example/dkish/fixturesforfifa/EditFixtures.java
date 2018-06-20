package com.example.dkish.fixturesforfifa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditFixtures extends AppCompatActivity {
    Fixtures TemporaryFixture3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fixtures);
    }


    public void EditFixture(Fixtures TemporaryFixture2 ){   //Receives the temp object from PassToEditFixture function in FixturesView Activity and assigns it to one of it's own variables
        TemporaryFixture3=TemporaryFixture2;
    }
                                                            //Separate buttons for each Edit so that the user can choose what to Edit
    public void ChangeName1(View view){
        EditText NewName1 = findViewById(R.id.NewName1);
        TemporaryFixture3.name1 = NewName1.getText().toString();
    }
    public void ChangeName2(View view){
        EditText NewName2 = findViewById(R.id.NewName2);
        TemporaryFixture3.name2 = NewName2.getText().toString();
    }
    public void ChangeDate(View view){
        EditText NewName2 = findViewById(R.id.NewName2);
        TemporaryFixture3.date = NewName2.getText().toString();
    }
    public void ChangeVenue(View view){
        EditText NewVenue = findViewById(R.id.NewVenue);
        TemporaryFixture3.venue = NewVenue.getText().toString();
    }
}
