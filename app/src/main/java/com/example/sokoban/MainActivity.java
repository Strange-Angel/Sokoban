package com.example.sokoban;

import android.os.Bundle;
import android.widget.RelativeLayout;


import androidx.appcompat.app.AppCompatActivity;

import com.example.sokoban.controller.Controller;


public class MainActivity extends AppCompatActivity {
    private RelativeLayout relativeLayout;
    public static   MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.idRLView);
        Controller controller = new Controller(this);
        relativeLayout.addView(controller.getDraw2D().getField());
        //Test test = new Test(this);
        //controller.getDraw2D().setOnTouchListener(new OnSwipeTouchListener(MainActivity.this));
        //setContentView(controller.getDraw2D().getField());

    }

}
