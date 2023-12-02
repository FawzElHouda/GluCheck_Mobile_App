package com.example.glucosemeasure.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.glucosemeasure.R;

public class ConsultActivity extends AppCompatActivity {
    private TextView tvres;
    private Button btback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        init();

        Intent intent= getIntent();
        String res = intent.getStringExtra("res");
        tvres.setText(res);

        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if(res != null)
                    setResult(RESULT_OK, intent);
                else
                    setResult(RESULT_CANCELED,intent);
            }
        });
    }

    private void  init(){
    tvres=(TextView) findViewById(R.id.tvRes);
    btback=(Button) findViewById(R.id.btback);}
}