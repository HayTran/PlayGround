package com.example.hay.esp8266client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    TextView tvResult;
    EditText etSendNumber;
    Button btnSend;
    String dstAddress;
    int dstPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adđControls();
        init();
        addEvents();
    }

    private void addEvents() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etSendNumber.getText().toString().equals("") == false){
                    new ConnectSocketManager().execute(etSendNumber.getText().toString());
                }
            }
        });
    }

    private void init() {

    }

    private void adđControls() {
        tvResult = (TextView)findViewById(R.id.tvResult);
        etSendNumber = (EditText)findViewById(R.id.etSendNumber);
        btnSend = (Button)findViewById(R.id.btnSend);
    }

    class ConnectSocketManager extends AsyncTask <String, Void, String> {
        @Override
        protected String doInBackground(String... args) {
            String response = null;
            Socket socket = null;
            try {
                socket = new Socket(dstAddress, dstPort);
                DataOutputStream dOut = new DataOutputStream(socket.getOutputStream());
                dOut.writeByte(Byte.valueOf(args[0]));
                DataInputStream dIn = new DataInputStream(socket.getInputStream());
                response += "The number received from server: "+ dIn.read();
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "UnknownHostException: " + e.toString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response = "IOException: " + e.toString();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvResult.setText(s);
        }
    }
}
