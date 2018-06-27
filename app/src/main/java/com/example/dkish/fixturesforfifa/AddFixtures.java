package com.example.dkish.fixturesforfifa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.io.File;


public class AddFixtures extends AppCompatActivity {

    Fixture TemporaryFixture3;
    Button GiveIcon1;
    Button GiveIcon2;

    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fixtures);
        GiveIcon1 = findViewById(R.id.GiveIcon1);
        GiveIcon2 = findViewById(R.id.GiveIcon2);


    }

    public void AssignIcon1(View view){
        Selection();
        TemporaryFixture3.image1 = bitmap;

    }
    public void AssignIcon2(View view){
        Selection();
        TemporaryFixture3.image2 = bitmap;


    }


    public void Selection(){
        final CharSequence[] Options={"Camera" ,"Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(AddFixtures.this);
        builder.setTitle("Choose");
        builder.setItems(Options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(Options[i]=="Camera"){
                    Intent OpenCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File TempImage = new File(Environment.getExternalStorageDirectory(),"icon.jpg");
                    OpenCamera.putExtra(MediaStore.EXTRA_OUTPUT , FileProvider.getUriForFile(getApplicationContext(), getPackageName()+".fileprovider", TempImage));
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
                    if (temp.getName().equals("icon.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {


                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                bitmap = (BitmapFactory.decodeFile(picturePath));


            }
        }
    }




    public  void AddFixture(){            //This function assigns the new values to corresponding variables and sends the object to FixturesView to get added to list
        EditText NewName1 = findViewById(R.id.AssignName1);
        TemporaryFixture3.name1 = NewName1.getText().toString();

        EditText NewName2 = findViewById(R.id.AssignName2);
        TemporaryFixture3.name2 = NewName2.getText().toString();

        EditText NewDate = findViewById(R.id.AssignDate);
        TemporaryFixture3.date = NewDate.getText().toString();

        EditText NewVenue = findViewById(R.id.AssignVenue);
        TemporaryFixture3.venue = NewVenue.getText().toString();

        FixturesView TemporaryFixturesView = new FixturesView();
        TemporaryFixturesView.ReceiveFromAddFixture(TemporaryFixture3);
    }
}
