package com.example.sokoban.view;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sokoban.R;
import com.example.sokoban.controller.Controller;
import com.example.sokoban.controller.EventListener;
import com.example.sokoban.model.GameObjects;

import org.w3c.dom.Text;


public class Draw2D extends View {
    private Controller controller;
    private Field field;


    public Draw2D(Controller controller, Context context) {
        super(context);
        this.controller = controller;
        field = new Field(context, this);


    }

    public void simulateTouch() {
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();
        float x = 0.0f;
        float y = 0.0f;
        int metaState = 0;
        MotionEvent motionEvent = MotionEvent.obtain(
                downTime,
                eventTime,
                MotionEvent.ACTION_UP,
                x,
                y,
                metaState
        );

        getField().dispatchTouchEvent(motionEvent);
    }


    public void init() {

    }

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);

    }

    public void update() {
        field.invalidate();
    }

    public GameObjects getGameObjects() {
        return controller.getGameObjects();
    }

    public void completed(int level) {
        update();
        Toast toast = getToast();
        View view = toast.getView();
        TextView textView = (TextView) view.findViewById(R.id.loadView);
        textView.setText("Уровень " + level + " пройден!");
        toast.show();
        controller.startNextLevel();
    }

    public void save() {
        Toast.makeText(getContext(), "Игра успешно сохранена!", Toast.LENGTH_SHORT).show();
    }

    public void load(int level) {
        Toast toast = getToast();
        View view = toast.getView();
        TextView textView = (TextView) view.findViewById(R.id.loadView);
        textView.setText("Уровень " + level + " загружен.");
        toast.show();

    }

    protected Toast getToast() {
        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_SHORT);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_toast, null);
        toast.setView(view);

        return toast;
    }

    public Field getField() {
        return field;
    }


}
