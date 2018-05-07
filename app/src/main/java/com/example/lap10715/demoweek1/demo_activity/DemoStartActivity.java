package com.example.lap10715.demoweek1.demo_activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lap10715.demoweek1.R;

public class DemoStartActivity extends AppCompatActivity {

    public static final int REQ_CODE_RESULT_FROM_CALLED_ACTIVITY = 1;
    private static final String RESULT_COLOR = "result_color";
    private Button btnCallActivity;
    private EditText edtName;
    private LinearLayout lnParent;
    private TextView tvDisplayColor;
    private String resultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_demo);

        btnCallActivity = findViewById(R.id.btn_call_activity);
        edtName = findViewById(R.id.edt_name);
        lnParent = findViewById(R.id.ln_parent);
        tvDisplayColor = findViewById(R.id.tv_display_result);

        btnCallActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DemoStartActivity.this,
                        DemoCalledActivity.class);
                DemoStartActivity.this.startActivityForResult(intent, REQ_CODE_RESULT_FROM_CALLED_ACTIVITY);
            }
        });

        if(savedInstanceState != null){
            resultColor = savedInstanceState.getString(RESULT_COLOR);
            switch (resultColor){
                case "RED":
                    lnParent.setBackgroundColor(Color.RED);
                    break;
                case "GREEN":
                    lnParent.setBackgroundColor(Color.GREEN);
                    break;
                case "BLUE":
                    lnParent.setBackgroundColor(Color.BLUE);
                    break;

            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_CODE_RESULT_FROM_CALLED_ACTIVITY:
                resultColor = data.getStringExtra(DemoCalledActivity.CODE_COLOR);
               switch (resultColor){
                   case "RED":
                       lnParent.setBackgroundColor(Color.RED);
                       break;
                   case "GREEN":
                       lnParent.setBackgroundColor(Color.GREEN);
                       break;
                   case "BLUE":
                       lnParent.setBackgroundColor(Color.BLUE);
                       break;

               }

                break;

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(resultColor != null){
            outState.putString(RESULT_COLOR, resultColor);
        }
        super.onSaveInstanceState(outState);
    }
}
