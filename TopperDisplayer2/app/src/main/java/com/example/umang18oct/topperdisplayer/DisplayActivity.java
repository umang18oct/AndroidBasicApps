package com.example.umang18oct.topperdisplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DisplayActivity extends Activity {

    TextView[] rno = new TextView[5];
    TextView[] name = new TextView[5];
    TextView[] cgpa = new TextView[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        rno[0] = (TextView) findViewById(R.id.rno1);
        rno[1] = (TextView) findViewById(R.id.rno2);
        rno[2] = (TextView) findViewById(R.id.rno3);
        rno[3] = (TextView) findViewById(R.id.rno4);
        rno[4] = (TextView) findViewById(R.id.rno5);

        name[0] = (TextView) findViewById(R.id.name1);
        name[1] = (TextView) findViewById(R.id.name2);
        name[2] = (TextView) findViewById(R.id.name3);
        name[3] = (TextView) findViewById(R.id.name4);
        name[4] = (TextView) findViewById(R.id.name5);

        cgpa[0] = (TextView) findViewById(R.id.cgpa1);
        cgpa[1] = (TextView) findViewById(R.id.cgpa2);
        cgpa[2] = (TextView) findViewById(R.id.cgpa3);
        cgpa[3] = (TextView) findViewById(R.id.cgpa4);
        cgpa[4] = (TextView) findViewById(R.id.cgpa5);

        Intent i = getIntent();
        ArrayList<String> namesS = i.getStringArrayListExtra("namesS");
        ArrayList<String> rnosS = i.getStringArrayListExtra("rnosS");
        ArrayList<Double> cgpasS = (ArrayList<Double>) getIntent().getSerializableExtra("cgpasS");


        for(int j=0;j<rnosS.size();j++)
        {
            this.rno[j].setText(rnosS.get(j));
            this.name[j].setText(namesS.get(j));
            this.cgpa[j].setText(Double.toString(cgpasS.get(j)));
        }
    }
}
