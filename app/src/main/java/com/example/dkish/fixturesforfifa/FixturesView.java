package com.example.dkish.fixturesforfifa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FixturesView extends AppCompatActivity {
    List<Fixture> FixturesList; //The main List of objects containing the parameters for the custom list view

    int k;
    ListView List;
    Fixture TemporaryFixture;
    Uri urimap;
    myadapter CustomAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures_view);
        TemporaryFixture = new Fixture();


        List = findViewById(R.id.List);

        FixturesList = new ArrayList<>(); //Initializing the List of objects as an Array List



         CustomAdapter = new myadapter(this,R.layout.custom_list,FixturesList);

        List.setAdapter(CustomAdapter); //Assigning the custom made List View adapter of the main list to the list view in FixturesView
    }


    public void AssignIcon1(View view){
        Selection();
        TemporaryFixture.image1 = urimap;

    }
    public void AssignIcon2(View view){
        Selection();
        TemporaryFixture.image2 = urimap;


    }
    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    public void Selection(){
        final CharSequence[] Options={"Camera" ,"Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(FixturesView.this);
        builder.setTitle("Choose");
        builder.setItems(Options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(Options[i]=="Camera"){
                    Intent OpenCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File TempImage = new File(Environment.getExternalStorageDirectory(),"icon.jpg"+k);

                    String authorities = getApplicationContext().getPackageName() + ".fileprovider";

                    OpenCamera.putExtra(MediaStore.EXTRA_OUTPUT , FileProvider.getUriForFile(getApplicationContext(), authorities, TempImage));
                    startActivityForResult(OpenCamera , 1);
                }
                else{
                    Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("icon.jpg"+k)) {
                        f = temp;
                        break;
                    }
                }
                try {


                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);
                    urimap = getImageUri(this,bitmap);
                    k++;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                urimap = data.getData();
                k++;



            }
        }
    }




    public  void AddFixture(View view){            //This function assigns the new values to corresponding variables and sends the object to FixturesView to get added to list
        EditText NewName1 = findViewById(R.id.AssignName1);
        TemporaryFixture.name1 = NewName1.getText().toString();

        EditText NewName2 = findViewById(R.id.AssignName2);
        TemporaryFixture.name2 = NewName2.getText().toString();

        EditText NewDate = findViewById(R.id.AssignDate);
        TemporaryFixture.date = NewDate.getText().toString();

        EditText NewVenue = findViewById(R.id.AssignVenue);
        TemporaryFixture.venue = NewVenue.getText().toString();

        FixturesList.add(TemporaryFixture);
        CustomAdapter.notifyDataSetChanged();

        TemporaryFixture = new Fixture();
    }










}

























