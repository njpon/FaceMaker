package com.example.facemaker;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class FaceController implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, Button.OnClickListener {
    private Face faceView;
    private SeekBar barR;
    private SeekBar barG;
    private SeekBar barB;
    private Spinner spin;

    /**
     * FaceController constructor. It takes in a Face, three Seekbars for RGB, and a spinner to
     * make sure that when an item is selected, the corresponding Seekbar values change with it
     *
     * @param faceView
     * @param barR
     * @param barG
     * @param barB
     * @param spin
     */
    public FaceController(Face faceView, SeekBar barR, SeekBar barG, SeekBar barB, Spinner spin) {
        this.faceView = faceView;
        this.barB = barB;
        this.barG = barG;
        this.barR = barR;
        this.spin = spin;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //selects a hair style from the spinner
        faceView.setHairStyle(i);
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
        faceView.invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * External Citation
     *  Date: 10/6/20
     *  Problem: Did not know how to differentiate different SeekBars
     *  Resource:
     *          https://stackoverflow.com/questions/8719632/multiple-seekbars-listener/13468578
     * Solution: Followed the example in this post
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        //sets the value of the SeekBar to the correct RGB value of the correct face feature
        switch (seekBar.getId()) {
            case R.id.Red:
                if (faceView.getHairSelected()) {
                    faceView.setHairColor(i, Color.green(faceView.getHairColor()), Color.blue(faceView.getHairColor()));
                }
                else if (faceView.getSkinSelected()) {
                    faceView.setSkinColor(i, Color.green(faceView.getSkinColor()), Color.blue(faceView.getSkinColor()));
                }
                else if (faceView.getEyeSelected()) {
                    faceView.setEyeColor(i, Color.green(faceView.getEyeColor()), Color.blue(faceView.getEyeColor()));
                }
                break;
            case R.id.Green:
                if (faceView.getHairSelected()) {
                    faceView.setHairColor(Color.red(faceView.getHairColor()), i, Color.blue(faceView.getHairColor()));
                }
                else if (faceView.getSkinSelected()) {
                    faceView.setSkinColor(Color.red(faceView.getSkinColor()), i, Color.blue(faceView.getSkinColor()));
                }
                else if (faceView.getEyeSelected()) {
                    faceView.setEyeColor(Color.red(faceView.getEyeColor()), i, Color.blue(faceView.getEyeColor()));
                }
                break;
            case R.id.Blue:
                if (faceView.getHairSelected()) {
                    faceView.setHairColor(Color.red(faceView.getHairColor()), Color.green(faceView.getHairColor()), i);
                }
                else if (faceView.getSkinSelected()) {
                    faceView.setSkinColor(Color.red(faceView.getSkinColor()), Color.green(faceView.getSkinColor()), i);
                }
                else if (faceView.getEyeSelected()) {
                    faceView.setEyeColor(Color.red(faceView.getEyeColor()), Color.green(faceView.getEyeColor()), i);
                }
                break;
        }
        faceView.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        //Changes selected button to the radio button based on id. Also changes the value
        //of the seekBars to match the color of that selection
        switch(radioGroup.getCheckedRadioButtonId()) {
            case R.id.hair:
                faceView.setHairSelected();
                barR.setProgress(Color.red(faceView.getHairColor()));
                barG.setProgress(Color.green(faceView.getHairColor()));
                barB.setProgress(Color.blue(faceView.getHairColor()));
                break;
            case R.id.eyes:
                faceView.setEyesSelected();
                barR.setProgress(Color.red(faceView.getEyeColor()));
                barG.setProgress(Color.green(faceView.getEyeColor()));
                barB.setProgress(Color.blue(faceView.getEyeColor()));
                break;
            case R.id.skin:
                faceView.setSkinSelected();
                barR.setProgress(Color.red(faceView.getSkinColor()));
                barG.setProgress(Color.green(faceView.getSkinColor()));
                barB.setProgress(Color.blue(faceView.getSkinColor()));
                break;
        }
        faceView.invalidate();
    }

    @Override
    public void onClick(View view) {
        //randomizes the face by calling the randomize() method from the face class
        faceView.randomize();
        spin.setSelection(faceView.getHairStyle());
        if (faceView.getHairSelected()) {
            barR.setProgress(Color.red(faceView.getHairColor()));
            barG.setProgress(Color.green(faceView.getHairColor()));
            barB.setProgress(Color.blue(faceView.getHairColor()));
        }
        else if (faceView.getEyeSelected()) {
            barR.setProgress(Color.red(faceView.getEyeColor()));
            barG.setProgress(Color.green(faceView.getEyeColor()));
            barB.setProgress(Color.blue(faceView.getEyeColor()));
        }
        else if (faceView.getSkinSelected()) {
            barR.setProgress(Color.red(faceView.getSkinColor()));
            barG.setProgress(Color.green(faceView.getSkinColor()));
            barB.setProgress(Color.blue(faceView.getSkinColor()));
        }
        faceView.invalidate();
    }
}
