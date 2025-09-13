package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    public static final int GRID_SIZE = 3;
    private GridLayout grid;
    private boolean cellState [][];
    private static final String TAG = "LightsOut";


    View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button current = (Button) view;

            for (int i = 0; i < grid.getChildCount(); i++) {
                Button gridButton = (Button) grid.getChildAt(i);

                if (gridButton == current) {
                    int row = i / GRID_SIZE;
                    int col = i % GRID_SIZE;

                    cellState[row][col] = !cellState[row][col];

                }
            }
            recolor();
            Toast.makeText(MainActivity.this, "Lights on: " + countLightsOn(), Toast.LENGTH_SHORT).show();

        }
        };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cellState = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};

        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.light_grid);

        randomize();

        recolor();

        Toast.makeText(this, "Initial lights on: " + countLightsOn(), Toast.LENGTH_SHORT).show();




        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);
            gridButton.setOnClickListener(buttonClickListener);
        }
    }

    public void recolor(){
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            // Find the button's row and col
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            if (cellState[row][col]) {
                gridButton.setBackgroundColor(getColor(R.color.steelblue));//Light on
            } else {
                gridButton.setBackgroundColor(getColor(R.color.black));//Lights off
            }
        }
    }

    public void randomize(){
        Random random = new Random();
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                cellState[i][j] = random.nextBoolean();
            }
        }
    }
    private int countLightsOn()
    {
        int count =0;
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                if(cellState[i][j]){
                    count++;
                }
            }
        }
        return count;
    }

   }
