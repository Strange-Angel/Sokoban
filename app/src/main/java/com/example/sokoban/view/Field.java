package com.example.sokoban.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import com.example.sokoban.MainActivity;
import com.example.sokoban.R;
import com.example.sokoban.controller.EventListener;
import com.example.sokoban.model.GameObject;



import java.util.Set;

public class Field extends View {
    private EventListener eventListener;
    private Draw2D draw2D;
    private Paint mPaint;

    private Context context;
    private MainActivity mainActivity;
    private Button btnMenu;
    private Button btnRestart;


    public Field(Context context, Draw2D draw2D) {
        super(context);
        this.draw2D = draw2D;
        this.mainActivity = MainActivity.mainActivity;
        mPaint = new Paint();
        paint(new Canvas());
        this.context = context;
        btnMenu = mainActivity.findViewById(R.id.button);
        btnRestart = mainActivity.findViewById(R.id.restart);


    }

    public Field(Context context) {
        super(context);

    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(context, btnMenu);
        popupMenu.inflate(R.menu.popupmenu);


        popupMenu.setOnMenuItemClickListener(item -> {
            for (int i = 0; i < popupMenu.getMenu().size(); i++) {
                MenuItem menuitem = popupMenu.getMenu().getItem(i);
                if (menuitem.equals(item)) {
                    String[] str = item.toString().split(" ");
                    int lvl = Integer.parseInt(str[1]);
                    eventListener.loadGame(lvl);
                    return true;
                }
            }

            return false;
        });

        popupMenu.show();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        paint(canvas);
        //drawButton(canvas);
    }



    public void paint(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(mPaint);
        Set<GameObject> gameObjects = draw2D.getGameObjects().getAll();
        for (GameObject o : gameObjects) {
            o.draw(mPaint, canvas);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;

    }


    public Button getBtnMenu() {
        return btnMenu;
    }


    public Button getBtnRestart() {
        return btnRestart;
    }
}
