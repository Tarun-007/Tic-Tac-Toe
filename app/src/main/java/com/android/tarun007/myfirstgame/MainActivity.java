package com.android.tarun007.myfirstgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.GridLayout;




public class MainActivity extends AppCompatActivity {
    int turn=0;
    //0=green; 1=red;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int winningPos[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int count=0;
    int winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void placeOne(View view){
        ImageView image=(ImageView)view;
        int tappedCounter =Integer.parseInt(image.getTag().toString());
       if( gameState[tappedCounter]==2) {
           gameState[tappedCounter]=turn;
           if (turn == 0) {
               image.setImageResource(R.drawable.green_circle);
               image.animate().alpha(1f).setDuration(300);
               turn = 1;
           } else {
               image.setImageResource(R.drawable.red_cross);
               image.animate().alpha(1f).setDuration(300);
               turn = 0;
           }

       }
       for(int i=0;i<8;i++)
       {
           for (int k=0;k<3;k++)
           {
              if( gameState[winningPos[i][k]]==gameState[tappedCounter]
                      && gameState[winningPos[i][k]]!=2)
              count++;

           }
           if(count==3){
               winner=gameState[tappedCounter];
               winnerFunc();
           }
           count=0;
       }
    }
    public void winnerFunc(){
        System.out.println(winner);
        String winer ="RED CROSS";
        TextView t=findViewById(R.id.textView);
        if(winner==0)
            winer="GREEN O";
        t.setText(winer+" has won!");

       LinearLayout l=findViewById(R.id.winLayout);
       l.setVisibility(View.VISIBLE);
        GridLayout grid =findViewById(R.id.boardLayout);
        for(int i=0;i<grid.getChildCount();i++)
            (grid.getChildAt(i)).setClickable(false);



    }
    public void playAgain(View view){
        LinearLayout l=findViewById(R.id.winLayout);
        l.setVisibility(View.INVISIBLE);
        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;
        GridLayout grid =findViewById(R.id.boardLayout);
        for(int i=0;i<grid.getChildCount();i++){
        ((ImageView)grid.getChildAt(i)).setImageResource(0);
            (grid.getChildAt(i)).setClickable(true);}





    }

}
