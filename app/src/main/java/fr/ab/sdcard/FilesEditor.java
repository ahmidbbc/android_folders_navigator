package fr.ab.sdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

public class FilesEditor extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private TextView textViewEditeur;
    private EditText editTextFile;
    private Button buttonSave;
    private InputStreamReader is;
    private FileInputStream fs;
    private BufferedReader br;
    private String line;
    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_editor);

        textViewEditeur = findViewById(R.id.textViewEditeur);
        editTextFile = findViewById(R.id.editTextFile);
        buttonSave = findViewById(R.id.buttonSave);
        Bundle params = this.getIntent().getExtras();
        uri = params.getString("file");
        File currentFile =  new File("sdcard/" + uri);

        //Toast.makeText(this, uri, Toast.LENGTH_LONG).show();

            if(currentFile.exists()) {
                try {
                    Toast.makeText(this, "OKKKKK", Toast.LENGTH_SHORT).show();
                    fs = new FileInputStream(currentFile);
                    is = new InputStreamReader(fs);
                    br = new BufferedReader(is);

                    while ((line = br.readLine()) != null) {
                        editTextFile.append(line + "\n");
                    }
                    br.close();
                    is.close();
                    fs.close();
                }
                catch(Exception e) {
                    editTextFile.append(e.getMessage() + "\n");
                }
            }
            else {
                editTextFile.append("Fichier vide editez-le !");
            }


/*
        textViewEditeur.append(" : " + valeur);
        editTextFile.append(valeur);*/

        //buttonSave.setOnClickListener(this);

    }






    public void onActivityResult(int requestCode, int resultCode, Intent data) {


                        // --- Recuperation des donnees recues
                        uri = data.getStringExtra("file");


                        //Toast.makeText(this, valeur, Toast.LENGTH_LONG).show();


                        /*
                        try {
                            while ((data.getDataString("file") = br.readLine()) != null) {
                                lsbContenu.append(lsLigne + "\n");
                            }

                        }catch (Exception e){

                        }*/

                        return;

                 // switch (resultCode)
         // switch (requestCode)

    } // onActivityResult


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(view == buttonSave){
            textViewEditeur.append(uri);
        }


    }
}
