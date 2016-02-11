package com.itis.androidlab.viewsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Устанавливаем xml c визуальной частью для этого activity
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        // Метод findViewById() позволяет нам связать view из xml и переменную в activity по её id
        mDisplay = (TextView) findViewById(R.id.display);
        Button button1 = (Button) findViewById(R.id.button1);
        // Абсолютно любому элементу мы можем задать действия, которые он будет выполнять, когда пользователь нажмёт на него
        // это можно сделать применив к view onClickListener так, как мы сделали это ниже
        button1.setOnClickListener(new View.OnClickListener() {
            // В методе onClick() задаём что будет происходить при нажатии
            @Override
            public void onClick(View v) {
                mDisplay.setText("1");
            }
        });
    }

    // поведение при нажатии для элемента также мы можем задать через xml
    // для этого в xml внутри элемента указываем параметр onClick (activity_main.xml, строка 25),
    // а в самой activity указываем метод с таким же названием и уже в нём прописываем поведение.
    public void onButton2Click(View view) {
        mDisplay.setText("2");
    }
}
