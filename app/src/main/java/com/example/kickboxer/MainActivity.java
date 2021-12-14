package com.example.kickboxer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static Spinner numeri;
    private static Spinner lettere;
    private static SeekBar ncolpi;
    private static TextView nSelected;
    private static SeekBar intervallo;
    private static TextView secondi;
    private static Switch sw1;
    private static Switch sw2;
    private static Spinner combo;
    private static Button start;
    private static Button load;
    private static Button save;

    public static final String FILE_name = "sessions.txt";
    public static final String EXTRA_numbers = "com.example.kickboxer.EXTRA_numbers";
    public static final String EXTRA_letters = "com.example.kickboxer.EXTRA_letters";
    public static final String EXTRA_combo = "com.example.kickboxer.EXTRA_combo";
    public static final String EXTRA_velocity = "com.example.kickboxer.EXTRA_velocity";
    public static final String EXTRA_intervallo = "com.example.kickboxer.EXTRA_intervallo";

    public void seeBar(){
        ncolpi = (SeekBar)findViewById(R.id.seekBar);
        nSelected = (TextView)findViewById(R.id.textView4);
        nSelected.setText("Velocità: " + (Integer)(ncolpi.getProgress() * 20) / 100);

        ncolpi.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progressValue;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = (progress * 20) / 100;
                        nSelected.setText("Velocità: " + progressValue);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        nSelected.setText("Velocità: " + progressValue);
                    }
                }
        );
    }


    public void checkSwitch(){
        sw1 = (Switch)findViewById(R.id.switch1);
        sw2 = (Switch)findViewById(R.id.switch2);

        sw1.setChecked(true);
        sw2.setChecked(true);

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this, "using numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(MainActivity.this, "using letters", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void intBar(){
        intervallo = (SeekBar)findViewById(R.id.seekBar2);
        secondi = (TextView)findViewById(R.id.textView6);
        secondi.setText("Secondi: " + (Integer)(ncolpi.getProgress() / 10));

        intervallo.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progressValue;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = progress / 10;
                        secondi.setText("Secondi: " + progressValue);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        secondi.setText("Secondi: " + progressValue);
                    }
                }
        );
    }

    public void setSpinners(){
        numeri = (Spinner) findViewById(R.id.spinner);
        lettere = (Spinner) findViewById(R.id.spinner2);
        combo = (Spinner) findViewById(R.id.spinner3);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        numeri.setAdapter(adapter);

        numeri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemvalue = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Selected: "+ itemvalue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> list3 = new ArrayList<>();
        list3.add("1");
        list3.add("2");
        list3.add("3");
        list3.add("4");
        list3.add("5");
        list3.add("6");
        list3.add("7");
        list3.add("8");
        list3.add("9");
        list3.add("10");
        list3.add("11");
        list3.add("12");

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list3);

        lettere.setAdapter(adapter3);

        lettere.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String itemvalue = parent.getItemAtPosition(position).toString();

                Toast.makeText(MainActivity.this, "Selected: "+ itemvalue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> list2 = new ArrayList<>();

        list2.add("2");
        list2.add("3");
        list2.add("4");
        list2.add("5");
        list2.add("6");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list2);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        combo.setAdapter(adapter2);

        combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemvalue = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Selected: "+ itemvalue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void openGenerator(){
        Intent intent = new Intent(this, Generator.class);
        Spinner sp = (Spinner)findViewById(R.id.spinner);
        SeekBar sk = (SeekBar)findViewById(R.id.seekBar);
        int nNumbers = 0, nLetters = 0;
        if(sw1.isChecked())
            nNumbers = Integer.parseInt(sp.getSelectedItem().toString());
        intent.putExtra(EXTRA_numbers, nNumbers);
        sp = (Spinner)findViewById(R.id.spinner2);
        if(sw2.isChecked())
            nLetters = Integer.parseInt(sp.getSelectedItem().toString());
        intent.putExtra(EXTRA_letters, nLetters);
        sp = (Spinner)findViewById(R.id.spinner3);
        intent.putExtra(EXTRA_combo, Integer.parseInt(sp.getSelectedItem().toString()));
        intent.putExtra(EXTRA_velocity, (sk.getProgress() * 20) / 100);
        sk = (SeekBar)findViewById(R.id.seekBar2);
        intent.putExtra(EXTRA_intervallo, sk.getProgress() / 10);
        startActivity(intent);
    }

    public void save(){
        StringBuilder sb = new StringBuilder();
        sb.append(numeri.getSelectedItemPosition() + "\n");
        sb.append(lettere.getSelectedItemPosition() + "\n");
        sb.append(sw1.isChecked() + "\n");
        sb.append(sw2.isChecked() + "\n");
        sb.append(combo.getSelectedItemPosition() + "\n");
        sb.append(ncolpi.getProgress() + "\n");
        sb.append(intervallo.getProgress() + "\n");

        String text = sb.toString();
        FileOutputStream fos;
        try{
            fos = new FileOutputStream(FILE_name);
        } catch (FileNotFoundException e){
            fos = null;
        }

        try {
            fos = openFileOutput(FILE_name, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(MainActivity.this, "Session saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(){
        FileInputStream fis = null;

        try{
            fis = openFileInput(FILE_name);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            for(int i = 0; i < 7; i++){
                String line = br.readLine();
                switch(i){
                    case 0:
                        numeri.setSelection(Integer.parseInt(line));
                        break;
                    case 1:
                        lettere.setSelection(Integer.parseInt(line));
                        break;
                    case 2:
                        sw1.setChecked(Boolean.valueOf(line));
                        break;
                    case 3:
                        sw2.setChecked(Boolean.valueOf(line));
                        break;
                    case 4:
                        combo.setSelection(Integer.parseInt(line));
                        break;
                    case 5:
                        ncolpi.setProgress(Integer.parseInt(line));
                        break;
                    case 6:
                        intervallo.setProgress(Integer.parseInt(line));
                        break;
                }
            }
            Toast.makeText(MainActivity.this, "Session loaded", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e){
            Toast.makeText(MainActivity.this, "No saved sessions", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try{
                    fis.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seeBar();
        intBar();
        checkSwitch();
        setSpinners();

        start = (Button) findViewById(R.id.button);
        load = (Button)findViewById(R.id.button3);
        save = (Button)findViewById(R.id.button4);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGenerator();
            }
        });
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                save();
            }
        });
        load.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                load();
            }
        });
    }

}
