package com.example.glucosemeasure.view;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.glucosemeasure.R;
import com.example.glucosemeasure.controller.Controller;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private TextView tvage;
    private SeekBar skage;
    private RadioButton rbf, rbnf;
    private EditText etvalue;
    private Button bt, logout;
    private String tvres;
    FirebaseAuth auth;
    FirebaseUser user;
    private boolean fast ;

    private final int  REQUEST_CODE=1;
    private Controller controller = Controller.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        if (user==null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();

            }
        });

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

                    controller.createpatient(age,value,rbf.isChecked()); //user action view --> controller

                    tvres=controller.getResponse(); // notify controller view --> string
                    resultat();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE)
            if(resultCode == RESULT_CANCELED)
                Toast.makeText(MainActivity.this,"Error !", Toast.LENGTH_SHORT).show();
    }

    private void init() {
        tvage = (TextView) findViewById(R.id.age);
        skage = (SeekBar) findViewById(R.id.sbAge);
        rbf = (RadioButton) findViewById(R.id.rbtyes);
        rbnf = (RadioButton) findViewById(R.id.rbtno);
        etvalue = (EditText) findViewById(R.id.value);
        bt = (Button) findViewById(R.id.btn);
        logout = (Button) findViewById(R.id.lgout);

    }
    private void  resultat(){
        Intent intent = new Intent(MainActivity.this, ConsultActivity.class) ;
        intent.putExtra("res",tvres);
        startActivityForResult(intent,REQUEST_CODE);
    }

}
