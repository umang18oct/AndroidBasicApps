package com.example.umang18oct.topperdisplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    Button buttonSubmit;
    EditText editTextYear;
    TextView strTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strTxt = (TextView) findViewById(R.id.strTxt);
        buttonSubmit = (Button) findViewById(R.id.button_submit);
        editTextYear = (EditText) findViewById(R.id.editText_year);

        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = editTextYear.getText().toString();
        int year;
        try{
            year = Integer.parseInt(str);
        }catch (Exception e){
            Toast.makeText(this,"Invalid year", Toast.LENGTH_SHORT).show();
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.data)));

        ArrayList<String> rnos = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Double> cgpas = new ArrayList<Double>();
        ArrayList<String> rnosS = new ArrayList<String>();
        ArrayList<String> namesS = new ArrayList<String>();
        ArrayList<Double> cgpasS = new ArrayList<Double>();
        String line;
        try {
            while((line = reader.readLine())!=null){
                String[] tokens = line.split(",");
                String rno = tokens[0].trim();
                String name = tokens[1].trim();
                double cgpa = Double.parseDouble(tokens[2].trim());
                int y = Integer.parseInt(rno.substring(0,2)) + 2000;
                if(y!=year)continue;
                rnos.add(rno);
                names.add(name);
                cgpas.add(cgpa);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int c=0;
        while(rnos.size()>0){
            int g = 0;
            for(int i=1;i<rnos.size();i++){
                if(cgpas.get(g) < cgpas.get(i)){
                    g = i;
                }
            }
            double cgpa = cgpas.get(g);
            String rno = rnos.get(g);
            String name = names.get(g);
            cgpas.remove(g);
            names.remove(g);
            rnos.remove(g);
            cgpasS.add(cgpa);
            namesS.add(name);
            rnosS.add(rno);
            c++;
            if(c>=5)break;
        }

        Intent i = new Intent(this, DisplayActivity.class);
        i.putExtra("cgpasS", cgpasS);
        i.putStringArrayListExtra("namesS", namesS);
        i.putStringArrayListExtra("rnosS", rnosS);
        i.putExtra("year", year);
        startActivity(i);
    }
}
