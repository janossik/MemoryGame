package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridLayout;

import java.util.Random;

public class Game4x4 extends AppCompatActivity implements View.OnClickListener {

    private int numberOfElements;

    private MemoryButton[] buttons;

    private int[] buttonGraphicLocations;
    private int[] buttonGraphics;

    private  MemoryButton selectedButton01;
    private  MemoryButton selectedButton02;

    private boolean isBusy = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4x4);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid_layout_4x4);
        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        numberOfElements = numColumns * numRows;

        buttons = new MemoryButton[numberOfElements];
        buttonGraphics = new int[numberOfElements/2];

        buttonGraphics[0] = R.drawable.animal_01;
        buttonGraphics[1] = R.drawable.animal_02;
        buttonGraphics[2] = R.drawable.animal_03;
        buttonGraphics[3] = R.drawable.animal_04;
        buttonGraphics[4] = R.drawable.animal_05;
        buttonGraphics[5] = R.drawable.animal_06;
        buttonGraphics[6] = R.drawable.animal_07;
        buttonGraphics[7] = R.drawable.animal_08;

        buttonGraphicLocations = new int[numberOfElements];

        shuffleButtonGraphics();
        for (int r = 0; r < numRows; r++){
            for (int c = 0; c < numColumns; c++){
                MemoryButton tempButton = new MemoryButton(this, r, c, buttonGraphics[buttonGraphicLocations[r * numColumns + c]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons[r * numColumns + c] = tempButton;
                gridLayout.addView(tempButton);
            }
        }
    }

    protected void shuffleButtonGraphics() {
        Random rand = new Random();
        for (int i = 0; i < numberOfElements;i++){
            buttonGraphicLocations[i] = i % numberOfElements / 2;
        }
        for (int i = 0; i < numberOfElements;i++){
            int temp = buttonGraphicLocations[i];
            int swapIndex = rand.nextInt(16);
            buttonGraphicLocations[i] = buttonGraphicLocations[swapIndex];
            buttonGraphicLocations[swapIndex] = temp;
        }
    }

    @Override
    public void onClick(View view) {
        if(isBusy){
            return;
        }
        MemoryButton button = (MemoryButton) view;
        if(button.isMatched){
            return;
        }
        if(selectedButton01 == null){

            selectedButton01 = button;
            selectedButton01.show();
            return;
        }
        if(selectedButton01.getId() == button.getId()){
            return;
        }
        if(selectedButton01.getFrontDrawableId() == button.getFrontDrawableId()){
            button.show();

            button.setMatched(true);
            selectedButton01.setMatched(true);

            selectedButton01.setEnabled(false);
            button.setEnabled(false);
            selectedButton01 = null;
        }else{
            selectedButton02 = button;
            selectedButton02.show();
            isBusy = true;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectedButton01.show();
                    selectedButton02.show();
                    selectedButton01 = null;
                    selectedButton02 = null;
                    isBusy= false;
                }
            },500);
        }
    }
}