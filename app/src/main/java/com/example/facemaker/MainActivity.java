package com.example.facemaker;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //Note: This program was optimized for portrait orientation, but still works
    //for the landscape orientation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar barR;
        SeekBar barG;
        SeekBar barB;
        RadioGroup selection;
        Button randomize;
        Spinner hair;

        Face face = findViewById(R.id.view);

        /**
         * External Citation
         * Date: 1/6/20
         * Problem: I didn't know how to add a spinner
         * Resource:
         *          https://www.youtube.com/watch?v=on_OrrX7Nw4&ab_channel=CodinginFlow
         * Solution: Followed the example in the video
         */
        hair = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.HairSelection, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hair.setAdapter(adapter);

        //initialize RadioButtons, SeekBars, and Button
        barR = findViewById(R.id.Red);
        barG = findViewById(R.id.Green);
        barB = findViewById(R.id.Blue);
        selection = findViewById(R.id.selection);
        randomize = findViewById(R.id.random);

        //controller constructor
        FaceController control = new FaceController(face, barR, barG, barB, hair);

        //set change listeners to the controller
        barB.setOnSeekBarChangeListener(control);
        barG.setOnSeekBarChangeListener(control);
        barR.setOnSeekBarChangeListener(control);
        hair.setOnItemSelectedListener(control);
        selection.setOnCheckedChangeListener(control);
        randomize.setOnClickListener(control);
    }
}
