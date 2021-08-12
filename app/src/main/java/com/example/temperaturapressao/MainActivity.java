package com.example.temperaturapressao;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.temperaturapressao.activities.TemperaturaPressaoActivity;
import com.example.temperaturapressao.helper.Response;
import com.example.temperaturapressao.helper.Utils;
import com.example.temperaturapressao.json.JsonHandler;
import com.example.temperaturapressao.network.GenericGetAsyncTask;
import com.example.temperaturapressao.network.HttpStatusCode;

public class MainActivity extends AppCompatActivity {

    private Button buttonTemp, buttonPress;
    private ProgressBar pb;
    private Bmp bmp;

    String mode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonTemp = findViewById(R.id.temperatura);
        buttonPress = findViewById(R.id.pressao);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.INVISIBLE);

        buttonTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exceptionMessage = "";
                boolean exception = false;

                try {
                    mode = "temp";
                    getTemperaturaPressaoFromWs();

                }catch (Exception e){
                    //Obtêm a mensagem de erro.
                    exceptionMessage = e.getMessage();
                    exception = true;
                }

                if(exception) {
                    //Mostra a mensagem de erro num toast.
                    Toast.makeText(MainActivity.this,exceptionMessage,Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exceptionMessage = "";
                boolean exception = false;

                try {
                    mode = "press";
                    getTemperaturaPressaoFromWs();
                }catch (Exception e){
                    //Obtêm a mensagem de erro.
                    exceptionMessage = e.getMessage();
                    exception = true;
                }

                if(exception) {
                    //Mostra a mensagem de erro num toast.
                    Toast.makeText(MainActivity.this,exceptionMessage,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getTemperaturaPressaoFromWs() {

        GenericGetAsyncTask task = new GenericGetAsyncTask(pb, this) {
            protected Response getResponseObject(String httpResponse) {
                Bmp bmp1 = JsonHandler.deSerializeBmp(httpResponse);
                return new Response(HttpStatusCode.OK, bmp1);
            }

            protected void onPostExecuteProcessDataUI(Object object) {
                if (object instanceof Bmp) {
                    bmp = (Bmp) object;
                    //Cria um intent que permite a passagem para a GeneralActivity.
                    Intent intent = new Intent(MainActivity.this, TemperaturaPressaoActivity.class);
                    intent.putExtra("mode",mode);
                    intent.putExtra("temp", bmp.getTemperatura());
                    intent.putExtra("press", bmp.getPressao());

                    //Começa a GeneralActivity.
                    startActivity(intent);
                }
            }
        };

        String address = Utils.getWSAddress(this);
        task.execute(address + "/pressure");

    }
}