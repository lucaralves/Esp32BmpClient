package com.example.temperaturapressao.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.temperaturapressao.R;

public class TemperaturaPressaoActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura_pressao);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        textView1 = findViewById(R.id.temperaturapressao);
        textView2 = findViewById(R.id.temperaturapressao1);

        configureUi();
    }

    public void configureUi() {
        switch (getIntent().getStringExtra("mode")) {
            case "temp" :
                textView1.setText("Temperatura: ");
                Double temp = getIntent().getDoubleExtra("temp", 0);
                textView2.setText(temp.toString() + " C");
                break;
            case "press":
                textView1.setText("Pressão: ");
                Double press = getIntent().getDoubleExtra("press", 0);
                textView2.setText(press.toString() + " mbA");
        }
    }
}
