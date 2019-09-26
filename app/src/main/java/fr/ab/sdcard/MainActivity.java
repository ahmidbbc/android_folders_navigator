package fr.ab.sdcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Path;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String etatSD = Environment.getExternalStorageState();
        if (etatSD.equals(Environment.MEDIA_MOUNTED)) {


            //Toast.makeText(this, "La SD est montée", Toast.LENGTH_SHORT).show();
            File storageDir = Environment.getExternalStorageDirectory();
            //oast.makeText(this, storageDir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            File[] list = storageDir.listFiles();
            StringBuilder lsb = new StringBuilder();
            File folder = new File(storageDir.getAbsolutePath());

            try {
                File newFolder = new File(folder, "TXT");
                boolean folderOK = newFolder.mkdir();
                boolean fileOK = false;
                boolean filesOK = false;

                for (Integer i = 1; i < 4; i++) {
                    File newFile = new File(folder, "/" + i.toString() + ".txt");
                    FileOutputStream fos = new FileOutputStream(newFile);
                    OutputStreamWriter osr = new OutputStreamWriter(fos);
                    osr.write(i.toString() + i.toString() + i.toString()  + "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
                    osr.close();
                    fos.close();
                    fileOK = newFile.createNewFile();
                }
                /*
                if (fileOK) {
                    Toast.makeText(this, "Les fichiers a bien été créés", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Les fichiers n'ont pas pu être créés", Toast.LENGTH_LONG).show();
                }*/

                    for (Integer i = 10; i < 13; i++) {
                        File newFile = new File(newFolder, "/" + i.toString() + ".txt");
                        FileOutputStream fos = new FileOutputStream(newFile);
                        OutputStreamWriter osr = new OutputStreamWriter(fos);
                        osr.write(i.toString() + i.toString() + i.toString());
                        osr.close();
                        fos.close();
                        filesOK = newFile.createNewFile();
                    }

                    if (filesOK) {
                        Toast.makeText(this, "Les fichiers a bien été créés", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Les fichiers n'ont pas pu être créés", Toast.LENGTH_LONG).show();
                    }


            }catch(Exception e){
                 Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_LONG).show();
            }

            for (int i = 0; i < list.length; i++) {

                lsb.append(list[i].getName());
                lsb.append("\n");
            }
            Toast.makeText(this, lsb.toString(), Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "Aucune carte SD dans l'appareil", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    @Override
    protected void onResume() {
        super.onResume();
        String etatSD = Environment.getExternalStorageState();
        if (etatSD.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "Fini la pause ?", Toast.LENGTH_SHORT).show();
        }

    }*/
}
