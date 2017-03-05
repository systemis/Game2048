package com.example.nickpham.game2048;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.MainThread;
import android.support.annotation.RequiresApi;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nickpham.game2048.DataManager.BestScoreData;
import com.example.nickpham.game2048.DialogManager.DialogGameOver;
import com.example.nickpham.game2048.GAS.GuideGame;
import com.example.nickpham.game2048.Handling.About_Board_Play;

public class PlayBoardActivity extends AppCompatActivity
{

    public static View   Get_Example_View;
    public static Button Get_Example_Button;

    public static LinearLayout    Comprise_Play_Board, Get_Event_Player_View;
    public static RelativeLayout  main_board;

    public static TextView Show_Play_Score, Show_Best_Score;
    public static int Play_Score = 0;

    public static About_Board_Play about_board_play;

    public static BestScoreData bestScoreData;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_board);

        Anhxa();

        about_board_play = new About_Board_Play(this, Comprise_Play_Board);

        Custom_Touch_Player();

        Get_Best_Score();

        new DialogGameOver(this, Play_Score, (long) Integer.parseInt(Show_Best_Score.getText().toString()), null);
    }


    public static void Return_Pos_View_Get_Event()
    {

        main_board.removeView(Get_Event_Player_View);
        main_board.addView(Get_Event_Player_View);

    }

    public void Anhxa()
    {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Get_Example_View        = inflater.inflate(R.layout.layout_example, null, false);
        Get_Example_Button      = (Button) Get_Example_View.findViewById(R.id.Example_Slide);
        main_board              = (RelativeLayout) this.findViewById(R.id.main_board);
        Comprise_Play_Board     = (LinearLayout)   this.findViewById(R.id.Comprise_Play_Board);
        Get_Event_Player_View   = (LinearLayout)   this.findViewById(R.id.Get_Event_Player);

        Show_Play_Score         = (TextView)       this.findViewById(R.id.Show_Play_Score);
        Show_Best_Score         = (TextView)       this.findViewById(R.id.Show_Best_Score);


    }


    public static void Update_Score_Playing(long Score)
    {

        Play_Score += Score;
        Show_Play_Score.setText(String.valueOf(Play_Score));

        if (Play_Score > Integer.parseInt(bestScoreData.Get_Best_Score()))
        {

            bestScoreData.Update_Best_Score(Play_Score);
            Show_Best_Score.setText(bestScoreData.Get_Best_Score());

        }

    }

    public void Custom_Touch_Player()
    {

        int Width_Comprise_Play_Board  =  Get_Example_Button.getLayoutParams().width  * 4;
        int Height_Comprise_Play_Board =  Get_Example_Button.getLayoutParams().height * 4;
        RelativeLayout.LayoutParams params_Comprise_Play_Board = new RelativeLayout.LayoutParams(Width_Comprise_Play_Board, Height_Comprise_Play_Board);

        Get_Event_Player_View.setLayoutParams(params_Comprise_Play_Board);

        final float[] x1 = { 0 };
        final float[] x2 = { 0 };
        final float[] y1 = { 0 };
        final float[] y2 = { 0 };
        Get_Event_Player_View.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction())
                {

                    case MotionEvent.ACTION_DOWN:

                        x1[0] = event.getX();
                        y1[0] = event.getY();

                        break;

                    case MotionEvent.ACTION_UP:

                        x2[0] = event.getX();
                        y2[0] = event.getY();


                        float H1 = 0, H2 = 0;

                        if (x1[0] > x2[0]) H1 =  (x1[0] - x2[0]);
                        if (x1[0] < x2[0]) H1 =  (x2[0] - x1[0]);
                        if (y1[0] > y2[0]) H2 =  (y1[0] - y2[0]);
                        if (y1[0] < y2[0]) H2 =  (y2[0] - y1[0]);


                        if (H1 > H2)
                        {

                            if (x1[0] > x2[0]) about_board_play.Check_Move_Slides(3);
                            if (x1[0] < x2[0]) about_board_play.Check_Move_Slides(4);

                        }else
                        {

                            if (y1[0] > y2[0]) about_board_play.Check_Move_Slides(2);
                            if (y1[0] < y2[0]) about_board_play.Check_Move_Slides(1);

                        }

                        break;

                    default:
                        break;


                }

                return false;
            }});

    }

    public void Get_Best_Score()
    {

        bestScoreData = new BestScoreData(PlayBoardActivity.this);

        String BestScore = bestScoreData.Get_Best_Score();

        if (BestScore != null)
        {
            Show_Best_Score.setText(BestScore);
        }else {

            bestScoreData.Add_Best_Score("1", 0);
            Show_Best_Score.setText(bestScoreData.Get_Best_Score());

        }

    }


    /**public static void GameOver(Context context)
    {

        Intent GameOver_Intent = new Intent(context, GameOverActivity.class);

        Bundle B_ToGameOver = new Bundle();

        B_ToGameOver.putInt("ScorePlay", Play_Score);

        GameOver_Intent.putExtra("Data", B_ToGameOver);

        context.startActivity(GameOver_Intent);

        ((AppCompatActivity) context).finish();

    }*/

    public static void Action_GameOver(Context context, Button[][] SlidesMove)
    {
        new DialogGameOver(context, Play_Score, (long) Integer.parseInt(Show_Best_Score.getText().toString()), SlidesMove);

    }


    public void ExitGame(View v)
    {

        PlayBoardActivity.this.finish();

    }


    public void HowToPlayAction(View v)
    {


        AlertDialog.Builder GDialog = new AlertDialog.Builder(PlayBoardActivity.this, R.style.Theme_DialogGuideGame);

        GDialog.setTitle("How to play");
        GDialog.setMessage(new GuideGame().Get_GuideGame());

        GDialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface mDialog, int i) {
                mDialog.dismiss();
            }
        });

        Dialog mDialog = GDialog.create();

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mDialog.show();

    }

    /**@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void Up(View v)    {about_board_play.Check_Move_Slides(2);}
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void Down(View v)  {about_board_play.Check_Move_Slides(1);}
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void Left(View v)  {about_board_play.Check_Move_Slides(4);}
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void Right(View v) {about_board_play.Check_Move_Slides(3);}
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
