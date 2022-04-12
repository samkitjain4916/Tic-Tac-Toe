package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity
{
    //-1 = O
    // 1 = X
    // 0 = Empty cell

    TextView textView,scorex,scoreo;

    int score_x=0,score_o=0;
    int tag,game=1;
    int prev=-1;
    int[][] grid = new int[3][3];


    public boolean check(int num)
    {
        for(int i=0;i<3;i++)
        {
            int count=0;
            for(int j=0;j<3;j++)
            {
                if(grid[i][j]==num)
                {
                    count++;
                }
            }
            if(count==3)
            {
                return true;
            }
        }
        for(int i=0;i<3;i++)
        {
            int count=0;
            for(int j=0;j<3;j++)
            {
                if(grid[j][i]==num)
                {
                    count++;
                }
            }
            if(count==3)
            {
                return true;
            }
        }
        if((grid[0][0]==num && grid[1][1]==num && grid[2][2]==num)||(grid[0][2]==num && grid[1][1]==num && grid[2][0]==num))
        {
            return true;
        }

        return false;
    }

    public void tap(View view)
    {
        ImageView image = (ImageView)view;
        tag = Integer.parseInt(image.getTag().toString());

        if(image.getDrawable() == null && game==1)
        {
            if(prev == -1)//put X image
            {
                image.setImageResource(R.drawable.x);
                grid[(tag-1)/3][(tag-1)%3] = 1;

                if(check(1))
                {
                    score_x++;
                    textView = findViewById(R.id.textView);
                    scorex   = findViewById(R.id.scorex);
                    scorex.setText("Score X : "+score_x);
                    textView.setText("Player X Won");
                    game=0;
                }
                prev = 1;
            }
            else
            {
                image.setImageResource(R.drawable.o);
                grid[(tag-1)/3][(tag-1)%3] = -1;

                if(check(-1))
                {
                    score_o++;
                    textView = findViewById(R.id.textView);
                    scoreo   = findViewById(R.id.scoreo);
                    scoreo.setText("Score O : "+score_o);
                    textView.setText("Player O Won");
                    game=0;
                }
                prev = -1;
            }
        }


    }

    public void restart(View view)
    {
        game=1;

        grid = new int[3][3];

        textView=findViewById(R.id.textView);
        textView.setText("");
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView image = (ImageView) gridLayout.getChildAt(i);
            image.setImageDrawable(null);
        }
    }

    public void reset(View view)
    {
        score_x=0;
        score_o=0;

        scorex   = findViewById(R.id.scorex);
        scoreo   = findViewById(R.id.scoreo);

        scorex.setText("Score X : 0");
        scoreo.setText("Score O : 0");

        game=1;
        prev=-1;

        grid = new int[3][3];

        textView=findViewById(R.id.textView);
        textView.setText("");
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView image = (ImageView) gridLayout.getChildAt(i);
            image.setImageDrawable(null);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}