package com.example.lap10715.demoweek1.demo_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lap10715.demoweek1.R;

import java.util.ArrayList;
import java.util.List;

public class DemoCalledActivity extends AppCompatActivity {

    public static final String CODE_COLOR = "code_color";
    private Button btnFinish;
    private Spinner spnChooseColor;
    private String choseColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_called);

        btnFinish = findViewById(R.id.btn_finish);
        spnChooseColor = findViewById(R.id.spn_choose_color);
        final List<String> data = new ArrayList();
        data.add("RED");
        data.add("GREEN");
        data.add("BLUE");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                data);
        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnChooseColor.setAdapter(adapter);


        spnChooseColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choseColor = data.get(position);
                spnChooseColor.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(CODE_COLOR, choseColor);
                setResult(DemoStartActivity.REQ_CODE_RESULT_FROM_CALLED_ACTIVITY, returnIntent);
                finish();
            }
        });

    }

}
