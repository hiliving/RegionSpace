package dev.practice.com.region;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RegionRemoteControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        control = (RegionRemoteControl) findViewById(R.id.control);
        control.setLisener(new RegionRemoteControl.MenuListener() {
            @Override
            public void onCenterClick() {
                Toast.makeText(MainActivity.this,"中间点击了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpClick() {
                Toast.makeText(MainActivity.this,"上点击了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(MainActivity.this,"右点击了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownClick() {
                Toast.makeText(MainActivity.this,"下点击了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLeftClick() {
                Toast.makeText(MainActivity.this,"左点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
