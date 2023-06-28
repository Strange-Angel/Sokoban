package com.example.sokoban.view;


import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.example.sokoban.controller.EventListener;
import com.example.sokoban.model.Direction;;


public class OnSwipeTouchListener implements View.OnTouchListener {
    private EventListener eventListener;
    private Context context;
    private final GestureDetector gestureDetector;
    private Field field;

    public OnSwipeTouchListener(Context context) {
        this.context = context;
        this.gestureDetector = new GestureDetector(context, new GestureListener());
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        field.getBtnMenu().setOnClickListener(v1 -> field.showPopupMenu(v1));
        field.getBtnRestart().setOnClickListener(v12 -> eventListener.loadGame());

        return gestureDetector.onTouchEvent(event);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void setField(Field field) {
        this.field = field;
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                    result = true;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

    }

    public void onSwipeRight() {
        eventListener.move(Direction.RIGHT);
    }

    public void onSwipeLeft() {
        eventListener.move(Direction.LEFT);
    }

    public void onSwipeTop() {
        eventListener.move(Direction.UP);
    }

    public void onSwipeBottom() {
        eventListener.move(Direction.DOWN);

    }
}
