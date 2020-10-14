package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_click = findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new MyOnClickListener());
        btn_click.setOnLongClickListener(new MyLongClickListener());

    }



    class MyOnClickListener implements View.OnClickListener {
        public void onClick(View v) {
            if (v.getId() == R.id.btn_click) {
                Toast.makeText(MainActivity.this, "您点击了控件" + ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    class MyLongClickListener implements View.OnLongClickListener {
        public boolean onLongClick(View v) {
            if (v.getId() == R.id.btn_click) {
                Toast.makeText(MainActivity.this, "您长按了控件" + ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
            return true;
        }

    }
}