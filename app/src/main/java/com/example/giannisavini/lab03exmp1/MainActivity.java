package com.example.giannisavini.lab03exmp1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pgbLoading;
    private TextView txvUpdate;
    private Button btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pgbLoading = (ProgressBar) findViewById(R.id.pgb_loading);
        txvUpdate = (TextView) findViewById(R.id.txv_update);
        btnGo = (Button) findViewById(R.id.btn_go);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Il "for" simula del codice pesante per l'esecuzione dell'applicazione.
                 * Il codice commentato rappresenta l'errata gestione di queste tipolgie
                 * di codice.
                 */

                /*pgbLoading.setVisibility(View.VISIBLE);

                for (int i = 0; i < 5000000; i++) {
                    if (i > 0) {
                        txvUpdate.setText(String.valueOf(i));
                    }
                }

                Toast.makeText(MainActivity.this, "Finito!", Toast.LENGTH_SHORT).show();
                pgbLoading.setVisibility(View.INVISIBLE);*/


                //Avvio dell'AsyncTask tramite il suo metodo execute()
                new UpdateTask().execute();
            }
        });
    }

    public class UpdateTask extends AsyncTask<String, Integer, String> {

        /**
         * Metodo principale del task; vengono eseguite le operazioni per cui il task stesso è stato creato.
         * Tramite il metodo publishProgress è possibile aggiornare la view sui progressi effettuati.
         * Viene eseguito in un thread separato dal main.
         *
         * @param params eventuali parametri di ingresso
         * @return il risultato delle operazioni
         */
        @Override
        protected String doInBackground(String... params) {
            for(int i = 0; i < 100000; i++) {
                if(i > 0) {
                    publishProgress(i);
                }
            }
            return "Update Terminato";
        }
        /**
         * Metodo richiamato prima dell'inizio dell'esecuzione del task; lavora sul main thread.
         * In questo metodo vengono solitamente avviate le procedure grafiche per segnalare all'utente
         * che sta avvenendo qualcosa in background.
         */
        @Override
        protected void onPreExecute() {
            pgbLoading.setVisibility(View.VISIBLE);
            txvUpdate.setText("onPreExecute");
        }
        /**
         * Metodo richiamato dal doInBackgound (tramite il comando publishPrograss) per effettuare degli update; lavora sul main thread.
         * In questo metodo vengono solitamente modificati degli aspetti della view per indicare all'utente l'avanzamento
         * del processo in background.
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            txvUpdate.setText(Integer.toString(values[0]));
        }
        /**
         * Metodo richiamato alla fine dell'esecuzione del task; lavora sul main thread.
         * In questo metodo vengono solitamente avviate le procedure grafiche per segnalare all'utente
         * che il processo si è concluso, ed eventualmente per mostrare i risultati.
         */
        @Override
        protected void onPostExecute(String result){
            txvUpdate.setText(result);
            pgbLoading.setVisibility(View.INVISIBLE);
        }
    }
}
