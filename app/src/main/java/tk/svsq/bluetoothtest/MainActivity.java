package tk.svsq.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView labelStateBluetooth;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        labelStateBluetooth = findViewById(R.id.tvBTstate);
        Button buttonBluetoothState = findViewById(R.id.btnCheck);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        buttonBluetoothState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bluetoothAdapter == null) {
                    labelStateBluetooth.setText("Bluetooth isn't supported on your device");
                } else {
                    if(bluetoothAdapter.isEnabled()) {
                        if(bluetoothAdapter.isDiscovering()) {
                            labelStateBluetooth.setText("Bluetooth is turning on");
                        } else {
                            labelStateBluetooth.setText("Bluetooth is ready");
                        }
                    } else {
                        labelStateBluetooth.setText("Bluetooth isn't accessible!");
                    }
                }
            }
        });
    }
}
