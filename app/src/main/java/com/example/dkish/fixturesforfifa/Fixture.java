package com.example.dkish.fixturesforfifa;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcelable;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;

public class Fixture {
   public
    String date;
    String name1 , name2 , venue;
    Uri image1;
    Uri image2;

    public Fixture(){ //Constructor for the class
        this.date = date;
        this.name1= name1;
        this.name2= name2;
        this.venue= venue;
        this.image1= image1;
        this.image2= image2;
    }

    public String returnDate(){
        return(date);
    }  //Return functions for all the parameters
    public String returnName1(){
        return(name1);
    }
    public String returnName2(){
        return(name2);
    }
    public String returnVenue(){
        return(venue);
    }
    public Uri returnIcon1(){
        return(image1);
    }
    public Uri returnIcon2(){
        return(image2);
    }


    public void ChangeName1(String x){   //Change functions to change the value of the parameters either in adding a new Fixture or editing an existing one
        name1=x;
    }
    public void ChangeName2(String x){
        name2=x;
    }
    public void ChangeDate(String x){
        date=x;
    }
    public void ChangeVenue(String x){
        venue=x;
    }

}
