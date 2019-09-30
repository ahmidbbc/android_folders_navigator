package fr.ab.sdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class FilesShower extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listViewRacine;
    private File currentFile;
    private Intent intentionFile;
    private File storageDir;
    private File folder;


    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_shower);
        listViewRacine = findViewById(R.id.listViewRacine);


        storageDir = Environment.getExternalStorageDirectory();
        File[] list = storageDir.listFiles();
        folder = new File(storageDir.getAbsolutePath());

        ArrayList<String> lsb = new ArrayList<String>();
        for (int i = 0; i < list.length; i++) {

            if(list[i].isDirectory()){
                lsb.add(list[i].getName() + " [FOLDER]");
            }else if(list[i].isFile()){
                lsb.add(list[i].getName() + " [FILE]");
            }else{
                lsb.add(list[i].getName() + " [?]");
            }

        }

        ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lsb);


        listViewRacine.setAdapter(adapterList);


        //Toast.makeText(this, lsb.toString(), Toast.LENGTH_SHORT).show();

        listViewRacine.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String item = listViewRacine.getAdapter().getItem(position).toString();
        try {
            if (item.contains("FILE")) {

                String[] itemTrim = item.split(" ");
                URI uri = null;
                String path = itemTrim[0];
                try{
                    uri = new URI("file:///" + path);

                }catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                intentionFile = new Intent(this, FilesEditor.class);
                intentionFile.putExtra("file", path);
                setResult(RESULT_OK, intentionFile);
                startActivityForResult(intentionFile, 1);

                // Log.d(listViewRacine.toString(),"VIEEEEEEEEEEWWWWWWWWWWWWWWWW !!!");
                Toast.makeText(this, "PATH: " + path + " - URI: " + uri.toString(), Toast.LENGTH_SHORT).show();
            }else if(listViewRacine.getAdapter().getItem(position).toString().contains("FOLDER")){

                Toast.makeText(this, "C'est un FOLDER", Toast.LENGTH_SHORT).show();

            }else{

                Toast.makeText(this, "Ce type de fichier n'est pas pris en charge", Toast.LENGTH_SHORT).show();

            }

        } // / try

        catch (Exception e) {
            Log.d(e.getMessage(), "ERRRRRRRRRREURRRRRRRRR !!!");
        }
    } // / onListItemClick()


    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1: // File
                switch (resultCode) {
                    case RESULT_OK:
                        // --- Recuperation des donnees recues
                        textViewEditeur.append(" : " + data.getStringExtra("nomEditeur"));
                        return;
                    case RESULT_CANCELED:
                        textViewMessageAccueil.setText("Annulation éditeur");
                        return;
                } // / switch (resultCode)


        } // / switch (requestCode)*/

}
