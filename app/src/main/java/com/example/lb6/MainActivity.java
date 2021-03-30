package com.example.lb6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    int activePlayer = 1;
    int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 1;
        for(int i=0;i<gameState.length; i++)
        {
            gameState[i] = -1;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("1st Player Turn. Tap to play");
    }

    public void drop(View view){
        ImageView v = (ImageView) view;
        int tappedImage = Integer.parseInt(v.getTag().toString());
        if(gameActive) {
            if (gameState[tappedImage] == -1) {
                gameState[tappedImage] = activePlayer;
                v.setTranslationY(-1000f);
                if (activePlayer == 1) {
                    v.setImageResource(R.drawable.cross);
                    // Toast.makeText(this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
                    activePlayer = 2;
                    TextView status = findViewById(R.id.status);
                    status.setText("2nd Player Turn. Tap to play");
                } else {
                    v.setImageResource(R.drawable.zero);
                    // Toast.makeText(this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("1st Player Turn. Tap to play");
                }
                v.animate().translationYBy(1000f).setDuration(300);
            }
            for (int[] winPosition : winPositions) {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != -1) {
                    String winner;
                    gameActive = false;
                    if (gameState[winPosition[0]] == 1) {
                        winner = "1st Player has won!";
                    } else {
                        winner = "2nd Player has won!";
                    }
                    TextView status = findViewById(R.id.status);
                    status.setText(winner);
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Code to hide Title bar
        getSupportActionBar().hide();
    }
}