package com.example.dkish.fixturesforfifa;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class myadapter extends ArrayAdapter<Fixtures> {
    List<Fixtures> teamList;

    Context context;

    int Resource;

    //The parameters required to be passed to the AdapterClass




    public myadapter(Context context , int Resource , List<Fixtures> teamList){  //Constructor
        super(context,Resource,teamList);
        this.context = context;
        this.Resource = Resource;
        this.teamList = teamList;
    }
    @NonNull
    @Override
    public View getView( final int position , @Nullable View convertedView , @NonNull ViewGroup parent){  //GetView function to assign the variables to their corresponding positions in the custom ListView
        LayoutInflater builder = LayoutInflater.from(context);
        View view = builder.inflate(Resource ,null , false);

        ImageView icon1 = view.findViewById(R.id.icon1);
        ImageView icon2 = view.findViewById(R.id.team2);

        TextView team1 = view.findViewById(R.id.team1);
        TextView team2 = view.findViewById(R.id.team2);

        TextView date = view.findViewById(R.id.date);
        TextView venue = view.findViewById(R.id.venue);

        Button delete = view.findViewById(R.id.delete);
        Button edit = view.findViewById(R.id.edit);


        Fixtures fixtures = teamList.get(position);//This is a separate list to be used by the custom adapter only

        icon1.setImageDrawable(context.getResources().getDrawable(fixtures.returnIcon1()));
        icon2.setImageDrawable(context.getResources().getDrawable(fixtures.returnIcon2()));

        team1.setText(fixtures.returnName1());
        team2.setText(fixtures.returnName2());

        date.setText(fixtures.returnDate());
        venue.setText(fixtures.returnVenue());




        //Since the custom adapter is associated with the custom list and also contains the correct position it makes sense to add the delete and edit functions here
        delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){ //Delete is a very simple function. Edit and Add are more complex
                teamList.remove(position);
                notifyDataSetChanged();
            }

        });

        edit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                FixturesView Temp = new FixturesView();
                Temp.PassToEditFixture(position);

            }

        });





        return view;

    }


}
