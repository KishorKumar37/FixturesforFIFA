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
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class EditFixtures extends AppCompatActivity {
    Fixture TemporaryFixture3;
    ImageView im_image2;
    Bitmap bitmap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fixtures);
    }



    public void EditFixture(Fixture TemporaryFixture2 ){   //Receives the temp object from PassToEditFixture function in FixturesView Activity and assigns it to one of it's own variables
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
    public void ChangeIcon1(View view){
        Selection();
        TemporaryFixture3.image1 = bitmap;

    }
    public void ChangeIcon2(View view){
        Selection();
        TemporaryFixture3.image2 = bitmap;

    }


    public void Selection(){
        final CharSequence[] Options={"Camera" ,"Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(EditFixtures.this);
        builder.setTitle("Choose");
        builder.setItems(Options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(Options[i]=="Camera"){
                    Intent OpenCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File TempImage = new File(Environment.getExternalStorageDirectory(),"icon1.jpg");
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
                File TempImage = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : TempImage.listFiles()) {
                    if (temp.getName().equals("icon1.jpg")) {
                        TempImage = temp;
                        break;
                    }
                }
                try {

                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(TempImage.getAbsolutePath(),
                            bitmapOptions);



                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap bitmap = (BitmapFactory.decodeFile(picturePath));

            }
        }
    }
}
