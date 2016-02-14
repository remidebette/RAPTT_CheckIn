package com.raptt.debette.raptt_checkin;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends MenuActivity {
    public int ID_NOTIFICATION = 0;
    public final static String ICON = "com.raptt.debette.raptt_checkin.extra.icon";

    // La chaîne de caractères par défaut
    private final String defaut = "Vous devez cliquer sur le bouton « Calculer l'IMC » pour obtenir un résultat.";


    Button envoyer = null;
    Button raz = null;

    EditText poids = null;
    EditText taille = null;

    RadioGroup group = null;

    TextView result = null;


    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(defaut);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private OnClickListener envoyerListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            result.setText("Votre IMC est ");
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On récupère toutes les vues dont on a besoin
        envoyer = (Button) findViewById(R.id.calcul);

        taille = (EditText) findViewById(R.id.taille);
        poids = (EditText) findViewById(R.id.poids);

        group = (RadioGroup) findViewById(R.id.group);

        result = (TextView) findViewById(R.id.result);

        // On attribue un listener adapté aux vues qui en ont besoin
        envoyer.setOnClickListener(envoyerListener);
        taille.addTextChangedListener(textWatcher);
        poids.addTextChangedListener(textWatcher);

        raz = (Button) findViewById(R.id.raz);
        raz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // L'icône sera une petite loupe
                int icon = R.drawable.ic_line_a;
                // Le premier titre affiché
                CharSequence tickerText = "Prise de service";
                // Daté de maintenant
                long when = System.currentTimeMillis();

                AlarmManager alarmManager = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
                Calendar calendar =  Calendar.getInstance();
                calendar.set(Calendar.SECOND, 5);
                //calendar.set(int year, int month, int date, int hour, int minute, int second);

                // La notification est crée
                Intent intent = new Intent(MainActivity.this, ReminderService.class);
                //intent.setAction(ReminderService.ACTION_SET_NOTIFICATION);
                PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, 0);
                alarmManager.set(AlarmManager.ELAPSED_REALTIME, calendar.getTimeInMillis(), pendingIntent);
            }
        });

    }
}