package fr.ab.files;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.*;

import fr.ab.sdcard.R;

public class WriteAndReadToFile extends AppCompatActivity {

    private TextView textViewMessage;
    private final String FICHIER_TXT = "tintin.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_and_read_to_files);


        try {
            String lsContenu = "";
            lsContenu += "Tintin\n";
            lsContenu += "Milou\n";
            lsContenu += "Haddock\n";

            textViewMessage = findViewById(R.id.textViewMessage);

            String lsMessage = ecrire(this, FICHIER_TXT, lsContenu);
            textViewMessage.setText(lsMessage);
            Toast.makeText(this, lsMessage,Toast.LENGTH_LONG).show();
//            Thread.sleep(3000);

            textViewMessage.setText("Après 3 secondes");

            lsMessage = lire(this,FICHIER_TXT);
            textViewMessage.setText(lsMessage);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("erreur", e.getMessage());
        }

    }

    /**
     * @param contexte
     * @param psFichier
     * @param psContenu
     * @return
     */
    private String ecrire(Context contexte, String psFichier, String psContenu) {

        String lsMessage = "";
        FileOutputStream fos;
        OutputStreamWriter osw;
        BufferedWriter bw;

        try {
            fos = contexte.openFileOutput(psFichier, Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write(psContenu);

            bw.close();
            osw.close();
            fos.close();
            lsMessage = "Jusque là tout va bien !\nLe fichier " + FICHIER_TXT + " a été créé et rempli";
        } catch (Exception e) {
            lsMessage = "Exception write : " + e.getMessage();
        }

        return lsMessage;

    } // / ecrire

    /**
     *
     * @param contexte
     * @param psFichier
     * @return
     */
    private String lire(Context contexte, String psFichier) {
        StringBuilder lsb = new StringBuilder();
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader br;
        String lsLine;
        try {
            fis = contexte.openFileInput(psFichier);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            while((lsLine = br.readLine()) != null) {
                lsb.append(lsLine);
                lsb.append("\n");
            }
            br.close();
            isr.close();
            fis.close();
        } catch (Exception e) {
            lsb.append("Exception write : " + e.getMessage());
        }
        return lsb.toString();

    } // / lire
}
