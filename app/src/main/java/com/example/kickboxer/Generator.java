package com.example.kickboxer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static android.net.Uri.parse;

public class Generator extends AppCompatActivity {

    private Chronometer cr;
    private boolean running = false;
    private long pauseOffset;
    private static TextView display;
    private static TextView interv;
    private static TextView vel;
    private static Button stop;
    private static TextView numeri;
    private static TextView lettere;
    private CountDownTimer t;
    private CountDownTimer t2;
    private CountDownTimer prepara;
    MediaPlayer mp = new MediaPlayer();
    private String[] audio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);
        stop = (Button) findViewById(R.id.button2);
        display = (TextView) findViewById(R.id.textView8);
        interv = (TextView) findViewById(R.id.textView10);
        vel = (TextView) findViewById(R.id.textView11);
        numeri = (TextView) findViewById(R.id.textView12);
        lettere = (TextView) findViewById(R.id.textView13);

        Intent intent = getIntent();
        int numbers = intent.getIntExtra(MainActivity.EXTRA_numbers, 0);
        int letters = intent.getIntExtra(MainActivity.EXTRA_letters, 0);
        int combo = intent.getIntExtra(MainActivity.EXTRA_combo, 0);
        int velocity = intent.getIntExtra(MainActivity.EXTRA_velocity, 0);
        int intervallo = intent.getIntExtra(MainActivity.EXTRA_intervallo, 0);

        interv.setText("Intervallo: " + intervallo);
        vel.setText("Velocità: " + velocity);
        numeri.setText("Numeri: " + numbers);
        lettere.setText("lettere: " + letters);

        double d = (double) 10 / velocity;
        d *= 1000;
        int timeForHit = (int) d + 0;
        display.setText("" + timeForHit * combo + "\n" + timeForHit);
        char[] combination = new char[numbers + letters];
        combination = buildCombination(numbers, letters);
        try {
            startCombination(timeForHit, combo, intervallo, combination);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cr = (Chronometer) findViewById(R.id.chronometer);
        startCr(cr);
        cr.start();

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMain();
            }
        });
    }

    public char[] buildCombination(int numbers, int letters) {
        char[] c = new char[numbers + letters];
        audio = new String[numbers + letters];
        char car;
        if(numbers != 0) {
            car = '1';
            for (int i = 0; i < numbers; i++) {
                c[i] = car;
                switch (Character.getNumericValue(car)){
                    case 1:
                        audio[i] = "uno";
                        break;
                    case 2:
                        audio[i] = "due";
                        break;
                    case 3:
                        audio[i] = "tre";
                        break;
                    case 4:
                        audio[i] = "quattro";
                        break;
                    case 5:
                        audio[i] = "cinque";
                        break;
                    case 6:
                        audio[i] = "sei";
                        break;
                    case 7:
                        audio[i] = "sette";
                        break;
                    case 8:
                        audio[i] = "otto";
                        break;
                    case 9:
                        audio[i] = "nove";
                        break;
                }
                car++;
            }
        }
        if(letters != 0) {
            car = 'A';
            for (int i = numbers; i < numbers + letters; i++) {
                c[i] = car;
                audio[i] = (Character.toLowerCase(car)) + "";
                car++;
            }
        }
        return c;
    }

    public void backMain() {
        stopCr(cr);
        t.cancel();
        t2.cancel();
        prepara.cancel();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startCr(View v) {
        if (!running) {
            cr.setBase(SystemClock.elapsedRealtime());
            running = true;
        }
    }

    public void pauseCr(View v) {
        if (running) {
            cr.stop();
            pauseOffset = SystemClock.elapsedRealtime() - cr.getBase();
            running = false;
        }
    }

    public void stopCr(View v) {
        if (running) {
            cr.setBase(SystemClock.elapsedRealtime());
            pauseOffset = 0;
        }
    }


    public void startCombination(int timeForHit, final int combo, int intervallo, final char[] combination) throws InterruptedException {
        final int size = combination.length;
        System.out.println("ARRAY: " + audio.toString());

        prepara = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                display.setText("Starting: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                t.start();
            }
        }.start();

        t  = new CountDownTimer(timeForHit * combo, timeForHit) {
            Random r = new Random();
            @Override
            public void onTick(long millisUntilFinished) {
                int numero = r.nextInt(size);
                playSound(audio[numero]);
                display.setText("" + combination[numero]);
            }

            @Override
            public void onFinish() {
                t2.start();
            }
        };

        t2 = new CountDownTimer(intervallo * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                display.setText("00:" + (millisUntilFinished - 100) / 1000);
            }

            @Override
            public void onFinish() {
                t.start();
            }
        };
    }

    private void playSound(String path) {
        mp = MediaPlayer.create(Generator.this, getResources().getIdentifier(path, "raw", getPackageName()));
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mp.start();
            }
        });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.reset();
            }
        });
    }
}
