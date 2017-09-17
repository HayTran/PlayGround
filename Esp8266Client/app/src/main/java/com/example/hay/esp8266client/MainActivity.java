package com.example.hay.esp8266client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    TextView tvResult;
    EditText etSendNumber;
    Button btnSend;
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
                    for (int i = 0; i < 127; i++) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 127; i >= 0; i--) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 0; i < 127; i++) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 127; i >= 0; i--) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 0; i < 127; i++) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 127; i >= 0; i--) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 0; i < 127; i++) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 127; i >= 0; i--) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 0; i < 127; i++) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 127; i >= 0; i--) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 0; i < 127; i++) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 127; i >= 0; i--) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 0; i < 127; i++) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                    for (int i = 127; i >= 0; i--) {
                        int value = Integer.valueOf(etSendNumber.getText().toString()) + i;
                        new ConnectSocketManager().execute(value+"");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "You must fill number into box", Toast.LENGTH_SHORT).show();
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
        private static final String TAG = "ConnectSocketManager";
        String dstAddress = "192.168.4.1";
        int dstPort = 4567;
        @Override
        protected String doInBackground(String... args) {
            Log.d(TAG, "doInBackground: Start with number " + Byte.valueOf(args[0]) );
            String response = null;
            Socket socket = null;
            DataOutputStream dOut = null;
            DataInputStream dIn = null;
            int resultNumber = -1;
            try {
                socket = new Socket(dstAddress, dstPort);
                dOut = new DataOutputStream(socket.getOutputStream());
                dOut.writeByte(Byte.valueOf(args[0]));
                dIn = new DataInputStream(socket.getInputStream());
                resultNumber = dIn.readByte();
                response += "The number received from server: "+ resultNumber;
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
            Log.d(TAG, "onPostExecute: ");
            tvResult.setText(s);
        }
    }
}
