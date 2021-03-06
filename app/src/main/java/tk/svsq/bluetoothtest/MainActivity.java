package tk.svsq.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView labelStateBluetooth;
    BluetoothAdapter bluetoothAdapter;
    String toastText;
    Button btnTurnOn, btnTurnoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        labelStateBluetooth = findViewById(R.id.tvBTstate);
        Button btnBTState = findViewById(R.id.btnCheck);
        btnTurnOn = findViewById(R.id.btnTurnOn);
        btnTurnoff = findViewById(R.id.btnTurnOff);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnBTState.setOnClickListener(this);
        btnTurnOn.setOnClickListener(this);
        btnTurnoff.setOnClickListener(this);
    }

    public void turnOnBluetooth() {

        if (bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, R.string.text_bt_already_on, Toast.LENGTH_SHORT).show();
        } else {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBtIntent);
        }
    }

    public void turnOffBluetooth() {

        if(!bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, R.string.text_bt_already_off, Toast.LENGTH_SHORT).show();
        } else {
            bluetoothAdapter.disable();
        }
    }

    public void checkBluetooth() {

        if (bluetoothAdapter == null) {
            labelStateBluetooth.setText(R.string.text_bt_not_supported);
        } else {
            if (bluetoothAdapter.isEnabled()) {
                String address = bluetoothAdapter.getAddress();
                String name = bluetoothAdapter.getName();
                toastText = name + " : " + address;
                if (bluetoothAdapter.isDiscovering()) {
                    labelStateBluetooth.setText(R.string.text_bt_turning_on);
                } else {
                    labelStateBluetooth.setText(R.string.text_bt_ready);
                }
                Toast.makeText(MainActivity.this, toastText, Toast.LENGTH_LONG).show();
            } else {
                labelStateBluetooth.setText(R.string.text_bt_isnt_accessible);
            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnCheck:

                checkBluetooth();
                break;

            case R.id.btnTurnOn:

                turnOnBluetooth();
                break;

            case R.id.btnTurnOff:

                turnOffBluetooth();
                break;
        }
    }
}
