package com.example.memorygame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatDrawableManager;

import com.example.memorygame.R;

public class MemoryButton extends AppCompatButton {
    protected int row;
    protected int column;
    protected int frontDrawableId;

    protected boolean isVisible = false;
    protected boolean isMatched = false;

    protected Drawable frontCard;
    protected Drawable backCard;
    public MemoryButton(Context context, int r, int c, int frontImageDrawableId){
        super(context);
        row = r;
        column = c;
        frontDrawableId = frontImageDrawableId;

        frontCard = context.getDrawable(frontImageDrawableId);
        backCard =  context.getDrawable(R.drawable.back_card);

        setBackground(backCard);

        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams(GridLayout.spec(r),GridLayout.spec(c));

        tempParams.width = (int) getResources().getDisplayMetrics().density * 100;
        tempParams.height = (int) getResources().getDisplayMetrics().density * 100;

        setLayoutParams(tempParams);
    }

    public int getFrontDrawableId() {
        return frontDrawableId;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public void show() {
        if(isMatched){
            return;
        }
        if(isVisible){
            setBackground(backCard);
            isVisible = false;
        }else{
            setBackground(frontCard);
            isVisible = true;
        }
    }
}
