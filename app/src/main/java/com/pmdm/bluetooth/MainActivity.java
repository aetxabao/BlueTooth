package com.pmdm.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

    public class MainActivity extends AppCompatActivity {

        TextView tvDevices;
        BluetoothAdapter BA;
        Set<BluetoothDevice> pairedDevices;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            tvDevices = (TextView)findViewById(R.id.LblDevices);
            BA = BluetoothAdapter.getDefaultAdapter();
            if (BA == null) {
                Toast.makeText(getApplicationContext(), "NO bluetooth", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        public void turnOn(View view){
            if (!BA.isEnabled()) {
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn, 0);
                Toast.makeText(getApplicationContext(),"Turned on",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Already on",Toast.LENGTH_LONG).show();
            }
        }

        public void getVisible(View view){
            Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivityForResult(getVisible, 0);
        }

        public void listDevices(View view){
            StringBuffer sb = new StringBuffer("");
            pairedDevices = BA.getBondedDevices();
            int n = pairedDevices.size();
            for(BluetoothDevice device : pairedDevices)
                sb.append(device.getName()+ "-" + device.getAddress()+"\n");
            tvDevices.setText("Lista("+n+"):\n"+sb.toString());
        }

        public void turnOff(View view){
            BA.disable();
            Toast.makeText(getApplicationContext(),"Turned off",Toast.LENGTH_LONG).show();
        }
    }