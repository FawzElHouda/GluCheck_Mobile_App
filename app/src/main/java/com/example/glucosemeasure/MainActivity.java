package com.example.glucosemeasure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private TextView tvage, tvres;
    private SeekBar skage;
    private RadioButton rbf, rbnf;
    private EditText etvalue;
    private Button bt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_connexion);
        init();

        skage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seek, int progress, boolean fromUser) {
                Log.i("information", "onProgressChanged:" + progress);
                tvage.setText("Your age: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Stop Tracking Touch", Toast.LENGTH_SHORT).show();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    calcul(v) ;
                }

        });
    }
    private void init ()
    {
        tvage = (TextView) findViewById(R.id.age);
        tvres = (TextView) findViewById(R.id.res);
        skage = (SeekBar) findViewById(R.id.sbAge);
        rbf = (RadioButton) findViewById(R.id.rbtyes);
        rbnf = (RadioButton) findViewById(R.id.rbtno);
        etvalue = (EditText) findViewById(R.id.value);
        bt = (Button) findViewById(R.id.btn);
    }
    public void calcul(View v) {
        int age;
        float value;
        boolean checkage = false, checkvalue = false;

        if (skage.getProgress() != 0)
            checkage = true;
        else
            Toast.makeText(MainActivity.this, "Check age", Toast.LENGTH_SHORT).show();

        if (!etvalue.getText().toString().isEmpty())
            checkvalue = true;
        else
            Toast.makeText(MainActivity.this, "Check value", Toast.LENGTH_LONG).show();

        if (checkage && checkvalue) {
            age = skage.getProgress();
            value = Float.valueOf(etvalue.getText().toString());

            if (rbf.isChecked()) {
                if (age >= 13) {
                    if (value < 5.0)
                        tvres.setText("Low blood sugar level");
                    else if (value > 5.0 && value <= 7.2) {
                        tvres.setText("Normal blood sugar level");
                    } else {
                        tvres.setText("Elevated blood glucose level");
                    }
                } else if (age >= 6 && age <= 12) {
                    if (value < 5.0)
                        tvres.setText("Low blood sugar level");
                    else if (value >= 5.0 && value <= 10.0)
                        tvres.setText("Normal blood sugar level");
                    else
                        tvres.setText("Elevated blood glucose level");
                } else { // age < 6
                    if (value < 5.5)
                        tvres.setText("Low blood sugar level");
                    else if (value > 5.5 && value <= 10)
                        tvres.setText("Normal blood sugar level");
                    else
                        tvres.setText("Elevated blood glucose level");
                }
            } else {
                if (value < 10.5)
                    tvres.setText("Low blood sugar level");
                else
                    tvres.setText("Elevated blood glucose level");
            }
        }
    }


}